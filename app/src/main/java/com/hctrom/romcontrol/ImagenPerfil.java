package com.hctrom.romcontrol;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hctrom.romcontrol.kenburnsview.KenBurnsView;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class ImagenPerfil extends AppCompatActivity implements View.OnClickListener {
    private Button btnDeletePerfil, btnDeleteBackground, btnAplicar;
    private CheckBox checkBoxImage, checkBoxImageBackground, checkBoxImageBackgroundMv;
    private Toolbar toolbar;
    private CircleImageView imgvw;
    private KenBurnsView imgBackground;
    private TextView textAplicar;
    private EditText editText;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ThemeSelectorUtility theme = new ThemeSelectorUtility(this);
        theme.onActivityCreateSetTheme(this);
        setContentView(R.layout.imagen_perfil);
        TypedValue typedValue = new TypedValue();
        Resources.Theme theme1 = getTheme();
        theme1.resolveAttribute(R.attr.colorPrimaryDark, typedValue, true);
        int color = typedValue.data;
        getWindow().setStatusBarColor(color);
		
        SharedPreferences prefs = getSharedPreferences("DatosLogin", Context.MODE_PRIVATE);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Perfil");
        }
        btnDeletePerfil = (Button)findViewById(R.id.btnDelete);
        btnDeleteBackground = (Button)findViewById(R.id.btnDeleteBackground);
        btnAplicar = (Button)findViewById(R.id.btnAplicar);
        checkBoxImage = (CheckBox) findViewById(R.id.checkBox);
        checkBoxImageBackground = (CheckBox) findViewById(R.id.checkBoxBackground);
        checkBoxImageBackgroundMv = (CheckBox) findViewById(R.id.checkBoxBackgroundMov);
        textAplicar = (TextView) findViewById(R.id.textoAplicar);
        editText = (EditText) findViewById(R.id.editText);
        imgvw = (CircleImageView)findViewById(R.id.imgView);
        imgBackground = (KenBurnsView) findViewById(R.id.header_image);

        editText.setText(prefs.getString("check_text_profile", ""));

        if (prefs.getBoolean("check_image_profile", true)){
            checkBoxImage.setChecked(true);
        }else{
            checkBoxImage.setChecked(false);
        }

        if (prefs.getBoolean("check_image_background", true)){
            checkBoxImageBackground.setChecked(true);
        }else{
            checkBoxImageBackground.setChecked(false);
        }

        if (prefs.getBoolean("check_image_background_mov", true)){
            imgBackground.resume();
            checkBoxImageBackgroundMv.setChecked(true);
        }else{
            imgBackground.pause();
            checkBoxImageBackgroundMv.setChecked(false);
        }

        final File fileBackground = new File(getApplicationContext().getFilesDir().getPath() + "/header_image.jpg");
        if (fileBackground.exists()) {
            btnDeleteBackground.setVisibility(View.VISIBLE);
            checkBoxImageBackground.setVisibility(View.VISIBLE);
            textAplicar.setVisibility(View.VISIBLE);
            Bitmap bitmap = null;

            try{
                FileInputStream fileInputStream = new FileInputStream(getApplicationContext().getFilesDir().getPath()+ "/header_image.jpg");
                bitmap = BitmapFactory.decodeStream(fileInputStream);
            }catch (IOException io){
                io.printStackTrace();
            }

            imgBackground.setImageBitmap(bitmap);
        }else{
            if (checkBoxImage.isChecked()){
                textAplicar.setVisibility(View.VISIBLE);
            }else{
                textAplicar.setVisibility(View.INVISIBLE);
            }
            btnDeleteBackground.setVisibility(View.INVISIBLE);
            checkBoxImageBackground.setVisibility(View.INVISIBLE);
        }

        final File file = new File(getApplicationContext().getFilesDir().getPath() + "/imagen_perfil_hctcontrol.jpg");
        if (file.exists()) {
            btnDeletePerfil.setVisibility(View.VISIBLE);
            checkBoxImage.setVisibility(View.VISIBLE);
            //btnAplicar.setVisibility(View.VISIBLE);
            textAplicar.setVisibility(View.VISIBLE);
            Bitmap bitmap = null;

            try{
                FileInputStream fileInputStream = new FileInputStream(getApplicationContext().getFilesDir().getPath()+ "/imagen_perfil_hctcontrol.jpg");
                bitmap = BitmapFactory.decodeStream(fileInputStream);
            }catch (IOException io){
                io.printStackTrace();
            }

            imgvw.setImageBitmap(bitmap);
        }else{
            if (checkBoxImageBackground.isChecked()){
                textAplicar.setVisibility(View.VISIBLE);
            }else{
                textAplicar.setVisibility(View.INVISIBLE);
            }
            btnDeletePerfil.setVisibility(View.INVISIBLE);
            checkBoxImage.setVisibility(View.INVISIBLE);
        }
        imgvw.setOnClickListener(this);
        imgBackground.setOnClickListener(this);
        btnDeletePerfil.setOnClickListener(this);
        btnDeleteBackground.setOnClickListener(this);
        checkBoxImage.setOnClickListener(this);
        checkBoxImageBackground.setOnClickListener(this);
        checkBoxImageBackgroundMv.setOnClickListener(this);
        btnAplicar.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        SharedPreferences prefs = getSharedPreferences("DatosLogin", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor;
        editor = prefs.edit();
        switch (v.getId()){
            case R.id.btnAplicar:
                String edittext = "" + editText.getText();
                Toast.makeText(this, "Nombre:  " + editText.getText(), Toast.LENGTH_LONG).show();
                editor.putString("check_text_profile", edittext);
                editor.commit();

                // Restart
                Intent reboot = getBaseContext().getPackageManager().getLaunchIntentForPackage( getBaseContext().getPackageName() );
                reboot.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(reboot);
                break;
            case R.id.imgView:
                PreferenceManager.getDefaultSharedPreferences(this).edit().putInt("image_perfil_background", 1).commit();
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                int code = 2;
                startActivityForResult(intent, code);
                btnAplicar.setVisibility(View.VISIBLE);
                textAplicar.setVisibility(View.VISIBLE);
                break;
            case R.id.header_image:
                PreferenceManager.getDefaultSharedPreferences(this).edit().putInt("image_perfil_background", 2).commit();
                Intent intent1 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                int code2 = 2;
                startActivityForResult(intent1, code2);
                btnAplicar.setVisibility(View.VISIBLE);
                textAplicar.setVisibility(View.VISIBLE);
                break;
            case R.id.checkBox:
                btnAplicar.setVisibility(View.VISIBLE);
                textAplicar.setVisibility(View.VISIBLE);
                if (checkBoxImage.isChecked()) {
                    editor.putBoolean("check_image_profile", true);
                    editor.commit();
                } else {
                    editor.putBoolean("check_image_profile", false);
                    editor.commit();
                }
                break;
            case R.id.checkBoxBackground:
                btnAplicar.setVisibility(View.VISIBLE);
                textAplicar.setVisibility(View.VISIBLE);
                if (checkBoxImageBackground.isChecked()) {
                    editor.putBoolean("check_image_background", true);
                    editor.commit();
                } else {
                    editor.putBoolean("check_image_background", false);
                    editor.commit();
                }
                break;
            case R.id.checkBoxBackgroundMov:
                if (checkBoxImageBackgroundMv.isChecked()) {
                    imgBackground.resume();
                    editor.putBoolean("check_image_background_mov", true);
                    editor.commit();
                } else {
                    imgBackground.pause();
                    editor.putBoolean("check_image_background_mov", false);
                    editor.commit();
                }
                break;
            case R.id.btnDelete:
                File file = new File(getApplicationContext().getFilesDir().getPath() + "/imagen_perfil_hctcontrol.jpg");
                file.delete();
                imgvw.setImageResource(R.drawable.imagen_perfil);
                editor.putBoolean("check_image_profile", false);
                editor.commit();
                textAplicar.setVisibility(View.VISIBLE);
                btnAplicar.setVisibility(View.VISIBLE);
                btnDeletePerfil.setVisibility(View.INVISIBLE);
                checkBoxImage.setChecked(false);
                checkBoxImage.setVisibility(View.INVISIBLE);
                break;
            case R.id.btnDeleteBackground:
                File file1 = new File(getApplicationContext().getFilesDir().getPath() + "/header_image.jpg");
                file1.delete();
                imgBackground.setImageResource(R.drawable.header_image);
                editor.putBoolean("check_image_background", false);
                editor.commit();
                textAplicar.setVisibility(View.VISIBLE);
                btnAplicar.setVisibility(View.VISIBLE);
                btnDeleteBackground.setVisibility(View.INVISIBLE);
                checkBoxImageBackground.setChecked(false);
                checkBoxImageBackground.setVisibility(View.INVISIBLE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        int selectImage = PreferenceManager.getDefaultSharedPreferences(this).getInt("image_perfil_background", 0);
        if (resultCode == RESULT_CANCELED){
            // No hacer nada. Esto evita un FC al pulsar atrás cuando se selecciona galería
        }else {
            Uri selectedImage = data.getData();
            InputStream is;
            if (selectImage == 1) {
                try {
                    is = getContentResolver().openInputStream(selectedImage);
                    BufferedInputStream bis = new BufferedInputStream(is);
                    Bitmap bitmap = BitmapFactory.decodeStream(bis);
                    CircleImageView iv = (CircleImageView) findViewById(R.id.imgView);
                    iv.setImageBitmap(bitmap);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();
                    try {
                        FileOutputStream outputStream = getApplicationContext().openFileOutput("imagen_perfil_hctcontrol.jpg", Context.MODE_PRIVATE);
                        outputStream.write(byteArray);
                        outputStream.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                } catch (FileNotFoundException e) {
                }
                btnDeletePerfil.setVisibility(View.VISIBLE);
                checkBoxImage.setVisibility(View.VISIBLE);
            }else{
                try {
                    is = getContentResolver().openInputStream(selectedImage);
                    BufferedInputStream bis = new BufferedInputStream(is);
                    Bitmap bitmap = BitmapFactory.decodeStream(bis);
                    ImageView iv = (ImageView) findViewById(R.id.header_image);
                    iv.setImageBitmap(bitmap);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();
                    try {
                        FileOutputStream outputStream = getApplicationContext().openFileOutput("header_image.jpg", Context.MODE_PRIVATE);
                        outputStream.write(byteArray);
                        outputStream.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                } catch (FileNotFoundException e) {
                }
                btnDeleteBackground.setVisibility(View.VISIBLE);
                checkBoxImageBackground.setVisibility(View.VISIBLE);
            }
        }
    }
}