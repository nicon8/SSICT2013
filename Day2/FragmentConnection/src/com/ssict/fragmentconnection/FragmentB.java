package com.ssict.fragmentconnection;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class FragmentB extends Fragment {
	Button button;

	public FragmentB() {
		super();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater
				.inflate(R.layout.fragment_b, container, false);
		return view;
	}

	@Override
	public void onStart() {
		super.onStart();
		addListenerOnButtons();
	}
	
	public void addListenerOnButtons() {

		button = (Button) getActivity().findViewById(R.id.button2);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Toast.makeText(getActivity(), " click", Toast.LENGTH_SHORT).show();
				Fragment canvasFragment = new FragmentA();
				FragmentTransaction ft = getFragmentManager().beginTransaction();
				ft.replace(android.R.id.content, canvasFragment);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.commit();
			}
		});
	}		
}
