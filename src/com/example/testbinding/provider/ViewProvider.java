package com.example.testbinding.provider;

import com.example.testbinding.attribute.ViewAttribute;
import com.example.testbinding.attribute.view.OnClickViewEvent;
import com.example.testbinding.attribute.view.OnLongClickViewEvent;
import com.example.testbinding.core.FieldTag;

public class ViewProvider extends BindingProvider {
	@Override
	public ViewAttribute<FieldTag, ?> createAttributeForView(FieldTag fieldTag, String attributeId) {
		if (attributeId.equals("onClick")) {
			return new OnClickViewEvent(fieldTag);
		} else if (attributeId.equals("onLongClick")) {
			return new OnLongClickViewEvent(fieldTag);
		}
		// else if (attributeId.equals("animation")) {
		// return new AnimationViewAttribute(view);
		// }
		return null;
	}
}
