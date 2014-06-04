package com.example.testbinding;

import java.lang.reflect.Field;
import java.util.ArrayList;

import android.app.Application;
import android.content.Context;
import android.view.View;

import com.example.testbinding.attribute.ViewAttribute;
import com.example.testbinding.listener.ViewMulticastListener;
import com.example.testbinding.syntaxresolver.ISyntaxResolver;
import com.example.testbinding.syntaxresolver.Utility;

public class Binder {
	public static final String BINDING_NAMESPACE = "http://www.gueei.com/android-binding/";
	public static final String ANDROID_NAMESPACE = "http://schemas.android.com/apk/res/android";

	private static IKernel _kernel;

	public static IKernel getInstance() {
		if (_kernel == null)
			throw new RuntimeException("Binder has not yet initializated. Do you forget to put Binder.init(application) in Application.Create? ");
		return _kernel;
	}

	public static ViewAttribute<?, ?> getAttributeForView(FieldTag fieldTag, String attributeId) throws AttributeNotDefinedException {
		return _kernel.getAttributeForView(fieldTag, attributeId);
	}

	// public static ViewTag getViewTag(View view) {
	// return _kernel.getViewTag(view);
	// }
	//
	// public static AttributeCollection getAttributeCollectionOfView(View view)
	// {
	// return _kernel.getAttributeCollectionOfView(view);
	// }

	public static void putBindingMapToField(Context context, Field field, BindingMap map) {
		_kernel.putBindingMapToField(context, field, map);
	}

	//
	// public static BindingMap getBindingMapForView(View view) {
	// return _kernel.getBindingMapForView(view);
	// }

	// public static InflateResult inflateView(Context context, int layoutId,
	// ViewGroup parent, boolean attachToRoot) {
	// return _kernel.inflateView(context, layoutId, parent, attachToRoot);
	// }

	public static View bindToModel(Context context, Object model) {
		Field[] fields = context.getClass().getDeclaredFields();
		int count = fields.length;
		for (int i = 0; i < count; i++) {
			Field field = fields[i];
			Binding binding = field.getAnnotation(Binding.class);
			if (binding != null) {
				Binder.putBindingMapToField(context, field, Utility.createBindingMap(binding.description()));
				_kernel.bindView(context, fields[i], model);
			}
		}
		return null;
	}

	public static View bindView(Context context, Field field, Object model) {
		return _kernel.bindView(context, field, model);
	}

	public static void init(Application application) {
		init(application, new DefaultKernel());
	}

	public static void init(Application application, IKernel kernel) {
		if (_kernel != null)
			throw new RuntimeException("Init should only called once. Please check your code. ");

		_kernel = kernel;
		_kernel.init(application);
	}

	public static <T extends ViewMulticastListener<?>> T getMulticastListenerForView(FieldTag fieldTag, Class<T> listenerType) {
		return _kernel.getMulticastListenerForView(fieldTag, listenerType);
	}

	public static ISyntaxResolver getSyntaxResolver() {
		return _kernel.getSyntaxResolver();
	}

	/**
	 * The class holding inflated result from Android Binding.
	 * 
	 * @author andy
	 * 
	 */
	public static class InflateResult {
		public ArrayList<View> processedViews = new ArrayList<View>();
		public View rootView;
	}

	public static BindingMap getBindingMapForField(Context context, Field field) {
		// TODO Auto-generated method stub
		return _kernel.getBindingMapForField(context, field);
	}

	public static FieldTag getFieldTag(Context context, Field field) {
		return _kernel.getFieldTag(context, field);
	}
}
