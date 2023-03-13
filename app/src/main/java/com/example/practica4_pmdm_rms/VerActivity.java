package com.example.practica4_pmdm_rms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.practica4_pmdm_rms.db.dbContactos;
import com.example.practica4_pmdm_rms.entidades.Contactos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VerActivity extends AppCompatActivity {

    EditText txtNombre,txtTelefono,txtCorreo;
    Button btnGuarda;
    FloatingActionButton fbeditar;
    Contactos contaco;

    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);
        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono2);
        txtCorreo = findViewById(R.id.txtEmail);
        btnGuarda = findViewById(R.id.btnGuarda);
        fbeditar = findViewById(R.id.editar);

        if(savedInstanceState ==null){
            Bundle extras = getIntent().getExtras();

            if(extras ==null){
                id = Integer.parseInt(null);
            }else{
                id = extras.getInt("ID");

            }
        }else{
            id = (int) savedInstanceState.getSerializable("ID");
        }

        dbContactos dbcontactos = new dbContactos(VerActivity.this);
        contaco = dbcontactos.verContacto(id);

        if(contaco!=null){
            txtNombre.setText(contaco.getNombre());
            txtTelefono.setText(contaco.getTelefono());
            txtCorreo.setText(contaco.getCorreo_electornico());
            btnGuarda.setVisibility(View.INVISIBLE );
            txtTelefono.setInputType(InputType.TYPE_NULL);
            txtNombre.setInputType(InputType.TYPE_NULL);
            txtCorreo.setInputType(InputType.TYPE_NULL);

        }
        fbeditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VerActivity.this,EditarActivity.class);
                intent.putExtra("ID",id);
                startActivity(intent);
            }
        });
    }
}