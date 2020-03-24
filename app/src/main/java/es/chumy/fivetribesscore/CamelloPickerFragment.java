package es.chumy.fivetribesscore;

import android.app.Dialog;
import android.graphics.Color;
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

public class CamelloPickerFragment extends DialogFragment {

    int[] camellosList = new int[] {0,0,0,0,0,0,0};


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //val dialog = super.onCreateDialog(savedInstanceState)
        //dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());



        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_camello_picker, null);


        //dialog.getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //dialog.getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        final int jugId = getArguments().getInt("jugador");

        camellosList = ((MainActivity)getActivity()).jugadores[jugId].getCamellos();


        for (int i = 0; i< camellosList.length; i++) {

            final int idCamello = i;
            final TextView tv_valor;

            int resID = getResources().getIdentifier("tv_valor"+((MainActivity)getActivity()).camellos[i]+"_dialogo", "id", getActivity().getPackageName());
            tv_valor = view.findViewById(resID);
            tv_valor.setText(String.valueOf(camellosList[idCamello]) );


            ImageView btn_plus;
            resID = getResources().getIdentifier("iv_ff_"+((MainActivity)getActivity()).camellos[i]+"_dialogo", "id", getActivity().getPackageName());
            btn_plus = view.findViewById(resID);

            ImageView btn_minus;
            resID = getResources().getIdentifier("iv_rew_"+((MainActivity)getActivity()).camellos[i]+"_dialogo", "id", getActivity().getPackageName());
            btn_minus = view.findViewById(resID);


            tv_valor.setTextSize(20);
            tv_valor.setPadding(0,10,0,0);

            btn_plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    incrementar(tv_valor, idCamello);
                }
            });

            btn_minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    decrementar(tv_valor, idCamello);
                }
            });


        }



        Button btn_ok = view.findViewById(R.id.bt_aceptar_camello_dialogo);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMercado(jugId);
                CamelloPickerFragment.this.getDialog().cancel();
            }
        });

        Button btn_cancel = view.findViewById(R.id.bt_cancel_camello_dialogo);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CamelloPickerFragment.this.getDialog().cancel();
            }
        });

        //Set a value change listener for NumberPicker



        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view)
                .setCancelable(false)
        ;
        //return builder.create();

        Dialog d = builder.create();
        d.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        return d;
    }

    public void incrementar(TextView tv1, int i){
        int num;


        num = Integer.parseInt(tv1.getText().toString());
        num++;

        tv1.setText(String.valueOf(num));
        camellosList[i]=num;

    }

    public void decrementar(TextView tv1, int i){
        int num;

        num = Integer.parseInt(tv1.getText().toString());
        num--;
        if (num < 0) {
            num = 0;
        }

        tv1.setText(String.valueOf(num));
        camellosList[i]=num;

    }

    public void saveMercado(int jugId){
        ((MainActivity)getActivity()).jugadores[jugId].setCamellos(camellosList);
        ((MainActivity)getActivity()).UpdateTotal();

    }

}
