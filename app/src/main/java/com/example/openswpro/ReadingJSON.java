package com.example.openswpro;

import android.content.Context;
import android.content.res.AssetManager;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReadingJSON {
    static String jsonData;

    public ReadingJSON(Context myContext) {
        AssetManager assetManager = myContext.getAssets();

        // assets/waypoints.json 파일 읽기 위한 InputStream
        try {
            InputStream is = assetManager.open("jsons/waypoints.json");
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);

            StringBuffer buffer = new StringBuffer();
            String line = reader.readLine();
            while (line != null) {
                buffer.append(line+"\n");
                line = reader.readLine();
            }
            this.jsonData = buffer.toString();

        } catch (IOException e) {e.printStackTrace();}
    }

    public static JSONArray GetNodes() throws JSONException {
        JSONArray jsonArray = new JSONArray(jsonData);
        return jsonArray;
    }
}
