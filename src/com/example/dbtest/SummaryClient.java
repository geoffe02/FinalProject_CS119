package com.example.dbtest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class SummaryClient extends Activity {
	
	private RegionDbAdapter dbHelper;
	private SimpleCursorAdapter dataAdapter;
	String code;
	@SuppressLint("NewApi") @Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sum1);

		dbHelper = new RegionDbAdapter(this);
		dbHelper.open();

		// Clean all data
		
		// Add some data
		dbHelper.seed();

		
		// Generate ListView from SQLite Database
		Cursor cursor = dbHelper.fetchAllRegions();

		// create the adapter using the cursor pointing to the desired data
		// as well as the layout information
		
		// new SimpleCursorAdapter(context, layout, c, from, to, flags)
		dataAdapter = new SimpleCursorAdapter(this, 
											  R.layout.movie_info,
											  cursor, 
											  new String[] { RegionDbAdapter.KEY_CODE, RegionDbAdapter.KEY_NAME },  	// columns to be bound 
											  new int[]    { R.id.code, R.id.name },									// views id which the data will be bound to 
											  0);

		// Assign adapter to ListView
		ListView listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(dataAdapter);

		
		// clicking an item in the listview
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> listView, View view,
					int position, long id) {
				// Get the cursor, positioned to the corresponding row in the
				// result set
				Cursor cursor = (Cursor) listView.getItemAtPosition(position);

				// Get the region's name from this row in the database.
				code = cursor.getString(cursor.getColumnIndexOrThrow(RegionDbAdapter.KEY_CODE)).toString();
				Toast.makeText(getApplicationContext(), code + " selected ", Toast.LENGTH_SHORT).show();
				Toast.makeText(getApplicationContext(), code + " selected ", Toast.LENGTH_SHORT).show();
				//ok(num);
				//System.out.println(num);
				//next.putExtra("client", name);
				//startActivity(next);
				// display in toast
				
				
			}
		});

		
		
		// this detects changes in the EditText as you type
		
		

		
		// indicates what query will be run when filtering
		dataAdapter.setFilterQueryProvider(new FilterQueryProvider() {
			public Cursor runQuery(CharSequence constraint) {
				return dbHelper.fetchRegionsByName(constraint.toString());
			}
		});

	}
	public void okClick(View v)
	{
		Intent next = new Intent (this, SummaryOrders.class);
		
		next.putExtra("class", code);
		startActivity(next);
	}
	public void ok(String cursor)
	{
		Intent next = new Intent (this, SummaryOrders.class);
		next.putExtra("class", cursor);
		startActivity(next);
	}
	public void Back (View V)
    {
    	
    	finish();
    }

}