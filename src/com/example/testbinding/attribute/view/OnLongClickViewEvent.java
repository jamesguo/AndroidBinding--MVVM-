package com.example.testbinding.attribute.view;

import android.view.View;

import com.example.testbinding.attribute.ViewEventAttribute;
import com.example.testbinding.core.Binder;
import com.example.testbinding.core.FieldTag;
import com.example.testbinding.listener.OnLongClickListenerMulticast;

public class OnLongClickViewEvent extends ViewEventAttribute<FieldTag> implements View.OnLongClickListener {
	public OnLongClickViewEvent(FieldTag fieldTag) {
		super(fieldTag, "onLongClick");
	}

	@Override
	protected void registerToListener(FieldTag fieldTag) {
		Binder.getMulticastListenerForView(fieldTag, OnLongClickListenerMulticast.class).register(this);
	}

	@Override
	public boolean onLongClick(View v) {
		invokeCommand(v, new Object[0]);
		return true;
	}
}
