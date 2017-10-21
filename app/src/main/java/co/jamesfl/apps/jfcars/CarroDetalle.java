package co.jamesfl.apps.jfcars;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;

public class CarroDetalle extends AppCompatActivity {
    private CollapsingToolbarLayout ctl;
    private Bundle b;
    private Resources res;
    private Carro c;
    private String id, placa, marca, modelo;
    private int foto, precio;
    private ImageView ivFoto;
    private LinkedList<String> marcas, modelos;
    private TextView tvMarca, tvModelo, tvPrecio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carro_detalle);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        tvMarca = (TextView) findViewById(R.id.tvMarcaD);
        tvModelo = (TextView) findViewById(R.id.tvModeloD);
        tvPrecio = (TextView) findViewById(R.id.tvPrecioD);

        ctl = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        ivFoto = (ImageView) findViewById(R.id.ivFotoD);
        res = getResources();
        b = getIntent().getExtras();

        id = b.getString("id");
        placa = b.getString("placa");
        marca = b.getString("marca");
        modelo = b.getString("modelo");
        precio = b.getInt("precio");
        foto = b.getInt("foto");

        ctl.setTitle(placa);
        ivFoto.setImageDrawable(ResourcesCompat.getDrawable(res,foto,null));
        tvMarca.setText(marca);
        tvModelo.setText(modelo);
        tvPrecio.setText(precio+"");
    }

    public void eliminar(View v) {
        String pos, neg;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(res.getString(R.string.elimTitulo));
        builder.setMessage(res.getString(R.string.elimMsj));
        pos = res.getString(R.string.Si);
        neg = res.getString(R.string.No);
        builder.setPositiveButton(pos, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Carro c = new Carro(id);
                c.eliminar();
                onBackPressed();
            }
        });
        builder.setNegativeButton(neg, null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void editar(View v) {
        Intent i = new Intent(CarroDetalle.this, CarroForm.class);
        i.putExtras(b);
        startActivity(i);
    }
}
