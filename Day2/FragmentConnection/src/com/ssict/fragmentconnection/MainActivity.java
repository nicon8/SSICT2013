package com.ssict.fragmentconnection;



import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (savedInstanceState == null) {
	        Fragment fg = new FragmentA();
	        getFragmentManager().beginTransaction().add(android.R.id.content, fg)
	            .commit();
	    }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
    
}
