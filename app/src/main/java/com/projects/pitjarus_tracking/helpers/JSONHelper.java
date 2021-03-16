package com.projects.pitjarus_tracking.helpers;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Created by Sohibun Nawawi on 12/03/2021.
 */

public class JSONHelper {

    public static JSONHelper ourInstance = new JSONHelper();
    private  static final int BUFFER_SIZE = 1024 * 4;

    public  static JSONHelper getOurInstance(){
        return ourInstance;
    }

    private JSONHelper(){}

    public String readString(JSONObject jsonObject, String attributeKey){
        if (jsonObject.has(attributeKey)){
            try {
                return jsonObject.getString(attributeKey);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public JSONObject readResource(Context context, int resId) throws IOException, JSONException {
        InputStream is = context.getResources().openRawResource(resId);
        String content = IOtoString(is);
        return new JSONObject(content);
    }

    private String IOtoString(InputStream is) throws IOException {
        return new String(toByteArray(is), StandardCharsets.UTF_8);
    }

    private byte[] toByteArray(InputStream is) throws IOException {
        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            final byte[] b = new byte[BUFFER_SIZE];
            int n;
            while ((n = is.read(b)) != -1) {
                output.write(b, 0, n);
            }
            return output.toByteArray();
        }
    }
}
