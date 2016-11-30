
package com.hctrom.romcontrol.cropper;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.hctrom.romcontrol.R;
import com.hctrom.romcontrol.ThemeSelectorUtility;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainCropImageView extends AppCompatActivity {

    // Static final constants
    private static final int DEFAULT_ASPECT_RATIO_VALUES = 10;
    private static final int ROTATE_NINETY_DEGREES = 90;
    private static final String ASPECT_RATIO_X = "ASPECT_RATIO_X";
    private static final String ASPECT_RATIO_Y = "ASPECT_RATIO_Y";
    private static final int ON_TOUCH = 1;

    // Instance variables
    private int mAspectRatioX = DEFAULT_ASPECT_RATIO_VALUES;
    private int mAspectRatioY = DEFAULT_ASPECT_RATIO_VALUES;

    Bitmap croppedImage;
    private ImageView croppedImageView;
    private EditText editName;
    private TextView textPng;
    private Button saveButton;
    private Toolbar toolbar;
    private TextView textSave;

    // Saves the state upon rotating the screen/restarting the activity
    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt(ASPECT_RATIO_X, mAspectRatioX);
        bundle.putInt(ASPECT_RATIO_Y, mAspectRatioY);
    }

    // Restores the state upon rotating the screen/restarting the activity
    @Override
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        mAspectRatioX = bundle.getInt(ASPECT_RATIO_X);
        mAspectRatioY = bundle.getInt(ASPECT_RATIO_Y);
    }

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ThemeSelectorUtility theme = new ThemeSelectorUtility(this);
        theme.onActivityCreateSetTheme(this);
        setContentView(R.layout.crop_image_view_activity_main);
        TypedValue typedValue = new TypedValue();
        Resources.Theme theme1 = getTheme();
        theme1.resolveAttribute(R.attr.colorPrimaryDark, typedValue, true);
        int color = typedValue.data;
        getWindow().setStatusBarColor(color);

        toolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Crop Image");
        }

        // Initialize components of the app
        final CropImageView cropImageView = (CropImageView) findViewById(R.id.CropImageView);
        final SeekBar aspectRatioXSeek = (SeekBar) findViewById(R.id.aspectRatioXSeek);
        final SeekBar aspectRatioYSeek = (SeekBar) findViewById(R.id.aspectRatioYSeek);
        final Switch fixedAspectRatioToggle = (Switch) findViewById(R.id.fixedAspectRatioToggle);
        final Button findButton = (Button) findViewById(R.id.Button_buscar);
        saveButton = (Button) findViewById(R.id.Button_save);
        textPng = (TextView) findViewById(R.id.name_png);
        editName = (EditText) findViewById(R.id.editTextCrop);
        croppedImageView = (ImageView) findViewById(R.id.croppedImageView);
        textSave = (TextView) findViewById(R.id.text_save);
        saveButton.setVisibility(View.GONE);
        textPng.setVisibility(View.GONE);
        editName.setVisibility(View.GONE);
        textSave.setVisibility(View.GONE);

        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                int code = 2;
                startActivityForResult(intent, code);
            }
        });
        Spinner showGuidelinesSpin = (Spinner) findViewById(R.id.showGuidelinesSpin);
        
        // Sets sliders to be disabled until fixedAspectRatio is set
        aspectRatioXSeek.setEnabled(false);
        aspectRatioYSeek.setEnabled(false);

        // Set initial spinner value
        showGuidelinesSpin.setSelection(ON_TOUCH);
        
        //Sets the rotate button
        final Button rotateButton = (Button) findViewById(R.id.Button_rotate);
        rotateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cropImageView.rotateImage(ROTATE_NINETY_DEGREES);
            }
        });
        
        // Sets fixedAspectRatio
        fixedAspectRatioToggle.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cropImageView.setFixedAspectRatio(isChecked);
                if (isChecked) {
                    aspectRatioXSeek.setEnabled(true);
                    aspectRatioYSeek.setEnabled(true);
                }
                else {
                    aspectRatioXSeek.setEnabled(false);
                    aspectRatioYSeek.setEnabled(false);
                }
            }
        });

        // Sets initial aspect ratio to 10/10, for demonstration purposes
        cropImageView.setAspectRatio(DEFAULT_ASPECT_RATIO_VALUES, DEFAULT_ASPECT_RATIO_VALUES);

        // Sets aspectRatioX
        final TextView aspectRatioX = (TextView) findViewById(R.id.aspectRatioX);

        aspectRatioXSeek.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar aspectRatioXSeek, int progress, boolean fromUser) {
                try {
                    mAspectRatioX = progress;
                    cropImageView.setAspectRatio(progress, mAspectRatioY);
                    aspectRatioX.setText(" " + progress);
                } catch (IllegalArgumentException e) {
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // Sets aspectRatioY
        final TextView aspectRatioY = (TextView) findViewById(R.id.aspectRatioY);

        aspectRatioYSeek.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar aspectRatioYSeek, int progress, boolean fromUser) {
                try {
                    mAspectRatioY = progress;
                    cropImageView.setAspectRatio(mAspectRatioX, progress);
                    aspectRatioY.setText(" " + progress);
                } catch (IllegalArgumentException e) {
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });


        // Sets up the Spinner
        showGuidelinesSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cropImageView.setGuidelines(i);
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

        final Button cropButton = (Button) findViewById(R.id.Button_crop);
        cropButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                croppedImage = cropImageView.getCroppedImage();
                croppedImageView.setImageBitmap(croppedImage);
                saveButton.setVisibility(View.VISIBLE);
                textPng.setVisibility(View.VISIBLE);
                editName.setVisibility(View.VISIBLE);
                textSave.setVisibility(View.VISIBLE);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                BitmapDrawable drawable = (BitmapDrawable) croppedImageView.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                String name = "" + editName.getText();
                if (name == ""){
                    saveImageFile(bitmap, "crop_imagen");
                }else{
                    saveImageFile(bitmap, name);
                }
            }
        });

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_CANCELED){
            // No hacer nada. Esto evita un FC al pulsar atrás cuando se selecciona galería
        }else {

            Uri selectedImage = data.getData();
            InputStream is;
            try {
                is = getContentResolver().openInputStream(selectedImage);
                BufferedInputStream bis = new BufferedInputStream(is);
                Bitmap bitmap = BitmapFactory.decodeStream(bis);
                CropImageView iv = (CropImageView) findViewById(R.id.CropImageView);
                iv.setImageBitmap(bitmap);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                try {
                    FileOutputStream outputStream = getApplicationContext().openFileOutput("crop_image.jpg", Context.MODE_PRIVATE);
                    outputStream.write(byteArray);
                    outputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            } catch (FileNotFoundException e) {}
        }
    }

    public void saveImageFile(Bitmap bitmap, String name) {
        //File sdCardDirectory = Environment.getExternalStorageDirectory();
        File sdCardDirectory = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/HCTControl/Crop");
        if(!sdCardDirectory.exists()) {
            sdCardDirectory.mkdir();
        }
        File image = new File(sdCardDirectory, name + ".png");
        boolean success = false;

        // Encode the file as a PNG image.
        FileOutputStream outStream;
        try {

            outStream = new FileOutputStream(image);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream);
                    /* 100 to keep full quality of the image */

            outStream.flush();
            outStream.close();
            success = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (success) {
            Toast.makeText(getApplicationContext(), "¡¡ Imagen guardada correctamente !!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "¡¡ Error al guardar la imagen !!", Toast.LENGTH_LONG).show();
        }
    }

    private String getFilename() {
        File file = new File(Environment.getExternalStorageDirectory()
                .getPath(), "TestFolder");
        if (!file.exists()) {
            file.mkdirs();
        }
        String uriSting = (file.getAbsolutePath() + "/"
                + System.currentTimeMillis() + ".jpg");
        return uriSting;
    }

}
