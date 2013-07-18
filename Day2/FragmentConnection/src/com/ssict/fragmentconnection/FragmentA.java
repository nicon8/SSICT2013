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

public class FragmentA extends Fragment {
	Button button;

	public FragmentA() {
		super();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater
				.inflate(R.layout.fragment_a, container, false);
		return view;
	}

	@Override
	public void onStart() {
		super.onStart();
		addListenerOnButtons();
	}
	
	public void addListenerOnButtons() {

		button = (Button) getActivity().findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//Toast.makeText(getActivity(), " click", Toast.LENGTH_SHORT).show();
				Fragment fragment = new FragmentB();
				FragmentTransaction ft = getFragmentManager().beginTransaction();
				ft.replace(android.R.id.content, fragment);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.commit();
			}
		});
	}		
}