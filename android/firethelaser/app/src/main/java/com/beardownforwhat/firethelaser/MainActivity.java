package com.beardownforwhat.firethelaser;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import java.util.List;


public class MainActivity extends Activity implements SensorEventListener {

    private SensorManager sm;
    private Sensor sAccel;
    private Sensor sGyro;
    private Sensor sGravity;
    float [] linear_acceleration;
    float[] gravity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sm.unregisterListener(this);
        linear_acceleration = new float[3];
        gravity = new float[3];
        List<Sensor> sensorList = sm.getSensorList(Sensor.TYPE_ALL);
        for (Sensor s: sensorList){
            Log.e("Sensor: ", s.getName());
        }
        sAccel = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sGyro = sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        sGravity = sm.getDefaultSensor(Sensor.TYPE_GRAVITY);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType()== Sensor.TYPE_ACCELEROMETER){
            final float alpha = (float) 0.8;

            // Isolate the force of gravity with the low-pass filter.
            gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
            gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
            gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];

            // Remove the gravity contribution with the high-pass filter.
            linear_acceleration[0] = event.values[0] - gravity[0];
            linear_acceleration[1] = event.values[1] - gravity[1];
            linear_acceleration[2] = event.values[2] - gravity[2];
        }
        else if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE){

        }

    }
}
