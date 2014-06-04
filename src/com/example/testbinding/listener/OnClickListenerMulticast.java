package com.example.testbinding.listener;

import android.view.View;

public class OnClickListenerMulticast extends ViewMulticastListener<View.OnClickListener> implements View.OnClickListener {

	@Override
	public void registerToView(View v) {
		v.setOnClickListener(this);
	}

	public void onClick(View v) {
		for (View.OnClickListener l : listeners) {
			l.onClick(v);
		}

	}
}