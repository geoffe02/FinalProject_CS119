package com.example.dbtest;
import com.example.dbtest.R;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;

@SuppressLint("NewApi")
public class SummaryOrders extends Activity 
{

	private OrderDbAdapter dbHelper;
	private SimpleCursorAdapter dataAdapter;
	
	String gets;
	String nu;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sum2);
		Bundle bundle = getIntent().getExtras();
		gets = bundle.getString("client");
		System.out.println(gets);
		TextView tv = (TextView) findViewById(R.id.textView2);
		nu = "Client: " + gets;
		tv.setText(nu);
		
		System.out.println("OPEN DB");
		
		// INSTANCE DB ADAPTER
		dbHelper = new OrderDbAdapter(this);
		
		// OPEN
		dbHelper.open();

		
		// DO ACTIONS
		// Clean all data
		

		System.out.println("SEED DB");
		// Add some data
		dbHelper.seed();
		
		
		
		// QUERY
		// print contents of DB
		Cursor cursor = dbHelper.fetchAllRegions();
		dataAdapter = new SimpleCursorAdapter(this, 
				  R.layout.order_info,
				  cursor, 
				  new String[] {  OrderDbAdapter.KEY_CLIENT , OrderDbAdapter.KEY_IDNUM, OrderDbAdapter.KEY_NAME, OrderDbAdapter.KEY_YRCOURSE, OrderDbAdapter.KEY_CELLNUM},  	// columns to be bound 
				  new int[]    {R.id.client, R.id.idNum, R.id.name, R.id.yrCourse, R.id.cellNum},									// views id which the data will be bound to 
				  0);
		dataAdapter.getFilter().filter(gets);
		dataAdapter.notifyDataSetChanged();
		nu = tv.toString();
		
  		dataAdapter.setFilterQueryProvider(new FilterQueryProvider() {
  			public Cursor runQuery(CharSequence constraint) {
  				return dbHelper.fetchRegionsByName(constraint.toString());
  			}
  			
  		});
		
	ListView listView = (ListView) findViewById(R.id.listView1);
	listView.setAdapter(dataAdapter);
	//return true;
	//
	registerForContextMenu(listView);

	


	// clicking an item in the listview
	listView.setOnItemClickListener(new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> listView, View view,
				int position, long id) {
			// Get the cursor, positioned to the corresponding row in the
			// result set
			Cursor cursor = (Cursor) listView.getItemAtPosition(position);

			// Get the region's name from this row in the database.
			String name = cursor.getString(cursor.getColumnIndexOrThrow(OrderDbAdapter.KEY_NAME));
			
			// display in toast
			Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();
			
			go(1);
		}
		
	});
	
	
}

	private void go(int a)
	{
		Intent z = new Intent (this, Student.class);
		z.putExtra("client", gets);
		startActivity(z);
	}
	public void back(View V)
	{
		finish();
	}
	
}