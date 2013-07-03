package com.ssict.componentactivity.canvas;

import com.sscit.componentactivity.R;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CanvasFragment extends Fragment implements OnTouchListener,
		OnClickListener {

	private GameBoard mGameBoard = null;
	private boolean isFlagHidden = false;
	private int level = 1;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater
				.inflate(R.layout.canvas_fragment, container, false);
		return view;
	}

	@Override
	public void onStart() {
		super.onStart();
		if (mGameBoard == null) {
			mGameBoard = (GameBoard) getActivity()
					.findViewById(R.id.the_canvas);
			mGameBoard.setOnTouchListener(this);
			Button b = (Button) getActivity().findViewById(R.id.the_button);
			b.setOnClickListener(this);
		}
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.the_button) {
			TextView tv = (TextView) getActivity().findViewById(R.id.the_label);
			tv.setText("");
			Button b = (Button) getActivity().findViewById(R.id.the_button);
			isFlagHidden = !isFlagHidden;
			if (isFlagHidden) {
				b.setText("Give Up!");
				mGameBoard.hideTheMonkey();
			} else {
				b.setText("Hide the Monkey!");
				mGameBoard.giveUp();
			}
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (v.getId() == R.id.the_canvas) {
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				if (isFlagHidden) {
					TextView tv = (TextView) getActivity().findViewById(
							R.id.the_label);
					switch (mGameBoard.takeAGuess(event.getX(), event.getY())) {
					case BULLSEYE:
						Button b = (Button) getActivity().findViewById(
								R.id.the_button);
						isFlagHidden = false;
						b.setText("Hide the Monkey!");
						tv.setText("You found it!");
						tv.setTextColor(Color.GREEN);
						mGameBoard.setLevel(level < 4 ? ++level : 5);
						if (level == 4)
							Toast.makeText(getActivity(), "You are a superchamp but now it is time to stop to play", Toast.LENGTH_SHORT).show();
						else 
							Toast.makeText(getActivity(), "Level "+level, Toast.LENGTH_SHORT).show();
						break;
					case HOT:
						tv.setText("You're hot!");
						tv.setTextColor(Color.RED);
						break;
					case WARM:
						tv.setText("Getting warm...");
						tv.setTextColor(Color.YELLOW);
						break;
					case COLD:
						tv.setText("You're cold.");
						tv.setTextColor(Color.BLUE);
						break;
					}
				}
			}
			return true;
		}
		return false;
	}
}
