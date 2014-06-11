package com.example.testbinding.observable;

import java.util.ArrayList;
import java.util.Collection;

import com.example.testbinding.core.WeakList;
import com.example.testbinding.observer.Observer;

/**
 * @author yrguo
 * 
 * @param <T>
 * 
 * 
 *            被观察者
 * 
 */
public class Observable<T> implements IObservable<T> {
	@Override
	public String toString() {
		if (!isNull())
			return mValue.toString();
		return "null";
	}

	/**
	 * 观察者列表
	 */
	private WeakList<Observer> observers = new WeakList<Observer>();
	private T mValue;
	private final Class<T> mType;

	public Observable(Class<T> type) {
		mType = type;
	}

	public Observable(Class<T> type, T initValue) {
		this(type);
		mValue = initValue;
	}

	public void subscribe(Observer o) {
		observers.add(o);
	}

	public void unsubscribe(Observer o) {
		observers.remove(o);
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

	public final void set(T newValue, Collection<Object> initiators) {
		if (initiators.contains(this))
			return;
		doSetValue(newValue, initiators);
		initiators.add(this);
		notifyChanged(initiators);
	}

	public void _setObject(Object newValue, Collection<Object> initiators) {
		try {
			T value = this.getType().cast(newValue);
			if (value == null)
				return;
			this.set(value, initiators);
		} catch (ClassCastException e) {

			return;
		}
	}

	public final void set(T newValue) {
		doSetValue(newValue, new ArrayList<Object>());
		notifyChanged(this);
	}

	protected void doSetValue(T newValue, Collection<Object> initiators) {
		mValue = newValue;
	}

	protected final void setWithoutNotify(T newValue) {
		mValue = newValue;
	}

	public T get() {
		return mValue;
	}

	public final Class<T> getType() {
		return mType;
	}

	public Observer[] getAllObservers() {
		return observers.toItemArray(new Observer[0]);
	}

	@Override
	public boolean isNull() {
		return mValue == null;
	}
}
