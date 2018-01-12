/**
 * Created by user on 25/11/2017.
 */

package com.appdiccion.andresbertel.voiceapp_;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by CASA on 28/10/2017.
 */

class AdaptadorContactos extends RecyclerView.Adapter<AdaptadorContactos.ViewHolderContactos> {

    ArrayList<Contactos> listaContacto;
    String  nombrecon;
    String celularcon;
    String emailcon;



    public AdaptadorContactos( ArrayList<Contactos> listaContactos){
        this.listaContacto=listaContactos;
    }

    @Override
    public AdaptadorContactos.ViewHolderContactos onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.modelocontactos,null,false);
        return new ViewHolderContactos(vista);
    }

    @Override
    public void onBindViewHolder(AdaptadorContactos.ViewHolderContactos holder, int position) {
        final int pos = position;
        final TextView item;
        holder.nombrec.setText(""+listaContacto.get(position).getNOMBRE());
        holder.celularc.setText(listaContacto.get(position).getTELEFONO());
        holder.emailc.setText(listaContacto.get(position).getEMAIL());

        nombrecon=listaContacto.get(position).getNOMBRE();
        celularcon=listaContacto.get(position).getTELEFONO();
        emailcon=listaContacto.get(position).getEMAIL();


    }

    @Override
    public int getItemCount() {
        return listaContacto.size();
    }

    public class ViewHolderContactos extends RecyclerView.ViewHolder {

        TextView nombrec, celularc, emailc;
        Button btnactualizar,btneliminar;


        public ViewHolderContactos(View itemView) {
            super(itemView);
            nombrec=(TextView)itemView.findViewById(R.id.textVNombre);
            celularc=(TextView)itemView.findViewById(R.id.textVCelular);
            emailc=(TextView)itemView.findViewById(R.id.textVEmail);
            btnactualizar=(Button)itemView.findViewById(R.id.btnactualizar);
            btneliminar=(Button)itemView.findViewById(R.id.btneliminar);

        }
    }

    public ArrayList<Contactos> getSelectedUnitsist() {
        return listaContacto;
    }
}