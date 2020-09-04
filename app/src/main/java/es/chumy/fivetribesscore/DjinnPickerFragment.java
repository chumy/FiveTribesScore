package es.chumy.fivetribesscore;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TableRow;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.List;


public class DjinnPickerFragment extends DialogFragment {

    List<Djinn> listaDjinsDisponibles = new ArrayList<Djinn>();


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();


        View view = inflater.inflate(R.layout.dialog_djins_picker, null);

        final int jugId = getArguments().getInt("jugador");

        listaDjinsDisponibles.addAll( ((MainActivity)getActivity()).DjinnList);

        for (int i=0; i< 4; i++)
        {
            if (i != jugId) {
                listaDjinsDisponibles.removeAll(((MainActivity) getActivity()).jugadores[i].getDjinns());
            }
        }
        TableLayout parent = view.findViewById(R.id.tbl_djinn);
        TableRow child = new TableRow(getContext());

        for (int i = 0 ; i< listaDjinsDisponibles.size(); i++){

            final int idDjinn = i;

            if (i % 2 == 0 && i > 1) {
                child = new TableRow(getContext());
            }

            child.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT));
            //child.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            //child.setOrientation(LinearLayout.HORIZONTAL);

            final CheckBox rb_djinn = new CheckBox(getContext());
            //final RadioButton rb_djinn = new RadioButton(getContext());
            rb_djinn.setText(listaDjinsDisponibles.get(i).getNombre());

            if ( ((MainActivity) getActivity()).jugadores[jugId].getDjinns().contains(listaDjinsDisponibles.get(i)) ){
                rb_djinn.setChecked(true);
            }


            //listaDjinsDisponibles.get(i)

            rb_djinn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if ( rb_djinn.isChecked() ){
                        addDjinn(jugId, listaDjinsDisponibles.get(idDjinn));
                    }else {
                        removeDjinn(jugId,listaDjinsDisponibles.get(idDjinn));
                    }
                }
            });



            //child.addView(tv_nombre);
            child.addView(rb_djinn);
            //child.addView(btn_minus);
            //child.addView(btn_plus);

            if (i % 2 == 1) {
                parent.addView(child);
            }

        }

        Button btn_ok = view.findViewById(R.id.bt_aceptar);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).UpdateTotal();
                DjinnPickerFragment.this.getDialog().cancel();
            }
        });

        Button btn_cancel = view.findViewById(R.id.bt_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DjinnPickerFragment.this.getDialog().cancel();
            }
        });

        builder.setView(view)
                .setCancelable(false)
        ;
        //return builder.create();

        Dialog d = builder.create();
        d.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        return d;

    }

    private void addDjinn(int jugId, Djinn djinn){

        ( (MainActivity)getActivity()).jugadores[jugId].addDjinn(djinn);
        //Toast.makeText(getContext(), "Añadido "+ djinn.getNombre(), Toast.LENGTH_LONG).show();
    }

    private void removeDjinn(int jugId, Djinn djinn){

        ( (MainActivity)getActivity()).jugadores[jugId].removeDjinn(djinn);
    }
}
