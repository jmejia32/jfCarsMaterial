package co.jamesfl.apps.jfcars;

import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;
import java.util.List;

public class Principal extends AppCompatActivity {
    private RecyclerView rvCarros;
    private Resources res;
    private CarAdapter.OnCarroClickListener carroClick;
    private CarAdapter adpCarros;
    private LinkedList<Carro> carros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        rvCarros = (RecyclerView) findViewById(R.id.rvCarros);
        res = getResources();
        carros = new LinkedList<>();

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        carroClick = new CarAdapter.OnCarroClickListener() {
            @Override
            public void onCarroClick(Carro c) {
                Intent i = new Intent(Principal.this, CarroDetalle.class);
                Bundle b = new Bundle();
                b.putInt("foto", c.getFoto());
                b.putString("placa", c.getPlaca());
                b.putString("modelo", c.getModelo());
                b.putString("marca", c.getMarca());
                b.putInt("precio", c.getPrecio());
                b.putString("id", c.getId());
                i.putExtras(b);
                startActivity(i);
                finish();
            }
        };

        adpCarros = new CarAdapter(this.getApplicationContext(), carros, carroClick);

        rvCarros.setLayoutManager(llm);
        rvCarros.setAdapter(adpCarros);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Principal.this, CarroForm.class);
                startActivity(i);
            }
        });

        DatabaseReference dbRef = AppLogic.getDbRef();
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                carros.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snp : dataSnapshot.getChildren()) {
                        Carro c = snp.getValue(Carro.class);
                        carros.add(c);
                    }
                }
                adpCarros.notifyDataSetChanged();
                AppLogic.setCarros(carros);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }
}
