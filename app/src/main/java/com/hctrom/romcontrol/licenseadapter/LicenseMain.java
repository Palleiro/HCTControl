package com.hctrom.romcontrol.licenseadapter;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.hctrom.romcontrol.R;
import com.hctrom.romcontrol.ThemeSelectorUtility;

import java.util.ArrayList;
import java.util.List;

public class LicenseMain extends AppCompatActivity {
  private Toolbar toolbar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ThemeSelectorUtility theme = new ThemeSelectorUtility(this);
    theme.onActivityCreateSetTheme(this);
    setContentView(R.layout.license_main);
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
      getSupportActionBar().setTitle("Licencias");
    }

    List<LicenseEntry> licenses = new ArrayList<>();

    licenses.add(Licenses.noContent("Android SDK", "Google Inc.", "https://developer.android.com/sdk/terms.html"));
    licenses.add(Licenses.noContent("RootTools", "Stericson", "https://github.com/Stericson/RootTools"));
    licenses.add(Licenses.noContent("HCT Control", "HCTeam", "https://github.com/Palleiro/HCTControl"));
    licenses.add(Licenses.noContent("CustomSettingsForDevs", "Wubydax", "https://github.com/wubydax/CustomSettingsForDevs"));
    licenses.add(Licenses.noContent("ColorPickerPreference", "Margaritov", "https://github.com/attenzione/android-ColorPickerPreference"));
    licenses.add(Licenses.fromGitHub("hdodenhof/CircleImageView", Licenses.LICENSE_APACHE_V2));
    licenses.add(Licenses.fromGitHub("shell-software/fab", Licenses.LICENSE_APACHE_V2));
    licenses.add(Licenses.fromGitHub("chrisjenx/Calligraphy", Licenses.LICENSE_APACHE_V2));
    licenses.add(Licenses.fromGitHub("cketti/ckChangeLog", Licenses.LICENSE_APACHE_V2));
    licenses.add(Licenses.fromGitHub("nathanpc/Build.prop-Editor", Licenses.LICENSE_APACHE_V2));
    licenses.add(Licenses.fromGitHub("bvalosek/cpuspy", Licenses.LICENSE_APACHE_V2));
    licenses.add(Licenses.fromGitHub("grennis/dpi-changer", Licenses.LICENSE_APACHE_V2));
    licenses.add(Licenses.fromGitHub("Free-Software-for-Android/FasterGPS", Licenses.LICENSE_APACHE_V2));
    licenses.add(Licenses.fromGitHub("yshrsmz/LicenseAdapter", Licenses.LICENSE_APACHE_V2));
    licenses.add(Licenses.fromGitHub("nolanlawson/Catlog", Licenses.LICENSE_APACHE_V2));
    licenses.add(Licenses.fromGitHub("wubydax/ToolboxController", Licenses.LICENSE_APACHE_V2));
    licenses.add(Licenses.fromGitHub("wasabeef/Blurry", Licenses.LICENSE_APACHE_V2));
    licenses.add(Licenses.fromGitHub("wubydax/HexConverter-v2.0", Licenses.LICENSE_APACHE_V2));
    licenses.add(Licenses.fromGitHub("nisrulz/packagehunter", Licenses.LICENSE_APACHE_V2));
    licenses.add(Licenses.fromGitHub("edmodo/cropper", Licenses.LICENSE_APACHE_V2));
    licenses.add(Licenses.fromGitHub("flavioarfaria/KenBurnsView", Licenses.LICENSE_APACHE_V2));
    licenses.add(Licenses.fromGitHub("Arjun-sna/android-passcodeview", Licenses.LICENSE_APACHE_V2));

    LicenseAdapter adapter = new LicenseAdapter(licenses);
    RecyclerView list = (RecyclerView) findViewById(R.id.list);
    list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    list.setAdapter(adapter);

    Licenses.load(licenses);
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
