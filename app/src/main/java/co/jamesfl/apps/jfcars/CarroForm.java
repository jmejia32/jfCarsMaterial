package co.jamesfl.apps.jfcars;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;
import java.util.LinkedList;

public class CarroForm extends AppCompatActivity {
    private EditText etPlaca, etPrecio;
    private Spinner spMarca, spModelo;
    private Resources res;
    private LinkedList<String> marcas, modelos;
    private LinkedList<Integer> fotos;
    private Bundle b;
    private boolean editable;
    private Carro c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carro_form);

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

        b = getIntent().getExtras();
        if (b != null) {
            etPlaca.setText(b.getString("placa"));
            etPrecio.setText(b.getInt("precio")+"");
            spMarca.setSelection(marcas.indexOf(b.getString("marca")));
            spModelo.setSelection(modelos.indexOf(b.getString("modelo")));
            editable = true;
        } else {
            editable = false;
        }
        TextView tvTitulo = (TextView) findViewById(R.id.tvTitulo);
        String tit = res.getString(editable ? R.string.titEdit : R.string.titCrear);
        tvTitulo.setText(tit);
    }

    private boolean validar() {
        if (AppLogic.etNoVacio(etPlaca, res.getString(R.string.errPlaca)) &&
                AppLogic.spinnerNoVacio(spMarca, res.getString(R.string.errMarca)) &&
                AppLogic.spinnerNoVacio(spModelo, res.getString(R.string.errModelo)) &&
                AppLogic.etNoVacio(etPrecio, res.getString(R.string.errPrecio))
                ) {
            c = new Carro(etPlaca.getText().toString(), marcas.get(spMarca.getSelectedItemPosition()),
                    modelos.get(spModelo.getSelectedItemPosition()), Integer.parseInt(etPrecio.getText().toString()));
            c.setFoto(editable ? b.getInt("foto") : AppLogic.fotoAleatoria(fotos));
            c.setId(editable ? b.getString("id") : null);
            if (AppLogic.existePlaca(AppLogic.getCarros(), c)) {
                etPlaca.setError(res.getString(R.string.errPlacaRep));
                etPlaca.requestFocus();
            } else {
                etPlaca.setError(null);
                return true;
            }
        }
        return false;
    }

    public void guardar(View v) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        if (validar()) {
            String msj = editable ? res.getString(R.string.msjCarroEdit) : res.getString(R.string.msjCarro);
            c.guardar();
            Snackbar.make(v, msj, Snackbar.LENGTH_LONG).addCallback(new Snackbar.Callback() {
                @Override
                public void onDismissed(Snackbar transientBottomBar, int event) {
                    onBackPressed();
                }
            }).show();
        }
        if (!editable) limpiar();
    }

    private void limpiar() {
        etPlaca.setText("");
        spMarca.setSelection(0);
        spModelo.setSelection(0);
        etPrecio.setText("");
        etPlaca.requestFocus();
    }

    public void onBackPressed(){
        Intent i = new Intent(CarroForm.this, Principal.class);
        startActivity(i);
        finish();
    }
}
