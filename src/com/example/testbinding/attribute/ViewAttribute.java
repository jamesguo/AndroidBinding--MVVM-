package com.example.testbinding.attribute;

import com.example.testbinding.FieldTag;

public abstract class ViewAttribute<Tv extends FieldTag, T> extends Attribute<Tv, T> {
	public ViewAttribute(Class<T> type, Tv view, String attributeName) {
		super(type, view, attributeName);
	}

	public Tv getView() {
		return super.getHost();
	}
}
