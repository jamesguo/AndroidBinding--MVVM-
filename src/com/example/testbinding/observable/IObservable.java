package com.example.testbinding.observable;

import java.util.Collection;

import com.example.testbinding.observer.Observer;

public interface IObservable<T> {

	public abstract Class<T> getType();

	public abstract void subscribe(Observer o);

	public abstract void unsubscribe(Observer o);

	abstract Observer[] getAllObservers();

	public abstract void notifyChanged(Object initiator);

	public abstract void notifyChanged(Collection<Object> initiators);

	public abstract void notifyChanged();

	public abstract void set(T newValue, Collection<Object> initiators);

	public abstract void set(T newValue);

	public abstract void _setObject(Object newValue, Collection<Object> initiators);

	public abstract T get();

	public abstract boolean isNull();
}
