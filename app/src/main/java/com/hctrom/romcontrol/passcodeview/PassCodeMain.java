package com.hctrom.romcontrol.passcodeview;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.widget.TextView;
import android.widget.Toast;

import com.hctrom.romcontrol.R;
import com.hctrom.romcontrol.ThemeSelectorUtility;

public class PassCodeMain extends AppCompatActivity {
    private PassCodeView passCodeView;
    private String PASSCODE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemeSelectorUtility theme = new ThemeSelectorUtility(this);
        theme.onActivityCreateSetTheme(this);
        TypedValue typedValue = new TypedValue();
        Resources.Theme theme1 = getTheme();
        theme1.resolveAttribute(R.attr.colorPrimaryDark, typedValue, true);
        int color = typedValue.data;
        getWindow().setStatusBarColor(color);
		
        PASSCODE = PreferenceManager.getDefaultSharedPreferences(this).getString("pass_control", "");
        setContentView(R.layout.passcodeview_main);
        TypedValue typedValue2 = new TypedValue();
        Resources.Theme theme2 = getTheme();
        theme2.resolveAttribute(R.attr.colorAccent, typedValue2, true);
        int color2 = typedValue2.data;
        passCodeView = (PassCodeView) findViewById(R.id.pass_code_view);
        TextView promptView = (TextView) findViewById(R.id.promptview);
        passCodeView.setKeyTextColor(color2);
        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/BornaSinner.ttf");
        passCodeView.setTypeFace(typeFace);
        promptView.setTypeface(typeFace);
        PASSCODE = PreferenceManager.getDefaultSharedPreferences(this).getString("pass_control", "");
        if (PASSCODE.equals("")){
            promptView.setTextSize(15);
            promptView.setText("Introduce un código de 4 dígitos como\n\nPIN de seguridad para acceder a HCTControl");
            confirmPass();
        } else{
            promptView.setTextSize(18);
            promptView.setText("Introduce tu código");
            bindEvents();
        }

    }

    private void confirmPass() {
        passCodeView.setOnTextChangeListener(new PassCodeView.TextChangeListener() {
            @Override
            public void onTextChanged(String text) {
                if (text.length() == 4) {
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("pass_control", "" + text).commit();
                    Toast.makeText(getBaseContext(), "¡¡PIN de seguridad\nguardado con éxito!!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(PassCodeMain.this, PassCodeMain.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void bindEvents() {
        passCodeView.setOnTextChangeListener(new PassCodeView.TextChangeListener() {
            @Override
            public void onTextChanged(String text) {
                if (text.length() == 4) {
                    if (text.equals(PASSCODE)) {
                        //Intent intent = new Intent(PassCodeMain.this, MainViewActivity.class);
                        //startActivity(intent);
                        //finish();
                        // Reiniciar aplicación
                        Intent i = getBaseContext().getPackageManager()
                                .getLaunchIntentForPackage( getBaseContext().getPackageName() );
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                    } else {
                        passCodeView.setError(true);
                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}
