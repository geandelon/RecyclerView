package com.example.bruno.recyclerview;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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

        recyclerViewAnimacao = findViewById(R.id.recycler_view_animacao);
        recyclerViewAnimacao.setHasFixedSize(true);
        recyclerViewAnimacao.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        new Teste(Generos.GENRE_ACTION, recyclerViewAcao).execute();
        new Teste(Generos.GENRE_ANIMATION, recyclerViewAnimacao).execute();

    }

    class Teste extends AsyncTask<Void, Void, List<Filme>> {

        private int genero;
        private RecyclerView recyclerView;
        private ProgressDialog progressDialog;

        public Teste(int genero, RecyclerView recyclerView) {
            this.genero = genero;
            this.recyclerView = recyclerView;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Carregando lista...");
            progressDialog.show();
        }

        @Override
        protected List<Filme> doInBackground(Void... voids) {
            List<Filme> filmes = new ArrayList<>();
            URL url;
            HttpURLConnection urlConnection = null;
            try {

                String URL_DATA = "https://api.themoviedb.org/3/discover/movie?api_key=904df950090932081e0405ba8fd17395&language=pt-BRsort_by=popularity.desc&include_adult=false&include_video=false&vote_count.gte=1000&with_genres=" + genero;

                url = new URL(URL_DATA);

                urlConnection = (HttpURLConnection) url
                        .openConnection();

                //int responseCode = urlConnection.getResponseCode();
                //String responseMessage = urlConnection.getResponseMessage();

                String responseString = readStream(urlConnection.getInputStream());


                int id;
                String title;
                String posterPath;

                final String OWN_ID = "id";
                final String OWN_TITLE = "title";
                final String OWN_POSTER = "poster_path";

                try {
                    JSONObject jsonObject = new JSONObject(responseString);
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

                        filmes.add(new Filme(id, title, posterPath));

                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }


            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }


            return filmes;
        }

        @Override
        protected void onPostExecute(List<Filme> filmes) {
            super.onPostExecute(filmes);
            progressDialog.dismiss();
            MyAdapter adapter = new MyAdapter(filmes);
            recyclerView.setAdapter(adapter);
        }

        private String readStream(InputStream in) {
            BufferedReader reader = null;
            StringBuffer response = new StringBuffer();
            try {
                reader = new BufferedReader(new InputStreamReader(in));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return response.toString();
        }

    }


    /*
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

                            Log.d("Genero", String.valueOf(genero));
                            Log.d("Lista Filmes", itemFilmes.get(0).getTitulo());
                            Log.d("RecyclerView", String.valueOf(recyclerView3.getId()));

                            MyAdapter adapter = new MyAdapter(itemFilmes);
                            recyclerView3.setAdapter(null);
                            recyclerView3.setAdapter(adapter);

                            if (Generos.GENRE_ACTION == genero) {
                                List<Filme> filmeAcao = itemFilmes;
                                adapter = new MyAdapter(filmeAcao, getApplicationContext());
                                recyclerView3.setAdapter(adapter);
                                int i = 9;
                            } else if (Generos.GENRE_ANIMATION == genero) {
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
    */

    private void inicializaAcoes() {
    }
}
