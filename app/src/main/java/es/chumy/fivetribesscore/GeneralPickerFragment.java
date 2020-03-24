package es.chumy.fivetribesscore;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class GeneralPickerFragment extends DialogFragment {

    TextView tv1;
    int valor, jugador;
    String tipo;

    ImageView incrementar,decrementar;
    Button enviar;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();


        valor = getArguments().getInt("valor",0);
        tipo = getArguments().getString("tipo");
        jugador = getArguments().getInt("jugador",0);
        final View dialogo;
        TextView tvTitulo, tvDesc;
        int stringTitulo = 0, stringDesc = 0, textColor =0;

        dialogo = inflater.inflate(R.layout.dialog_general_picker, null);


        if (tipo == "noble"){
            dialogo.setBackgroundResource(R.drawable.dialog_noble);
            stringTitulo = R.string.nobles;
            stringDesc = R.string.dialogoNobles;
            textColor = R.color.nobleDark;

        }else if (tipo == "sabio"){
            dialogo.setBackgroundResource(R.drawable.dialog_sabio);
            stringTitulo = R.string.sabios;
            stringDesc = R.string.dialogoSabios;
            textColor = R.color.sabioDark;
        }else if (tipo == "palmera"){
            dialogo.setBackgroundResource(R.drawable.dialog_palmera);
            stringTitulo = R.string.palmeras;
            stringDesc = R.string.dialogoPalmeras;
            textColor = R.color.palmeraDark;
        }else if (tipo == "castillo"){
            dialogo.setBackgroundResource(R.drawable.dialog_castillo);
            stringTitulo = R.string.castillos;
            stringDesc = R.string.dialogoCastillos;
            textColor = R.color.castilloDark;
        }else if (tipo == "camello"){
            dialogo.setBackgroundResource(R.drawable.dialog_camello);
            stringTitulo = R.string.camellos;
            stringDesc = R.string.dialogoCamellos;
            textColor = R.color.camelloDark;
        }

        tv1 = dialogo.findViewById(R.id.contador);
        tv1.setText(String.valueOf(valor));

        tvTitulo = dialogo.findViewById((R.id.tvDialogTitulo));
        tvTitulo.setText(stringTitulo);
        tvTitulo.setTextColor(textColor);
        tvDesc = dialogo.findViewById((R.id.tvDialogDesc));
        tvDesc.setText(stringDesc);
        tvDesc.setTextColor(textColor);

        incrementar = dialogo.findViewById((R.id.ivIncrementar));
        decrementar = dialogo.findViewById((R.id.ivDecrementar));
        enviar = dialogo.findViewById(R.id.bEnviar);

        incrementar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementar(dialogo);
            }
        });
        decrementar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementar(dialogo);
            }
        });

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarNumero(dialogo);
            }
        });

        //Set a value change listener for NumberPicker

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(dialogo)
                .setCancelable(false)
        ;

        //return builder.create();
        Dialog d = builder.create();
        d.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


        return d;

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
        GeneralPickerFragment.this.getDialog().cancel();

/*

        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("valor", valor);
        i.putExtra("jugador", jugador);
        i.putExtra("tipo", tipo);
        startActivity(i);*/

    }
}
