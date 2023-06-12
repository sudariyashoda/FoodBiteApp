package com.sudariyashoda.foodbiteapp.domain;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class Gyroscope {
    // Interface to handle gyroscope rotation events
    public interface Listner{
        void onRotation(float rx,float ry,float rz);
    }
    private Listner listner;// Listener object for gyroscope events

    // Method to set the gyroscope listener
    public void setListner(Listner l){
        listner = l;
    }
    private Sensor sensor;
    private SensorEventListener sensorEventListener;
    private SensorManager sensorManager;

    // Constructor for the Gyroscope class
    public Gyroscope(Context context){
        sensorManager= (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor =sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        // Event listener implementation for the gyroscope sensor
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                // Check if the gyroscope listener is set and trigger the onRotation event with gyroscope values
                if (listner != null){
                    listner.onRotation(sensorEvent.values[0],sensorEvent.values[1],sensorEvent.values[2]);
                }
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
                // No implementation needed for gyroscope accuracy changes
            }
        };

    }
    // Method to register the gyroscope listener
    public void register(){
        sensorManager.registerListener(sensorEventListener,sensor,sensorManager.SENSOR_DELAY_NORMAL);
    }
    // Method to unregister the gyroscope listener
    public void unegister(){
        sensorManager.unregisterListener(sensorEventListener);
    }
}
