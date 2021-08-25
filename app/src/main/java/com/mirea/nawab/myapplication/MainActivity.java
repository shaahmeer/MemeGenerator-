package com.mirea.nawab.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    static ProgressBar simpleProgressBar;
    Button next ,share;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imageView=(ImageView)findViewById(R.id.imageView);
        next=findViewById(R.id.button);
        share=findViewById(R.id.button2);
        simpleProgressBar = (ProgressBar) findViewById(R.id.simpleProgressBar);
        loadmeme();
        simpleProgressBar.setVisibility(View.INVISIBLE);
    }

public void loadmeme()
{


    String url="https://meme-api.herokuapp.com/gimme";
    JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            try {
                String url = response.getString("url");
                Glide.with(MainActivity.this).load(url).into(imageView);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(MainActivity.this,"error",Toast.LENGTH_SHORT);
        }

    });
    MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);

}

    public void share(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(intent.EXTRA_TEXT,"funny or not");
        startActivity(intent);
    }

    public void next(View view) { loadmeme();
simpleProgressBar.setVisibility(View.INVISIBLE);


    }


}