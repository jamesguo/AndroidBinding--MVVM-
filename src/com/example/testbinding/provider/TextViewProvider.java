package com.example.testbinding.provider;

import android.widget.TextView;

import com.example.testbinding.FieldTag;
import com.example.testbinding.attribute.TextViewAttribute;
import com.example.testbinding.attribute.ViewAttribute;

public class TextViewProvider extends BindingProvider {

	@SuppressWarnings("unchecked")
	@Override
	public <Tv extends FieldTag> ViewAttribute<Tv, ?> createAttributeForView(FieldTag fieldTag, String attributeId) {
		if (!(fieldTag.view instanceof TextView))
			return null;
		if (attributeId.equals("text")) {
			TextViewAttribute attr = new TextViewAttribute(fieldTag, "text");
			return (ViewAttribute<Tv, ?>) attr;
		}

		return null;
	}
}
