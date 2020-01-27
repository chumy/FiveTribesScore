package es.chumy.fivetribesscore;

class Djinn {

    private String nombre;
    private int puntos;
    int mult_sabios, mult_nobles, mult_palmeras;


    Djinn(String nombre, int puntos, int mult_nobles, int mult_sabios , int mult_palmeras) {
        this.nombre = nombre;
        this.puntos = puntos;
        this.mult_nobles = mult_nobles;
        this.mult_sabios = mult_sabios;
        this.mult_palmeras = mult_palmeras;

    }

    Djinn(String nombre, int puntos) {
        this.nombre = nombre;
        this.puntos = puntos;
        this.mult_nobles = 1;
        this.mult_sabios = 2;
        this.mult_palmeras = 3;

    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntos() {
        return puntos;
    }

    public int getMult_sabios() {
        return mult_sabios;
    }

    public int getMult_nobles() {
        return mult_nobles;
    }

    public int getMult_palmeras() {
        return mult_palmeras;
    }
}
