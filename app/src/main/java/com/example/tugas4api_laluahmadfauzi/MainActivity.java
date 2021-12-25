package com.example.tugas4api_laluahmadfauzi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tugas4api_laluahmadfauzi.model.DataWeb;
import com.example.tugas4api_laluahmadfauzi.R;

import com.bumptech.glide.Glide;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import com.example.tugas4api_laluahmadfauzi.R;
import com.example.tugas4api_laluahmadfauzi.model.DataWeb;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    ArrayList<DataWeb> datawebs= new ArrayList();
    JSONObject jsonObject;
    ListView listview;
    FloatingActionButton btnRefresh;
    ProgressBar loadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadingIndicator = findViewById(R.id.loading);
        getDataWeb();
    }

    void setupListviewWeb () {
        listview = findViewById(R.id.list);
        AdapterWeb adapter = new AdapterWeb(this, datawebs);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(onItemClick);
    }

    private AdapterView.OnItemClickListener onItemClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            DataWeb web = datawebs.get(position);
            Intent intent = new Intent(MainActivity.this, DetailWeb.class);
            intent.putExtra("Web", web);
            startActivity(intent);
        }
    };
    final String url = "https://ewinsutriandi.github.io/mockapi/web_framework.json";
    void getDataWeb() {
        datawebs.clear();
        loadingIndicator.setVisibility(View.VISIBLE);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        jsonObject = response;
                        refreshView();
                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("Not Found", error.toString());
                        loadingIndicator.setVisibility(View.GONE);
                        Toast.makeText(MainActivity.this, "Pastikan Perangkat Anda Terhubung Dengan Interet", Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObjectRequest);
        setupListviewWeb();
    }

    void refreshView() {
        Iterator<String> key = jsonObject.keys();
        while(key.hasNext()) {
            String nameWeb = key.next();
            try {
                JSONObject data = jsonObject.getJSONObject(nameWeb);
                String description = data.getString("description");
//                String original_outhor = data.getString("original_outhor");

                String logo = data.getString("logo");
                String official_website = data.getString("official_website");
                datawebs.add(new DataWeb(nameWeb,  official_website, description, logo));
            }catch (JSONException e) {
                e.printStackTrace();
            }
        }
        loadingIndicator.setVisibility(View.GONE);
        setupListviewWeb();
    }

}