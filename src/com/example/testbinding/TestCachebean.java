package com.example.testbinding;

import android.view.View;

import com.example.testbinding.observable.Command;
import com.example.testbinding.observable.StringObservable;


public class TestCachebean {
	public StringObservable name = new StringObservable("123");
	public Command Switch = new Command() {

		@Override
		public void Invoke(View view, Object... args) {
			name.set("2546");
		}
	};

	public TestCachebean() {

	}
	public void notifyChanges() {

	}

	public void resetChanges() {

	}
}
