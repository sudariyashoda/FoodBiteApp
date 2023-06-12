package com.sudariyashoda.foodbiteapp.domain;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class Accelerometer {
    // Interface to listen for translation values
    public interface Listner{
        void onTranslation(float tx,float ty,float tz);
    }
    private Listner listner;
    // Set the listener for translation values
    public void setListner(Listner l){
        listner = l;
    }
    private SensorManager sensorManager;
    private Sensor sensor;
    private SensorEventListener sensorEventListener;
    // Constructor for the Accelerometer class
    public Accelerometer(Context context){
        sensorManager= (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor= sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        // Define the sensor event listener
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if (listner != null){
                    // Pass the translation values to the listener
                    listner.onTranslation(sensorEvent.values[0],sensorEvent.values[1],sensorEvent.values[2]);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
                // Method stub, no action needed
            }
        };
    }
    // Register the sensor event listener
    public void register(){
        sensorManager.registerListener(sensorEventListener,sensor,sensorManager.SENSOR_DELAY_NORMAL);
    }
    // Unregister the sensor event listener
    public void unegister(){
        sensorManager.unregisterListener(sensorEventListener);
    }
}
