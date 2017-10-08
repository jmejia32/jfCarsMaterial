package co.jamesfl.apps.jfcars;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.LinkedList;

public class Listado extends AppCompatActivity {
    private ListView lstCarros;
    private Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        lstCarros = (ListView) findViewById(R.id.lstCarros);
        res = getResources();
        LinkedList<Carro> carros = AppLogic.getCarros();
        CarAdapter adp = new CarAdapter(this, carros);
        lstCarros.setAdapter(adp);
    }
}
