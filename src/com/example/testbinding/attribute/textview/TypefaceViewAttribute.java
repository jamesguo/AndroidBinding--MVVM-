package com.example.testbinding.attribute.textview;

import android.graphics.Typeface;
import android.widget.TextView;

import com.example.testbinding.attribute.ViewAttribute;
import com.example.testbinding.core.BindingType;
import com.example.testbinding.core.FieldTag;

public class TypefaceViewAttribute extends ViewAttribute<FieldTag, Typeface> {
	public TypefaceViewAttribute(FieldTag fieldTag) {
		super(Typeface.class, fieldTag, "typeface");
	}

	protected void doSetAttributeValue(Object newValue) {
		if (getView() == null)
			return;
		if (newValue == null) {
			return;
		}
		if ((newValue instanceof Typeface))
			(((TextView) (((FieldTag) getView()).view))).setTypeface((Typeface) newValue);
	}

	protected BindingType AcceptThisTypeAs(Class<?> type) {
		return BindingType.OneWay;
	}

	public Typeface get() {
		return null;
	}
}
