package com.example.testbinding.observable;

import java.util.Collection;

public abstract class TwoWayDependentObservable<T> extends DependentObservable<T> {

	public TwoWayDependentObservable(Class<T> type, IObservable<?>... dependents) {
		super(type, dependents);
	}

	public abstract boolean ConvertBack(Object value, Object[] outResult);

	@Override
	protected void doSetValue(T newValue, Collection<Object> initiators) {
		int count = mDependents.length;
		Object[] outResult = new Object[count];
		if (!ConvertBack(newValue, outResult))
			return;
		for (int i = 0; i < count; i++) {
			mDependents[i]._setObject(outResult[i], initiators);
		}
	}
}
