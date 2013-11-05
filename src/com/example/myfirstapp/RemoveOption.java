package com.example.myfirstapp;

import java.util.ArrayList;
import java.util.TreeMap;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RemoveOption extends Activity {

	ArrayList<String> tasks;
	TreeMap<CheckBox, String> boxes;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_remove_option);
		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		
		Intent intent = getIntent();
		
		String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
		tasks = MainActivity.getList();
		for(int i = 0; i<tasks.size(); i++)
		{
			CheckBox cb = new CheckBox(getApplicationContext());
			cb.setText(tasks.get(i));
			cb.setTextColor(Color.BLACK);
			cb.setTextSize(40);
			//boxes.put(cb, tasks.get(i));
			ll.addView(cb);
			
		}
		TextView textView = new TextView(this);
		textView.setTextSize(40);
		setContentView(ll);
		textView.setText(message);
		
		
		//setContentView(textView);
		// Show the Up button in the action bar.
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.remove_option, menu);
		return true;
	}

}
