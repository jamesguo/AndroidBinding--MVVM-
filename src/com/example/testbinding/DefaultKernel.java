package com.example.testbinding;

import android.app.Application;

import com.example.testbinding.provider.TextViewProvider;
import com.example.testbinding.provider.ViewProvider;
import com.example.testbinding.syntaxresolver.DefaultSyntaxResolver;
import com.example.testbinding.syntaxresolver.ISyntaxResolver;

public class DefaultKernel extends KernelBase {

	@Override
	protected void registerProviders(AttributeBinder attrBinder) {
		attrBinder.registerProvider(new ViewProvider());
		attrBinder.registerProvider(new TextViewProvider());
	}

	@Override
	protected AttributeBinder createAttributeBinder(Application application) {
		return AttributeBinder.getInstance();
	}

	@Override
	protected ISyntaxResolver createSyntaxResolver(Application application) {
		return new DefaultSyntaxResolver();
	}
}
