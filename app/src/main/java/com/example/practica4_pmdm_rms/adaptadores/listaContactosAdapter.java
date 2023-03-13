package com.example.practica4_pmdm_rms.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practica4_pmdm_rms.R;
import com.example.practica4_pmdm_rms.VerActivity;
import com.example.practica4_pmdm_rms.entidades.Contactos;

import java.util.ArrayList;

public class listaContactosAdapter extends RecyclerView.Adapter<listaContactosAdapter.ContactoViewHolder> {

    ArrayList<Contactos> listaContactos;

    public listaContactosAdapter(ArrayList<Contactos> listaContactos){
        this.listaContactos = listaContactos;
    }

    @NonNull
    @Override
    public listaContactosAdapter.ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_contacto,null,false);
      return new ContactoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull listaContactosAdapter.ContactoViewHolder holder, int position) {
        holder.viewNombre.setText(listaContactos.get(position).getNombre());
        holder.viewTelefono.setText(listaContactos.get(position).getTelefono());
        holder.viewCorreo.setText(listaContactos.get(position).getCorreo_electornico());

    }

    @Override
    public int getItemCount() {
       return listaContactos.size();
    }

    public class ContactoViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombre,viewTelefono,viewCorreo;
        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);
            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewTelefono = itemView.findViewById(R.id.viewTelefono);
            viewCorreo = itemView.findViewById(R.id.viewCorreo);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, VerActivity.class);
                    intent.putExtra("ID",listaContactos.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}