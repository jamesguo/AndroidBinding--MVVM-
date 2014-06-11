package com.example.testbinding.attribute.view;

import android.view.View;

import com.example.testbinding.attribute.ViewEventAttribute;
import com.example.testbinding.core.Binder;
import com.example.testbinding.core.FieldTag;
import com.example.testbinding.listener.OnClickListenerMulticast;

public class OnClickViewEvent extends ViewEventAttribute<FieldTag> implements View.OnClickListener {
	public OnClickViewEvent(FieldTag fieldTag) {
		super(fieldTag, "onClick");
	}

	public void onClick(View v) {
		invokeCommand(v);
	}

	@Override
	protected void registerToListener(FieldTag fieldTag) {
		Binder.getMulticastListenerForView(fieldTag, OnClickListenerMulticast.class).register(this);
	}
}
