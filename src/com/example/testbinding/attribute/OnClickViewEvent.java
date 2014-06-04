package com.example.testbinding.attribute;

import android.view.View;

import com.example.testbinding.Binder;
import com.example.testbinding.FieldTag;
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
