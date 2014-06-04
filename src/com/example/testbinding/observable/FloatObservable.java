package com.example.testbinding.observable;

public class FloatObservable extends Observable<Float> {

	public FloatObservable() {
		super(Float.class);
	}

	public FloatObservable(Float initValue) {
		super(Float.class, initValue);
	}
}
