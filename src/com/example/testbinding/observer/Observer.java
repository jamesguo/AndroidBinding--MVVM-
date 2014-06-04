package com.example.testbinding.observer;

import java.util.Collection;

import com.example.testbinding.observable.IObservable;

public interface Observer {
	public void onPropertyChanged(IObservable<?> prop, Collection<Object> initiators);
}
