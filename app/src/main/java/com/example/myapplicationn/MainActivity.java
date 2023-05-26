package com.example.myapplicationn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAGNAME = MainActivity.class.getSimpleName();
    //private static final String HTTP_URL = "https://belatar.name/rest/profile.php?login=test&passwd=test&id=9998";

    private static final String HTTP_URL = "https://belatar.name/rest/profile.php?login=test&passwd=test&id=9998";

    private static final String HTTP_Images = "https://belatar.name/images/";
    private Etudiant etd;
    private ListView listview;

    private ListView ListNote=null ;


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

        ListNote = findViewById(R.id.labelNote);
        String noteparametre;
        if(ListNote == null)
        {
            noteparametre="";

        }
        else {
            noteparametre = "&notes=true";
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, HTTP_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(MainActivity.class.getSimpleName(), response.toString());
                try {
                    etd = new Etudiant(response.getInt("id"),response.getString("nom"),response.getString("prenom"),
                            response.getString("classe"),response.getString("phone"),null);
                            /*JSONArray notesArray = response.getJSONArray("note");
                            List<Note> notes = new ArrayList<>();
                            for (int i = 0; i < notesArray.length(); i++) {
                                JSONObject noteObject = notesArray.getJSONObject(i);
                                double node = noteObject.getDouble("note");
                                notes.add(new Note(12,2));
                            }
                            etd.setNotes(notes);*/
                    if(response.has("notes"))
                    {
                        JSONArray ja = response.getJSONArray("notes");
                        for(int i=0;i<ja.length();i++)
                        {
                            etd.addNote(new Note(ja.getJSONObject(i).getString("label"),ja.getJSONObject(i).getDouble("score")));
                        }
                    }


                    VolleySingleton.getInstance(getApplicationContext()).getImageLoader().get(HTTP_Images + response.getString("photo"),new ImageLoader.ImageListener(){

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e(MainActivity.class.getSimpleName(),error.getMessage());

                        }

                        @Override
                        public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                            /*etd.setPhone(response.getBitmap());
                            ImageView img = findViewById(R.id.image);
                            img.setImageBitmap(etd.getPhoto());*/


                        }
                    });

                    EditText txtnom = findViewById(R.id.labelNom);
                    EditText txtprenom = findViewById(R.id.labelPrenom);
                    EditText txtclasse = findViewById(R.id.labelClasse);

                    txtnom.setText(etd.getNom());
                    txtprenom.setText(etd.getPrenom());
                    txtclasse.setText(etd.getClasse());

                    ListView listView = findViewById(R.id.txt_note);
                    List<Object> noteStrings = new ArrayList<>();
                   /* for (Note n : notes) {
                        noteStrings.add(n.getNote() + n.getCoef());
                    }*/
                    ArrayAdapter<Object> adapter = new ArrayAdapter<>(getApplicationContext(),
                            android.R.layout.simple_list_item_1, noteStrings);
                    listView.setAdapter(adapter);

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