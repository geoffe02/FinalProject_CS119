package com.example.dbtest;

import com.example.dbtest.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AddOrder extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addoreder);}

		

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem idNum) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = idNum.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(idNum);
	}
	
	public void Cancel (View V)
	{
		finish();
	}
	public void pick (View v)
    {
    	Intent pick = new Intent(this,Pick.class);
    	startActivityForResult(pick,0);
    }
	public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
    	if(data==null)
    	{
    		return;
    	}
    	String changer = data.getStringExtra("client");
    	TextView Clientinput = (TextView) findViewById(R.id.textView6);
    	Clientinput.setText(changer);
    	
    }
	
public void Add2 (View V)
{
	TextView tv = (TextView) findViewById(R.id.textView6);
	EditText idNumInput = (EditText) findViewById(R.id.editText2);
	EditText nameInput = (EditText) findViewById(R.id.editText3);
	EditText yrCourseInput = (EditText) findViewById(R.id.editText1);
	EditText cellNumInput = (EditText) findViewById(R.id.editText4);

Intent back = getIntent();
	

			String idNum = idNumInput.getText().toString();
			if (idNum.matches(""))
			{
				Toast.makeText(getApplicationContext(), "Please Fill Up All Text Boxes", Toast.LENGTH_SHORT).show();
				return;
			}
			else
			{
				String name = nameInput.getText().toString();
				if (name.matches(""))
				{
					Toast.makeText(getApplicationContext(), "Please Fill Up All Text Boxes", Toast.LENGTH_SHORT).show();
					return;
				}
				else
				{
					String yrCourse = yrCourseInput.getText().toString();
					if (yrCourse.matches(""))
					{
						Toast.makeText(getApplicationContext(), "Please Fill Up All Text Boxes", Toast.LENGTH_SHORT).show();
						return;
					}
					else
					{
						String client = tv.getText().toString();
						if (client.matches(""))
						{
							Toast.makeText(getApplicationContext(), "Please Choose a Client", Toast.LENGTH_SHORT).show();
							return;
						}
						else
						{
							String cellNum = cellNumInput.getText().toString();
							if (cellNum.matches(""))
							{
								cellNum = "No cellphone number";
							}
					back.putExtra("idNum", idNum);
					back.putExtra("name", name);
					back.putExtra("yrCourse", yrCourse);
					back.putExtra("cellNum", cellNum);
					back.putExtra("client", client);	
					setResult(0, back);
					finish();
	}}}}
			}
}
	


