package es.chumy.fivetribesscore;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class SelectorNumActivity extends DialogFragment {

    TextView tv1;
    int valor, jugador;
    String tipo;

    Button incrementar,decrementar, enviar;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        final View view = inflater.inflate(R.layout.activity_numpicker, null);
        valor = getArguments().getInt("valor",0);
        tipo = getArguments().getString("tipo");
        jugador = getArguments().getInt("jugador",0);


        tv1 = view.findViewById(R.id.contador);
        tv1.setText(String.valueOf(valor));

        incrementar = view.findViewById((R.id.bIncrementar));
        decrementar = view.findViewById((R.id.bDecrementar));
        enviar = view.findViewById(R.id.bEnviar);

        incrementar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementar(view);
            }
        });
        decrementar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementar(view);
            }
        });

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarNumero(view);
            }
        });

        //Set a value change listener for NumberPicker

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view)
                .setCancelable(false)
        ;

        return builder.create();

    }

    public void incrementar(View view){
        int num;

        tv1 = view.findViewById(R.id.contador);
        num = Integer.parseInt(tv1.getText().toString());
        num++;
        tv1.setText(String.valueOf(num));

    }

    public void decrementar(View view){
        int num;

        tv1 = view.findViewById(R.id.contador);
        num = Integer.parseInt(tv1.getText().toString());
        num--;
        if (num < 0) {
            num = 0;
        }
        tv1.setText(String.valueOf(num));

    }

    public void enviarNumero(View view) {
        int num;

        tv1 = view.findViewById(R.id.contador);
        num = Integer.parseInt(tv1.getText().toString());

        ((MainActivity)getActivity()).jugadores[jugador].setValue(num, tipo);
        ((MainActivity)getActivity()).setMaxNoble(jugador);
        ((MainActivity)getActivity()).UpdateTotal();
        SelectorNumActivity.this.getDialog().cancel();

/*

        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("valor", valor);
        i.putExtra("jugador", jugador);
        i.putExtra("tipo", tipo);
        startActivity(i);*/

    }
}
