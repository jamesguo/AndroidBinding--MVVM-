package com.example.testbinding.attribute.layout;

import com.example.testbinding.core.Binder.InflateResult;

public abstract class BindingLayout {
	private int mDefaultId = -1;

	public BindingLayout(int defaultId) {
		setDefaultLayoutId(defaultId);
	}

	public void setDefaultLayoutId(int id) {
		mDefaultId = id;
	}

	public int getDefaultLayoutId() {
		return mDefaultId;
	}

	public abstract int getLayoutTypeId(int pos);

	public abstract int getLayoutId(int pos);

	public abstract int getTemplateCount();

	public void onAfterInflate(InflateResult result, int pos) {
	}
}
