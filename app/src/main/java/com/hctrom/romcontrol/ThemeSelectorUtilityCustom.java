package com.hctrom.romcontrol;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.hctrom.romcontrol.prefs.CircleView;

/**
 * Created by Palleiro on 13/12/2015.
 */
public class ThemeSelectorUtilityCustom extends DialogFragment {
    private CircleView cTomato, cTangerine, cBanana, cBasil, cSage, cPeacock, cBlueberry, cLavender, cGrape, cFlamingo, cGraphite;
    private ImageView mTomato, mTangerine, mBanana, mBasil, mSage, mPeacock, mBlueberry, mLavender, mGrape, mFlamingo, mGraphite;
    private RadioButton radioDark, radioLight;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ThemeSelectorUtility theme = new ThemeSelectorUtility(getActivity());
        theme.onActivityCreateSetTheme(getActivity());
        final View view = inflater.inflate(R.layout.theme_selector_utility, container, false);
        cTomato = (CircleView) view.findViewById(R.id.colorTomato);
        cTangerine = (CircleView) view.findViewById(R.id.colorTangerine);
        cBanana = (CircleView) view.findViewById(R.id.colorBanana);
        cBasil = (CircleView) view.findViewById(R.id.colorBasil);
        cSage = (CircleView) view.findViewById(R.id.colorSage);
        cPeacock = (CircleView) view.findViewById(R.id.colorPeacock);
        cBlueberry = (CircleView) view.findViewById(R.id.colorBlueberry);
        cLavender = (CircleView) view.findViewById(R.id.colorLavender);
        cGrape = (CircleView) view.findViewById(R.id.colorGrape);
        cFlamingo = (CircleView) view.findViewById(R.id.colorFlamingo);
        cGraphite = (CircleView) view.findViewById(R.id.colorGraphite);

        mTomato = (ImageView) view.findViewById(R.id.checkTomato);
        mTangerine = (ImageView) view.findViewById(R.id.checkTangerine);
        mBanana = (ImageView) view.findViewById(R.id.checkBanana);
        mBasil = (ImageView) view.findViewById(R.id.checkBasil);
        mSage = (ImageView) view.findViewById(R.id.checkSage);
        mPeacock = (ImageView) view.findViewById(R.id.checkPeacock);
        mBlueberry = (ImageView) view.findViewById(R.id.checkBlueberry);
        mLavender = (ImageView) view.findViewById(R.id.checkLavender);
        mGrape = (ImageView) view.findViewById(R.id.checkGrape);
        mFlamingo = (ImageView) view.findViewById(R.id.checkFlamingo);
        mGraphite = (ImageView) view.findViewById(R.id.checkGraphite);

        mTomato.setVisibility(View.GONE);
        mTangerine.setVisibility(View.GONE);
        mBanana.setVisibility(View.GONE);
        mBasil.setVisibility(View.GONE);
        mSage.setVisibility(View.GONE);
        mPeacock.setVisibility(View.GONE);
        mBlueberry.setVisibility(View.GONE);
        mLavender.setVisibility(View.GONE);
        mGrape.setVisibility(View.GONE);
        mFlamingo.setVisibility(View.GONE);
        mGraphite.setVisibility(View.GONE);

        int n = PreferenceManager.getDefaultSharedPreferences(getActivity()).getInt("theme_prefs", 0);
        switch (n){
            case 5:
                mTomato.setVisibility(View.VISIBLE);
                break;
            case 6:
                mTomato.setVisibility(View.VISIBLE);
                break;
            case 7:
                mTangerine.setVisibility(View.VISIBLE);
                break;
            case 8:
                mTangerine.setVisibility(View.VISIBLE);
                break;
            case 9:
                mBanana.setVisibility(View.VISIBLE);
                break;
            case 10:
                mBanana.setVisibility(View.VISIBLE);
                break;
            case 11:
                mBasil.setVisibility(View.VISIBLE);
                break;
            case 12:
                mBasil.setVisibility(View.VISIBLE);
                break;
            case 13:
                mSage.setVisibility(View.VISIBLE);
                break;
            case 14:
                mSage.setVisibility(View.VISIBLE);
                break;
            case 15:
                mPeacock.setVisibility(View.VISIBLE);
                break;
            case 16:
                mPeacock.setVisibility(View.VISIBLE);
                break;
            case 17:
                mBlueberry.setVisibility(View.VISIBLE);
                break;
            case 18:
                mBlueberry.setVisibility(View.VISIBLE);
                break;
            case 19:
                mLavender.setVisibility(View.VISIBLE);
                break;
            case 20:
                mLavender.setVisibility(View.VISIBLE);
                break;
            case 21:
                mGrape.setVisibility(View.VISIBLE);
                break;
            case 22:
                mGrape.setVisibility(View.VISIBLE);
                break;
            case 23:
                mFlamingo.setVisibility(View.VISIBLE);
                break;
            case 24:
                mFlamingo.setVisibility(View.VISIBLE);
                break;
            case 25:
                mGraphite.setVisibility(View.VISIBLE);
                break;
            case 26:
                mGraphite.setVisibility(View.VISIBLE);
                break;
        }

        radioDark = (RadioButton) view.findViewById(R.id.dark);
        radioLight = (RadioButton) view.findViewById(R.id.light);

        TypedValue typedValue1 = new TypedValue();
        Resources.Theme theme1 = getActivity().getTheme();
        theme1.resolveAttribute(R.attr.colorAccent, typedValue1, true);
        TypedValue typedValue2 = new TypedValue();
        Resources.Theme theme2 = getActivity().getTheme();
        theme2.resolveAttribute(R.attr.colorPrimary, typedValue2, true);
        if (radioDark.isChecked()) {
            radioDark.setHighlightColor(typedValue1.data);
        }else{
            radioDark.setHighlightColor(typedValue2.data);
        }

        cTomato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioDark.isChecked()) {
                    PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putInt("theme_prefs", 5).commit();
                }else{
                    PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putInt("theme_prefs", 6).commit();
                }
                getActivity().finish();
                getActivity().overridePendingTransition(0, R.animator.fadeout);
                startActivity(new Intent(getActivity(), MainViewActivity.class));
                getActivity().overridePendingTransition(R.animator.fadein, 0);
            }
        });
        cTangerine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioDark.isChecked()) {
                    PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putInt("theme_prefs", 7).commit();
                }else{
                    PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putInt("theme_prefs", 8).commit();
                }
                getActivity().finish();
                getActivity().overridePendingTransition(0, R.animator.fadeout);
                startActivity(new Intent(getActivity(), MainViewActivity.class));
                getActivity().overridePendingTransition(R.animator.fadein, 0);
            }
        });
        cBanana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioDark.isChecked()) {
                    PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putInt("theme_prefs", 9).commit();
                }else{
                    PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putInt("theme_prefs", 10).commit();
                }
                getActivity().finish();
                getActivity().overridePendingTransition(0, R.animator.fadeout);
                startActivity(new Intent(getActivity(), MainViewActivity.class));
                getActivity().overridePendingTransition(R.animator.fadein, 0);
            }
        });
        cBasil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioDark.isChecked()) {
                    PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putInt("theme_prefs", 11).commit();
                }else{
                    PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putInt("theme_prefs", 12).commit();
                }
                getActivity().finish();
                getActivity().overridePendingTransition(0, R.animator.fadeout);
                startActivity(new Intent(getActivity(), MainViewActivity.class));
                getActivity().overridePendingTransition(R.animator.fadein, 0);
            }
        });
        cSage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioDark.isChecked()) {
                    PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putInt("theme_prefs", 13).commit();
                }else{
                    PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putInt("theme_prefs", 14).commit();
                }
                getActivity().finish();
                getActivity().overridePendingTransition(0, R.animator.fadeout);
                startActivity(new Intent(getActivity(), MainViewActivity.class));
                getActivity().overridePendingTransition(R.animator.fadein, 0);
            }
        });
        cPeacock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioDark.isChecked()) {
                    PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putInt("theme_prefs", 15).commit();
                }else{
                    PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putInt("theme_prefs", 16).commit();
                }
                getActivity().finish();
                getActivity().overridePendingTransition(0, R.animator.fadeout);
                startActivity(new Intent(getActivity(), MainViewActivity.class));
                getActivity().overridePendingTransition(R.animator.fadein, 0);
            }
        });
        cBlueberry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioDark.isChecked()) {
                    PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putInt("theme_prefs", 17).commit();
                }else{
                    PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putInt("theme_prefs", 18).commit();
                }
                getActivity().finish();
                getActivity().overridePendingTransition(0, R.animator.fadeout);
                startActivity(new Intent(getActivity(), MainViewActivity.class));
                getActivity().overridePendingTransition(R.animator.fadein, 0);
            }
        });
        cLavender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioDark.isChecked()) {
                    PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putInt("theme_prefs", 19).commit();
                }else{
                    PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putInt("theme_prefs", 20).commit();
                }
                getActivity().finish();
                getActivity().overridePendingTransition(0, R.animator.fadeout);
                startActivity(new Intent(getActivity(), MainViewActivity.class));
                getActivity().overridePendingTransition(R.animator.fadein, 0);
            }
        });
        cGrape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioDark.isChecked()) {
                    PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putInt("theme_prefs", 21).commit();
                }else{
                    PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putInt("theme_prefs", 22).commit();
                }
                getActivity().finish();
                getActivity().overridePendingTransition(0, R.animator.fadeout);
                startActivity(new Intent(getActivity(), MainViewActivity.class));
                getActivity().overridePendingTransition(R.animator.fadein, 0);
            }
        });
        cFlamingo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioDark.isChecked()) {
                    PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putInt("theme_prefs", 23).commit();
                }else{
                    PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putInt("theme_prefs", 24).commit();
                }
                getActivity().finish();
                getActivity().overridePendingTransition(0, R.animator.fadeout);
                startActivity(new Intent(getActivity(), MainViewActivity.class));
                getActivity().overridePendingTransition(R.animator.fadein, 0);
            }
        });
        cGraphite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioDark.isChecked()) {
                    PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putInt("theme_prefs", 25).commit();
                }else{
                    PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putInt("theme_prefs", 26).commit();
                }
                getActivity().finish();
                getActivity().overridePendingTransition(0, R.animator.fadeout);
                startActivity(new Intent(getActivity(), MainViewActivity.class));
                getActivity().overridePendingTransition(R.animator.fadein, 0);
            }
        });

        return view;
    }
}
