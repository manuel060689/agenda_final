package com.example.pf_agenda_morales_cp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText idcontacto,nombre, apellidos, telefono, apodo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        idcontacto = findViewById(R.id.etIdContacto);
        nombre = findViewById(R.id.etNombre);
        apellidos = findViewById(R.id.etApellidos);
        telefono = findViewById(R.id.etTelefono);
        apodo = findViewById(R.id.etApodo);

    }

    public void altaContacto(View view){
       AdminSQLiteOpenHelp admin = new AdminSQLiteOpenHelp(this,"administracion",null,1);
        SQLiteDatabase bd = admin.getWritableDatabase();//base de datos disponible para escritura

        //capturamos del formulario para procesarlos a la tabla articulo
        String Idcontacto = idcontacto.getText().toString();
        String Nombre = nombre.getText().toString();
        String Apellidos = apellidos.getText().toString();
        String Telefono = telefono.getText().toString();
        String Apodo = apodo.getText().toString();

        //se crea un contenedorde variables para inyectar los valores a la tabla
        ContentValues registro = new ContentValues();

        //se integra los campos de la tabla paciente con los valores del formulario
        registro.put("idcontacto", Idcontacto);
        registro.put("nombre", Nombre);
        registro.put("apellidos", Apellidos);
        registro.put("telefono", Telefono);
        registro.put("apodo", Apodo);

        //se inserta registro en la tabla articulo
        bd.insert("agenda",null, registro);
        bd.close();

        //limpiar campos de formulario
        idcontacto.setText("");
        nombre.setText("");
        apellidos.setText("");
        telefono.setText("");
        apodo.setText("");

        //ventana emergente
        Toast.makeText(this,"Exito al agregar \nContacto", Toast.LENGTH_LONG).show();
    } //termina metodo alta


    //metodo por consultar por campo distintivo
    public void consultarContacto(View view){
//objeto de administracion a la base de datos sqlite
       AdminSQLiteOpenHelp admin = new AdminSQLiteOpenHelp(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();//base de datos disponible para escritura

        //Campo distintivo de busqueda a la tabla agenda
        String IdContacto = idcontacto.getText().toString();

        //cursor
        Cursor fila = bd.rawQuery("select  nombre ,apellidos, telefono, apodo from agenda where idcontacto=" + IdContacto, null);

        if (fila.moveToFirst()) {
            nombre .setText(fila.getString(0));
            apellidos .setText(fila.getString(1));
            telefono.setText(fila.getString(2));
            apodo.setText(fila.getString(3));
            //Calcular.setText(fila.getString(2));
        } else {
            //ventana emergente
            Toast.makeText(this, "Error no existe nombre de \nContacto", Toast.LENGTH_LONG).show();
        }

    }//termina metodo consultar

    //metodo para eliminar porducto
    public void eliminarContacto(View view){
        //objeto de administracion a la base de datos sqlite
       AdminSQLiteOpenHelp admin = new AdminSQLiteOpenHelp(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();//base de datos disponible para escritura

        //CAmpo distintivo de para eliminar desde la tabla articulo
        String ContactoBaja = idcontacto.getText().toString();

        //se genera la instruccion SQL para dar de baja un registro mediante un campo distintivo
        int confirmaBaja = bd.delete("agenda","idcontacto="+ContactoBaja,null);
        bd.close();
        if(confirmaBaja==1){
            Toast.makeText(this,"¡¡Registro eliminado!!", Toast.LENGTH_LONG).show();
            this.idcontacto.setText(null);
            this.nombre.setText(null);
            this.apellidos.setText(null);
            this.telefono.setText(null);
            this.apodo.setText(null);

        }else{
            Toast.makeText(this,"¡¡Error contacto inexistente\nVerificar!!", Toast.LENGTH_LONG).show();

        }

    }//termina metodo

    public void editarContacto(View view){
        AdminSQLiteOpenHelp admin = new AdminSQLiteOpenHelp(this,"administracion",null,1);
        SQLiteDatabase bd = admin.getWritableDatabase();//base de datos disponible para escritura

        //capturamos del formulario para procesarlos a la tabla articulo
        String IdContacto = idcontacto.getText().toString();
        String Nombre = nombre.getText().toString();
        String Apellidos = apellidos.getText().toString();
        String Telefono = telefono.getText().toString();
        String Apodo = apodo.getText().toString();

        //se crea un contenedorde variables para inyectar los valores a la tabla
        ContentValues registro = new ContentValues();

        //se integra los campos de la tabla contacto con los valores del formulario
        registro.put("idcontacto", IdContacto);
        registro.put("nombre", Nombre);
        registro.put("apellidos", Apellidos);
        registro.put("telefono", Telefono);
        registro.put("apodo", Apodo);

        //se actualiza registro en la tabla articulo
        int actualizacion=bd.update("agenda", registro, "idcontacto="+IdContacto, null);
        bd.close();

        //limpiar campos de formulario
        idcontacto.setText("");
        nombre.setText("");
        apellidos.setText("");
        telefono.setText("");
        apodo.setText("");

        if (actualizacion == 1) {
        //ventana emergente
        Toast.makeText(this,"Exito al actualizar \nContacto", Toast.LENGTH_LONG).show();
        } else {

            Toast.makeText(this, "Error ningun contacto \nActualizado", Toast.LENGTH_LONG).show();
        }
    } //termina metodo alta

    public void abrirCamara(View view){
        Intent intent5=new Intent("android.media.action.IMAGE_CAPTURE");
        startActivity(intent5);
    }
    public void abrirTelefonia(View view){
        Intent intent2=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:(+52)5544641797"));
        startActivity(intent2);
    }

}