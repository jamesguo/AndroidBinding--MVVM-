package com.example.testbinding;

import com.example.testbinding.observable.Command;
import com.example.testbinding.observable.IObservable;

public interface IPropertyContainer {
	public IObservable<?> getObservableByName(String name) throws Exception;

	public Command getCommandByName(String name) throws Exception;

	public Object getValueByName(String name) throws Exception;
}
