package com.example.myfirstapp;

import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class RemoveOption extends Activity {

	static ArrayList<Task> tasks;
	// ArrayList<CheckBox> boxes;
	CheckBox[] boxes = new CheckBox[100];
	int size = 0;
	public Activity thisActivity = this;
	private LinearLayout ll;
	private CheckBox aGlobalBox;
	// private CheckBox box;
	private int index;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_remove_option);
		ScrollView scroll = new ScrollView(this);
		scroll.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
		ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);

		Intent intent = getIntent();

		String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
		tasks = MainActivity.getList();
		Collections.sort(tasks);
		aGlobalBox = new CheckBox(getApplicationContext());
		for (index = tasks.size()-1; index >=0; index--) {
			CheckBox box;
			box = new CheckBox(getApplicationContext());
			box.setText(tasks.get(index).toString());
			box.setTextColor(Color.BLACK);
			int color;
			double priority = tasks.get(index).getPriority();
			if (priority <= 20)
				color = Color.GREEN;
			else if (priority <= 40)
				color = Color.rgb(100,100,255);
			else if (priority <= 60)
				color = Color.YELLOW;
			else if (priority <= 80)
				color = Color.rgb(255, 165, 0);
			else
				color = Color.RED;
			box.setBackgroundColor(color);
			box.setTextSize(40);
			// cb.setOnClickListener(new OnClickListener() {
			// public void onClick(View v) {
			// if (((CheckBox) v).isChecked()) {
			// v.getId();
			// ll.removeView(findViewById(v.getId()));
			// //RemoveOption.removeTasks(index);
			// //tasks.remove(index);
			// //MainActivity.updateTasks(tasks);
			// }
			// }
			// });

			boxes[index] = box;
			size++;
			ll.addView(box);
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
		scroll.addView(ll);
		// else
		// {
		// TextView textView = new TextView(this);
		// textView.setTextSize(40);
		// textView.setText("button null");
		// ll.addView(textView);
		// }
		setContentView(scroll);

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
		// tasks.remove(i);
		// MainActivity.updateTasks(tasks);

		Intent intent = new Intent(this, MainActivity.class);
		ArrayList<Integer> toRemove = new ArrayList<Integer>();
		Object[] list = tasks.toArray();
		for (int i = 0; i < list.length; i++) {
			CheckBox thisBox = boxes[i];
			// System.out.println(thisBox);
			if (thisBox != null && ((CheckBox) thisBox).isChecked()) {
				Task t = (Task) list[i];

				tasks.remove(t);
				ll.removeView(boxes[i]);
				boxes[i] = null;

			}
		}

		//

		MainActivity.updateTasks(tasks);
		startActivity(intent);
	}
}
