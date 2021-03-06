package com.hctrom.romcontrol.otaupdater.otalibary;

import android.util.Log;

import com.hctrom.romcontrol.otaupdater.otalibary.objects.Update;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

public class JSONParser {
    private URL jsonUrl;

    private static final String KEY_LATEST_VERSION = "RomLatestVersion";
    private static final String KEY_RELEASE_NOTES = "Changelog";
    private static final String KEY_URL = "URL";

    public JSONParser(String url) {
        try {
            this.jsonUrl = new URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

    }

    public Update parse(){

        try {
            JSONObject json = readJsonFromUrl();
            Update update = new Update();
            update.setLatestVersion(json.getString(KEY_LATEST_VERSION).trim());
            JSONArray releaseArr = json.optJSONArray(KEY_RELEASE_NOTES);
            StringBuilder builder = new StringBuilder();
            for(int i = 0; i < releaseArr.length(); ++i) {
                builder.append(releaseArr.getString(i).trim());
                builder.append(System.getProperty("line.separator"));
            }
            update.setReleaseNotes(builder.toString());
            URL url = new URL(json.getString(KEY_URL).trim());
            update.setUrlToDownload(url);
            return update;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            Log.e("RomUpdater", "The JSON updater file is mal-formatted. RomUpdate can't check for updates.");
        }

        return null;
    }


    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    private JSONObject readJsonFromUrl() throws IOException, JSONException {
        InputStream is = this.jsonUrl.openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            return new JSONObject(jsonText);
        } finally {
            is.close();
        }
    }

}
