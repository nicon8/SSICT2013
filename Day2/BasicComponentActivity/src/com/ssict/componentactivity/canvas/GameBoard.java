package com.ssict.componentactivity.canvas;

import com.sscit.componentactivity.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class GameBoard extends View{
	
	private int mFlagX = -1;
	private int mFlagY = -1;
	private Bitmap mBitmap = null;
	private Paint mPaint = null;
	private boolean isFlagHidden = false;
	private int mBoundX = -1;
	private int mBoundY = -1;
	
	//play with these values to make the app more or less challenging
	public final int CLOSER = 50;
	public final int CLOSE = 100;
	
	public int level = 1;
	
	public GameBoard(Context context, AttributeSet aSet) { 
		super(context, aSet); 
		//load our bitmap
		Bitmap tmp = BitmapFactory.decodeResource(getResources(), R.drawable.monkey);
		mBitmap = Bitmap.createScaledBitmap(tmp, 250 / level, 250 / level , true);
		//create a paint brush
		mPaint = new Paint();
		mPaint.setColor(Color.DKGRAY);
	}
	
	public void setLevel(int level){
		this.level = level;
		Bitmap tmp = BitmapFactory.decodeResource(getResources(), R.drawable.monkey);
		mBitmap = Bitmap.createScaledBitmap(tmp, 250 / level, 250 / level , true);
	}
	
	@Override
    public void onDraw(Canvas canvas) {
		//initialize
		if ((mFlagX < 1) || (mFlagY < 1)) {
    		mFlagX = (int) (getWidth() / 2) - mBitmap.getWidth() / 2;
    		mFlagY = (int) (getHeight() / 2) - mBitmap.getHeight() / 2;
    		mBoundX = (int)getWidth() - mBitmap.getWidth();
    		mBoundY = (int)getHeight() - mBitmap.getHeight();
    	}
		//draw background
    	canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
		//draw the flag
    	if (!isFlagHidden) {
    		canvas.drawBitmap(mBitmap, mFlagX, mFlagY, null);
    	}
    }
	
	public void hideTheMonkey(){
		//randomize flag location 
		mFlagX = (int) Math.ceil(Math.random() * mBoundX);
		mFlagY = (int) Math.ceil(Math.random() * mBoundY);
		isFlagHidden = true;
		//force redraw
		invalidate();	
	}
	
	public void giveUp(){
		isFlagHidden = false;
		//force redraw
		invalidate();
	}	
	
	public Indicators takeAGuess(float x, float y) {
		
		//this is our "warm" area
		Rect prettyClose = new Rect(mFlagX - CLOSE, mFlagY - CLOSE, mFlagX+mBitmap.getWidth() + CLOSE, mFlagY+mBitmap.getHeight() + CLOSE);
		//normalize
		if (prettyClose.left < 0) prettyClose.left = 0;
		if (prettyClose.top < 0) prettyClose.top = 0;
		if (prettyClose.right > mBoundX) prettyClose.right = mBoundX;
		if (prettyClose.bottom > mBoundY) prettyClose.bottom = mBoundY;
		
		//this is our "hot" area
		Rect reallyClose = new Rect(mFlagX - CLOSER, mFlagY - CLOSER, mFlagX+mBitmap.getWidth() + CLOSER, mFlagY+mBitmap.getHeight() + CLOSER);
		//normalize
		if (reallyClose.left < 0) reallyClose.left = 0;
		if (reallyClose.top < 0) reallyClose.top = 0;
		if (reallyClose.right > mBoundX) reallyClose.right = mBoundX;
		if (reallyClose.bottom > mBoundY) reallyClose.bottom = mBoundY;
		
		//this is the area that contains our flag
		Rect bullsEye = new Rect(mFlagX, mFlagY, mFlagX+mBitmap.getWidth(), mFlagY+mBitmap.getHeight());
		
		//check to see where on the board the user pressed
		if (bullsEye.contains((int) x, (int)y)) {
			//found it
			isFlagHidden = false;
			invalidate();
			return Indicators.BULLSEYE;
		} else if (reallyClose.contains((int) x, (int)y)) {
			//hot
			return Indicators.HOT;
		} else if (prettyClose.contains((int)x, (int)y)) {
			//warm
			return Indicators.WARM;
		} else {
			//not even close
			return Indicators.COLD;
		}
	}
}
