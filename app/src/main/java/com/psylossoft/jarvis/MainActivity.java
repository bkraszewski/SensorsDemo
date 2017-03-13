package com.psylossoft.jarvis;

import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements SensorEventListener
{

	private static final String TAG = "MainActivity";
	private SensorManager manage;
	private Sensor sensor;
	private SensorView sensorView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		sensorView = (SensorView) findViewById(R.id.sensor_view);

		testAccelerometer();
	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
	private void testAccelerometer()
	{
		manage = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		sensor = manage.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);


	}

	@Override
	protected void onResume()
	{
		super.onResume();
		manage.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onPause()
	{
		super.onPause();
		manage.unregisterListener(this);
	}

	@Override
	public void onSensorChanged(SensorEvent event)
	{
//		Log.d(TAG, String.format("Sensor data: x:%f", event.values[0]));
//		Log.d(TAG, String.format("Sensor data: y:%f", event.values[1]));
//		Log.d(TAG, String.format("Sensor data: z:%f", event.values[2]));
		sensorView.movePosition(event.values[0], event.values[1], event.values[2]);


	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy)
	{

	}
}
