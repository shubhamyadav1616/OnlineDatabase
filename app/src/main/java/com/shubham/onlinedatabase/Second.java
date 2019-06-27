package com.shubham.onlinedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Second extends AppCompatActivity {
    EditText e1, e2, e3, e4, e5;
    Button fetch,update,delete;
    String s1, s2, s3, s4, s5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        e1 = findViewById(R.id.id);
        e2 = findViewById(R.id.name);
        e3 = findViewById(R.id.address);
        e4 = findViewById(R.id.Qualification);
        e5 = findViewById(R.id.phone);
        update=findViewById(R.id.update);
        delete=findViewById(R.id.delete);
        fetch = findViewById(R.id.fetch);
        fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1 = e1.getText().toString();
                s2 = e2.getText().toString();
                s3 = e3.getText().toString();
                s4 = e4.getText().toString();
                s5 = e5.getText().toString();
                fetch();
            }

        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1 = e1.getText().toString();
                s2 = e2.getText().toString();
                s3 = e3.getText().toString();
                s4 = e4.getText().toString();
                s5 = e5.getText().toString();
                update();
            }

        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1 = e1.getText().toString();
                s2 = e2.getText().toString();
                s3 = e3.getText().toString();
                s4 = e4.getText().toString();
                s5 = e5.getText().toString();
                delete();
            }

        });

    }

    public void fetch() {
        RequestQueue rq = Volley.newRequestQueue(Second.this);
        String url = Config.Data_URL + s1;
        StringRequest sr = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Second.this, "Data Fetch", Toast.LENGTH_SHORT).show();
                Showjson(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Second.this, "data not fetch", Toast.LENGTH_SHORT).show();
            }

        });
        rq.add(sr);
    }

    public void Showjson(String response) {
        String name1 = "";
        String address1 = "";
        String qualification1 = "";
        String mobile1 = "";
        try {
            JSONObject jo = new JSONObject(response);
            JSONArray jr = jo.getJSONArray(Config.KEY_RESULT);
            JSONObject jn = jr.getJSONObject(0);
            name1 = jn.getString(Config.KEY_NAME);
            address1 = jn.getString(Config.KEY_ADDRESS);
            qualification1 = jn.getString(Config.KEY_QUALIFICATION);
            mobile1 = jn.getString(Config.KEY_MOBILE);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        e2.setText(name1);
        e3.setText(address1);
        e4.setText(qualification1);
        e5.setText(mobile1);
    }

    public void update() {
        RequestQueue rq = Volley.newRequestQueue(Second.this);
        String url = "https://studentmanagement1616.000webhostapp.com/Studentapi/update.php";
        StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                Toast.makeText(Second.this, "database updated", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Second.this, "database unable to update", Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hm1 = new HashMap<String, String>();
                hm1.put("i", s1);
                hm1.put("n", s2);
                hm1.put("a", s3);
                hm1.put("q", s4);
                hm1.put("m", s5);
                return hm1;
            }
        };
        rq.add(sr);

    }

    public void delete() {
        RequestQueue rq = Volley.newRequestQueue(Second.this);
        String url = "https://studentmanagement1616.000webhostapp.com/Studentapi/delete.php";
        StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                Toast.makeText(Second.this, "data deleted", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Second.this, "data not deleted", Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hm1 = new HashMap<String, String>();
                hm1.put("i", s1);
                hm1.put("n", s2);
                hm1.put("a", s3);
                hm1.put("q", s4);
                hm1.put("m", s5);
                return hm1;
            }
        };
        rq.add(sr);

    }
}
