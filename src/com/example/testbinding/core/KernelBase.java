package com.example.testbinding.core;

import java.lang.reflect.Field;

import android.app.Application;
import android.content.Context;
import android.view.View;

import com.example.testbinding.attribute.ViewAttribute;
import com.example.testbinding.listener.MulticastListenerCollection;
import com.example.testbinding.listener.ViewMulticastListener;
import com.example.testbinding.syntaxresolver.ISyntaxResolver;

public abstract class KernelBase implements IKernel {
	public KernelBase() {
		super();
	}

	@Override
	public ViewAttribute<?, ?> getAttributeForView(FieldTag fieldTag, String attributeId) throws AttributeNotDefinedException {
		ViewAttribute<?, ?> viewAttribute = null;

		AttributeCollection collection = getAttributeCollectionOfView(fieldTag);
		if (collection.containsAttribute(attributeId))
			return collection.getAttribute(attributeId);

		// if (view instanceof IBindableView) {
		// viewAttribute = ((IBindableView<?>)
		// view).createViewAttribute(attributeId);
		// }

		if (viewAttribute == null)
			viewAttribute = AttributeBinder.getInstance().createAttributeForView(fieldTag, attributeId);

		if (viewAttribute == null)
			throw new AttributeNotDefinedException(String.format("The view of type '%s' does not have attribute '%s' defined. ", fieldTag.view.getClass().getSimpleName(), attributeId));

		collection.putAttribute(attributeId, viewAttribute);
		return viewAttribute;
	}


	@Override
	public View bindView(Context context, Field filed, Object model) {
		AttributeBinder.getInstance().bindView(context, filed, model);
		return null;
	}

	@Override
	public AttributeCollection getAttributeCollectionOfView(FieldTag fieldTag) {
		AttributeCollection collection = fieldTag.get(AttributeCollection.class);
		if (collection != null)
			return collection;
		collection = new AttributeCollection();
		fieldTag.put(AttributeCollection.class, collection);
		return collection;
	}

	@Override
	public void putBindingMapToField(Context context, Field field, BindingMap map) {
		getFieldTag(context, field).put(BindingMap.class, map);
	}

	@Override
	public BindingMap getBindingMapForField(Context context, Field field) {
		// TODO Auto-generated method stub
		if (!getFieldTag(context, field).containsKey(BindingMap.class)) {
			putBindingMapToField(context, field, new BindingMap());
		}
		return getFieldTag(context, field).get(BindingMap.class);
	}

	@Override
	public FieldTag getFieldTag(Context context, Field field) {
		Binding binding = field.getAnnotation(Binding.class);
		String fieldDecription = binding.description();
		FieldTag fieldTag = FieldTagCollection.getInstance().get(fieldDecription);
		if (fieldTag == null) {
			try {
				View view = (View) field.get(context);
				fieldTag = new FieldTag(field, view);
				FieldTagCollection.getInstance().put(fieldDecription, fieldTag);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return fieldTag;
	}


	// @Override
	// public InflateResult inflateView(Context context, int layoutId, ViewGroup
	// parent, boolean attachToRoot) {
	// LayoutInflater inflater =
	// LayoutInflater.from(context).cloneInContext(context);
	// ViewFactory factory = new ViewFactory(inflater);
	// inflater.setFactory(factory);
	// InflateResult result = new InflateResult();
	// result.rootView = inflater.inflate(layoutId, parent, attachToRoot);
	// result.processedViews = factory.getProcessedViews();
	// return result;
	// }

	@Override
	public <T extends ViewMulticastListener<?>> T getMulticastListenerForView(FieldTag fieldTag, Class<T> listenerType) {
		MulticastListenerCollection collection = fieldTag.get(MulticastListenerCollection.class);
		if (collection == null) {
			collection = new MulticastListenerCollection();
			fieldTag.put(MulticastListenerCollection.class, collection);
		}

		if (collection.containsKey(listenerType)) {
			return collection.get(listenerType);
		}
		try {
			T listener = listenerType.getConstructor().newInstance();
			listener.registerToView(fieldTag.view);
			collection.put(listenerType, listener);
			return listener;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void init(Application application) {
		syntaxResolver = createSyntaxResolver(application);
		attributeBinder = createAttributeBinder(application);
		registerProviders(attributeBinder);
	}

	protected abstract AttributeBinder createAttributeBinder(Application application);

	protected abstract void registerProviders(AttributeBinder attrBinder);

	protected abstract ISyntaxResolver createSyntaxResolver(Application application);

	@Override
	public AttributeBinder getAttributeBinder() {
		return attributeBinder;
	}

	protected AttributeBinder attributeBinder;
	protected ISyntaxResolver syntaxResolver;

	@Override
	public ISyntaxResolver getSyntaxResolver() {
		return syntaxResolver;
	}
}
