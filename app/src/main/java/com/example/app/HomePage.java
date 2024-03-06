package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class HomePage extends AppCompatActivity {

    TextView updateDate, portugal, losAngeles, france, sweden, spain,dayPortugal, dayUS, dayFrance, daySweden, daySpain;

    String urlPortugal, urlLosAngeles, urlFrance, urlSweden, urlSpain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        updateDate = findViewById(R.id.updatedate);

        portugal = findViewById(R.id.portugal);
        losAngeles = findViewById(R.id.losAngeles);
        france = findViewById(R.id.france);
        sweden = findViewById(R.id.sweden);
        spain = findViewById(R.id.spain);

        dayPortugal = findViewById(R.id.dayPortugal);
        dayUS = findViewById(R.id.dayUS);
        dayFrance = findViewById(R.id.dayFrance);
        daySweden = findViewById(R.id.daySweden);
        daySpain = findViewById(R.id.daySpain);

        urlPortugal = "https://timeapi.io/api/Time/current/zone?timeZone=Europe/Lisbon";
        urlLosAngeles = "https://timeapi.io/api/Time/current/zone?timeZone=America/Los_Angeles";
        urlFrance = "https://timeapi.io/api/Time/current/zone?timeZone=Europe/Paris";
        urlSweden = "https://timeapi.io/api/Time/current/zone?timeZone=Europe/Stockholm";
        urlSpain = "https://timeapi.io/api/Time/current/zone?timeZone=Europe/Madrid";

        actualHour(urlPortugal, portugal);
        actualHour(urlLosAngeles, losAngeles);
        actualHour(urlFrance, france);
        actualHour(urlSweden, sweden);
        actualHour(urlSpain, spain);

        actualDate(updateDate);

        actualDay(urlPortugal, dayPortugal);
        actualDay(urlLosAngeles, dayUS);
        actualDay(urlFrance, dayFrance);
        actualDay(urlSweden, daySweden);
        actualDay(urlSpain, daySpain);
    }

    private void actualHour(String url, final TextView textView) {
        JsonObjectRequest makeRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String time = response.getString("time");
                    String actualtime = time.split("T")[0];
                    textView.setText(actualtime);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(this).add(makeRequest);
    }

    private void actualDate(final TextView textView) {
        String urlForDate = "https://timeapi.io/api/Time/current/zone?timeZone=Europe/Lisbon";

        JsonObjectRequest actualdateRequest = new JsonObjectRequest(Request.Method.GET, urlForDate, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    String date = response.getString("date");
                    textView.setText(date);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        Volley.newRequestQueue(this).add(actualdateRequest);
    }

    private void actualDay(String url, final TextView textView) {

        JsonObjectRequest actualdateRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    String dayOfWeek = response.getString("dayOfWeek");
                    textView.setText(dayOfWeek);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        Volley.newRequestQueue(this).add(actualdateRequest);
    }
}