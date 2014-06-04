package com.example.testbinding.syntaxresolver;

import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.example.testbinding.BindingMap;

public class Utility {
	public static BindingMap createBindingMap(String attrs) {
		BindingMap map = new BindingMap();
		try {
			JSONObject jsonObject;
			jsonObject = new JSONObject(attrs);
			@SuppressWarnings("unchecked")
			Iterator<String> iterator = jsonObject.keys();
			while (iterator.hasNext()) {
				String attrName = iterator.next();
				String attrValue = jsonObject.optString(attrName, "");
				if (attrValue != null) {
					map.put(attrName, attrValue);
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

	public static int resolveResourceId(String attrValue, Context context, String type) {
		String name = attrValue; // remove the @ sign
		if (attrValue.startsWith("@"))
			name = attrValue.substring(1); // remove the @ sign
		return context.getResources().getIdentifier(name, type, context.getPackageName());
	}
}
