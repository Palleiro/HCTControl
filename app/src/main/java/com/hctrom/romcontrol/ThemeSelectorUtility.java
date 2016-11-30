package com.hctrom.romcontrol;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;

/*      Created by Roberto Mariani and Anna Berkovitch, 08/06/15
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
public class ThemeSelectorUtility{

    Context c;

    public ThemeSelectorUtility(Context context){
        this.c = context;
    }



    /**
     * Set the theme of the activity, according to the configuration.
     */
    public  void onActivityCreateSetTheme(Activity activity) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(c);
        int themeId = prefs.getInt("theme_prefs", 0);

        switch (themeId) {
            default:
            case 0:
                activity.setTheme(R.style.AppThemeHCT);
                break;
            case 1:
                activity.setTheme(R.style.AppThemeDark);
                break;
            case 2:
                activity.setTheme(R.style.AppThemeLight);
                break;
            case 3:
                activity.setTheme(R.style.AppThemeSamsungLight);
                break;
            case 4:

                break;
            case 5:
                activity.setTheme(R.style.AppThemeTomato);
                break;
            case 6:
                activity.setTheme(R.style.AppThemeTomatoLight);
                break;
            case 7:
                activity.setTheme(R.style.AppThemeTangerine);
                break;
            case 8:
                activity.setTheme(R.style.AppThemeTangerineLight);
                break;
            case 9:
                activity.setTheme(R.style.AppThemeBanana);
                break;
            case 10:
                activity.setTheme(R.style.AppThemeBananaLight);
                break;
            case 11:
                activity.setTheme(R.style.AppThemeBasil);
                break;
            case 12:
                activity.setTheme(R.style.AppThemeBasilLight);
                break;
            case 13:
                activity.setTheme(R.style.AppThemeSage);
                break;
            case 14:
                activity.setTheme(R.style.AppThemeSageLight);
                break;
            case 15:
                activity.setTheme(R.style.AppThemePeacock);
                break;
            case 16:
                activity.setTheme(R.style.AppThemePeacockLight);
                break;
            case 17:
                activity.setTheme(R.style.AppThemeBlueberry);
                break;
            case 18:
                activity.setTheme(R.style.AppThemeBlueberryLight);
                break;
            case 19:
                activity.setTheme(R.style.AppThemeLavender);
                break;
            case 20:
                activity.setTheme(R.style.AppThemeLavenderLight);
                break;
            case 21:
                activity.setTheme(R.style.AppThemeGrape);
                break;
            case 22:
                activity.setTheme(R.style.AppThemeGrapeLight);
                break;
            case 23:
                activity.setTheme(R.style.AppThemeFlamingo);
                break;
            case 24:
                activity.setTheme(R.style.AppThemeFlamingoLight);
                break;
            case 25:
                activity.setTheme(R.style.AppThemeGraphite);
                break;
            case 26:
                activity.setTheme(R.style.AppThemeGraphiteLight);
                break;

        }
    }

    public static void ThemeDrawableBG(AlertDialog dialog, Context context){
        int i = PreferenceManager.getDefaultSharedPreferences(context).getInt("theme_prefs", 0);
        if (i == 0) {
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_bg_hct);
        }else if (i == 3){
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_bg_samsung_light);
        }else if (i == 5) {
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_bg_tomato);
        }else if (i == 6) {
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_bg_tomato_light);
        }else if (i == 7) {
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_bg_tangerine);
        }else if (i == 8) {
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_bg_tangerine_light);
        }else if (i == 9) {
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_bg_banana);
        }else if (i == 10) {
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_bg_banana_light);
        }else if (i == 11) {
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_bg_basil);
        }else if (i == 12) {
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_bg_basil_light);
        }else if (i == 13) {
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_bg_sage);
        }else if (i == 14) {
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_bg_sage_light);
        }else if (i == 15) {
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_bg_peacock);
        }else if (i == 16) {
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_bg_peacock_light);
        }else if (i == 17) {
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_bg_blueberry);
        }else if (i == 18) {
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_bg_blueberry_light);
        }else if (i == 19) {
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_bg_lavender);
        }else if (i == 20) {
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_bg_lavender_light);
        }else if (i == 21) {
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_bg_grape);
        }else if (i == 22) {
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_bg_grape_light);
        }else if (i == 23) {
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_bg_flamingo);
        }else if (i == 24) {
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_bg_flamingo_light);
        }else if (i == 25) {
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_bg_graphite);
        }else if (i == 26) {
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_bg_graphite_light);
        }else {
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_bg_dark_light);
        }
    }
}
