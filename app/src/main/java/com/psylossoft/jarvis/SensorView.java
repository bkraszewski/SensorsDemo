package com.psylossoft.jarvis;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

public class SensorView extends FrameLayout
{
	private static final String TAG = "SensorView";
	private float x;
	private float y;
	private float z;
	private Paint background;

	private float xPosition;
	private float yPosition;
	private Paint redPaint;

	public SensorView(Context context)
	{
		super(context);
		init();
	}

	public SensorView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		init();
	}

	public SensorView(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		init();
	}

	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	public SensorView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
	{
		super(context, attrs, defStyleAttr, defStyleRes);
		init();
	}

	public void movePosition(float x, float y, float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;

		xPosition += x * 10f;
		yPosition +=y * 10f;

		invalidate();
	}

	private void init()
	{
		background = new Paint();
		background.setColor(Color.BLACK);
		background.setStyle(Paint.Style.FILL);
		setWillNotDraw(false);

		redPaint = new Paint();
		redPaint.setStyle(Paint.Style.FILL);
		redPaint.setColor(Color.RED);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
		int parentHeight = MeasureSpec.getSize(heightMeasureSpec);
		this.setMeasuredDimension(parentWidth, parentHeight);
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		xPosition = parentWidth / 2f;
		yPosition = parentHeight / 2f;
		Log.d(TAG, String.format("OnMeasure: x: %f, y: %f", xPosition, yPosition));
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		Log.d(TAG, String.format("OnDraw: x: %f, y: %f", xPosition, yPosition));


		canvas.drawRect(0, 0, getWidth(), getHeight(), background);
		float size = 50;
		canvas.drawOval(new RectF(xPosition - size, yPosition - size, xPosition + size, yPosition + size), redPaint);
	}
}
