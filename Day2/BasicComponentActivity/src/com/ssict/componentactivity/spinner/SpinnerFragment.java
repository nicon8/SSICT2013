package com.ssict.componentactivity.spinner;

import java.util.ArrayList;
import java.util.List;

import com.sscit.componentactivity.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SpinnerFragment extends Fragment {
	
	private Spinner spinner1, spinner2;
	private Button btnSubmit;
	
	@Override
	  public View onCreateView(LayoutInflater inflater, ViewGroup container,
	      Bundle savedInstanceState) {
	    View view = inflater.inflate(R.layout.spinner_fragment,
	        container, false);
	    return view;
	  }

	@Override
	  public void onStart(){
		super.onStart();
		addItemsOnSpinner2();
		addListenerOnButton();
		addListenerOnSpinnerItemSelection();
		setText("Fragment");
	  }
	
	
	public void addItemsOnSpinner2() {
		spinner2 = (Spinner) getActivity().findViewById(R.id.spinner2);
		List<String> list = new ArrayList<String>();
		list.add("list 1");
		list.add("list 2");
		list.add("list 3");
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
			android.R.layout.simple_spinner_item, list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner2.setAdapter(dataAdapter);
	  }
	 
	  public void addListenerOnSpinnerItemSelection() {
		spinner1 = (Spinner) getActivity().findViewById(R.id.spinner1);
		spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
	  }
	 
	  // get the selected dropdown list value
	  public void addListenerOnButton() {
	 
		spinner1 = (Spinner) getActivity().findViewById(R.id.spinner1);
		spinner2 = (Spinner) getActivity().findViewById(R.id.spinner2);
		btnSubmit = (Button) getActivity().findViewById(R.id.btnSubmit);
	 
		btnSubmit.setOnClickListener(new OnClickListener() {
	 
		  @Override
		  public void onClick(View v) {
	 
		    Toast.makeText(getActivity(),
			"OnClickListener : " + 
	                "\nSpinner 1 : "+ String.valueOf(spinner1.getSelectedItem()) + 
	                "\nSpinner 2 : "+ String.valueOf(spinner2.getSelectedItem()),
				Toast.LENGTH_SHORT).show();
		  }
	 
		});
	  }
	
	  public void setText(String item) {
	    TextView view = (TextView) getView().findViewById(R.id.textView2);
	    view.setText(item);
	  }
	
}
