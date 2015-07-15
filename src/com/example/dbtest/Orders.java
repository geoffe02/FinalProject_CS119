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
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;

@SuppressLint("NewApi")
public class Orders extends Activity 
{

	private OrderDbAdapter dbHelper;
	private SimpleCursorAdapter dataAdapter;
	
 

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_orders);
		
		
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
				  new String[] { OrderDbAdapter.KEY_IDNUM, OrderDbAdapter.KEY_NAME, OrderDbAdapter.KEY_YRCOURSE, OrderDbAdapter.KEY_CELLNUM, OrderDbAdapter.KEY_CLIENT },  	// columns to be bound 
				  new int[]    { R.id.idNum, R.id.name, R.id.yrCourse, R.id.cellNum, R.id.client },									// views id which the data will be bound to 
				  0);

		
		
	
	ListView listView = (ListView) findViewById(R.id.listView1);
	listView.setAdapter(dataAdapter);
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
			String name = cursor.getString(cursor.getColumnIndexOrThrow(OrderDbAdapter.KEY_IDNUM));
			
			// display in toast
			Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();
		}
		
	});
	EditText myFilter = (EditText) findViewById(R.id.editText1);
	myFilter.addTextChangedListener(new TextWatcher() {

		public void afterTextChanged(Editable s) {
		}

		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
		}

		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			
			// this tells the adapter to trigger the filter
			dataAdapter.getFilter().filter(s.toString());
		}
	});

	
	// indicates what query will be run when filtering
	dataAdapter.setFilterQueryProvider(new FilterQueryProvider() {
		public Cursor runQuery(CharSequence constraint) {
			return dbHelper.fetchRegionsByName(constraint.toString());
		}
		
	});
	
	
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	 @Override
	    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo)
	    {
	    	super.onCreateContextMenu(menu, v, menuInfo);
	    	MenuInflater inflater = getMenuInflater();
	    	
	    	// places the contents of the XML to the menu
	    	inflater.inflate(R.menu.context_menu, menu);
	    }
	    
	    @Override
	    public boolean onContextItemSelected(MenuItem item) {
	      AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
	      switch (item.getItemId()) {
	      case R.id.delete:
	    	// delete the currently selected row
	    	  System.out.println(info.id);
	    	  System.out.println(info.position);    
	    	  
	    	  // NOTE: trigger the changes
	    	  dbHelper.deleteRegion(info.id);
	    	  dataAdapter.notifyDataSetChanged();
	    	  Cursor cursor = dbHelper.fetchAllRegions();
	  		dataAdapter = new SimpleCursorAdapter(this, 
	  				  R.layout.order_info,
	  				  cursor, 
	  				 new String[] { OrderDbAdapter.KEY_IDNUM, OrderDbAdapter.KEY_NAME, OrderDbAdapter.KEY_YRCOURSE, OrderDbAdapter.KEY_CELLNUM, OrderDbAdapter.KEY_CLIENT },  	// columns to be bound 
					  new int[]    { R.id.idNum, R.id.name, R.id.yrCourse, R.id.cellNum, R.id.client },								// views id which the data will be bound to 
	  				  0);

	
	  		EditText myFilter = (EditText) findViewById(R.id.editText1);
	  		myFilter.addTextChangedListener(new TextWatcher() {

	  			public void afterTextChanged(Editable s) {
	  			}

	  			public void beforeTextChanged(CharSequence s, int start, int count,
	  					int after) {
	  			}

	  			public void onTextChanged(CharSequence s, int start, int before,
	  					int count) {
	  				
	  				// this tells the adapter to trigger the filter
	  				dataAdapter.getFilter().filter(s.toString());
	  			}
	  		});
	  		dataAdapter.setFilterQueryProvider(new FilterQueryProvider() {
	  			public Cursor runQuery(CharSequence constraint) {
	  				return dbHelper.fetchRegionsByName(constraint.toString());
	  			}
	  			
	  		});
	  	
	  	ListView listView = (ListView) findViewById(R.id.listView1);
	  	listView.setAdapter(dataAdapter);
	        return true;
	      default:
	        return super.onContextItemSelected(item);
	      }
	    }
	    public void Back (View V)
	    {
	    	finish();
	    }
	    public void Add (View V)
	    {
	    	Intent Add = new Intent(this, AddOrder.class);
	    	startActivityForResult(Add, 0);
	    }
	    
	    public void onActivityResult(int requestCode, int resultCode, Intent data)
	    {
	    	if(data==null)
	    	{
	    		return;
	    	}
	    	
	    	String idNum = data.getStringExtra("idNum").toString();
	    	String name = data.getStringExtra("name").toString();
	    	String yrCourse = data.getStringExtra("yrCourse").toString();
	    	String cellNum = data.getStringExtra("cellNum").toString();
	    	String client = data.getStringExtra("client").toString();
	    	dbHelper.createRegion(idNum, name, yrCourse, cellNum, client);
	    	
	    	Cursor cursor = dbHelper.fetchAllRegions();
			dataAdapter = new SimpleCursorAdapter(this, 
					  R.layout.order_info,
					  cursor, 
					  new String[] { OrderDbAdapter.KEY_IDNUM, OrderDbAdapter.KEY_NAME, OrderDbAdapter.KEY_YRCOURSE, OrderDbAdapter.KEY_CELLNUM, OrderDbAdapter.KEY_CLIENT },  	// columns to be bound 
					  new int[]    { R.id.idNum, R.id.name, R.id.yrCourse, R.id.cellNum, R.id.client },								// views id which the data will be bound to 
					  0);

			
		
		ListView listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(dataAdapter);
	    }

}
