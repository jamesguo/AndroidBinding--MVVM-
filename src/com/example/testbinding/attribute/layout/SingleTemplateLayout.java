package com.example.testbinding.attribute.layout;

public class SingleTemplateLayout extends BindingLayout {
	public SingleTemplateLayout(int defaultId) {
		super(defaultId);
	}

	@Override
	public int getTemplateCount() {
		return 1;
	}

	@Override
	public int getLayoutTypeId(int pos) {
		return 0;
	}

	@Override
	public int getLayoutId(int pos) {
		return getDefaultLayoutId();
	}
}
