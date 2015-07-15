package com.example.dbtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Add extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void Cancel (View V)
	{
		finish();
	}
	
	
public void Add2 (View V)
{
	EditText CodeInput = (EditText) findViewById(R.id.editText2);
	EditText NameInput = (EditText) findViewById(R.id.editText3);
	String code = CodeInput.getText().toString();
	if (code.matches(""))
	{
		Toast.makeText(getApplicationContext(), "Please Fill Up All Text Boxes", Toast.LENGTH_SHORT).show();
		return;
	}
	else
	{
	String name = NameInput.getText().toString();
		if (name.matches(""))
		{
			Toast.makeText(getApplicationContext(), "Please Fill Up All Text Boxes", Toast.LENGTH_SHORT).show();
			return;
		}
		else
		{
			
	Intent back = getIntent();
	back.putExtra("code", code);
	back.putExtra("name", name);
	
	setResult(0, back);
	finish();
	}
		}
	}
}
