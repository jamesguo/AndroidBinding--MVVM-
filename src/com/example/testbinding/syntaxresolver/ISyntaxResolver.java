package com.example.testbinding.syntaxresolver;

import android.content.Context;

import com.example.testbinding.IReferenceObservableProvider;
import com.example.testbinding.SyntaxResolveException;
import com.example.testbinding.observable.IObservable;

public interface ISyntaxResolver {

	public IObservable<?> constructObservableFromStatement(final Context context, final String bindingStatement, final Object model, final IReferenceObservableProvider refProvider)
			throws SyntaxResolveException;

	public IObservable<?> constructObservableFromStatement(final Context context, final String bindingStatement, final Object model) throws SyntaxResolveException;

	public abstract Object getFieldForModel(String fieldName, Object model) throws SyntaxResolveException;


	public <T> T tryEvaluateValue(Context context, String statement, Object model, T defaultValue);
}
