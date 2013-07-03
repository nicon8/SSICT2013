package com.sscit.componentactivity.radio;

import com.sscit.componentactivity.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class RadioFragment extends Fragment implements
		RadioGroup.OnCheckedChangeListener, OnClickListener {

	private TextView mChoice;
	private RadioGroup mRadioGroup;
	private boolean valid = true;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.radio_fragment, container, false);
		return view;
	}

	@Override
	public void onStart() {
		super.onStart();

		mRadioGroup = (RadioGroup) getActivity().findViewById(R.id.menu);

		// test listening to checked change events
		String selection = getString(R.string.radio_group_selection);
		mRadioGroup.setOnCheckedChangeListener(this);
		mChoice = (TextView) getActivity().findViewById(R.id.choice);
		mChoice.setText(selection + mRadioGroup.getCheckedRadioButtonId());

		// test clearing the selection
		Button clearButton = (Button) getActivity().findViewById(R.id.clear);
		clearButton.setOnClickListener(this);
	}

	public void onCheckedChanged(RadioGroup group, int checkedId) {
		String selection = getString(R.string.radio_group_selection);
		String none = getString(R.string.radio_group_none);
		mChoice.setText(selection
				+ (checkedId == View.NO_ID ? none : checkedId));
		RadioButton rb = (RadioButton) getActivity().findViewById(checkedId);
		if (valid && checkedId != View.NO_ID) 
			Toast.makeText(getActivity(), rb.getText(), Toast.LENGTH_SHORT).show();
		else valid = true;
	}

	public void onClick(View v) {
		valid = false;
		mRadioGroup.clearCheck();
	}
}