package com.example.checkmyconn.utilidades;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by alienware on 26/04/2015.
 */
public class ConnUtilities {
    public static boolean checkWifiStatus(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connectivityManager != null) {
            networkInfo =
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        }
        return networkInfo == null ? false : networkInfo.isConnected();
    }

    public static Boolean checkGPSStatus(Context context) {
        LocationManager locationManager = null;
        boolean gps_enabled = false;
        boolean network_enabled = false;
        if (locationManager == null) {
            locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        }
        try {
            gps_enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
            return false;
        }
        try {
            network_enabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
        }
        if (!gps_enabled && !network_enabled) {
            return false;
        } else return true;
    }

    public static Boolean checkBluetoohStatus(Context context) {
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            return false;
        } else {
            return mBluetoothAdapter.isEnabled();

        }
    }

    public static Boolean checkMobileDataStatus(Context context) {
        boolean mobileDataEnabled = false;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        Class cmClass = null;
        try {
            cmClass = Class.forName(cm.getClass().getName());
            Method method = cmClass.getDeclaredMethod("getMobileDataEnabled");
            method.setAccessible(true);
            mobileDataEnabled = (Boolean) method.invoke(cm);
        } catch (ClassNotFoundException e) {
        } catch (InvocationTargetException e) {
        } catch (NoSuchMethodException e) {
        } catch (IllegalAccessException e) {
        }
        if (mobileDataEnabled) return true;
        else {
            return false;
        }
    }

    public static void setting(Context context, String cual) {
        Intent intent;
        if (cual.equalsIgnoreCase("gps")) {
            intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            context.startActivity(intent);
        } else if (cual.equalsIgnoreCase("bluetooh")) {
            intent = new Intent(Settings.ACTION_BLUETOOTH_SETTINGS);
            context.startActivity(intent);
        } else if (cual.equalsIgnoreCase("wifi")) {
            intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
            context.startActivity(intent);
        } else if (cual.equalsIgnoreCase("mobile")) {
            intent = new Intent();
            intent.setClassName("com.android.settings",
                    "com.android.settings.Settings$DataUsageSummaryActivity");
            context.startActivity(intent);
        } else {
            intent = new Intent(Settings.ACTION_SETTINGS);
            context.startActivity(intent);
        }

    }
}
