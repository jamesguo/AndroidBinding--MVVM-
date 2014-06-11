package com.example.testbinding;

import com.example.testbinding.core.Binder;
import com.example.testbinding.core.Binding;
import com.example.testbinding.core.DefaultKernel;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	@Binding(description = "{\"text\":\"name\"}")
	public TextView text;
	@Binding(description = "{\"text\":\"@string/cityName\",\"onClick\":\"Switch\"}")
	public Button cityName;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		text = (TextView) findViewById(R.id.text);
		cityName = (Button) findViewById(R.id.cityName);
		TestCachebean cachebean = new TestCachebean();
		Binder.init(getApplication(), new DefaultKernel());
		Binder.bindToModel(MainActivity.this, cachebean);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
