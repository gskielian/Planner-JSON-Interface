package org.foodrev.planner_json_interface;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import org.foodrev.planner_json_interface.GsonModels.GsonTemplate;
import org.foodrev.planner_json_interface.Helpers.JSONAsync;

//TODO create firebase helpers (write success etc)

public class ScrollPlanActivity extends AppCompatActivity {

    private final String TAG = "main app";
    //String url = "https://planner-json-interface.firebaseio.com/rest.json";
    FirebaseDatabase database;
    DatabaseReference myRef;

    public ListView listView ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_plan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        String[] items = { "Milk", "Butter", "Yogurt", "Toothpaste", "Ice Cream" };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);


        listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        database = FirebaseDatabase.getInstance();
//        myRef = database.getReference("rest");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            //todo create model which matches the firebase
            //todo write retrieveStream() which will do an http request on the firebase
            @Override
            public void onClick(View view) {
                new JSONAsync(ScrollPlanActivity.this, listView).execute();

            }
        });
    }


    public void writeMessageToFirebase() {
        // Write a message to the database
        myRef.setValue("Hello, World!");
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scroll_plan, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
