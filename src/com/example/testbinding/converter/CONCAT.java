package com.example.testbinding.converter;

import android.text.SpannableStringBuilder;

import com.example.testbinding.observable.Converter;
import com.example.testbinding.observable.IObservable;

public class CONCAT extends Converter<CharSequence> {

	public CONCAT(IObservable<?>[] dependents) {
		super(CharSequence.class, dependents);
	}

	@Override
	public CharSequence calculateValue(Object... args) throws Exception {
		int len = args.length;
		SpannableStringBuilder result = new SpannableStringBuilder("");
		for (int i = 0; i < len; i++) {
			if (args[i] == null)
				continue;
			if (args[i] instanceof CharSequence)
				result.append((CharSequence) args[i]);
			else
				result.append(args[i].toString());
		}
		return result.toString();
	}
}
