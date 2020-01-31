package es.chumy.fivetribesscore;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class MonedaPickerFragment extends DialogFragment {

    int[] monedaList = new int[] {0,0};


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_moneda_picker, null);

        final int jugId = getArguments().getInt("jugador");

        monedaList = ((MainActivity)getActivity()).jugadores[jugId].getMonedas();


        for (int i = 0; i< monedaList.length; i++) {

            final int idMoneda = i;
            final TextView tv_valor;

            int resID = getResources().getIdentifier("tv_valor"+((MainActivity)getActivity()).monedas[i]+"_dialogo", "id", getActivity().getPackageName());
            tv_valor = view.findViewById(resID);
            tv_valor.setText(String.valueOf(monedaList[idMoneda]) );


            ImageView btn_plus;
            resID = getResources().getIdentifier("iv_ff_"+((MainActivity)getActivity()).monedas[i]+"_dialogo", "id", getActivity().getPackageName());
            btn_plus = view.findViewById(resID);

            ImageView btn_minus;
            resID = getResources().getIdentifier("iv_rew_"+((MainActivity)getActivity()).monedas[i]+"_dialogo", "id", getActivity().getPackageName());
            btn_minus = view.findViewById(resID);


            tv_valor.setTextSize(20);
            tv_valor.setPadding(0,10,0,0);

            btn_plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    incrementar(tv_valor, idMoneda);
                }
            });

            btn_minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    decrementar(tv_valor, idMoneda);
                }
            });


        }



        Button btn_ok = view.findViewById(R.id.bt_aceptar_moneda_dialogo);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMercado(jugId);
                MonedaPickerFragment.this.getDialog().cancel();
            }
        });

        Button btn_cancel = view.findViewById(R.id.bt_cancel_moneda_dialogo);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MonedaPickerFragment.this.getDialog().cancel();
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

    public void incrementar(TextView tv1, int i){
        int num;


        num = Integer.parseInt(tv1.getText().toString());
        num++;

        tv1.setText(String.valueOf(num));
        monedaList[i]=num;

    }

    public void decrementar(TextView tv1, int i){
        int num;

        num = Integer.parseInt(tv1.getText().toString());
        num--;
        if (num < 0) {
            num = 0;
        }

        tv1.setText(String.valueOf(num));
        monedaList[i]=num;

    }

    public void saveMercado(int jugId){
        ((MainActivity)getActivity()).jugadores[jugId].setMonedas(monedaList);
        ((MainActivity)getActivity()).UpdateTotal();

    }

}
