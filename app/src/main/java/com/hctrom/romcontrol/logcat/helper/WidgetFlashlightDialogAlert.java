package com.hctrom.romcontrol.logcat.helper;

/**
 * Created by Ivan on 23/09/2016.
 */


import android.hardware.Camera;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hctrom.romcontrol.R;
import com.hctrom.romcontrol.ThemeSelectorUtility;


public class WidgetFlashlightDialogAlert extends AppCompatActivity {
    private Chronometer simpleChronometer;
    private String temp, minutes;
    private TextView text_temp;
    private CheckBox checkDialogFlash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemeSelectorUtility theme = new ThemeSelectorUtility(this);
        theme.onActivityCreateSetTheme(this);
        setContentView(R.layout.widget_flashlight);
        getWindow().setStatusBarColor(getResources().getColor(R.color.statusbar_widget));
        simpleChronometer = (Chronometer) findViewById(R.id.simpleChronometer);
        text_temp = (TextView) findViewById(R.id.text_security_flash) ;
        checkDialogFlash = (CheckBox) findViewById(R.id.checkBoxFlash);
        checkDialogFlash.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (checkDialogFlash.isChecked()){
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putInt("aviso_temp_flash", 1).apply();
                    WidgetFlashlightReceiver.isLightOn = false;
                }else{
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putInt("aviso_temp_flash", 0).apply();
                }
            }
        });
        final String spinnerTemp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("temp_flash", "01:00");
        simpleChronometer.start();
        simpleChronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {

            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if( chronometer.getText().toString().equals(spinnerTemp)) {
                    if (spinnerTemp == "01:00"){
                        Toast.makeText(getApplicationContext(), "¡¡ Linterna Desactivada !!\n" + spinnerTemp + " minuto transcurrido", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "¡¡ Linterna Desactivada !!\n" + spinnerTemp + " minutos transcurridos", Toast.LENGTH_SHORT).show();
                    }
                    Camera cam = Camera.open();
                    Camera.Parameters p = cam.getParameters();
                    p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                    cam.setParameters(p);
                    cam.stopPreview();
                    cam.release();
                    finish();
                }
            }
        });
        Button btOff = (Button) findViewById(R.id.off_flash);
        btOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Camera cam = Camera.open();
                Camera.Parameters p = cam.getParameters();
                p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                cam.setParameters(p);
                cam.stopPreview();
                cam.release();
                finish();
                Toast.makeText(getApplicationContext(), "¡¡ Linterna Desactivada !!", Toast.LENGTH_SHORT).show();
            }
        });

        Spinner tempFlashSpinner = (Spinner) findViewById(R.id.temp_flash);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, getResources().getStringArray(R.array.tempFlash));
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        tempFlashSpinner.setAdapter(spinnerArrayAdapter);
        String t = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("temp_flash", "01:00");
        if (t == "01:00"){
            tempFlashSpinner.setSelection(1);
        }
        if (t == "02:00"){
            tempFlashSpinner.setSelection(2);
        }
        if (t == "03:00"){
            tempFlashSpinner.setSelection(3);
        }
        if (t == "05:00"){
            tempFlashSpinner.setSelection(4);
        }
        if (t == "08:00"){
            tempFlashSpinner.setSelection(5);
        }
        if (t == "10:00"){
            tempFlashSpinner.setSelection(6);
        }
        // Sets up the Spinner
        tempFlashSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if ( i == 0){
                    temp = "01:00";
                    minutes = "1 minuto";
                }
                if ( i == 1){
                    temp = "02:00";
                    minutes = "2 minutos";
                }
                if ( i == 2){
                    temp = "03:00";
                    minutes = "3 minutos";
                }
                if ( i == 3){
                    temp = "05:00";
                    minutes = "5 minutos";
                }
                if ( i == 4){
                    temp = "08:00";
                    minutes = "8 minutos";
                }
                if ( i == 5){
                    temp = "10:00";
                    minutes = "10 minutos";
                }
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("temp_flash", temp).apply();
                text_temp.setText("Por seguridad, la Linterna se apagará en " + minutes + " automáticamente.");
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });
    }

    @Override
    public void onBackPressed() {
        Camera cam = Camera.open();
        Camera.Parameters p = cam.getParameters();
        p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        cam.setParameters(p);
        cam.stopPreview();
        cam.release();
        finish();
        Toast.makeText(getApplicationContext(), "¡¡ Linterna Desactivada !!", Toast.LENGTH_SHORT).show();
    }
}
