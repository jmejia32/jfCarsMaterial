package co.jamesfl.apps.jfcars;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Arrays;
import java.util.LinkedList;

public class Crear extends AppCompatActivity {
    private EditText etPlaca, etPrecio;
    private Spinner spMarca, spModelo;
    private Resources res;
    private LinkedList<String> marcas, modelos;
    private LinkedList<Integer> fotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear);

        etPlaca = (EditText) findViewById(R.id.etPlaca);
        etPrecio = (EditText) findViewById(R.id.etPrecio);
        spMarca = (Spinner) findViewById(R.id.spMarca);
        spModelo = (Spinner) findViewById(R.id.spModelo);
        res = getResources();

        marcas = new LinkedList<>(Arrays.asList(res.getStringArray(R.array.marcas)));
        marcas.addFirst(res.getString(R.string.itemVacio));

        modelos = new LinkedList<>(Arrays.asList(res.getStringArray(R.array.modelos)));
        modelos.addFirst(res.getString(R.string.itemVacio));

        ArrayAdapter<String> adpMarca = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, marcas);
        spMarca.setAdapter(adpMarca);

        ArrayAdapter<String> adpModelo = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, modelos);
        spModelo.setAdapter(adpModelo);

        fotos = new LinkedList<>();
        fotos.add(R.drawable.mclaren_500x500);
        fotos.add(R.drawable.ford_fiesta);
        fotos.add(R.drawable.nuevas_carros);
    }

    private boolean valido() {
        if (etPlaca.getText().toString().isEmpty()) {
            etPlaca.setError(res.getString(R.string.errPlaca));
            etPlaca.requestFocus();
            return false;
        }
        return true;
    }

    public void agregar(View v) {
        if (valido()) {
            String placa, marca, modelo;
            int foto, precio;

            placa = etPlaca.getText().toString();
            marca = marcas.get(spMarca.getSelectedItemPosition());
            modelo = modelos.get(spModelo.getSelectedItemPosition());

            foto = AppLogic.fotoAleatoria(fotos);
            precio = Integer.parseInt(etPrecio.getText().toString());

            Carro c = new Carro(placa, marca, modelo, precio);
            c.setFoto(foto);
            c.guardar();

            Toast.makeText(this, res.getString(R.string.msjCarro), Toast.LENGTH_LONG).show();
            onBackPressed();
        }
    }
}
