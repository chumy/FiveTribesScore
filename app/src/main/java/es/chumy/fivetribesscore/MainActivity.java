package es.chumy.fivetribesscore;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String[] numericos = { "sabio", "castillo", "noble", "palmera"};

    public static String[] monedas = new String[] {"1","5"};

    public static String[] camellos = new String[] {"4","5","6","8","10","12","15"};

    public static final List<Djinn> DjinnList = new ArrayList<Djinn>(){{
        add(new Djinn("Al-Amin", 5));
        add(new Djinn("Anun-Nak", 8));
        add(new Djinn("Ba'Ai", 6));
        add(new Djinn("Boaz", 6));
        add(new Djinn("Bouraq", 6));
        add(new Djinn("Echidna", 4));
        add(new Djinn("Enki", 8));
        add(new Djinn("Hagis", 10));
        add(new Djinn("Haurvatat", 8, 1,2, 5));
        add(new Djinn("Iblis", 8));
        add(new Djinn("Jafaar", 6, 3,2,3));
        add(new Djinn("Kandicha", 6));
        add(new Djinn("Kumarbi", 6));
        add(new Djinn("Lamia", 10));
        add(new Djinn("Leta", 4));
        add(new Djinn("Marid", 6));
        add(new Djinn("Monkir", 6));
        add(new Djinn("Nekir", 6));
        add(new Djinn("Shamhat", 6, 1 ,4 ,3));
        add(new Djinn("Sibittis", 4));
        add(new Djinn("Sidar", 8));
        add(new Djinn("Utug", 4));
    }};

    //Mapeo de casillas

    private int[] casillasMoneda = { R.id.tv_moneda0, R.id.tv_moneda1,R.id.tv_moneda2, R.id.tv_moneda3};
    private int[] casillasNoble = { R.id.tv_noble0, R.id.tv_noble1,R.id.tv_noble2, R.id.tv_noble3};
    private int[] casillasSabio= { R.id.tv_sabio0, R.id.tv_sabio1,R.id.tv_sabio2, R.id.tv_sabio3};
    private int[] casillasDjinn = { R.id.tv_djinn0, R.id.tv_djinn1,R.id.tv_djinn2, R.id.tv_djinn3};
    private int[] casillasPalmera = { R.id.tv_palmera0, R.id.tv_palmera1,R.id.tv_palmera2, R.id.tv_palmera3};
    private int[] casillasCastillo = { R.id.tv_castillo0, R.id.tv_castillo1,R.id.tv_castillo2, R.id.tv_castillo3};
    private int[] casillasCamello= { R.id.tv_camello0, R.id.tv_camello1,R.id.tv_camello2, R.id.tv_camello3};
    private int[] casillasMercado = { R.id.tv_mercado0, R.id.tv_mercado1,R.id.tv_mercado2, R.id.tv_mercado3};

    AlertDialog modal, modal_text;
    EditText et_modal, et_modal_text;


    public Jugador[] jugadores;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toast.makeText(getApplicationContext(), "Evento on Create "  , Toast.LENGTH_LONG).show();

        jugadores = new Jugador[]{
                new Jugador(getResources().getString(R.string.jugador1)),
                new Jugador(getResources().getString(R.string.jugador2)),
                new Jugador(getResources().getString(R.string.jugador3)),
                new Jugador(getResources().getString(R.string.jugador4))
        };



        modal = new AlertDialog.Builder(this).create();
        et_modal = new EditText(this);
        et_modal.setInputType(InputType.TYPE_CLASS_NUMBER);
        modal.setView(et_modal);


        modal_text = new AlertDialog.Builder(this).create();
        et_modal_text = new EditText(this);
        modal_text.setView(et_modal_text);



        //Dibujar
        UpdateTotal();



        // Jugadores

        for (int i=0;i<jugadores.length;i++) {
            final TextView jugador;
            final int resID = getResources().getIdentifier("tv_jugador"+i, "id", getPackageName());
            final int jugId = i;

            jugadores[i].setMercado(new Mercancia[]{
                    new Mercancia(getResources().getString(R.string.marfil), 0, 2),
                    new Mercancia(getResources().getString(R.string.joyas), 0, 2),
                    new Mercancia(getResources().getString(R.string.oro), 0, 2),
                    new Mercancia(getResources().getString(R.string.papiros), 0, 4),
                    new Mercancia(getResources().getString(R.string.seda), 0, 4),
                    new Mercancia(getResources().getString(R.string.especias), 0, 4),
                    new Mercancia(getResources().getString(R.string.pescado), 0, 6),
                    new Mercancia(getResources().getString(R.string.trigo), 0, 6),
                    new Mercancia(getResources().getString(R.string.ceramica), 0, 6)});



            jugador = findViewById(resID);

            jugador.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    et_modal_text.setText(jugador.getText());
                    showModal(getResources().getString(R.string.tit_actualizar), jugador.getText().toString(), jugId, "jugadores");
                    //Toast.makeText(getApplicationContext(), "Nombre del jugador " + jugId + " es "+ jugador.getText().toString() , Toast.LENGTH_LONG).show();
                }
            });

        }

/*

        for (final String numerico : numericos) {

            for (int i = 0; i < jugadores.length; i++) {
                final TextView jugador;
                final int resID = getResources().getIdentifier("tv_" + numerico + i, "id", getPackageName());
                final int jugId = i;

                jugador = findViewById(resID);

                jugador.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showNumberPickerDialog(numerico, jugadores[jugId].getValue(numerico), jugId);

                    }
                });
            }
        }*/



    }



    public void showModal(String titulo, String valor, int id, String tipo ){
        final TextView tv_modal;
        final int resID;
        final int judId = id;
        switch (tipo) {
            case "jugadores":
                modal_text.setTitle(titulo);
                modal_text.setView(et_modal_text);

                resID = getResources().getIdentifier("tv_jugador"+id, "id", getPackageName());
                tv_modal = findViewById(resID);

                modal_text.setButton(modal.BUTTON_POSITIVE, getResources().getString(R.string.btn_actualizar), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tv_modal.setText(et_modal_text.getText());
                        jugadores[judId].setNombre( et_modal_text.getText().toString() );
                    }
                });
                modal_text.show();
                break;


        }

    }


    public void UpdateTotal(){

        int total;

        for (int i = 0; i <  jugadores.length ; i++ ) {


            // Nombres
            int resID = getResources().getIdentifier("tv_jugador"+i, "id", getPackageName());
            TextView tv_aux = findViewById(resID);
            tv_aux.setText(jugadores[i].getNombre());

            // Nobles
            resID = getResources().getIdentifier("tv_noble"+i, "id", getPackageName());
            tv_aux = findViewById(resID);
            tv_aux.setText(String.valueOf(CalculateNobles(i) ) );
            total=CalculateNobles(i);

            // Monedas
            resID = getResources().getIdentifier("tv_moneda"+i, "id", getPackageName());
            tv_aux = findViewById(resID);
            tv_aux.setText(String.valueOf(CalculateMonedas(i)) );
            total+=CalculateMonedas(i);

            // Sabios
            resID = getResources().getIdentifier("tv_sabio"+i, "id", getPackageName());
            tv_aux = findViewById(resID);
            tv_aux.setText(String.valueOf(CalculateSabios(i) ) );
            total+=CalculateSabios(i);

            // Palmeras
            resID = getResources().getIdentifier("tv_palmera"+i, "id", getPackageName());
            tv_aux = findViewById(resID);
            tv_aux.setText(String.valueOf(CalculatePalmeras(i) ) );
            total+=CalculatePalmeras(i);

            // Castillos
            resID = getResources().getIdentifier("tv_castillo"+i, "id", getPackageName());
            tv_aux = findViewById(resID);
            tv_aux.setText(String.valueOf(CalculateCastillo(i) ) );
            total+=CalculateCastillo(i);

            //Mercado
            resID = getResources().getIdentifier("tv_mercado"+i, "id", getPackageName());
            tv_aux = findViewById(resID);
            tv_aux.setText(String.valueOf(jugadores[i].getMercadoValue() ) );
            total+=jugadores[i].getMercadoValue();


            // Camellos
            resID = getResources().getIdentifier("tv_camello"+i, "id", getPackageName());
            tv_aux = findViewById(resID);
            tv_aux.setText(String.valueOf(CalculateCamellos(i)) );
            total+=CalculateCamellos(i);

            // Djinn
            resID = getResources().getIdentifier("tv_djinn"+i, "id", getPackageName());
            tv_aux = findViewById(resID);
            tv_aux.setText(String.valueOf(jugadores[i].getDjinnsValue()) );
            total+=jugadores[i].getDjinnsValue();


            //Totales
            resID = getResources().getIdentifier("tv_total"+i, "id", getPackageName());
            tv_aux = findViewById(resID);
            tv_aux.setText(String.valueOf(total) );

        }

    }

    public int CalculateNobles(int jugador){
        Jugador p1 = jugadores[jugador];
        return p1.getNobles() * p1.getMultNobles() + ( (p1.isMaxNobles()) ? 10 : 0);
    }


    public void setMaxNoble(int jugador){

        int idMax = -1;
        int nobleMax = 0;

        //Buscamos el maximo, en caso de igual valor no hay maximo
        for (int i=0; i < jugadores.length; i++) {

            jugadores[i].setMaxNobles(false);

            if (jugadores[i].getNobles() > nobleMax) {
                nobleMax = jugadores[i].getNobles();
                idMax = i;
            }else if (jugadores[i].getNobles() == nobleMax) {
                idMax = -1;
            }

        }

        // Si solo hay un maximo, se informa
        if (idMax >= 0) {
            jugadores[idMax].setMaxNobles(true);
        }


    }

    public int CalculateSabios(int jugador){
        Jugador p1 = jugadores[jugador];
        return p1.getSabios() * p1.getMultSabios();
    }
    public int CalculatePalmeras(int jugador){
        Jugador p1 = jugadores[jugador];
        return p1.getPalmeras() * p1.getMultPalmera();
    }

    public int CalculateCastillo(int jugador){
        Jugador p1 = jugadores[jugador];
        return p1.getCastillos() * p1.getMultCastillo();
    }

    public int CalculateMonedas (int jugador){
        Jugador p1 = jugadores[jugador];
        int resultado=0;
        for (int i=0;i < monedas.length; i++){
            resultado+= p1.getMonedas()[i]*Integer.parseInt(monedas[i]);
        }
        return resultado;
    }

    public int CalculateCamellos (int jugador){
        Jugador p1 = jugadores[jugador];
        int resultado=0;
        for (int i=0;i < camellos.length; i++){
            resultado+= p1.getCamellos()[i]*Integer.parseInt(camellos[i]);
        }
        return resultado;
    }



    public void showMercadoDialog(View casilla) {

        int jugId = 0;
        for (int i = 0; i<casillasMercado.length; i++) {
            if (casilla.getId() == casillasMercado[i]) {
                jugId = i;
                break;
            }
        }
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new MercadoPickerFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("jugador", jugId);
        dialog.setArguments(args);

        dialog.show(getSupportFragmentManager(), "MercadoDialogFragment");
    }

    public void showMonedaDialog(View casilla) {

        int jugId = 0;
        for (int i = 0; i<casillasMoneda.length; i++) {
            if (casilla.getId() == casillasMoneda[i]) {
                jugId = i;
                break;
            }
        }
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new MonedaPickerFragment();

        //dialog.getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //dialog.getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("jugador", jugId);
        dialog.setArguments(args);

        dialog.show(getSupportFragmentManager(), "MonedaDialogFragment");
    }

    public void showDjinnDialog(View casilla) {

        int jugId = 0;
        for (int i = 0; i<casillasDjinn.length; i++) {
            if (casilla.getId() == casillasDjinn[i]) {
                jugId = i;
                break;
            }
        }
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new DjinnPickerFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("jugador", jugId);
        dialog.setArguments(args);
        dialog.show(getSupportFragmentManager(), "DjinnDialogFragment");
    }

    public void showSabioDialog(View casilla){
        String tipo = "sabio";
        int numero;
        int jugId = 0;

        for (int i = 0; i<casillasSabio.length; i++) {
            if (casilla.getId() == casillasSabio[i]) {
                jugId = i;
                break;
            }
        }

        numero = jugadores[jugId].getValue(tipo);
        DialogFragment dialog = new GeneralPickerFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("valor", numero);
        args.putString("tipo", tipo);
        args.putInt("jugador", jugId);
        dialog.setArguments(args);
        dialog.show(getSupportFragmentManager(), "NumberDialogFragment");

    }

    public void showNobleDialog(View casilla){
        String tipo = "noble";
        int numero;
        int jugId = 0;

        for (int i = 0; i<casillasNoble.length; i++) {
            if (casilla.getId() == casillasNoble[i]) {
                jugId = i;
                break;
            }
        }

        numero = jugadores[jugId].getValue(tipo);
        DialogFragment dialog = new GeneralPickerFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("valor", numero);
        args.putString("tipo", tipo);
        args.putInt("jugador", jugId);
        dialog.setArguments(args);
        dialog.show(getSupportFragmentManager(), "NumberDialogFragment");

    }

    public void showPalmeraDialog(View casilla){
        String tipo = "palmera";
        int numero;
        int jugId = 0;

        for (int i = 0; i<casillasPalmera.length; i++) {
            if (casilla.getId() == casillasPalmera[i]) {
                jugId = i;
                break;
            }
        }

        numero = jugadores[jugId].getValue(tipo);
        DialogFragment dialog = new GeneralPickerFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("valor", numero);
        args.putString("tipo", tipo);
        args.putInt("jugador", jugId);
        dialog.setArguments(args);
        dialog.show(getSupportFragmentManager(), "NumberDialogFragment");

    }

    public void showCastilloDialog(View casilla){
        String tipo = "castillo";
        int numero;
        int jugId = 0;

        for (int i = 0; i<casillasCastillo.length; i++) {
            if (casilla.getId() == casillasCastillo[i]) {
                jugId = i;
                break;
            }
        }

        numero = jugadores[jugId].getValue(tipo);
        DialogFragment dialog = new GeneralPickerFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("valor", numero);
        args.putString("tipo", tipo);
        args.putInt("jugador", jugId);
        dialog.setArguments(args);
        dialog.show(getSupportFragmentManager(), "NumberDialogFragment");

    }
    public void showCamelloDialog(View casilla){
        String tipo = "camello";
        int numero;
        int jugId = 0;

        for (int i = 0; i<casillasCamello.length; i++) {
            if (casilla.getId() == casillasCamello[i]) {
                jugId = i;
                break;
            }
        }

        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new CamelloPickerFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("jugador", jugId);
        dialog.setArguments(args);
        dialog.show(getSupportFragmentManager(), "CamelloDialogFragment");

    }

}
