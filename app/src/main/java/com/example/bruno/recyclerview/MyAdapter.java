package com.example.bruno.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Filme> listItens;

    public MyAdapter(List<Filme> listItens) {
        this.listItens = listItens;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.celula_titulo_baixo, parent, false);
        return new ViewHolder(v, parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Filme itemFilme = listItens.get(position);
        holder.bind(itemFilme);
        Log.d("MyAdapter", itemFilme.getTitulo());
    }

    @Override
    public int getItemCount() {
        return listItens.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tv_titulo;
        public ImageView iv_imagem;
        private Context context;

        public ViewHolder(View itemView, Context context) {
            super(itemView);

            this.context = context;
            tv_titulo = itemView.findViewById(R.id.celula_tv);
            iv_imagem = itemView.findViewById(R.id.celula_iv);
        }

        public void bind(Filme filme) {
            tv_titulo.setText(filme.getTitulo());
            Picasso.get().load("https://image.tmdb.org/t/p/w500" + filme.getImageUrl())
                    .into(iv_imagem);


            //Log.d("Imagens", "https://image.tmdb.org/t/p/w500" + filme.getImageUrl());
        }
    }


}
