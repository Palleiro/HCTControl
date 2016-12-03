package com.hctrom.romcontrol;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;


public class HCTControlConfig extends PreferenceFragment implements Preference.OnPreferenceClickListener {
    HandlePreferenceFragments hpf;
    private SwitchPreference switchPreference_menuflash_widget, switchPreference_passcontrol, switchPreference_avisobackup, switchPreference_menushow, switchPreference_menuactivate;
    private PreferenceScreen preferenceScreen_passreset;
    private String pass;
    private int backup, flashWidget;
    private Animation fab_open,fab_close;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //addPreferencesFromResource(R.xml.acerca_de_prefs);
        hpf = new HandlePreferenceFragments(getActivity(), this, "hctcontrol_config");

        switchPreference_passcontrol = (SwitchPreference) findPreference("pass_check");
        switchPreference_avisobackup = (SwitchPreference) findPreference("show_backup");
        switchPreference_menushow = (SwitchPreference) findPreference("menu_show");
        switchPreference_menuactivate = (SwitchPreference) findPreference("menu_activate");
        preferenceScreen_passreset = (PreferenceScreen) findPreference("pass_reset");
        switchPreference_menuflash_widget = (SwitchPreference) findPreference("menu_flash_widget");
    }

    @Override
    public void onResume() {
        super.onResume();
        hpf.onResumeFragment();
        flashWidget = PreferenceManager.getDefaultSharedPreferences(getActivity()).getInt("aviso_temp_flash", 0);
        if (flashWidget == 1){
            switchPreference_menuflash_widget.setChecked(true);
        } else{
            switchPreference_menuflash_widget.setChecked(false);
        }

        pass = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("pass_activate", "off");
        if (pass.equals("on")){
            switchPreference_passcontrol.setChecked(true);
        } else{
            switchPreference_passcontrol.setChecked(false);
        }

        backup = PreferenceManager.getDefaultSharedPreferences(getActivity()).getInt("aviso_backup", 0);
        if (backup == 0){
            switchPreference_avisobackup.setChecked(true);
        }else{
            switchPreference_avisobackup.setChecked(false);
        }

        SharedPreferences prefs = getActivity().getSharedPreferences("ConfigMenuFlotante", Context.MODE_PRIVATE);
        if(prefs.getBoolean("floating_button_vista", true)){
            switchPreference_menushow.setChecked(true);
        }else {
            switchPreference_menushow.setChecked(false);
        }

        if(prefs.getBoolean("floating_button_activador", true)){
            switchPreference_menuactivate.setChecked(true);
        }else {
            switchPreference_menuactivate.setChecked(false);
        }

        switchPreference_passcontrol.setOnPreferenceClickListener(this);
        switchPreference_avisobackup.setOnPreferenceClickListener(this);
        switchPreference_menushow.setOnPreferenceClickListener(this);
        switchPreference_menuactivate.setOnPreferenceClickListener(this);
        preferenceScreen_passreset.setOnPreferenceClickListener(this);
        switchPreference_menuflash_widget.setOnPreferenceClickListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        hpf.onPauseFragment();
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        SharedPreferences.Editor editor;
        SharedPreferences prefs = getActivity().getSharedPreferences("ConfigMenuFlotante", Context.MODE_PRIVATE);
        if(preference == switchPreference_menuflash_widget) {
            if (switchPreference_menuflash_widget.isChecked()){
                PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()).edit().putInt("aviso_temp_flash", 1).commit();
            }else{
                PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()).edit().putInt("aviso_temp_flash", 0).commit();
            }
        }

        if(preference == switchPreference_passcontrol) {
            if (switchPreference_passcontrol.isChecked()){
                PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()).edit().putString("pass_activate", "on").commit();
            }else{
                PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()).edit().putString("pass_activate", "off").commit();
            }
        }

        if(preference == switchPreference_avisobackup) {
            if (switchPreference_avisobackup.isChecked()){
                PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()).edit().putInt("aviso_backup", 0).commit();
            }else{
                PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()).edit().putInt("aviso_backup", 1).commit();
            }
        }

        if(preference == switchPreference_menushow) {
            if (switchPreference_menushow.isChecked()){
                Toast.makeText(getActivity(), "Menú flotante visible", Toast.LENGTH_LONG).show();
                editor = prefs.edit();
                editor.putBoolean("floating_button_vista",true);
                editor.commit();
                fab_open = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fab_open);
                MainViewActivity.menu.startAnimation(fab_open);
                MainViewActivity.menu.show();
            }else{
                fab_close = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fab_close);
                MainViewActivity.menu.startAnimation(fab_close);
                Toast.makeText(getActivity(), "Menú flotante oculto", Toast.LENGTH_LONG).show();
                editor = prefs.edit();
                editor.putBoolean("floating_button_vista",false);
                editor.commit();
                MainViewActivity.menu.hide();
            }
        }

        if(preference == switchPreference_menuactivate) {
            if (switchPreference_menuactivate.isChecked()){
                Toast.makeText(getActivity(), "Menú flotante activado", Toast.LENGTH_LONG).show();
                editor = prefs.edit();
                editor.putBoolean("floating_button_activador",true);
                editor.commit();
                MainViewActivity.menu.setEnabled(true);
            }else{
                Toast.makeText(getActivity(), "Menú flotante desactivado", Toast.LENGTH_LONG).show();
                editor = prefs.edit();
                editor.putBoolean("floating_button_activador",false);
                editor.commit();
                MainViewActivity.menu.setEnabled(false);
            }
        }

        if (preference == preferenceScreen_passreset) {
            PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()).edit().putString("pass_control", "").commit();
        }

        return false;
    }

}
