package org.foodrev.planner_json_interface.Helpers;

import android.content.Context;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.foodrev.planner_json_interface.GsonModels.GsonTemplate;

/**
 * Created by magulo on 5/30/16.
 */
public class JSONAsync extends AsyncTask<String, Void, String> {

    private Context context;
    private View view;

    public JSONAsync(Context context,View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... urls) {
        return doGet("https://planner-json-interface.firebaseio.com/Plans.json");
    }
    /**
     * @param in
     * @return
     * @throws IOException
     */
    private String readStream(InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        System.out.println(sb.toString());
        br.close();
        return sb.toString();
    }

    public String doGet(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = null;
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                return readStream(in);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                urlConnection.disconnect();

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //update UI here
    protected void onPostExecute(String result) throws JsonParseException{
     Gson gson = new Gson();

//     Snackbar.make(view,parse(gson.toJson(result)), Snackbar.LENGTH_LONG)
     Snackbar.make(view,parse(result), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
    }

    public String parse(String jsonLine) {
        JsonElement jelement = new JsonParser().parse(jsonLine);
        JsonObject jobject = jelement.getAsJsonObject();
        jobject = jobject.getAsJsonObject("Plan_A");
        String result = jobject.get("center_a").toString();
//        JsonElement jelement = new JsonParser().parse(jsonLine);
//        JsonObject jobject = jelement.getAsJsonObject();
//        jobject = jobject.getAsJsonObject("data");
//        JsonArray jarray = jobject.getAsJsonArray("translations");
//        jobject = jarray.get(0).getAsJsonObject();
//        String result = jobject.get("translatedText").toString();
        return result;
    }

    public String createGson() {
        final Gson gson = new Gson();

        // original object instantiation
        GsonTemplate modelObject = new GsonTemplate("myname", 12, "honda");

        // converting an object to json object
        String json = gson.toJson(modelObject);
        Log.d("TEST","Converted JSON string is : " + json);
        return json;
    }
}

