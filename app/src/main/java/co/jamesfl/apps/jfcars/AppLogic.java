package co.jamesfl.apps.jfcars;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by javie on 7/10/2017.
 */

public class AppLogic {
    public static LinkedList<Carro> carros = new LinkedList<>();

    public static LinkedList<Carro> getCarros() {
        return carros;
    }

    public static void guardarCarro(Carro c) {
        carros.add(c);
    }

    public static int fotoAleatoria(LinkedList<Integer> fotos){
        Random r = new Random();
        int foto = r.nextInt(fotos.size());
        return fotos.get(foto);
    }
}
