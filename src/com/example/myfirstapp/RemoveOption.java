package com.example.myfirstapp;

import java.util.ArrayList;
import java.util.TreeMap;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RemoveOption extends Activity {

	static ArrayList<String> tasks;
	// ArrayList<CheckBox> boxes;
	CheckBox[] boxes = new CheckBox[100];
	int size = 0;
	public Activity thisActivity = this;
	private LinearLayout ll;
	private CheckBox aGlobalBox;
	private int index;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_remove_option);
		ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);

		Intent intent = getIntent();

		String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
		tasks = MainActivity.getList();
		aGlobalBox = new CheckBox(getApplicationContext());
		for (index = 0; index < tasks.size(); index++) {
			CheckBox cb = new CheckBox(getApplicationContext());
			cb.setText(tasks.get(index));
			cb.setTextColor(Color.BLACK);
			cb.setTextSize(40);
//			cb.setOnClickListener(new OnClickListener() {
//				public void onClick(View v) {
//					if (((CheckBox) v).isChecked()) {
//						v.getId();
//						ll.removeView(findViewById(v.getId()));
//						//RemoveOption.removeTasks(index);
//						//tasks.remove(index);
//						//MainActivity.updateTasks(tasks);
//					}
//				}
//			});

			boxes[index] = cb;
			size++;
			ll.addView(cb);
		}
		Button removeButton = new Button(this);
		removeButton.setText("Remove tasks");
		removeButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				removeTasks(view);
			}
		});
		// if(removeButton!=null)
		ll.addView(removeButton);
		// else
		// {
		// TextView textView = new TextView(this);
		// textView.setTextSize(40);
		// textView.setText("button null");
		// ll.addView(textView);
		// }
		setContentView(ll);

		// setContentView(textView);
		// Show the Up button in the action bar.
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.remove_option, menu);
		return true;
	}

	public void removeTasks(View view) {
		//tasks.remove(i);
		//MainActivity.updateTasks(tasks);
		
		 Intent intent = new Intent(this, MainActivity.class);
		for (int i = 0; i < size; i++) {
			CheckBox thisBox = boxes[i];
			System.out.println(thisBox);
			if (thisBox != null && ((CheckBox) thisBox).isChecked()) {
				// ll.removeView(boxes[i]);
				// boxes[i] = null;
				// tasks.remove(i);
				// size--;
			}
		}
		 MainActivity.updateTasks(tasks);
		startActivity(intent);
	}
}
