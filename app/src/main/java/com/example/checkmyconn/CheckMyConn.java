package com.example.checkmyconn;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import static com.example.checkmyconn.utilidades.ConnUtilities.checkBluetoohStatus;
import static com.example.checkmyconn.utilidades.ConnUtilities.checkGPSStatus;
import static com.example.checkmyconn.utilidades.ConnUtilities.checkMobileDataStatus;
import static com.example.checkmyconn.utilidades.ConnUtilities.checkWifiStatus;
import static com.example.checkmyconn.utilidades.ConnUtilities.setting;


public class CheckMyConn extends Activity  {
    private TextView gps;
    private TextView bluetooh;
    private TextView wifi;
    private TextView mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_my_conn);
        StringBuilder sb = new StringBuilder();

        gps = (TextView) findViewById(R.id.gps);
        bluetooh = (TextView) findViewById(R.id.bluetooh);
        wifi = (TextView) findViewById(R.id.wifi);
        mobile = (TextView) findViewById(R.id.mobile);

        chequear();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_check_my_conn, menu);
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

    public void chequear() {
        if (checkWifiStatus(this)) {
            wifi.setText("WIFI activo\n");
            wifi.setTextAppearance(this,R.style.verde);
        } else {
            wifi.setText("WIFI inactivo\n");
            wifi.setTextAppearance(this,R.style.rojo);
        }

        if (checkGPSStatus(this)) {
            gps.setText("GPS activo\n");
            gps.setTextAppearance(this,R.style.verde);
        } else {
            gps.setText("GPS inactivo\n");
            gps.setTextAppearance(this,R.style.rojo);
        }

        if (checkBluetoohStatus(this)) {
            bluetooh.setText("BLUETOOH  activo\n");
            bluetooh.setTextAppearance(this,R.style.verde);
        } else {
            bluetooh.setText("BLUETOOH inactivo\n");
            bluetooh.setTextAppearance(this,R.style.rojo);
        }

        if (checkMobileDataStatus(this)) {
            mobile.setText("DATOS MOVILES  activo\n");
            mobile.setTextAppearance(this,R.style.verde);
        } else {
            mobile.setText("DATOS MOVILES inactivo\n");
            mobile.setTextAppearance(this,R.style.rojo);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        chequear();
    }

    public void accion(View v) {
       switch (v.getId()) {
           case R.id.confGPS : setting(this,"gps");
               break;
           case R.id.confBluetooh : setting(this,"bluetooh");
               break;
           case R.id.confWiFi : setting(this,"wifi");
               break;
           case R.id.confMobile : setting(this,"mobile");
               break;
           case R.id.chequear : chequear();
               break;
       }
    }
}
