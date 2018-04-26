package com.appdiccion.andresbertel.voiceapp_.ListaContactos;

/**
 * Created by user on 27/11/2017.
 */


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.appdiccion.andresbertel.voiceapp_.Contactos;
import com.appdiccion.andresbertel.voiceapp_.MiBaseDatos;
import com.appdiccion.andresbertel.voiceapp_.R;
import com.appdiccion.andresbertel.voiceapp_.actualizarContacto;

import java.util.List;

/**
 *
 */
public class MaterialPaletteAdapter extends RecyclerView.Adapter<MaterialPaletteAdapter.PaletteViewHolder> {
    private List<Contactos> data;
    private RecyclerViewOnItemClickListener recyclerViewOnItemClickListener;
    MiBaseDatos MDB;
    AlertDialog.Builder dialogo1;

    public MaterialPaletteAdapter(@NonNull List<Contactos> data,
                                  @NonNull RecyclerViewOnItemClickListener
                                          recyclerViewOnItemClickListener) {
        this.data = data;
        this.recyclerViewOnItemClickListener = recyclerViewOnItemClickListener;

    }

    @Override
    public PaletteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new PaletteViewHolder(row);
    }

    @Override
    public void onBindViewHolder(PaletteViewHolder holder, int position) {
        Contactos contacto = data.get(position);



        holder.getTitleTextView().setText(contacto.getNOMBRE());
        holder.getSubtitleTextView().setText(contacto.getEMAIL());
        holder.getNombreContacto().setText(contacto.getTELEFONO());


       /* GradientDrawable gradientDrawable = (GradientDrawable) holder.getCircleView().getBackground();
        int colorId = android.graphics.Color.parseColor(color.getHex());
        gradientDrawable.setColor(colorId);*/
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class PaletteViewHolder extends RecyclerView.ViewHolder implements View
            .OnClickListener {

        private TextView titleTextView;
        private TextView subtitleTextView;
        private TextView NombreContacto;
        private Button BtnEliminar;
        private Button BtnActualizar;


        public PaletteViewHolder(View itemView) {
            super(itemView);
            MDB = new MiBaseDatos(itemView.getContext());


            titleTextView = (TextView) itemView.findViewById(R.id.titleTextView);
            subtitleTextView = (TextView) itemView.findViewById(R.id.subtitleTextView);
            NombreContacto= (TextView) itemView.findViewById(R.id.nombrecontacto);
            BtnEliminar=(Button) itemView.findViewById(R.id.btneliminar);
            BtnActualizar=(Button) itemView.findViewById(R.id.btnactualizar);

            //itemView.setOnClickListener(this);

            BtnActualizar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Actualizar(view);



                }
            });


            BtnEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Eliminar(view);


                }
            });
        }



        public void Actualizar(View view){
            Intent irActualizar= new Intent(view.getContext(),actualizarContacto.class);
            irActualizar.putExtra("id",data.get(getAdapterPosition()).getID());
            irActualizar.putExtra("nombre",data.get(getAdapterPosition()).getNOMBRE());
            irActualizar.putExtra("celular",data.get(getAdapterPosition()).getTELEFONO());
            irActualizar.putExtra("email",data.get(getAdapterPosition()).getEMAIL());
            view.getContext().startActivity(irActualizar);

        }


        public void Eliminar(View view){
           // Toast.makeText(view.getContext(),"Entre",Toast.LENGTH_SHORT).show();





                dialogo1 = new AlertDialog.Builder(itemView.getContext());
                dialogo1.setTitle("Eliminar");
                dialogo1.setMessage("¿Desea eliminar este contacto?");
                dialogo1.setCancelable(false);
                dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        MDB.borrarCONTACTO(data.get(getAdapterPosition()).getID());
                        removeAt(getAdapterPosition());

                    }
                });
                dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {

                    }
                });
                dialogo1.show();







        }

        public void removeAt(int position) {
            data.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, data.size());
        }

        public TextView getTitleTextView() {
            return titleTextView;
        }

        public TextView getSubtitleTextView() {
            return subtitleTextView;
        }



        public TextView getNombreContacto() {
            return NombreContacto;
        }

        @Override
        public void onClick(View v) {
            recyclerViewOnItemClickListener.onClick(v, getAdapterPosition());
        }
    }

}

