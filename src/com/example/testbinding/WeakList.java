package com.example.testbinding;

import java.lang.ref.WeakReference;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class WeakList<E> extends AbstractList<E> {

	private volatile ArrayList<WeakReference<E>> items;

	public WeakList() {
		items = new ArrayList<WeakReference<E>>();
	}

	public WeakList(Collection<E> c) {
		items = new ArrayList<WeakReference<E>>();
		addAll(0, c);
	}

	public WeakList(int initCapcacity) {
		items = new ArrayList<WeakReference<E>>(initCapcacity);
	}

	public void add(int index, E element) {
		synchronized (this) {
			items.add(index, new WeakReference<E>(element));
		}
	}

	public Iterator<E> iterator() {
		throw new UnsupportedOperationException();
	}

	public int size() {
		synchronized (this) {
			removeReleased();
			return items.size();
		}
	}

	public E get(int index) {
		synchronized (this) {
			return ((WeakReference<E>) items.get(index)).get();
		}
	}

	private void removeReleased() {
		synchronized (this) {
			ArrayList<WeakReference<E>> removeList = new ArrayList<WeakReference<E>>();
			for (Iterator<WeakReference<E>> it = items.iterator(); it.hasNext();) {
				WeakReference<E> ref = (WeakReference<E>) it.next();
				if (ref.get() == null)
					removeList.add(ref);
			}
			for (int i = 0; i < removeList.size(); i++) {
				items.remove(removeList.get(i));
			}
		}
	}

	public Object[] toArray() {
		synchronized (this) {
			removeReleased();
			ArrayList<E> copy = new ArrayList<E>();
			for (WeakReference<E> itemRef : items) {
				E item = itemRef.get();
				if (item != null)
					copy.add(item);
			}
			return copy.toArray();
		}
	}

	@Override
	public boolean remove(Object object) {
		synchronized (this) {
			int len = items.size();
			for (int i = 0; i < len; i++) {
				if (items.get(i).get() == null) {
					items.remove(i);
					return remove(object);
				}
				if (items.get(i).get().equals(object)) {
					items.remove(i);
					return true;
				}
			}
			return false;
		}
	}

	@Override
	public boolean add(E object) {
		synchronized (this) {
			return items.add(new WeakReference<E>(object));
		}
	}

	@SuppressWarnings("unchecked")
	public E[] toItemArray(E[] arr) {
		ArrayList<E> copy = new ArrayList<E>();
		for (WeakReference<E> itemRef : items) {
			E item = itemRef.get();
			if (item != null)
				copy.add(item);
		}
		return (E[]) copy.toArray();
	}
}