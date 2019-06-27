package com.shubham.onlinedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
EditText e1,e2,e3,e4,e5;
Button submit,button;
String s1,s2,s3,s4,s5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1 = findViewById(R.id.id);
        e2 = findViewById(R.id.name);
        e3 = findViewById(R.id.address);
        e4 = findViewById(R.id.Qualification);
        e5 = findViewById(R.id.phone);
        submit = findViewById(R.id.submit);
        button = findViewById(R.id.next);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Second.class);
                startActivity(i);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                s1 = e1.getText().toString();
                s2 = e2.getText().toString();
                s3 = e3.getText().toString();
                s4 = e4.getText().toString();
                s5 = e5.getText().toString();
                Register();

            }
        });
    }
    public void Register(){
        RequestQueue rq= Volley.newRequestQueue(MainActivity.this);
        String url="https://studentmanagement1616.000webhostapp.com/Studentapi/register.php";
        StringRequest sr=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                Toast.makeText(MainActivity.this,"dataregister",Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"data not register",Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hm1=new HashMap<String, String>();
                hm1.put("i",s1);
                hm1.put("n",s2);
                hm1.put("a",s3);
                hm1.put("q",s4);
                hm1.put("m",s5);
                return hm1;
            }
        };
        rq.add(sr);

    }
}
