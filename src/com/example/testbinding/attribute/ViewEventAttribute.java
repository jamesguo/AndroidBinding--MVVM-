package com.example.testbinding.attribute;

import android.view.View;

import com.example.testbinding.FieldTag;
import com.example.testbinding.observable.Command;

public abstract class ViewEventAttribute<T extends FieldTag> extends ViewAttribute<T, Command> {
	public ViewEventAttribute(T fieldTag, String viewAttributeName) {
		super(Command.class, fieldTag, viewAttributeName);
		registerToListener(fieldTag);
	}

	protected abstract void registerToListener(T fieldTag);

	private Command mCommand;

	@Override
	protected void doSetAttributeValue(Object newValue) {
		if (newValue instanceof Command) {
			mCommand = (Command) newValue;
		}
	}

	@Override
	public Command get() {
		return mCommand;
	}

	public void invokeCommand(View view, Object... args) {
		if (mCommand != null)
			mCommand.InvokeCommand(view, args);
	}
}