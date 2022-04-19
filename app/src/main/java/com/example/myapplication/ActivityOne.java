package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Calendar;

public class ActivityOne extends AppCompatActivity {
    TextView time;
    TextView temperature, humid;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Button bttnNext = (Button) findViewById(R.id.bttnAct2);
        Button bttn = (Button) findViewById(R.id.bttnDate);
        Calendar c = Calendar.getInstance();
        DateFormat fmtDate = DateFormat.getDateInstance();
        TextView date = (TextView) findViewById(R.id.txtDate);
        Log.d("Rayan", "Welcome to the project of rayan/200203");

        DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(android.widget.DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {
                c.set(Calendar.YEAR, year);
                c.set(Calendar.MONTH, monthOfYear);
                c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                date.setText("Your date is "+
                        fmtDate.format(c.getTime()));
            }
        };

        bttn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                new DatePickerDialog(ActivityOne.this, d,
                        c.get(Calendar.YEAR),
                        c.get(Calendar.MONTH),
                        c.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        bttnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityOne.this, ActivityTwo.class));
            }
        });

        temperature = (TextView) findViewById(R.id.londonTemp);
        humid = (TextView) findViewById(R.id.humid);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Spinner spinnerCity=findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.city, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerCity.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                url = "https://api.openweathermap.org/data/2.5/weather?q="+ spinner.getSelectedItem().toString() +"&appid=a6db16468be1247fe3c436fe88426379&units=metric";
                weather(url);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void weather(String url) {
        JsonObjectRequest jsonObj = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("Rayan", "Response Received");
                Log.d("Rayan", response.toString());

                try {
                    JSONObject jsonMain = response.getJSONObject("main");

                    Double temp = jsonMain.getDouble("temp");
                    Double humidity = jsonMain.getDouble("humidity");

                    Log.d("Rayan", "Temp = "+String.valueOf(temp));
                    Log.d("Rayan", "humidity = "+String.valueOf(humidity));

                    temperature.setText(String.valueOf(temp)+"°C");
                    humid.setText("Humidity: "+String.valueOf(humidity));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Rayan", "Error Retrieving URL");
            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObj);

    }
}

