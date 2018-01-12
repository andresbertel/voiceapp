package com.appdiccion.andresbertel.voiceapp_;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by CASA on 28/10/2017.
 */

public class AdaptadorPreguntas extends RecyclerView.Adapter<AdaptadorPreguntas.ViewHolderPreguntas> {

    ArrayList<PreguntasVo> listaPregunta;
    Integer Id;
    Integer Puntos;



    public AdaptadorPreguntas( ArrayList<PreguntasVo> listaPregunta){
        this.listaPregunta=listaPregunta;
    }

    @Override
    public AdaptadorPreguntas.ViewHolderPreguntas onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.modeloitem,null,false);
        return new ViewHolderPreguntas(vista);
    }

    @Override
    public void onBindViewHolder(AdaptadorPreguntas.ViewHolderPreguntas holder, int position) {
        final int pos = position;
        final TextView item;
        holder.item.setText(""+listaPregunta.get(position).getId());
        holder.pregunta.setText(listaPregunta.get(position).getPregunta());
        holder.puntos.setProgress(listaPregunta.get(position).getItem());

        Id=listaPregunta.get(position).getId();
        Puntos=listaPregunta.get(position).getPuntos();
        holder.puntos.setTag(listaPregunta.get(position));


        //holder.items.setText();

       // item.setTag(2);

        item=holder.items;
        Log.i("VALOR",""+listaPregunta.get(position).getId());




        holder.puntos.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               //Log.i("mas",""+progress);
                listaPregunta.get(pos).setItem(progress);
                item.setText(""+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return listaPregunta.size();
    }

    public class ViewHolderPreguntas extends RecyclerView.ViewHolder {

        TextView item, pregunta, items;
        SeekBar puntos;

        public ViewHolderPreguntas(View itemView) {
            super(itemView);
            item=(TextView)itemView.findViewById(R.id.textVNumero);
            pregunta=(TextView)itemView.findViewById(R.id.textVPregunta);
            puntos=(SeekBar)itemView.findViewById(R.id.seekBar);
            items=(TextView)itemView.findViewById(R.id.textView11);
        }
    }

    public ArrayList<PreguntasVo> getSelectedUnitsist() {
        return listaPregunta;
    }
}
