package co.jamesfl.apps.jfcars;

/**
 * Created by javie on 7/10/2017.
 */

public class Carro {
    private String placa, marca, modelo;
    private int precio, foto;

    public Carro(String placa, String marca, String modelo, int precio) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public void guardar() {
        AppLogic.guardarCarro(this);
    }
}
