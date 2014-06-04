package com.example.testbinding.observable;

import android.content.Context;

public abstract class Converter<T> extends TwoWayDependentObservable<T> {
	public Converter(Class<T> type, IObservable<?>[] dependents) {
		super(type, dependents);
	}

	@Override
	public boolean ConvertBack(Object value, Object[] outResult) {
		return false;
	}

	private Context mContext;

	public void setContext(Context context) {
		mContext = context;
	}

	public Context getContext() {
		return mContext;
	}
}
