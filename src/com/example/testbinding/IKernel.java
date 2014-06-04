package com.example.testbinding;

import java.lang.reflect.Field;

import android.app.Application;
import android.content.Context;
import android.view.View;

import com.example.testbinding.attribute.ViewAttribute;
import com.example.testbinding.listener.ViewMulticastListener;
import com.example.testbinding.syntaxresolver.ISyntaxResolver;

public interface IKernel {
	public ViewAttribute<?, ?> getAttributeForView(FieldTag fieldTag, String attributeId) throws AttributeNotDefinedException;

	// public ViewTag getViewTag(View view);

	public AttributeCollection getAttributeCollectionOfView(FieldTag fieldTag);

	// public void putBindingMapToView(View view, BindingMap map);
	//
	// public BindingMap getBindingMapForView(View view);

	// public InflateResult inflateView(Context context, int layoutId, ViewGroup
	// parent, boolean attachToRoot);

	// public View bindView(Context context, InflateResult inflatedView, Object
	// model);

	public <T extends ViewMulticastListener<?>> T getMulticastListenerForView(FieldTag fieldTag, Class<T> listenerType);

	public void init(Application application);

	public AttributeBinder getAttributeBinder();

	public ISyntaxResolver getSyntaxResolver();

	public View bindView(Context context, Field filed, Object model);

	public void putBindingMapToField(Context context, Field field, BindingMap map);

	public BindingMap getBindingMapForField(Context context, Field field);

	FieldTag getFieldTag(Context context, Field field);
}
