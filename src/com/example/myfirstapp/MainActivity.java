package com.example.myfirstapp;

//import java.util.*;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	public static ArrayList<String> tasks = new ArrayList<String>();
	public String mainList;
	public Activity thisActivity = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setupUI(findViewById(R.id.parent));

	}
	public static ArrayList<String> getList(){
		return tasks;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void addTask(View view) {
		EditText task = (EditText) findViewById(R.id.textBox);
		String message = "test";
		message = (task.getText().toString());
		
		TextView response = (TextView) findViewById(R.id.response);
		task.setText("");
		if (message.length() == 0){
			response.setText("No task entered: Please enter a task.");
		}
		else{
			response.setText("Task added!");
			tasks.add(message);
		}
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(task.getWindowToken(), 0);

	}

	public void removeTasks(View view) {
		Intent intent = new Intent(this, RemoveOption.class);
		mainList = "";
		for (String s : tasks)
			mainList += s + "\n";
		intent.putExtra(EXTRA_MESSAGE, mainList);

		startActivity(intent);
	}

	public void sendMessage(View view) {
		Intent intent = new Intent(this, DisplayMessageActivity.class);
		mainList = "";
		for (String s : tasks) {
			mainList += s + "\n";
		}
		intent.putExtra(EXTRA_MESSAGE, mainList);

		startActivity(intent);

		/*
		 * ArrayList<EditText> tasks = new ArrayList<EditText>();
		 * tasks.add((EditText) findViewById(R.id.editText1));
		 * tasks.add((EditText) findViewById(R.id.editText2));
		 * tasks.add((EditText) findViewById(R.id.editText3)); EditText
		 * editText1 = (EditText) findViewById(R.id.editText1);
		 * 
		 * EditText editText2 = (EditText) findViewById(R.id.editText2);
		 * 
		 * EditText editText3 = (EditText) findViewById(R.id.editText3);
		 * 
		 * String message = "";
		 * 
		 * try {
		 * 
		 * 
		 * } catch (NullPointerException e) { message = "Nothing to do!"; }
		 * 
		 * String message = ""; for (int i = 0; i < tasks.size(); i++) { if
		 * (!tasks.get(i).getText().toString().equals("Enter a task")) { message
		 * += tasks.get(i).getText().toString() + "\n"; } }
		 * 
		 * if (message.equals("")) { message = "Nothing to do!"; }
		 */

	}

	public void setupUI(View view) {

		// Set up touch listener for non-text box views to hide keyboard.
		if (!(view instanceof EditText)) {

			view.setOnTouchListener(new OnTouchListener() {

				public boolean onTouch(View v, MotionEvent event) {
					Activity a = thisActivity;
					hideSoftKeyboard(a);
					return false;
				}

			});
		}

		// If a layout container, iterate over children and seed recursion.
		if (view instanceof ViewGroup) {

			for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

				View innerView = ((ViewGroup) view).getChildAt(i);

				setupUI(innerView);
			}
		}
	}
	public static void hideSoftKeyboard(Activity activity) {
	    InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
	    inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
	}
	
	public static void updateTasks(ArrayList<String> newTasks)
	{
		tasks = newTasks;
	}
}
