package com.example.testbinding.provider;

import com.example.testbinding.FieldTag;
import com.example.testbinding.attribute.ViewAttribute;

public abstract class BindingProvider {
	public abstract <Tv extends FieldTag> ViewAttribute<Tv, ?> createAttributeForView(FieldTag fieldTag, String attributeId);

	@Override
	public boolean equals(Object o) {
		return this.getClass().getName().equals(o.getClass().getName());
	}
}
