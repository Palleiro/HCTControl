package com.hctrom.romcontrol.prefs;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.TypedArray;
import android.preference.Preference;
import android.preference.SwitchPreference;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;

import com.hctrom.romcontrol.R;
import com.hctrom.romcontrol.alertas.DialogoAlertaReiniciar;
import com.hctrom.romcontrol.alertas.DialogoAlertaReiniciarSystemUI;

/*      Created by Roberto Mariani and Anna Berkovitch, 13/06/2016
        This program is free software: you can redistribute it and/or modify
        it under the terms of the GNU General Public License as published by
        the Free Software Foundation, either version 3 of the License, or
        (at your option) any later version.

        This program is distributed in the hope that it will be useful,
        but WITHOUT ANY WARRANTY; without even the implied warranty of
        MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
        GNU General Public License for more details.

        You should have received a copy of the GNU General Public License
        along with this program.  If not, see <http://www.gnu.org/licenses/>.*/

public class MySwitchPreference extends SwitchPreference implements Preference.OnPreferenceChangeListener {
    private ContentResolver mContentResolver;
    private String mPackageToKill;
    private boolean mIsRestartSystemUI, mIsRebootRequired;
    private static FragmentActivity myContext;

    public MySwitchPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Preference);
        mPackageToKill = typedArray.getString(R.styleable.Preference_packageNameToKill);
        mIsRestartSystemUI = typedArray.getBoolean(R.styleable.Preference_restartSystemUI, true);
        mIsRebootRequired = typedArray.getBoolean(R.styleable.Preference_rebootDevice, false);
        typedArray.recycle();
        mContentResolver = context.getContentResolver();
        setOnPreferenceChangeListener(this);
    }

    @Override
    protected void onSetInitialValue(boolean restoreValue, Object defaultValue) {
        int dbInt = 0;
        try {
            dbInt = Settings.System.getInt(mContentResolver, getKey());
        } catch (Settings.SettingNotFoundException e) {
            if (defaultValue != null) {
                dbInt = (boolean) defaultValue ? 1 : 0;
                Settings.System.putInt(mContentResolver, getKey(), dbInt);
            }
        }
        persistBoolean(dbInt != 0);
        setChecked(dbInt != 0);

    }



    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        boolean isTrue = (boolean) newValue;
        int dbInt = isTrue ? 1 : 0;
        Settings.System.putInt(mContentResolver, getKey(), dbInt);
        if (mIsRestartSystemUI) {
            if (mIsRebootRequired) {
                // no hacer nada
            } else{
                //Utils.showRebootRequiredDialog(getContext());
                myContext = (FragmentActivity) getContext();
                final DialogoAlertaReiniciarSystemUI dialogo = new DialogoAlertaReiniciarSystemUI();
                dialogo.show(myContext.getSupportFragmentManager(), "tagAlerta");
            }
        }
        if (mIsRebootRequired) {
            //Utils.showRebootRequiredDialog(getContext());
            myContext = (FragmentActivity) getContext();
            final DialogoAlertaReiniciar dialogo = new DialogoAlertaReiniciar();
            dialogo.show(myContext.getSupportFragmentManager(), "tagAlerta");
        } else {
            if (mPackageToKill != null) {
                if(Utils.isPackageInstalled(mPackageToKill)) {
                        Utils.showKillPackageDialog(mPackageToKill, getContext());
                }
            }
        }

        return true;
    }

}
