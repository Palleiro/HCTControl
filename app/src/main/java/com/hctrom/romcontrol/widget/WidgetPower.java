package com.hctrom.romcontrol.widget;

/**
 * Created by Palleiro on 18/04/2016.
 */

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hctrom.romcontrol.R;
import com.hctrom.romcontrol.ThemeSelectorUtility;
import com.hctrom.romcontrol.alertas.DialogosMenuReiniciar;
import com.hctrom.romcontrol.blurry.Blurry;
import com.software.shell.fab.ActionButton;

public class WidgetPower extends AppCompatActivity implements  View.OnClickListener {
    int[] ids;
    int[] ids_text;
    ActionButton[] rebootFabs;
    TextView[] rebootFabs_text;
    ActionButton reboot, hotboot, recovery, bl, ui, lch, incall;
    TextView text_reboot, text_hotboot, text_recovery, text_bl, text_ui, text_lch, text_incall;
    View overlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemeSelectorUtility theme = new ThemeSelectorUtility(this);
        theme.onActivityCreateSetTheme(this);
        setContentView(R.layout.power_menu);
        getWindow().setStatusBarColor(getResources().getColor(R.color.statusbar_widget));
        initRebootMenu();
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

    /**
     * Comprueba si hay conexión a internet.
     * @return boolean
     */
    private boolean existeConexionInternet() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

    /*Handling onClick event for the Reboot Menu (round Action Buttons array)
    * For now we handle them under su, later on, since app is intended to be a system app,
    * we will add PowerManager for items: Reboot, Reboot recovery and Reboot Download*/
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            /*Handles the onClick event for the semi transparent white overlay
            * Once clicked, we consider it a click outside the Reboot Menu and it invokes methos hideRebootMenu()*/
            case R.id.overlay:
                hideRebootMenu();
                break;
            case R.id.action_reboot:
                DialogosMenuReiniciar.reiniciar(v);
                break;
            case R.id.action_reboot_hotboot:
                DialogosMenuReiniciar.reiniciarRapido(v);
                break;
            case R.id.action_reboot_recovery:
                DialogosMenuReiniciar.reiniciarRecovery(v);
                break;
            case R.id.action_reboot_bl:
                DialogosMenuReiniciar.reiniciarDownload(v);
                break;
            case R.id.action_reboot_systemUI:
                DialogosMenuReiniciar.reiniciarSystemUI(v);
                break;
            case R.id.action_reboot_launcher:
                DialogosMenuReiniciar.reiniciarLauncher(v);
                break;
            case R.id.action_reboot_incallui:
                DialogosMenuReiniciar.reiniciarInCallUI(v);
                break;
        }
    }

    //Initializes the reboot menu as arrray of views, finds by id and sets animations and onClickListener to each in a loop
    private void initRebootMenu() {
        ids_text = new int[]{R.id.text_action_reboot, R.id.text_action_reboot_hotboot, R.id.text_action_reboot_recovery, R.id.text_action_reboot_bl, R.id.text_action_reboot_systemUI, R.id.text_action_reboot_launcher, R.id.text_action_reboot_incallui};
        ids = new int[]{R.id.action_reboot, R.id.action_reboot_hotboot, R.id.action_reboot_recovery, R.id.action_reboot_bl, R.id.action_reboot_systemUI, R.id.action_reboot_launcher, R.id.action_reboot_incallui};
        rebootFabs_text = new TextView[]{text_reboot, text_hotboot, text_recovery, text_bl, text_ui, text_lch, text_incall};
        rebootFabs = new ActionButton[]{reboot, hotboot, recovery, bl, ui, lch, incall};
        overlay = findViewById(R.id.overlay);
        overlay.setVisibility(View.VISIBLE);
        int l = ids.length;
        for (int i = 0; i < l; i++) {
            rebootFabs[i] = (ActionButton) findViewById(ids[i]);
            rebootFabs[i].show();
            rebootFabs[i].setHideAnimation(ActionButton.Animations.ROLL_TO_RIGHT);
            rebootFabs[i].setShowAnimation(ActionButton.Animations.ROLL_FROM_RIGHT);
            rebootFabs_text[i] = (TextView) findViewById(ids_text[i]);
            rebootFabs_text[i].setVisibility(View.VISIBLE);
            GradientDrawable gd = new GradientDrawable();
            gd.setColor(getResources().getColor(R.color.button_material_dark)); // Changes this drawbale to use a single color instead of a gradient
            gd.setCornerRadius(20);
            gd.setStroke(1, 0xFF000000);
            rebootFabs_text[i].setBackgroundDrawable(gd);
        }
        Blurry.with(WidgetPower.this)
                .radius(25)
                .sampling(2)
                .async()
                .animate(200)
                .onto((ViewGroup) findViewById(R.id.content));
    }

    //Show/Hide reboot menu with animation depending on the view's visibility
    public void hideRebootMenu() {

        for (int i = 0; i < rebootFabs.length; i++) {
            overlay.setVisibility(View.GONE);
            rebootFabs[i].hide();
            rebootFabs_text[i].setVisibility(View.GONE);
            Blurry.delete((ViewGroup) findViewById(R.id.content));
            finish();
        }
    }
}