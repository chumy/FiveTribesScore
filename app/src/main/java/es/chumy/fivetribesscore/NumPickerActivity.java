package es.chumy.fivetribesscore;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
/*
public class NumPickerActivity extends Activity {

}*/


import androidx.fragment.app.DialogFragment;

public class NumPickerActivity extends DialogFragment {



    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the Builder class for convenient dialog construction
        //AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();


        View view = inflater.inflate(R.layout.activity_num_picker, null);

        final int mNum = getArguments().getInt("num");
        final String tipo = getArguments().getString("tipo");
        final int jugId = getArguments().getInt("jugId");

        final TextView tv = view.findViewById(R.id.tv_npd);
        final NumberPicker np = view.findViewById(R.id.np_npd);


        //Set TextView text color
        tv.setTextColor(Color.parseColor("#ffd32b3b"));
        tv.setText("texto");

        //Populate NumberPicker values from minimum and maximum value range
        //Set the minimum value of NumberPicker
        np.setMinValue(0);
        //Specify the maximum value/number of NumberPicker
        np.setMaxValue(10);
        np.setValue(mNum);

        //Gets whether the selector wheel wraps when reaching the min/max value.
        np.setWrapSelectorWheel(true);



        //Set a value change listener for NumberPicker



        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view)
                .setCancelable(false)
                // Add action buttons
                .setPositiveButton(R.string.aceptar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //Toast.makeText(getContext(), "hecho " + np.getValue(), Toast.LENGTH_LONG).show();
                        //Intent intent = new Intent();
                        //intent.putExtra("resultado","valor");
                        ((MainActivity)getActivity()).jugadores[jugId].setValue(np.getValue(), tipo);
                        ((MainActivity)getActivity()).setMaxNoble(jugId);
                        ((MainActivity)getActivity()).UpdateTotal();


                    }
                })
                .setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        NumPickerActivity.this.getDialog().cancel();
                    }
                })

        ;
        return builder.create();
    }

    @Override
    public void onResume() {
        super.onResume();
    };

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


}
