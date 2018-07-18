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

import java.util.List;

public class MyAdapterTeste extends RecyclerView.Adapter<MyAdapterTeste.ViewHolder> {

    private List<Filme> listItens;
    private Context context;

    public MyAdapterTeste(List<Filme> listItens, Context context) {
        this.listItens = listItens;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.celula_titulo_baixo, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Filme itemFilme = listItens.get(position);

        holder.tv_titulo.setText(itemFilme.getTitulo());
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + itemFilme.getImageUrl())
                .into(holder.iv_imagem);

        //Log.d("MyAdapterTeste", itemFilme.getTitulo());

    }

    @Override
    public int getItemCount() {
        return listItens.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tv_titulo;
        public ImageView iv_imagem;

        public ViewHolder(View itemView) {
            super(itemView);

            tv_titulo = itemView.findViewById(R.id.celula_tv);
            iv_imagem = itemView.findViewById(R.id.celula_iv);
        }
    }


}
