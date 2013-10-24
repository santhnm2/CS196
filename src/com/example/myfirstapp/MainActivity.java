package com.example.myfirstapp;

//import java.util.*;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void sendMessage(View view)
	{
		Intent intent = new Intent(this, DisplayMessageActivity.class);
		
		ArrayList<EditText> tasks = new ArrayList<EditText>();
		tasks.add((EditText) findViewById(R.id.editText1));
		tasks.add((EditText) findViewById(R.id.editText2));
		tasks.add((EditText) findViewById(R.id.editText3));
		/*EditText editText1 = (EditText) findViewById(R.id.editText1);
		
		EditText editText2 = (EditText) findViewById(R.id.editText2);
		
		EditText editText3 = (EditText) findViewById(R.id.editText3);
		
		//String message = "";
		
		//try
		//{
		
		
			String message = (editText1.getText().toString() + "\n" + editText2.getText().toString() + "\n" + editText3.getText().toString());
		/*}
		catch (NullPointerException e)
		{
			message = "Nothing to do!";
		}*/
		
		String message = "";
		for (int i = 0; i < tasks.size(); i++)
		{
			if (!tasks.get(i).getText().toString().equals("Enter a task"))
			{
				message += tasks.get(i).getText().toString() + "\n";
			}
		}
		
		if (message.equals(""))
		{
			message = "Nothing to do!";
		}
		
		intent.putExtra(EXTRA_MESSAGE, message);
		
		startActivity(intent);
	
	}

}
