package com.example.practica4_pmdm_rms;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.practica4_pmdm_rms.db.dbContactos;
import com.example.practica4_pmdm_rms.entidades.Contactos;

public class EditarActivity extends AppCompatActivity {

    EditText txtNombre,txtTelefono,txtCorreo;
    Button btnGuarda;
    Contactos contaco;
    boolean correcto = false;

    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);
        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono2);
        txtCorreo = findViewById(R.id.txtEmail);
        btnGuarda = findViewById(R.id.btnGuarda);

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

        dbContactos dbcontactos = new dbContactos(EditarActivity.this);
        contaco = dbcontactos.verContacto(id);

        if(contaco!=null){
            txtNombre.setText(contaco.getNombre());
            txtTelefono.setText(contaco.getTelefono());
            txtCorreo.setText(contaco.getCorreo_electornico());


        }
        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txtNombre.getText().equals("")){
                   correcto = dbcontactos.editarContacto(id,txtNombre.getText().toString(),txtTelefono.getText().toString(),txtNombre.getText().toString());

                   if(correcto){
                       Toast.makeText(EditarActivity.this,"Registro Modificado",Toast.LENGTH_LONG).show();
                       verRegistros();
                   }else {
                       Toast.makeText(EditarActivity.this,"Error al modificar",Toast.LENGTH_LONG).show();
                   }
                }else{
                    Toast.makeText(EditarActivity.this,"Debe llenar los datos",Toast.LENGTH_LONG).show();
                };

            }
        });
    }

    private void verRegistros(){
        Intent intent = new Intent(this,NegativeArraySizeException.class);
        intent.putExtra("ID",id);
        startActivity(intent);

    }


}