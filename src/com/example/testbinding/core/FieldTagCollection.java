package com.example.testbinding.core;

import java.util.HashMap;

public class FieldTagCollection {
	private HashMap<String, FieldTag> mCollection = new HashMap<String, FieldTag>();
	protected static FieldTagCollection fieldTagCollection;
	protected FieldTagCollection() {

	}

	public synchronized static FieldTagCollection getInstance() {
		if (fieldTagCollection == null) {
			fieldTagCollection = new FieldTagCollection();
		}
		return fieldTagCollection;
	}
	public void put(String type, FieldTag value) {
		mCollection.put(type, value);
	}

	public FieldTag get(String type) {
		if (mCollection.containsKey(type)) {
			return mCollection.get(type);
		} else {
			return null;
		}
	}

	public void remove(String type) {
		mCollection.remove(type);
	}

	public boolean containsKey(String type) {
		return mCollection.containsKey(type);
	}
}
