package com.example.dbtest;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainScreen extends Activity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_screen);
		
	}
	public void Orders (View x)
	{
		Intent orders = new Intent (this, Orders.class);
		startActivity(orders);
	}
	public void Xorders (View z)
	{
		Intent orders = new Intent (this, Orders.class);
		startActivity(orders);
	}
	public void Client (View v)
	{
		Intent client = new Intent (this, Client.class);
		startActivity(client);
	}
	
	public void Summary (View y)
	{
		Intent summary = new Intent (this, SummaryClient.class);
		startActivity(summary);
	}
	
	public void Quit (View v)
	{
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_screen, menu);
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
}
