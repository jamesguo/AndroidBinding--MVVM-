package com.example.testbinding.observable;

import java.util.ArrayList;
import java.util.Collection;

import com.example.testbinding.core.Binder;
import com.example.testbinding.core.SyntaxResolveException;
import com.example.testbinding.core.Undetermined;
import com.example.testbinding.core.WeakList;
import com.example.testbinding.observer.Observer;

public class InnerFieldObservable<T> implements IObservable<T>, Undetermined {
	private WeakList<Observer> observers = new WeakList<Observer>();

	private String mFieldPath;
	private InnerFieldObservable<T> mChild;
	private IObservable<T> mObservable;
	private Observer valueObserver = new Observer() {
		@Override
		public void onPropertyChanged(IObservable<?> prop, Collection<Object> initiators) {
			if (initiators.contains(InnerFieldObservable.this))
				return;
			if (mFieldPath.indexOf(".") > 0) {
				createChildNodes(prop.get());
			}
			notifyChanged(initiators);
		}
	};

	private Observer childObserver = new Observer() {
		@Override
		public void onPropertyChanged(IObservable<?> prop, Collection<Object> initiators) {
			if (initiators.contains(InnerFieldObservable.this))
				return;
			notifyChanged(initiators);
		}
	};

	public InnerFieldObservable(String fieldPath) {
		mFieldPath = fieldPath;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean createNodes(Object viewModel) {
		if (mObservable != null) {
			mObservable.unsubscribe(valueObserver);
		}
		int dot = mFieldPath.indexOf(".");
		String fieldName = mFieldPath;
		if (dot > 0) {
			fieldName = mFieldPath.substring(0, dot).trim();
		}
		Object field;
		try {
			field = Binder.getSyntaxResolver().getFieldForModel(fieldName, viewModel);
		} catch (SyntaxResolveException e) {
			return false;
		}

		if (field instanceof IObservable) {
			mObservable = (IObservable) field;
		} else if (field == null) {
			return false;
		} else {
			mObservable = new ConstantObservable(field.getClass(), field);
		}

		mObservable.subscribe(valueObserver);

		if (dot > 0)
			createChildNodes(mObservable.get());

		return true;
	}

	public void createChildNodes(Object value) {
		if (mChild != null)
			mChild.unsubscribe(childObserver);
		mChild = null;

		if (value == null)
			return;

		String subPath = mFieldPath.substring(mFieldPath.indexOf(".") + 1, mFieldPath.length());
		subPath = subPath.trim();
		if (subPath.length() == 0) {
			return;
		} else {
			mChild = new InnerFieldObservable<T>(subPath);
			mChild.createNodes(value);
			mChild.subscribe(childObserver);
		}
	}

	@Override
	public Class<T> getType() {
		if (mChild != null)
			return mChild.getType();
		return (Class<T>) mObservable.getType();
	}

	@Override
	public void subscribe(Observer o) {
		observers.add(o);
	}

	@Override
	public void unsubscribe(Observer o) {
		observers.remove(o);
	}

	@Override
	public Observer[] getAllObservers() {
		return observers.toArray(new Observer[0]);
	}

	public final void notifyChanged(Object initiator) {
		ArrayList<Object> initiators = new ArrayList<Object>();
		initiators.add(initiator);
		this.notifyChanged(initiators);
	}

	public final void notifyChanged(Collection<Object> initiators) {
		initiators.add(this);
		for (Object o : observers.toArray()) {
			if (initiators.contains(o))
				continue;
			((Observer) o).onPropertyChanged(this, initiators);
		}
	}

	public final void notifyChanged() {
		ArrayList<Object> initiators = new ArrayList<Object>();
		notifyChanged(initiators);
	}

	@Override
	public void set(T newValue, Collection<Object> initiators) {
		if (mChild != null) {
			mChild.set(newValue);
			notifyChanged(initiators);
		} else if (mObservable != null) {
			mObservable.set(newValue);
			notifyChanged(initiators);
		}
	}

	@Override
	public void set(T newValue) {
		set(newValue, new ArrayList<Object>());
	}

	@Override
	public void _setObject(Object newValue, Collection<Object> initiators) {
		if (mChild != null) {
			mChild._setObject(newValue, initiators);
		} else if (mObservable != null) {
			mObservable._setObject(newValue, initiators);
		}
	}

	@Override
	public T get() {
		if (mChild != null) {
			return mChild.get();
		} else if (mObservable != null) {
			return mObservable.get();
		}
		return null;
	}

	@Override
	public boolean isNull() {
		return get() == null;
	}
}
