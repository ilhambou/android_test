package com.example.myapplicationn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private static final String TAGNAME = MainActivity.class.getSimpleName();
    private static final String HTTP_URL = "https://belatar.name/rest/profile.php?login=test&passwd=test&id=9998";
    private static final String HTTP_Images = "https://belatar.name/images/";
    private Etudiant etd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ClickHandler(View view) {
        Toast.makeText(this,"Button clické",Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAGNAME, "on est dans onStart");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAGNAME, "on est dans onStop");


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAGNAME, "on est dans onDestroy");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAGNAME, "on est dans onPause");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAGNAME, "on est dans onResume");
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, HTTP_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(MainActivity.class.getSimpleName(), response.toString());
                try {
                    etd = new Etudiant(response.getInt("id"),response.getString("nom"),response.getString("prenom"),
                            response.getString("classe"),response.getString("phone"),null);

                    VolleySingleton.getInstance(getApplicationContext()).getImageLoader().get(HTTP_Images + response.getString("photo"),new ImageLoader.ImageListener(){

                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }

                        @Override
                        public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {

                        }
                    });

                    EditText txtnom = findViewById(R.id.labelNom);
                    EditText txtprenom = findViewById(R.id.labelPrenom);
                    EditText txtclasse = findViewById(R.id.labelClasse);

                    txtnom.setText(etd.getNom());
                    txtprenom.setText(etd.getPrenom());
                    txtclasse.setText(etd.getClasse());

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(MainActivity.class.getSimpleName(),error.getMessage());

            }
        }
        );
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAGNAME, "on est dans onRestart");

    }
}