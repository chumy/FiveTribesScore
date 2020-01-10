package es.chumy.fivetribesscore;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    //TextView tv_jugador1,tv_jugador2, tv_jugador3, tv_jugador4;
    //TextView tv_jugador1,tv_jugador2, tv_jugador3, tv_moneda;

    String[] numericos = {"moneda", "noble" , "sabio", "djin", "castillo", "palmera", "camello", "mercado"};

    AlertDialog modal, modal_text;
    EditText et_modal, et_modal_text;
    String salida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*tv_jugador1 = (TextView) findViewById(R.id.tv_jugador1);
        tv_jugador2 = (TextView) findViewById(R.id.tv_jugador2);
        tv_jugador3 = (TextView) findViewById(R.id.tv_jugador3);
        tv_jugador4 = (TextView) findViewById(R.id.tv_jugador4);*/

        modal = new AlertDialog.Builder(this).create();
        et_modal = new EditText(this);
        et_modal.setInputType(InputType.TYPE_CLASS_NUMBER);
        modal.setView(et_modal);


        modal_text = new AlertDialog.Builder(this).create();
        et_modal_text = new EditText(this);
        modal_text.setView(et_modal_text);


        // Jugadores

        for (int i=1;i<5;i++) {
            final TextView jugador;
            final int resID = getResources().getIdentifier("tv_jugador"+i, "id", getPackageName());

            jugador = (TextView) findViewById(resID);

            jugador.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    et_modal_text.setText(jugador.getText());
                    showModal(getResources().getString(R.string.tit_actualizar), jugador.getText().toString(), resID, "text");
                }
            });
        }

        //Numericos

        for (int j=0; j < numericos.length; j++) {

            for (int i = 1; i < 5; i++) {
                final TextView jugador;
                final int resID = getResources().getIdentifier("tv_"+numericos[j] + i, "id", getPackageName());

                jugador = (TextView) findViewById(resID);

                jugador.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String valor = (jugador.getText().equals("0") ) ? "" : jugador.getText().toString();
                        et_modal.setText(valor);
                        showModal(getResources().getString(R.string.tit_actualizar), valor, resID, "number");
                    }
                });
            }
        }


    }

    public void showModal(String titulo, String valor, int resID, String tipo ){
        if (tipo.equals("text")) {
            modal_text.setTitle(titulo);
            modal_text.setView(et_modal_text);
            final TextView tv_modal;
            //int resID = getResources().getIdentifier(id, "id", getPackageName());

            tv_modal = (TextView) findViewById(resID);

            modal_text.setButton(modal.BUTTON_POSITIVE, getResources().getString(R.string.btn_actualizar), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    tv_modal.setText(et_modal_text.getText());
                }
            });
            modal_text.show();
        }else{
            // Parte numerica
            modal.setTitle(titulo);
            modal.setView(et_modal);
            final TextView tv_modal;
            //int resID = getResources().getIdentifier(id, "id", getPackageName());

            tv_modal = (TextView) findViewById(resID);

            modal.setButton(modal.BUTTON_POSITIVE, getResources().getString(R.string.btn_actualizar), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    tv_modal.setText(et_modal.getText());
                    UpdateTotales();
                }
            });
            modal.show();
        }
    }

    public void UpdateTotales(){
        Integer valor=0;
        int resID;
        TextView tv_aux;
        for (int i = 1; i<5; i++) {
            for (int j = 0; j < numericos.length; j++) {
                resID = getResources().getIdentifier("tv_"+numericos[j] + i, "id", getPackageName());
                tv_aux = (TextView) findViewById(resID);
                String tmp_value = (tv_aux.getText().toString().equals("") ) ? "0" : tv_aux.getText().toString();
                tv_aux.setText(tmp_value);
                valor += Integer.parseInt(tmp_value);
            }
            resID = getResources().getIdentifier("tv_total" + i, "id", getPackageName());
            tv_aux = (TextView) findViewById(resID);
            tv_aux.setText(valor.toString());
            valor = 0;

        }
    }

}
