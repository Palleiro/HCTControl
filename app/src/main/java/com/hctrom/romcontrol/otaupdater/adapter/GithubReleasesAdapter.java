package com.hctrom.romcontrol.otaupdater.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.hctrom.romcontrol.R;

import org.json.JSONException;
import org.json.JSONObject;

import static com.hctrom.romcontrol.otaupdater.util.Config.DownloadFileName;
import static com.hctrom.romcontrol.otaupdater.util.Config.Downloader;
import static com.hctrom.romcontrol.otaupdater.util.Config.getRomInstalledVersion;
import static com.hctrom.romcontrol.otaupdater.util.Config.uri;

/**
 * Created by: veli
 * Date: 10/25/16 10:13 PM
 */

public class GithubReleasesAdapter extends GithubAdapterIDEA
{
	private String fileName;
	private Long fileId;
	private TextView text1,text2,betaWarningText,StableText,latestRomText,oldRomText,mInstalledText;
	public GithubReleasesAdapter(Context context)
	{
		super(context);
	}


	@Override
	protected View onView(int position, View convertView, ViewGroup parent)
	{
		final JSONObject release = (JSONObject) getItem(position);

		if (convertView == null)
			convertView = mInflater.inflate(R.layout.ota_list_release, parent, false);

		text1 = (TextView) convertView.findViewById(R.id.list_release_name);
		text2 = (TextView) convertView.findViewById(R.id.list_release_changelog);
		betaWarningText = (TextView) convertView.findViewById(R.id.list_release_beta_release_beta);
		StableText = (TextView) convertView.findViewById(R.id.list_release_stable);
		latestRomText=(TextView)convertView.findViewById(R.id.list_release_latest);
        oldRomText=(TextView)convertView.findViewById(R.id.list_release_old);
		mInstalledText=(TextView)convertView.findViewById(R.id.list_release_installed);
		final Button actionButton = (Button) convertView.findViewById(R.id.list_release_action_button);

		convertView.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				actionButton.setVisibility((actionButton.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE);
			}
		});

		try
		{

			if (release.getBoolean("prerelease")) {
				if (release.getBoolean("prerelease") == true)

				{
					betaWarningText.setVisibility(View.VISIBLE);
				}
			}
			if (release.getBoolean("stable")) {
				if (release.getBoolean("stable") == true) {
					StableText.setVisibility(View.VISIBLE);
				}
			}
			if (release.has("version"))
			{
				String InstalledRomVersion= release.getString("version");
				if(getRomInstalledVersion().equals(InstalledRomVersion)){
					mInstalledText.setVisibility(View.VISIBLE);
				}
				Log.i("version",InstalledRomVersion+" Rom "+getRomInstalledVersion());
			}
			if (release.has("tag_name"))
				text1.setText(release.getString("tag_name"));

			if (release.has("body"))
				text2.setText(release.getString("body"));
            if(release.has("latest")==true)
            {
                latestRomText.setText("Última");
                latestRomText.setVisibility(View.VISIBLE);
                oldRomText.setVisibility(View.GONE);
            }
            else {
                oldRomText.setText("Anterior");
                oldRomText.setVisibility(View.VISIBLE);
            }

			if(release.has("browser_download_url"))
			{
				actionButton.setOnClickListener(new View.OnClickListener()
				{
					@Override
					public void onClick(View v)
					{

						try
						{
							fileName = release.getString("name");
							fileId = release.getLong("id");
							DownloadFileName=fileId + "-" + fileName;
							uri = Uri.parse(release.getString("browser_download_url"));

							Downloader(getContext());

							actionButton.setEnabled(false);
						} catch (JSONException e)
						{
							e.printStackTrace();
						}
					}
				});
			}

		} catch (JSONException e)
		{
			e.printStackTrace();
		}

		return convertView;
	}
}