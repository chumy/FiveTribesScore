package es.chumy.fivetribesscore;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String[] numericos = { "sabio", "castillo", "noble", "palmera", "camello"};

    public static String[] monedas = new String[] {"1","5"};

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
                    new Mercancia(getResources().getString(R.string.seda), 0, 2),
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
        }

        //Monedas
        for (int i = 0; i < jugadores.length; i++) {
            final TextView jugador;
            final int resID = getResources().getIdentifier("tv_moneda"  + i, "id", getPackageName());
            final int jugId = i;

            jugador = findViewById(resID);

            jugador.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showMonedaPickerDialog(jugId);

                }
            });
        }


        //Mercado
        for (int i = 0; i < jugadores.length; i++) {
            final TextView jugador;
            final int resID = getResources().getIdentifier("tv_mercado"  + i, "id", getPackageName());
            final int jugId = i;

            jugador = findViewById(resID);

            jugador.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showMercadoPickerDialog(jugId);

                }
            });
        }

        //Djinns

        for (int i = 0; i < jugadores.length; i++) {
            final TextView jugador;
            final int resID = getResources().getIdentifier("tv_djinn"  + i, "id", getPackageName());
            final int jugId = i;

            jugador = findViewById(resID);

            jugador.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Integer valor = jugadores[jugId].getMercadoValue();
                    //et_modal.setText(valor.toString());
                    //showCustomNumberPicker(numerico, jugadores[jugId].getValue(numerico), jugId);
                    showDjinnPickerDialog(jugId);

                }
            });
        }

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
           /* case "noble":
                modal.setTitle(titulo);
                modal.setView(et_modal);
                resID = getResources().getIdentifier("tv_noble"+id, "id", getPackageName());
                tv_modal = findViewById(resID);

                modal.setButton(modal.BUTTON_POSITIVE, getResources().getString(R.string.btn_actualizar), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String var = et_modal.getText().toString();
                        jugadores[judId].setNobles( (var.length() == 0) ? 0 : Integer.parseInt(var) );
                        tv_modal.setText(var);
                        setMaxNoble(judId);
                        UpdateTotal();
                    }
                });
                modal.show();

                break;
            case "sabio":
                modal.setTitle(titulo);
                modal.setView(et_modal);
                resID = getResources().getIdentifier("tv_sabio"+id, "id", getPackageName());
                tv_modal = findViewById(resID);

                modal.setButton(modal.BUTTON_POSITIVE, getResources().getString(R.string.btn_actualizar), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String var = et_modal.getText().toString();
                        jugadores[judId].setCamellos((var.length() == 0) ? 0 : Integer.parseInt(var) );
                        tv_modal.setText(var);
                        UpdateTotal();
                    }
                });
                modal.show();

                break;
            case "palmera":
                modal.setTitle(titulo);
                modal.setView(et_modal);
                resID = getResources().getIdentifier("tv_palmera"+id, "id", getPackageName());
                tv_modal = findViewById(resID);

                modal.setButton(modal.BUTTON_POSITIVE, getResources().getString(R.string.btn_actualizar), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String var = et_modal.getText().toString();
                        jugadores[judId].setPalmeras((var.length() == 0) ? 0 : Integer.parseInt(var) );
                        tv_modal.setText(var);
                        UpdateTotal();
                    }
                });
                modal.show();

                break;
            case "castillo":
                modal.setTitle(titulo);
                modal.setView(et_modal);
                resID = getResources().getIdentifier("tv_castillo"+id, "id", getPackageName());
                tv_modal = findViewById(resID);

                modal.setButton(modal.BUTTON_POSITIVE, getResources().getString(R.string.btn_actualizar), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String var = et_modal.getText().toString();
                        jugadores[judId].setCastillos( (var.length() == 0) ? 0 : Integer.parseInt(var));
                        tv_modal.setText(var);
                        UpdateTotal();
                    }
                });
                modal.show();

                break;
            case "camello":
                modal.setTitle(titulo);
                modal.setView(et_modal);
                resID = getResources().getIdentifier("tv_camello"+id, "id", getPackageName());
                tv_modal = findViewById(resID);

                modal.setButton(modal.BUTTON_POSITIVE, getResources().getString(R.string.btn_actualizar), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String var = et_modal.getText().toString();
                        jugadores[judId].setCamellos(  (var.length() == 0) ? 0 : Integer.parseInt(var) );
                        tv_modal.setText(var);
                        UpdateTotal();
                    }
                });
                modal.show();

                break;
            case "moneda":
                modal.setTitle(titulo);
                modal.setView(et_modal);
                resID = getResources().getIdentifier("tv_moneda"+id, "id", getPackageName());
                tv_modal = findViewById(resID);

                modal.setButton(modal.BUTTON_POSITIVE, getResources().getString(R.string.btn_actualizar), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String var = et_modal.getText().toString();
                        jugadores[judId].setMonedas( (var.length() == 0) ? 0 : Integer.parseInt(var) );
                        tv_modal.setText(var);
                        UpdateTotal();
                    }
                });
                modal.show();

                break;

            default:
                modal.setTitle(titulo);
                modal.setView(et_modal);
                resID = getResources().getIdentifier("tv_" + tipo +id, "id", getPackageName());
                tv_modal = findViewById(resID);

                modal.setButton(modal.BUTTON_POSITIVE, getResources().getString(R.string.btn_actualizar), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        tv_modal.setText(et_modal.getText());
                         UpdateTotal();
                     }
                    });
                 modal.show();*/

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
            tv_aux.setText(String.valueOf(jugadores[i].getCamellos()) );
            total+=jugadores[i].getCamellos();

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


    public void showNumberPickerDialog(String tipo, int numero, int jugId) {

        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new SelectorNumFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("valor", numero);
        args.putString("tipo", tipo);
        args.putInt("jugador", jugId);
        dialog.setArguments(args);
        dialog.show(getSupportFragmentManager(), "NumberDialogFragment");



    }

    private void showMonedaPickerDialog(int jugId) {

        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new MonedaPickerFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("jugador", jugId);
        dialog.setArguments(args);
        dialog.show(getSupportFragmentManager(), "MonedaDialogFragment");
    }

    private void showMercadoPickerDialog(int jugId) {

        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new MercadoPickerFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("jugador", jugId);
        dialog.setArguments(args);
        dialog.show(getSupportFragmentManager(), "MercadoDialogFragment");
    }



    private void showDjinnPickerDialog(int jugId) {

        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new DjinnPickerFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("jugador", jugId);
        dialog.setArguments(args);
        dialog.show(getSupportFragmentManager(), "DjinnDialogFragment");
    }


    public void showCustomNumberPicker(String tipo, int numero, int jugId) {
        Intent i = new Intent(this, SelectorNumFragment.class);
        i.putExtra("jugador", jugId);
        i.putExtra("tipo", tipo);
        i.putExtra("valor", 5);
        startActivity(i);
    }

}
