package com.example.testbinding.core;

import android.app.Application;

import com.example.testbinding.provider.TextViewProvider;
import com.example.testbinding.provider.ViewProvider;
import com.example.testbinding.syntaxresolver.DefaultSyntaxResolver;
import com.example.testbinding.syntaxresolver.ISyntaxResolver;

/**
 * @author yrguo
 */
public class DefaultKernel extends KernelBase {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.example.testbinding.KernelBase#registerProviders(com.example.testbinding
	 * .AttributeBinder)
	 * 
	 * 注册provider
	 */
	@Override
	protected void registerProviders(AttributeBinder attrBinder) {
		attrBinder.registerProvider(new ViewProvider());
		attrBinder.registerProvider(new TextViewProvider());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.example.testbinding.KernelBase#createAttributeBinder(android.app.
	 * Application)
	 * 
	 * 属性解析器
	 */
	@Override
	protected AttributeBinder createAttributeBinder(Application application) {
		return AttributeBinder.getInstance();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.testbinding.KernelBase#createSyntaxResolver(android.app.
	 * Application)
	 * 
	 * 
	 * 指令解析器
	 */
	@Override
	protected ISyntaxResolver createSyntaxResolver(Application application) {
		return new DefaultSyntaxResolver();
	}
}
