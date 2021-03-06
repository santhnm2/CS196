package com.example.myfirstapp;

//import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
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
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity {

	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	public static ArrayList<Task> tasks = new ArrayList<Task>();
	public String mainList;
	public Activity thisActivity = this;
	public SeekBar prioritySet;
	public double priority;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setupUI(findViewById(R.id.parent));
		prioritySet = (SeekBar) findViewById(R.id.seekBar1);

		prioritySet.setOnSeekBarChangeListener(seekBarListener);
		//loadData();
	}

	public static ArrayList<Task> getList() {
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
		if (message.length() == 0) {
			response.setText("No task entered: Please enter a task.");
		} else {
			int date = -1;
			response.setText("Task added!");
			
			priority = prioritySet.getProgress();
			
			EditText dateFrom = (EditText) findViewById(R.id.dateText);
			try {
				date = Integer.parseInt(dateFrom.getText().toString());
			} catch (NumberFormatException e) {
			}
			;
			dateFrom.setText("");
			Task t = new Task(message, priority, date);

			tasks.add(t);
			prioritySet.setProgress(20);
		}
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(task.getWindowToken(), 0);
		//saveData();
	}
	private OnSeekBarChangeListener seekBarListener = new OnSeekBarChangeListener(){
		public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2){
			priority=arg0.getProgress();
			TextView label= (TextView)findViewById(R.id.priorityLabel);
			label.setText("Current Priority: "+priority);
		}

		@Override
		public void onStartTrackingTouch(SeekBar arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onStopTrackingTouch(SeekBar arg0) {
			// TODO Auto-generated method stub
			
		}
	};
	/*
	public void saveData()
	{
		ArrayList<Serializable> saveList = new ArrayList<Serializable>();
		saveList.add(tasks);
		File folder = getFolder();
		File file = new File(folder,"data.dat");
		FileOutputStream fos;
		ObjectOutputStream os;
		try 
		{
			fos = new FileOutputStream(file);
			try 
			{
				os = new ObjectOutputStream(fos);
				os.writeObject(saveList);
				os.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}			
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}				
	}
	
	public File getFolder(){
	    //All this are options:
	    return this.getDir("", Context.MODE_PRIVATE);  //Internal
	    //return ctx.getExternalFilesDir(); // External Private
	    //return ctx.getExternalCacheDir();//External Private Cache
	    //return Environment.getExternalStoragePublicDirectory(); //External
	}
	
	public void loadData()
	{
		ArrayList<Serializable> saveList = null;
		File folder = getFolder();
		File file = new File(folder,"data.dat");
		FileInputStream fis;
		ObjectInputStream in;
		try
		{
			fis = new FileInputStream(file);
			in = new ObjectInputStream(fis);
			saveList = (ArrayList<Serializable>) in.readObject();
			in.close();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		if (saveList != null)
			tasks = (ArrayList<Task>) (saveList.get(0));
	}*/

	public void removeTasks(View view) {
		Intent intent = new Intent(this, RemoveOption.class);
		mainList = "";
		for (Task t : tasks)
		{
			mainList += t.getName() + "\n";
			//t.checkDaysLeft();
		}			
		intent.putExtra(EXTRA_MESSAGE, mainList);

		startActivity(intent);
	}

	/*
	 * public void sendMessage(View view) { Intent intent = new Intent(this,
	 * DisplayMessageActivity.class); mainList = ""; for (Task t : tasks)
	 * mainList += t.toString() + "\n"; intent.putExtra(EXTRA_MESSAGE,
	 * mainList);
	 * 
	 * startActivity(intent);
	 * 
	 * /* ArrayList<EditText> tasks = new ArrayList<EditText>();
	 * tasks.add((EditText) findViewById(R.id.editText1)); tasks.add((EditText)
	 * findViewById(R.id.editText2)); tasks.add((EditText)
	 * findViewById(R.id.editText3)); EditText editText1 = (EditText)
	 * findViewById(R.id.editText1);
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
	 * (!tasks.get(i).getText().toString().equals("Enter a task")) { message +=
	 * tasks.get(i).getText().toString() + "\n"; } }
	 * 
	 * if (message.equals("")) { message = "Nothing to do!"; }
	 */

	// }

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
		InputMethodManager inputMethodManager = (InputMethodManager) activity
				.getSystemService(Activity.INPUT_METHOD_SERVICE);
		inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus()
				.getWindowToken(), 0);
	}

	public static void updateTasks(ArrayList<Task> newTasks) {
		tasks = newTasks;
	}
}
