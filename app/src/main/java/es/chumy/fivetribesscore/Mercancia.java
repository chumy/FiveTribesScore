package es.chumy.fivetribesscore;

public class Mercancia {
    String nombre;
    int cantidad, total;

    public Mercancia(String nombre, int cantidad,int total) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.total=total;
    }

    public Mercancia(String nombre, int cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.total=1;
    }

    public Mercancia(String nombre) {
        this.nombre = nombre;
        this.cantidad = 0;
        this.total = 1;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getTotal() {
        return total;
    }
}
