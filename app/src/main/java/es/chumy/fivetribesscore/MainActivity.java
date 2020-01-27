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
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import static android.widget.Toast.*;

public class MainActivity extends AppCompatActivity {

    TextView tv_jugador1,tv_jugador2, tv_jugador3, tv_jugador4;

    String[] numericos = {"moneda", "sabio", "castillo", "noble", "palmera", "camello", "mercado"};

    AlertDialog modal, modal_text;
    EditText et_modal, et_modal_text;


    public Jugador[] jugadores;

    public Djinn[] DjinList = new Djinn[]{
            new Djinn("Al-Amin", 5),
            new Djinn("Anun-Nak", 8),
            new Djinn("Ba'Ai", 6),
            new Djinn("Boaz", 6),
            new Djinn("Bouraq", 6),
            new Djinn("Echidna", 4),
            new Djinn("Enki", 8),
            new Djinn("Hagis", 10),
            new Djinn("Haurvatat", 8, 1,2, 5),
            new Djinn("Iblis", 8),
            new Djinn("Jafaar", 6, 3,2,3),
            new Djinn("Kandicha", 6),
            new Djinn("Kumarbi", 6),
            new Djinn("Lamia", 10),
            new Djinn("Leta", 4),
            new Djinn("Marid", 6),
            new Djinn("Monkir", 6),
            new Djinn("Nekir", 6),
            new Djinn("Shamhat", 6, 1 ,4 ,3),
            new Djinn("Sibittis", 4),
            new Djinn("Sidar", 8),
            new Djinn("Utug", 4)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(getApplicationContext(), "Evento on Create "  , Toast.LENGTH_LONG).show();

        Bundle extras = getIntent().getExtras();

        if (extras != null) {

            int valor = getIntent().getIntExtra("valor",0);
            String tipo =  getIntent().getStringExtra("tipo");
            int jugador = getIntent().getIntExtra("jugador",0);

            jugadores[jugador].setValue(valor,tipo);

        }
        else {
            jugadores = new Jugador[]{
                    new Jugador(getResources().getString(R.string.jugador1)),
                    new Jugador(getResources().getString(R.string.jugador2)),
                    new Jugador(getResources().getString(R.string.jugador3)),
                    new Jugador(getResources().getString(R.string.jugador4))
            };
        }

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
                        Integer valor = jugadores[jugId].getValue(numerico);
                        et_modal.setText(valor.toString());
                        //showCustomNumberPicker(numerico, jugadores[jugId].getValue(numerico), jugId);
                        showNumberPickerDialog(numerico, jugadores[jugId].getValue(numerico), jugId);

                    }
                });
            }
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
            case "noble":
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
            case "mercado":
                modal.setTitle(titulo);
                modal.setView(et_modal);
                resID = getResources().getIdentifier("tv_mercado"+id, "id", getPackageName());
                tv_modal = findViewById(resID);

                modal.setButton(modal.BUTTON_POSITIVE, getResources().getString(R.string.btn_actualizar), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String var = et_modal.getText().toString();
                        jugadores[judId].setMercado ( (var.length() == 0) ? 0 : Integer.parseInt(var) );
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
                 modal.show();

        }

    }

    public void UpdateTotales(){
        int valor=0;
        int resID;
        TextView tv_aux;
        for (int i = 0; i<jugadores.length; i++) {
            for (String numerico : numericos) {
                resID = getResources().getIdentifier("tv_" + numerico + i, "id", getPackageName());
                tv_aux = findViewById(resID);
                String tmp_value = (tv_aux.getText().toString().equals("")) ? "0" : tv_aux.getText().toString();
                tv_aux.setText(tmp_value);
                valor += Integer.parseInt(tmp_value);
            }
            resID = getResources().getIdentifier("tv_total" + i, "id", getPackageName());
            tv_aux = findViewById(resID);
            tv_aux.setText(valor);
            valor = 0;

        }
    }

    public void UpdateTotal(){

        for (int i = 0; i <  jugadores.length ; i++ ) {

            // Nombres
            int resID = getResources().getIdentifier("tv_jugador"+i, "id", getPackageName());
            TextView tv_aux = findViewById(resID);
            tv_aux.setText(jugadores[i].getNombre());

            // Nobles
            resID = getResources().getIdentifier("tv_noble"+i, "id", getPackageName());
            tv_aux = findViewById(resID);
            tv_aux.setText(String.valueOf(CalculateNobles(i) ) );

            // Monedas
            resID = getResources().getIdentifier("tv_moneda"+i, "id", getPackageName());
            tv_aux = findViewById(resID);
            tv_aux.setText(String.valueOf(jugadores[i].getMonedas()) );

            // Sabios
            resID = getResources().getIdentifier("tv_sabio"+i, "id", getPackageName());
            tv_aux = findViewById(resID);
            tv_aux.setText(String.valueOf(CalculateSabios(i) ) );

            // Palmeras
            resID = getResources().getIdentifier("tv_palmera"+i, "id", getPackageName());
            tv_aux = findViewById(resID);
            tv_aux.setText(String.valueOf(CalculatePalmeras(i) ) );

            // Castillos
            resID = getResources().getIdentifier("tv_castillo"+i, "id", getPackageName());
            tv_aux = findViewById(resID);
            tv_aux.setText(String.valueOf(CalculateCastillo(i) ) );


            // Camellos
            resID = getResources().getIdentifier("tv_camello"+i, "id", getPackageName());
            tv_aux = findViewById(resID);
            tv_aux.setText(String.valueOf(jugadores[i].getCamellos()) );


            //Totales
            int total = 0;
            for (String numerico : numericos) {

                resID = getResources().getIdentifier("tv_" + numerico + i, "id", getPackageName());
                tv_aux = findViewById(resID);
                total = total + Integer.parseInt(tv_aux.getText().toString());
            }
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



    public void showNumberPickerDialog(String tipo, int numero, int jugId) {

        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new SelectorNumActivity();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("valor", numero);
        args.putString("tipo", tipo);
        args.putInt("jugador", jugId);
        dialog.setArguments(args);
        dialog.show(getSupportFragmentManager(), "NoticeDialogFragment");



    }



    public void showCustomNumberPicker(String tipo, int numero, int jugId) {
        Intent i = new Intent(this, SelectorNumActivity.class);
        i.putExtra("jugador", jugId);
        i.putExtra("tipo", tipo);
        i.putExtra("valor", 5);
        startActivity(i);
    }

}
