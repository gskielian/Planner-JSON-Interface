package org.foodrev.planner_json_interface.Helpers;

import android.content.Context;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.renderscript.ScriptGroup;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import org.foodrev.planner_json_interface.R;
import org.foodrev.planner_json_interface.ScrollPlanActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;

import org.foodrev.planner_json_interface.GsonModels.GsonTemplate;

/**
 * Created by magulo on 5/30/16.
 */
public class JSONAsync extends AsyncTask<String, Void, String> {

    private InputStream is;
    URL url;
    HttpURLConnection client;
    private Context context;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    public JSONAsync(Context context,ListView listView) {
        this.context = context;
        this.listView = listView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... urls) {
//        return doGet("https://planner-json-interface.firebaseio.com/Plans.json");
//        curl -X PUT -H "Content-Type: application/json" -d
//

// '{"persons":["Alice", "Bob", "Charlie"],
// "locations":["loc1", "loc2", "loc3"],
// "cars":["car1", "car2"],
// "at_persons":[["Alice","loc1"], ["Bob", "loc1"], ["Charlie", "loc1"]],
// "at_cars":[["car1", "loc1"], ["car2", "loc2"]],
// "car_capacities":[["car1", 100], ["car2", 100]],
// "supply_init":[["loc2", 200]],
// "demand_init":[["loc3", 200]]}'

        doPut2("https://planner-json-interface.firebaseio.com/Plans.json");
        return "request sent";
    }
    /**
     * @param in
     * @return
     * @throws IOException
     */
    private String readStream(InputStream in) throws IOException {
        Log.d("made it to read stream","TGA");
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        Log.d(sb.toString(),"TAG");
        br.close();
        return sb.toString();
    }

    public void httpPutRequest(String urlString) {
        String response;
        try {
           url = new URL(urlString);
           client = (HttpURLConnection) url.openConnection();
           client.setRequestMethod("POST");
           client.setRequestProperty("Content-Type","application/json");
           client.setRequestProperty("Accept","application/json");
           client.setDoOutput(true);
           client.connect();

           JSONObject plan_request = new JSONObject();

           JSONArray jsonArray = new JSONArray();

           Log.d("Checkpoint 3","TAG");
           //persons
           // '{"persons":["Alice", "Bob", "Charlie"],
           jsonArray.put("Alice");
           jsonArray.put("Bob");
           jsonArray.put("Charlie");

           plan_request.put("persons",jsonArray);
           jsonArray = new JSONArray();

//           Log.d("plan request is " + plan_request.toString() + "\n\n","TAG");
           //locations
           // "locations":["loc1", "loc2", "loc3"],
           jsonArray.put("loc1");
           jsonArray.put("loc2");
           jsonArray.put("loc3");

           plan_request.put("locations",jsonArray);
           jsonArray = new JSONArray();

//           Log.d("plan request is " + plan_request.toString() + "\n\n","TAG");
           //cars
           // "cars":["car1", "car2"],
           jsonArray.put("car1");
           jsonArray.put("car2");

           plan_request.put("cars",jsonArray);
           jsonArray = new JSONArray();

//           Log.d("plan request is " + plan_request.toString() + "\n\n","TAG");
           //at_persons
           // "at_persons":[["Alice","loc1"], ["Bob", "loc1"], ["Charlie", "loc1"]],

           JSONArray[] jsonArrays = new JSONArray[3];
           jsonArrays[0] = new JSONArray();
           jsonArrays[1] = new JSONArray();
           jsonArrays[2] = new JSONArray();

           jsonArrays[0].put("Alice");
           jsonArrays[0].put("loc1");

           jsonArrays[1].put("Bob");
           jsonArrays[1].put("loc1");

           jsonArrays[2].put("Charlie");
           jsonArrays[2].put("loc1");

           jsonArray.put(jsonArrays[0]);
           jsonArray.put(jsonArrays[1]);
           jsonArray.put(jsonArrays[2]);

           plan_request.put("cars",jsonArray);
           jsonArray = new JSONArray();
           jsonArrays[0] = new JSONArray();
           jsonArrays[1] = new JSONArray();
           jsonArrays[2] = new JSONArray();

//           Log.d("plan request is " + plan_request.toString() + "\n\n","TAG");
           //at_cars
           // "at_cars":[["car1", "loc1"], ["car2", "loc2"]],
           jsonArrays[0].put("car1");
           jsonArrays[0].put("loc1");

           jsonArrays[1].put("car2");
           jsonArrays[1].put("loc2");

           jsonArray.put(jsonArrays[0]);
           jsonArray.put(jsonArrays[1]);

           plan_request.put("at_cars",jsonArray);
           jsonArray = new JSONArray();
           jsonArrays[0] = new JSONArray();
           jsonArrays[1] = new JSONArray();

//           Log.d("plan request is " + plan_request.toString() + "\n\n","TAG");
           //car_capacities
           // "car_capacities":[["car1", 100], ["car2", 100]],
           jsonArrays[0].put("car1");
           jsonArrays[0].put(100);

           jsonArrays[1].put("car2");
           jsonArrays[1].put(100);

           jsonArray.put(jsonArrays[0]);
           jsonArray.put(jsonArrays[1]);

           plan_request.put("car_capacities",jsonArray);

           jsonArray = new JSONArray();
           jsonArrays[0] = new JSONArray();
           jsonArrays[1] = new JSONArray();

//           Log.d("plan request is " + plan_request.toString() + "\n\n","TAG");
           //supply_init
           // "supply_init":[["loc2", 200]],
           jsonArrays[0].put("loc2");
           jsonArrays[0].put(200);

           jsonArray.put(jsonArrays[0]);
           plan_request.put("supply_init",jsonArray);
           jsonArray = new JSONArray();
           jsonArrays[0] = new JSONArray();

//           Log.d("plan request is " + plan_request.toString() + "\n\n","TAG");
           //demand_init
           // "demand_init":[["loc3", 200]]'
           jsonArrays[0].put("loc3");
           jsonArrays[0].put(200);

           jsonArray.put(jsonArrays[0]);
           plan_request.put("demand_init",jsonArray);

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            writer.write(plan_request.toString());
            writer.flush();
            writer.close();


            if(client.getResponseCode() == HttpURLConnection.HTTP_OK){
                is = client.getInputStream();// is is inputstream
            } else {
                is = client.getErrorStream();
            }


            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            response = sb.toString();
            Log.e("JSON", response);
//            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
//                    client.getOutputStream());
//            outputStreamWriter.write(plan_request.toString());
//            outputStreamWriter.flush();
//
//            outputPost.flush();
//            outputPost.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        } finally {
            if (client != null) {
                client.disconnect();
            }
        }


//        try {
//            Log.d("Checkpoint 1","TAG");
//            URL url = new URL(urlString);
//            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//            httpURLConnection.setDoOutput(true);
//            httpURLConnection.setRequestProperty("Content-Type","application/json");
//            httpURLConnection.setRequestProperty("Accept","application/json");
//            httpURLConnection.setRequestMethod("PUT");
//            Log.d("Checkpoint 2","TAG");
//
//            JSONObject plan_request = new JSONObject();
//
//            JSONArray jsonArray = new JSONArray();
//
//            Log.d("Checkpoint 3","TAG");
//            //persons
//            // '{"persons":["Alice", "Bob", "Charlie"],
//            jsonArray.put("Alice");
//            jsonArray.put("Bob");
//            jsonArray.put("Charlie");
//
//            plan_request.put("persons",jsonArray);
//            jsonArray = new JSONArray();
//
//            Log.d("plan request is " + plan_request.toString() + "\n\n","TAG");
//            //locations
//            // "locations":["loc1", "loc2", "loc3"],
//            jsonArray.put("loc1");
//            jsonArray.put("loc2");
//            jsonArray.put("loc3");
//
//            plan_request.put("locations",jsonArray);
//            jsonArray = new JSONArray();
//
//            Log.d("plan request is " + plan_request.toString() + "\n\n","TAG");
//            //cars
//            // "cars":["car1", "car2"],
//            jsonArray.put("car1");
//            jsonArray.put("car2");
//
//            plan_request.put("cars",jsonArray);
//            jsonArray = new JSONArray();
//
//            Log.d("plan request is " + plan_request.toString() + "\n\n","TAG");
//            //at_persons
//            // "at_persons":[["Alice","loc1"], ["Bob", "loc1"], ["Charlie", "loc1"]],
//
//            JSONArray[] jsonArrays = new JSONArray[3];
//            jsonArrays[0] = new JSONArray();
//            jsonArrays[1] = new JSONArray();
//            jsonArrays[2] = new JSONArray();
//
//            jsonArrays[0].put("Alice");
//            jsonArrays[0].put("loc1");
//
//            jsonArrays[1].put("Bob");
//            jsonArrays[1].put("loc1");
//
//            jsonArrays[2].put("Charlie");
//            jsonArrays[2].put("loc1");
//
//            jsonArray.put(jsonArrays[0]);
//            jsonArray.put(jsonArrays[1]);
//            jsonArray.put(jsonArrays[2]);
//
//            plan_request.put("cars",jsonArray);
//            jsonArray = new JSONArray();
//            jsonArrays[0] = new JSONArray();
//            jsonArrays[1] = new JSONArray();
//            jsonArrays[2] = new JSONArray();
//
//            Log.d("plan request is " + plan_request.toString() + "\n\n","TAG");
//            //at_cars
//            // "at_cars":[["car1", "loc1"], ["car2", "loc2"]],
//            jsonArrays[0].put("car1");
//            jsonArrays[0].put("loc1");
//
//            jsonArrays[1].put("car2");
//            jsonArrays[1].put("loc2");
//
//            jsonArray.put(jsonArrays[0]);
//            jsonArray.put(jsonArrays[1]);
//
//            plan_request.put("at_cars",jsonArray);
//            jsonArray = new JSONArray();
//            jsonArrays[0] = new JSONArray();
//            jsonArrays[1] = new JSONArray();
//
//            Log.d("plan request is " + plan_request.toString() + "\n\n","TAG");
//            //car_capacities
//            // "car_capacities":[["car1", 100], ["car2", 100]],
//            jsonArrays[0].put("car1");
//            jsonArrays[0].put(100);
//
//            jsonArrays[1].put("car2");
//            jsonArrays[1].put(100);
//
//            jsonArray.put(jsonArrays[0]);
//            jsonArray.put(jsonArrays[1]);
//
//            plan_request.put("car_capacities",jsonArray);
//
//            jsonArray = new JSONArray();
//            jsonArrays[0] = new JSONArray();
//            jsonArrays[1] = new JSONArray();
//
//            Log.d("plan request is " + plan_request.toString() + "\n\n","TAG");
//            //supply_init
//            // "supply_init":[["loc2", 200]],
//            jsonArrays[0].put("loc2");
//            jsonArrays[0].put(200);
//
//            jsonArray.put(jsonArrays[0]);
//            plan_request.put("supply_init",jsonArray);
//            jsonArray = new JSONArray();
//            jsonArrays[0] = new JSONArray();
//
//            Log.d("plan request is " + plan_request.toString() + "\n\n","TAG");
//            //demand_init
//            // "demand_init":[["loc3", 200]]'
//            jsonArrays[0].put("loc3");
//            jsonArrays[0].put(200);
//
//            jsonArray.put(jsonArrays[0]);
//            plan_request.put("demand_init",jsonArray);
//            Log.d("plan request is " + plan_request.toString() + "\n\n","TAG");
//
//            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
//                    httpURLConnection.getOutputStream());
//            outputStreamWriter.write(plan_request.toString());
//            outputStreamWriter.flush();
//
//            //display the result
//            StringBuilder stringBuilder = new StringBuilder();
//            int httpResult = httpURLConnection.getResponseCode();
//            Log.d("response code is " + String.valueOf(httpResult),"TAG");
//            if (httpResult == HttpURLConnection.HTTP_OK) {
//                BufferedReader bufferedReader = new BufferedReader(
//                        new InputStreamReader(httpURLConnection.getInputStream(), "utf-8"));
//                String line = null;
//                while ((line = bufferedReader.readLine()) != null) {
//                    stringBuilder.append(line + "\n");
//                }
//                bufferedReader.close();
//                Log.d("JSON returned is "  + stringBuilder.toString(),"URL_REQUEST");
//            } else {
//                Log.d(httpURLConnection.getResponseMessage(),"URL_REQUEST");
//            }
//        } catch (Exception e) {
//            Log.d("url exception" + e, "HTTP_PUT_REQUEST");
//        }
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

    public void doPut2(String urlString) {
        HttpURLConnection httpcon;
        String url = null;
        String data = null;
        String result = null;
        try{
//Connect
            URL foodrev_url = new URL(urlString);
            httpcon = (HttpURLConnection)  foodrev_url.openConnection();
            httpcon.setDoOutput(true);
            httpcon.setRequestProperty("Content-Type", "application/json");
            httpcon.setRequestProperty("Accept", "application/json");
            httpcon.setRequestMethod("POST");
            httpcon.connect();

//Write
            JSONObject plan_request = createPlanRequest();
            OutputStream os = httpcon.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(plan_request.toString());
            writer.close();
            os.close();

//Read
            BufferedReader br = new BufferedReader(new InputStreamReader(httpcon.getInputStream(),"UTF-8"));

            String line = null;
            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            br.close();
            result = sb.toString();
            Log.d(result,"is the result");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public String doPut(String urlString) {
        HttpURLConnection urlConnection = null;
        int HttpResult;
        try {

            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("PUT");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setUseCaches(false);
            urlConnection.setConnectTimeout(10000);
            urlConnection.setReadTimeout(10000);
            urlConnection.setRequestProperty("Content-Type","application/json; charset=UTF-8");
            urlConnection.connect();

            Log.d("Something","happened");
            JSONObject plan_request = createPlanRequest();


            DataOutputStream printout = new DataOutputStream(urlConnection.getOutputStream());
            String str =plan_request.toString();
            byte[] data=str.getBytes("UTF-8");
            printout.write(data);
            printout.flush();
            printout.close();

//            OutputStream os = urlConnection.getOutputStream();

//            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");

//            osw.write(plan_request.toString());
//            Log.d("osw`","wrote");
//
//            osw.flush();
//            osw.close()o;
            InputStream in = null;
            StringBuilder sb = null;
            HttpResult =urlConnection.getResponseCode();
            if(HttpResult ==HttpURLConnection.HTTP_OK){
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        urlConnection.getInputStream(),"utf-8"));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();

                Log.d(""+sb.toString(),"THE_STRING");
                sb.toString();


            }else{
                Log.d(urlConnection.getResponseMessage(),"ERROR_MESSAGE");
            }
//
//            BufferedReader br = new BufferedReader(new InputStreamReader( urlConnection.getInputStream(),"utf-8"));
//            String line = null;
//            while ((line = br.readLine()) != null) {
//                sb.append(line + "\n");
//            }
//            br.close();
//            Log.d(""+sb.toString(), "somestring");
//
//            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
//            Log.d("almost there",in.toString());
            return readStream(in);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        } finally {
        }
            return null;
    }

    //create JSON
    private JSONObject createPlanRequest() {
        try {
            JSONObject plan_request = new JSONObject();

          JSONArray jsonArray = new JSONArray();

          Log.d("Checkpoint 3","TAG");
          //persons
            // '{"persons":["Alice", "Bob", "Charlie"],
            jsonArray.put("Alice");
            jsonArray.put("Bob");
            jsonArray.put("Charlie");

            plan_request.put("persons",jsonArray);
            jsonArray = new JSONArray();

//            Log.d("plan request is " + plan_request.toString() + "\n\n","TAG");
            //locations
            // "locations":["loc1", "loc2", "loc3"],
            jsonArray.put("loc1");
            jsonArray.put("loc2");
            jsonArray.put("loc3");

            plan_request.put("locations",jsonArray);
            jsonArray = new JSONArray();

//            Log.d("plan request is " + plan_request.toString() + "\n\n","TAG");
            //cars
            // "cars":["car1", "car2"],
            jsonArray.put("car1");
            jsonArray.put("car2");

            plan_request.put("cars",jsonArray);
            jsonArray = new JSONArray();

//            Log.d("plan request is " + plan_request.toString() + "\n\n","TAG");
            //at_persons
            // "at_persons":[["Alice","loc1"], ["Bob", "loc1"], ["Charlie", "loc1"]],

            JSONArray[] jsonArrays = new JSONArray[3];
            jsonArrays[0] = new JSONArray();
            jsonArrays[1] = new JSONArray();
            jsonArrays[2] = new JSONArray();

            jsonArrays[0].put("Alice");
            jsonArrays[0].put("loc1");

            jsonArrays[1].put("Bob");
            jsonArrays[1].put("loc1");

            jsonArrays[2].put("Charlie");
            jsonArrays[2].put("loc1");

            jsonArray.put(jsonArrays[0]);
            jsonArray.put(jsonArrays[1]);
            jsonArray.put(jsonArrays[2]);

            plan_request.put("cars",jsonArray);
            jsonArray = new JSONArray();
            jsonArrays[0] = new JSONArray();
            jsonArrays[1] = new JSONArray();
            jsonArrays[2] = new JSONArray();

//            Log.d("plan request is " + plan_request.toString() + "\n\n","TAG");
            //at_cars
            // "at_cars":[["car1", "loc1"], ["car2", "loc2"]],
            jsonArrays[0].put("car1");
            jsonArrays[0].put("loc1");

            jsonArrays[1].put("car2");
            jsonArrays[1].put("loc2");

            jsonArray.put(jsonArrays[0]);
            jsonArray.put(jsonArrays[1]);

            plan_request.put("at_cars",jsonArray);
            jsonArray = new JSONArray();
            jsonArrays[0] = new JSONArray();
            jsonArrays[1] = new JSONArray();

//            Log.d("plan request is " + plan_request.toString() + "\n\n","TAG");
            //car_capacities
            // "car_capacities":[["car1", 100], ["car2", 100]],
            jsonArrays[0].put("car1");
            jsonArrays[0].put(100);

            jsonArrays[1].put("car2");
            jsonArrays[1].put(100);

            jsonArray.put(jsonArrays[0]);
            jsonArray.put(jsonArrays[1]);

            plan_request.put("car_capacities",jsonArray);

            jsonArray = new JSONArray();
            jsonArrays[0] = new JSONArray();
            jsonArrays[1] = new JSONArray();

//            Log.d("plan request is " + plan_request.toString() + "\n\n","TAG");
            //supply_init
            // "supply_init":[["loc2", 200]],
            jsonArrays[0].put("loc2");
            jsonArrays[0].put(200);

            jsonArray.put(jsonArrays[0]);
            plan_request.put("supply_init",jsonArray);
            jsonArray = new JSONArray();
            jsonArrays[0] = new JSONArray();

//            Log.d("plan request is " + plan_request.toString() + "\n\n","TAG");
            //demand_init
            // "demand_init":[["loc3", 200]]'
            jsonArrays[0].put("loc3");
            jsonArrays[0].put(200);

            jsonArray.put(jsonArrays[0]);
            plan_request.put("demand_init",jsonArray);
//            Log.d("plan request is " + plan_request.toString() + "\n\n","TAG");

            return plan_request;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    //update UI here
    protected void onPostExecute(String result){
//     Gson gson = new Gson();
//
//        StringBuilder sb = new StringBuilder();
//        sb.append(parse(result) + "\n");
//        sb.append(parse(result));
//        String[] arrayString = {"b","c","d"};
//
//        adapter = new ArrayAdapter<>(this.context, android.R.layout.simple_list_item_1, arrayString);
//
//        listView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
//
//
//     Snackbar.make(listView,parse(result), Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
    }

//    public String parse(String jsonLine) {
//        JsonElement jelement = new JsonParser().parse(jsonLine);
//        JsonObject jobject = jelement.getAsJsonObject();
//        jobject = jobject.getAsJsonObject("Plan_A");
//        String result = jobject.get("center_a").toString();
////        JsonElement jelement = new JsonParser().parse(jsonLine);
////        JsonObject jobject = jelement.getAsJsonObject();
////        jobject = jobject.getAsJsonObject("data");
////        JsonArray jarray = jobject.getAsJsonArray("translations");
////        jobject = jarray.get(0).getAsJsonObject();
////        String result = jobject.get("translatedText").toString();
//        return result;
//    }

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

