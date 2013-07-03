package com.sscit.componentactivity.button;

import com.sscit.componentactivity.R;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class ButtonFragment extends Fragment {
	Button button;
	ImageButton imageButton;
	private final int MONKEY = 1;
	private final int ELEPHANT = 2;
	private final int BEAR = 3;
	private int currentImage = MONKEY;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater
				.inflate(R.layout.button_fragment, container, false);
		return view;
	}

	public void onStart() {
		super.onStart();
		addListenerOnButtons();
	}
	
	public void addListenerOnButtons() {

		button = (Button) getActivity().findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri
						.parse("http://www.inam.gov.mz/ranet/previsao/diaria.htm"));
				startActivity(browserIntent);
			}
		});
		
		imageButton = (ImageButton) getActivity().findViewById(R.id.imageButton1);
		 
		imageButton.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
				Toast.makeText(getActivity(), " "+currentImage, Toast.LENGTH_SHORT).show();
				switch (currentImage) {
				case MONKEY: 
					imageButton.setImageResource(R.drawable.elephant);
					currentImage = ELEPHANT;
					break;
				case ELEPHANT: 
					imageButton.setImageResource(R.drawable.bear);
					currentImage = BEAR;
					break;
				case BEAR: 
					imageButton.setImageResource(R.drawable.monkey);
					currentImage = MONKEY;
					break;
				}
				Toast.makeText(getActivity(), " "+currentImage, Toast.LENGTH_SHORT).show();
			}
 
		});
		
	}
}