package co.jamesfl.apps.jfcars;

import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by javie on 7/10/2017.
 */

public class AppLogic {
    public static LinkedList<Carro> carros = new LinkedList<>();
    private static String db = "Carros";
    private static DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();

    public static LinkedList<Carro> getCarros() {
        return carros;
    }

    public static void guardarCarro(Carro c) {
        if(c.getId() == null) {
           c.setId(dbRef.push().getKey());
        }
        getDbRef().child(c.getId()).setValue(c);
    }

    public static void eliminarCarro(Carro c) {
        getDbRef().child(c.getId()).removeValue();
    }

    public static int fotoAleatoria(LinkedList<Integer> fotos){
        Random r = new Random();
        int foto = r.nextInt(fotos.size());
        return fotos.get(foto);
    }

    public static DatabaseReference getDbRef() {
        return dbRef.child(db);
    }
    public static void setCarros(LinkedList<Carro> l) { carros = l; }

    public static boolean etNoVacio(EditText et, String msj) {
        if (et.getText().toString().isEmpty()) {
            et.setError(msj);
            et.requestFocus();
            return false;
        } else {
            et.setError(null);
        }
        return true;
    }

    public static boolean spinnerNoVacio(Spinner sp, String msj) {
        View v = sp.getSelectedView();
        TextView tv = v != null ? (TextView) v : null;
        if (sp.getSelectedItemPosition() == 0) {
            if (tv != null) {
                tv.setError("1");
                sp.requestFocus();
            }
            Snackbar.make(sp, msj, Snackbar.LENGTH_LONG).show();
            return false;
        } else {
            if (tv != null) tv.setError(null);
            return true;
        }
    }

    public static boolean existePlaca(LinkedList<Carro> carros, Carro c) {
        for (Carro car : carros) {
            if (car.getId().equals(c.getId())) continue;
            if (car.getPlaca().equals(c.getPlaca())) return true;
        }
        return false;
    }
}
