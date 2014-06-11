package com.example.testbinding.core;

import java.lang.reflect.Field;

import android.view.View;

import com.example.testbinding.listener.TypeAsKeyHashMap;

public class FieldTag extends TypeAsKeyHashMap<Object> {
	public Field field;
	public View view;

	public FieldTag(Field field, View view) {
		this.field = field;
		this.view = view;
	}

}
