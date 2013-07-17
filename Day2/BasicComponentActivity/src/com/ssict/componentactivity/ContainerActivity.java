package com.ssict.componentactivity;

import com.sscit.componentactivity.R;
import com.ssict.componentactivity.button.ButtonFragment;
import com.ssict.componentactivity.canvas.CanvasFragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.Menu;

public class ContainerActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_container);
		if (savedInstanceState == null) {
	        Fragment fg = new CanvasFragment();
	        getFragmentManager().beginTransaction().add(android.R.id.content, fg)
	            .commit();
	    }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.container, menu);
		return true;
	}

}
