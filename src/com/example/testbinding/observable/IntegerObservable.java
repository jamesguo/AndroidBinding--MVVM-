package com.example.testbinding.observable;

public class IntegerObservable extends Observable<Integer> {
	public IntegerObservable() {
		super(Integer.class);
	}

	public IntegerObservable(int value) {
		super(Integer.class, value);
	}
}
