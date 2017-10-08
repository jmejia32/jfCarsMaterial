package co.jamesfl.apps.jfcars;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class Principal extends AppCompatActivity {
    private ListView menu;
    private Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        menu = (ListView) findViewById(R.id.lstMenu);
        res = getResources();

        ArrayAdapter<String> adp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, res.getStringArray(R.array.menu));
        menu.setAdapter(adp);
        menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Intent i = null;
                switch (pos) {
                    case 0: //Listar
                        i = new Intent(Principal.this, Listado.class);
                        break;
                    case 1: //Agregar
                        i = new Intent(Principal.this, Crear.class);
                        break;
                }
                startActivity(i);
            }
        });
    }
}
