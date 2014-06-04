package com.example.testbinding.provider;

import com.example.testbinding.FieldTag;
import com.example.testbinding.attribute.OnClickViewEvent;
import com.example.testbinding.attribute.ViewAttribute;

public class ViewProvider extends BindingProvider {
	@Override
	public ViewAttribute<FieldTag, ?> createAttributeForView(FieldTag fieldTag, String attributeId) {
		if (attributeId.equals("onClick")) {
			return new OnClickViewEvent(fieldTag);
		}
		// else if (attributeId.equals("onLongClick")) {
		// return new OnLongClickViewEvent(view);
		// } else if (attributeId.equals("animation")) {
		// return new AnimationViewAttribute(view);
		// }
		return null;
	}
}
