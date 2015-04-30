package miprimerapp.proyecto.android.miprimeraapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Region;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    EditText edd_codi, etx_login, et_nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edd_codi=(EditText)findViewById(R.id.edd_codi);
        etx_login=(EditText)findViewById(R.id.etx_login);
        et_nombre=(EditText)findViewById(R.id.et_nombre);

    }
    public void alta(View v)
    {
       try {
           almacen admin = new almacen(this, "almacen", null, 1);
           SQLiteDatabase bd = admin.getWritableDatabase();

           String codi = edd_codi.getText().toString();
           String login = etx_login.getText().toString();
           String nombre = et_nombre.getText().toString();
           ContentValues registro = new ContentValues();
           registro.put("codi", codi);
           registro.put("login", login);
           registro.put("nombre", nombre);

           bd.insert("almacen", null, registro);
           bd.close();

           edd_codi.setText("");
           etx_login.setText("");
           et_nombre.setText("");

           Toast.makeText(this, "se agrego un nuevo usuario", Toast.LENGTH_LONG).show();
       }
       catch (Exception ex){
           Toast.makeText(this, "ERROR"+ex.getMessage(),Toast.LENGTH_LONG).show();

    }
    }
    public void consulta(View v){
        almacen admin = new almacen(this,"almacen",null,1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String codi = edd_codi.getText().toString();
        Cursor fila = bd.rawQuery("select login,nombre from almacen where codi ="+ codi, null);
        if (fila.moveToFirst()) {
            etx_login.setText(fila.getString(0));
            et_nombre.setText(fila.getString(1));
        }
        else {
            Toast.makeText(this,"no existe el usuario columna ",Toast.LENGTH_LONG).show();
        }
        bd.close();

    }
    public void  baja (View v){
        almacen admin = new almacen(this,"almacen" ,null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
       String codi = edd_codi.getText().toString();
        int cant = bd.delete("almacen","codi="+ codi,null);
        bd.close();
        edd_codi.setText("");
        etx_login.setText("");
        et_nombre.setText("");
        if (cant == 1) {
            Toast.makeText(this, "se elimino el usuario que se indico ", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "no existe el usuario que indica ", Toast.LENGTH_LONG).show();
        }
        bd.close();

        }
    public void modificar (View v) {
        almacen admin = new almacen(this, "almacen", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String codi = edd_codi.getText().toString();
        String login = etx_login.getText().toString();
        String nombre = et_nombre.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("codi", codi);
        registro.put("login", login);
        registro.put("nombre", nombre);
        int cant = bd.update("almacen", registro, "codi=" + codi, null);
        bd.close();
        if (cant == 1) {
            Toast.makeText(this, "se modificaron los datos del usuario ", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "no existe el usario que indico", Toast.LENGTH_LONG).show();
        }
    }
     public  void  limpiar(View v){
         edd_codi.setText("");
         etx_login.setText("");
         et_nombre.setText("");

     }
        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
