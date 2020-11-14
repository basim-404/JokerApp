package com.example.jokeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    RequestQueue queue;
    String url = "-https://github.com/15Dkatz/official_joke_api.";
    TextView  joke,id,type,setup,punch;
    Button button3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button3 = findViewById(R.id.button3);
        joke = findViewById(R.id.textView4);
        id = findViewById(R.id.textView5);
        type = findViewById(R.id.textView6);
        setup = findViewById(R.id.textView7);
        punch = findViewById(R.id.textView8);

    }

        public void getJoke(View view) {
            JsonObjectRequest jsonObjectRequest =
                    new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            int ID = 0;
                            String types, setups, punchs;
                            try {
                                ID = response.getInt("id");
                                types = response.getString("type");
                                setups = response.getString("setup");
                                punchs = response.getString("punchline");
                                Jokes joke = new Jokes(ID, types, setups, punchs);
                                id.setText(joke.getId() + "");
                                id.setVisibility(View.VISIBLE);
                                type.setText(joke.getType() + "");
                                type.setVisibility(View.VISIBLE);
                                setup.setText(joke.getSetup() + "");
                                setup.setVisibility(View.VISIBLE);
                                punch.setText(joke.getPunchline() + "");
                                punch.setVisibility(View.VISIBLE);

                            } catch (JSONException e) {

                                e.printStackTrace();
                            }

                            joke.setText("Response: " + ID);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            String erro = error.toString();
                            joke.setText("Data cannot loaded"+erro.toString());

                        }
        });


        queue.add(jsonObjectRequest);
    }
}
