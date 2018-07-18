package com.example.bruno.recyclerview;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //private static final String URL_DATA = "https://api.themoviedb.org/3/movie/popular?api_key=904df950090932081e0405ba8fd17395&language=pt-BR&page=1";


    private RecyclerView recyclerViewAcao;
    private RecyclerView recyclerViewAventura;
    private RecyclerView recyclerViewAnimacao;
    private RecyclerView recyclerViewComedia;

    private RecyclerView.Adapter adapter;
    private RecyclerView.Adapter adapter1;
    private RecyclerView.Adapter adapter2;
    private RecyclerView.Adapter adapter3;

    private List<Filme> itemFilmes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista);

        inicializaVariaves();

        inicializaAcoes();
    }

    private void inicializaVariaves() {
        int u = 10;
        recyclerViewAcao = findViewById(R.id.recycler_view_acao);
        recyclerViewAcao.setHasFixedSize(true);
        recyclerViewAcao.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        loadRecyclerViewData(Generos.GENRE_ACTION, recyclerViewAcao);
        int y = 10;
        //
//        recyclerViewAventura = findViewById(R.id.recycler_view_aventura);
//        recyclerViewAventura.setHasFixedSize(true);
//        recyclerViewAventura.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//        loadRecyclerViewData(Generos.GENRE_ADVENTURE, recyclerViewAventura );
//        //
        int t = 10;
        recyclerViewAnimacao = findViewById(R.id.recycler_view_animacao);
        recyclerViewAnimacao.setHasFixedSize(true);
        recyclerViewAnimacao.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        loadRecyclerViewData(Generos.GENRE_ANIMATION, recyclerViewAnimacao);
        int e = 10;
        //
//        recyclerViewComedia = findViewById(R.id.recycler_view_comedia);
//        recyclerViewComedia.setHasFixedSize(true);
//        recyclerViewComedia.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//        loadRecyclerViewData(Generos.GENRE_COMEDY, recyclerViewComedia );
//        //

        itemFilmes = new ArrayList<>();

    }

    //int genero
    private void loadRecyclerViewData(final int genero, final RecyclerView recyclerView3) {

        String URL_DATA = "https://api.themoviedb.org/3/discover/movie?api_key=904df950090932081e0405ba8fd17395&language=pt-BRsort_by=popularity.desc&include_adult=false&include_video=false&vote_count.gte=1000&with_genres=" + genero;

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Carregando lista...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA,
                new Response.Listener<String>() {
            int t = 10;
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        int b = 10;

                        int id;
                        String title;
                        String posterPath;

                        final String OWN_ID = "id";
                        final String OWN_TITLE = "title";
                        final String OWN_POSTER = "poster_path";

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("results");

                            if (itemFilmes != null) {
                                itemFilmes.clear();
                            }
                            int z = 10;
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject o = array.getJSONObject(i);

                                id = o.getInt(OWN_ID);
                                title = o.getString(OWN_TITLE);
                                posterPath = o.getString(OWN_POSTER);

                                itemFilmes.add(new Filme(id, title, posterPath));

                            }

                            if(genero == 28) {
                                List<Filme> filmeAcao = itemFilmes;
                                adapter = new MyAdapter(filmeAcao, getApplicationContext());
                                recyclerView3.setAdapter(adapter);
                                int i = 9;

                            }else if(genero == 16){
                                List<Filme> filmeAnimacao = itemFilmes;
                                adapter1 = new MyAdapterTeste(filmeAnimacao, getApplicationContext());
                                recyclerView3.setAdapter(adapter1);
                                int i = 10;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        int u = 9;

    }

    private void inicializaAcoes() {
    }
}
