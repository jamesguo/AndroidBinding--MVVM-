package com.example.testbinding.core;

import com.example.testbinding.observable.IObservable;


public interface IReferenceObservableProvider {
	public IObservable<?> getReferenceObservable(int referenceId, String field);
}
