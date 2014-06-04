package com.example.testbinding;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map.Entry;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.example.testbinding.attribute.ViewAttribute;
import com.example.testbinding.observable.IObservable;
import com.example.testbinding.provider.BindingProvider;

public class AttributeBinder {
	private static AttributeBinder _attributeFactory;
	private ArrayList<BindingProvider> providers = new ArrayList<BindingProvider>(10);
	private RefViewAttributeProvider refViewAttributeProvider = new RefViewAttributeProvider();
	protected AttributeBinder() {
	}

	public static AttributeBinder getInstance() {
		if (_attributeFactory == null)
			_attributeFactory = new AttributeBinder();
		return _attributeFactory;
	}

	public void registerProvider(BindingProvider provider) {
		if (!providers.contains(provider)) {
			providers.add(provider);
		}
	}

	public void bindView(Context context, Field field, Object model) {
		BindingMap map = Binder.getBindingMapForField(context, field);
		FieldTag fieldTag = Binder.getFieldTag(context, field);
		String filterKey = "filter";
		String filterValue = map.get(filterKey);
		if (null != filterValue) {
			bindAttributeWithModel(context, fieldTag, filterKey, filterValue, model);
		}

		for (Entry<String, String> entry : map.getMapTable().entrySet()) {
			bindAttributeWithModel(context, fieldTag, entry.getKey(), entry.getValue(), model);
		}
	}

	public boolean bindAttributeWithModel(Context context, FieldTag fieldTag, String viewAttributeName, String statement, Object model) {
		IObservable<?> property;
		refViewAttributeProvider.viewContextRef = new WeakReference<FieldTag>(fieldTag);
		try {
			property = Binder.getSyntaxResolver().constructObservableFromStatement(context, statement, model, refViewAttributeProvider);
		} catch (SyntaxResolveException e1) {
			return false;
		}
		if (property != null) {
			try {
				ViewAttribute<?, ?> attr = Binder.getAttributeForView(fieldTag, viewAttributeName);
				BindingType result = attr.BindTo(context, property);
				if (result.equals(BindingType.NoBinding)) {

				}
				return true;
			} catch (AttributeNotDefinedException e) {
				return false;
			}
		}
		return false;
	}

	public static void bind(Activity mainActivity, TestCachebean cachebean) {
		// try {
		// Class<? extends Activity> actvity = mainActivity.getClass();
		// Field[] fields = actvity.getDeclaredFields();
		// int count = fields.length;
		// for(int i = 0;i<count;i++){
		// try {
		// Field field = fields[i];
		// Binding binding = field.getAnnotation(Binding.class);
		// if (binding != null) {
		// String cacheFieldName = binding.fieldName();
		// String[] subNameList = null;
		// if (cacheFieldName.contains("$")) {
		// subNameList = cacheFieldName.split("\\$");
		// } else {
		// subNameList = new String[] { cacheFieldName };
		// }
		// int length = subNameList.length;
		// Object object = cachebean;
		// for (int j = 0; j < length; j++) {
		// String subFieldName = subNameList[j];
		// Field field2 = object.getClass().getDeclaredField(subFieldName);
		// field2.setAccessible(true);
		// object = field2.get(object);
		// }
		// Class<?> fieldType = field.getType();
		// if (fieldType.isAssignableFrom(TextView.class) && object instanceof
		// CharSequence) {
		// field.setAccessible(true);
		// TextView textView = (TextView) field.get(mainActivity);
		// textView.setText((CharSequence) object);
		// }
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}

	private static class RefViewAttributeProvider implements IReferenceObservableProvider {
		public WeakReference<FieldTag> viewContextRef;

		public IObservable<?> getReferenceObservable(int referenceId, String field) {
			if (viewContextRef == null || viewContextRef.get() == null)
				return null;

			View reference = viewContextRef.get().view.getRootView().findViewById(referenceId);
			if (reference == null)
				return null;
			try {
				return Binder.getAttributeForView(viewContextRef.get(), field);
			} catch (AttributeNotDefinedException e) {
				return null;
			}
		}
	}

	public ViewAttribute<?, ?> createAttributeForView(FieldTag fieldTag, String attributeId) {
		for (BindingProvider p : providers) {
			ViewAttribute<?, ?> a = p.createAttributeForView(fieldTag, attributeId);
			if (a != null)
				return a;
		}
		return null;
	}
}
