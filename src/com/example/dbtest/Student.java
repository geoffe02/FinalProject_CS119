package com.example.dbtest;

//import com.example.lab1.R;

//import com.example.telephonydemo.R;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
public class Student extends Activity {
	String num;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_student);
		Intent stud = getIntent();
		 num = stud.getStringExtra("client");
		 System.out.println(num);
		  Button log= (Button) findViewById(R.id.button1);
		//  Bundle extras = data.getExtras();
	        log.setOnClickListener(new OnClickListener() 
	        {		
				public void onClick(View v) 
				{
					call();
				//	EditText ed1 = (EditText) findViewById(R.id.editText1);
		    	//	EditText ed2 = (EditText) findViewById(R.id.editText2);
		    		//System.out.println("store" + user);
		    		//System.out.println("store" + pass);
		    		//System.out.println("txt" + ed1.getText().toString());
		    		//System.out.println("txt" + ed2.getText().toString());
		 /*   	if( find(ed1.getText().toString(),ed2.getText().toString()) )
		    	{
		    		openlog(1);
		    	}
		    	else
		    	{
		    		openlog(0);
		    	}*/
				}
			});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.student, menu);
		return true;
	}
	public void call()
    {
    	try {
			// add to call
			Intent dial = new Intent(Intent.ACTION_CALL);
			dial.setData(Uri.parse("tel:" + num));
			startActivity(dial);
		} catch (Exception e) {
			// TODO: handle exception
		} 
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
}
