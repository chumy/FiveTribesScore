package es.chumy.fivetribesscore;

import es.chumy.fivetribesscore.Djinn;

class Jugador {

    private int sabios, nobles, monedas, camellos, castillos, palmeras, mercado;
    private Djinn[] djinns;
    private String nombre;
    private boolean maxNobles;


    Jugador(String nombre) {
        this.nombre = nombre;
        this.sabios = 0;
        this.monedas = 0;
        this.camellos = 0;
        this.castillos = 0;
        this.palmeras = 0;
        this.nobles = 0;
        this.djinns = new Djinn[]{};
        this.maxNobles = false;

    }

    int getMultNobles(){
        int noble = 1;
        for (Djinn djinn : this.djinns) {
            noble = (djinn.mult_nobles > noble) ? djinn.mult_nobles : noble;
        }
        return  noble;
    }

    int getMultSabios(){
        int sabio = 2;
        for (Djinn djin : djinns){
            sabio = (djin.mult_sabios > sabio) ? djin.mult_sabios : sabio;
        }
        return  sabio;
    }

    int getMultPalmera(){
        int palmera = 3;
        for (Djinn djin : djinns){
            palmera = (djin.mult_palmeras > palmera) ? djin.mult_palmeras : palmera;
        }
        return  palmera;
    }

    int getMultCastillo(){
        return  5;
    }


    boolean isMaxNobles() {
        return maxNobles;
    }

    void setMaxNobles(boolean maxNobles) {
        this.maxNobles = maxNobles;
    }

    void setValue(int valor, String tipo){
        switch (tipo) {
            case "sabio":
                this.sabios = valor;
                break;
            case "castillo":
                this.castillos = valor;
                break;
            case "palmera":
                this.palmeras = valor;
                break;
            case "noble":
                this.nobles = valor;
                break;
            case "mercado":
                this.mercado = valor;
                break;
            case "moneda":
                this.monedas =valor;
                break;
            case "camello":
                this.camellos = valor;
                break;

        }
    }

    int getValue(String tipo){
        switch (tipo) {
            case "sabio":
                return this.sabios;
            case "castillo":
                return this.castillos;
            case "palmera":
                return this.palmeras;
            case "noble":
                return this.nobles;
            case "mercado":
                return this.mercado;
            case "moneda":
                return this.monedas;
            case "camello":
                return this.camellos;
            default:
                return 0;
        }
    }

    public int getSabios() {
        return sabios;
    }

    public void setSabios(int sabios) {
        this.sabios = sabios;
    }

    public int getNobles() {
        return nobles;
    }

    public void setNobles(int nobles) {
        this.nobles = nobles;
    }

    public int getMonedas() {
        return monedas;
    }

    public void setMonedas(int monedas) {
        this.monedas = monedas;
    }

    public int getCamellos() {
        return camellos;
    }

    public void setCamellos(int camellos) {
        this.camellos = camellos;
    }

    public int getCastillos() {
        return castillos;
    }

    public void setCastillos(int castillos) {
        this.castillos = castillos;
    }

    public int getPalmeras() {
        return palmeras;
    }

    public void setPalmeras(int palmeras) {
        this.palmeras = palmeras;
    }

    public int getMercado() {
        return mercado;
    }

    public void setMercado(int mercado) {
        this.mercado = mercado;
    }

    public Djinn[] getDjinns() {
        return djinns;
    }

    public void setDjinns(Djinn[] djinns) {
        this.djinns = djinns;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
