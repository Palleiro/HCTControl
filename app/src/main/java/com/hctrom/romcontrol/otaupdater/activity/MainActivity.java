package com.hctrom.romcontrol.otaupdater.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.hctrom.romcontrol.R;
import com.hctrom.romcontrol.ThemeSelectorUtility;
import com.hctrom.romcontrol.otaupdater.fragment.GithubReleasesFragment;
import com.hctrom.romcontrol.otaupdater.util.Config;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private GithubReleasesFragment mFragmentOldRelease;
    private Button mCheckUpdate;
    private CheckBox checkAutoUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemeSelectorUtility theme = new ThemeSelectorUtility(this);
        theme.onActivityCreateSetTheme(this);
        setContentView(R.layout.ota_activity_main);
        TypedValue typedValue = new TypedValue();
        Resources.Theme theme1 = getTheme();
        theme1.resolveAttribute(R.attr.colorPrimaryDark, typedValue, true);
        int color = typedValue.data;
        getWindow().setStatusBarColor(color);

        // Initializing Toolbar and setting it as the actionbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("OTA");
        }
        this.mFragmentOldRelease = new GithubReleasesFragment().setTargetURL(Config.URL_OLD_RELEASES());
        updateFragment(mFragmentOldRelease);
        checkAutoUpdate = (CheckBox) findViewById(R.id.checkBoxAutoCheck);
        Boolean checkAuto = PreferenceManager.getDefaultSharedPreferences(this).getBoolean("ota_autocheck", true);
        if (checkAuto == false) {
            checkAutoUpdate.setChecked(false);
        }else{
            checkAutoUpdate.setChecked(true);
        }

        checkAutoUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkAutoUpdate.isChecked()){
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putBoolean("ota_autocheck", true).commit();
                }else{
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putBoolean("ota_autocheck", false).commit();
                }
            }
        });

        mCheckUpdate=(Button)findViewById(R.id.activity_main_check_for_update);
        mCheckUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), DialogActivity.class);
                startActivity(i);
            }
        });
    }
    protected void updateFragment(Fragment fragment)
    {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        ft.replace(R.id.content_frame, fragment);
        ft.commit();
    }

    @Override
    public boolean onKeyUp( int keyCode, KeyEvent event )
    {
        if( keyCode == KeyEvent.KEYCODE_BACK ) {
            onBackPressed();
            return true;
        }else {
            return super.onKeyUp(keyCode, event);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

