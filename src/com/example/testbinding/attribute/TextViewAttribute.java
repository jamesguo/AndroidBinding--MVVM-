package com.example.testbinding.attribute;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import com.example.testbinding.BindingType;
import com.example.testbinding.FieldTag;

public class TextViewAttribute extends ViewAttribute<FieldTag, CharSequence> implements TextWatcher {

	private CharSequence mValue = null;

	public TextViewAttribute(FieldTag fieldTag, String attributeName) {
		super(CharSequence.class, fieldTag, attributeName);
		// if (fieldTag.view instanceof EditText) {
		// Binder.getMulticastListenerForView(fieldTag,
		// TextWatcherMulticast.class).registerWithHighPriority(this);
		// }
	}

	@Override
	public CharSequence get() {
		if (getView() == null)
			return null;
		return cloneCharSequence(((TextView) (((FieldTag) getView()).view)).getText());
	}

	private CharSequence cloneCharSequence(CharSequence o) {
		return o.subSequence(0, o.length());
	}

	private boolean compareCharSequence(CharSequence a, CharSequence b) {
		boolean result = false;
		if (a != null) {
			if (b != null)
				result = a.toString().equals(b.toString());
		} else {
			if (b == null)
				result = true;
		}
		return result;
	}

	@Override
	protected void doSetAttributeValue(Object newValue) {
		if (getView() == null)
			return;
		synchronized (this) {
			CharSequence nVal = "";
			if (null != newValue) {
				if (newValue instanceof CharSequence) {
					nVal = (CharSequence) newValue;
				} else {
					nVal = newValue.toString();
				}
			}
			if (!compareCharSequence(nVal, mValue)) {
				mValue = cloneCharSequence(nVal);
				((TextView) (((FieldTag) getView()).view)).setTextKeepState(cloneCharSequence(nVal));
			}
		}
	}

	public void afterTextChanged(Editable arg0) {
	}

	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
	}

	public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
		synchronized (this) {
			if (compareCharSequence(mValue, arg0))
				return;
			mValue = cloneCharSequence(arg0);

			this.notifyChanged();
		}
	}

	@Override
	protected BindingType AcceptThisTypeAs(Class<?> type) {
		if (CharSequence.class.isAssignableFrom(type))
			return BindingType.TwoWay;
		if (type.isAssignableFrom(CharSequence.class))
			return BindingType.TwoWay;
		return BindingType.OneWay;
	}
}
