package com.br.marcio.campos.pastel4you.adpter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.br.marcio.campos.pastel4you.R;
import com.br.marcio.campos.pastel4you.domain.Pastel;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Marcio on 15/11/2015.
 */

// Herda de RecyclerView.Adapter e declara o tipo genérico <CarroAdapterV2.CarrosViewHolder>
public class PastelAdpter extends RecyclerView.Adapter<PastelAdpter.PastelViewHolder> {
    protected static final String TAG = "pastel4you";
    private final List<Pastel> pasteis;
    private final PastelOnClickListener pastelOnClickListener;
    private Context context;



    public PastelAdpter(Context context, List<Pastel> pasteis, PastelOnClickListener pastelOnClickListener){
        this.context = context;
        this.pasteis = pasteis;
        this.pastelOnClickListener = pastelOnClickListener;
    }
    @Override
    public int getItemCount() {
        return this.pasteis != null ? this.pasteis.size() : 0;
    }

    @Override
    public PastelViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Infla a view do layout
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_pastel, viewGroup, false);

        CardView cardView = (CardView) view.findViewById(R.id.card_view);

        // Criar o viewHolder
        PastelViewHolder holder = new PastelViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final PastelViewHolder holder, final int position) {

        // Atualiza a view
        Pastel p = pasteis.get(position);

        holder.tNome.setText(p.getNamePastel());
        holder.progress.setVisibility(View.VISIBLE);

        Picasso.with(context).load(p.getUrlFoto()).fit().into(holder.img, new Callback() {
            @Override
            public void onSuccess() {
                holder.progress.setVisibility(View.GONE);
            }

            @Override
            public void onError() {
                holder.progress.setVisibility(View.GONE);
            }
        });

        // Click
        if (pastelOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pastelOnClickListener.onClickPastel(holder.itemView, position);
                    // A variável position é final
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener(){

                @Override
                public boolean onLongClick(View v) {
                    pastelOnClickListener.onLongClickPastel(holder.itemView, position);
                    return true;
                }
            });
        }

    }


    // Falta pintar

    // ViewHolder com as views
    public static class PastelViewHolder extends RecyclerView.ViewHolder {
        public TextView tNome;
        ImageView img;
        ProgressBar progress;
        public CardView cardView;

        public PastelViewHolder(View view) {
            super(view);
            // Cria as views para salvar no ViewHolder
            tNome = (TextView) view.findViewById(R.id.text);
            //img = (ImageView) view.findViewById(R.id.img);
            progress = (ProgressBar) view.findViewById(R.id.progressImg);
            cardView = (CardView) view.findViewById(R.id.card_view);
        }
    }

    public interface PastelOnClickListener {
        public void onClickPastel (View view, int idx);
        public void onLongClickPastel (View view, int idx);
    }

}
