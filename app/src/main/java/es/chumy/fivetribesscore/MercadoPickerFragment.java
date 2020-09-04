package es.chumy.fivetribesscore;

import android.app.Dialog;

import android.graphics.Color;

import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.Button;
import android.widget.ImageButton;

import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import org.w3c.dom.Text;

public class MercadoPickerFragment  extends DialogFragment {

        Mercancia mercanciaList[];

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            // Get the layout inflater
            LayoutInflater inflater = requireActivity().getLayoutInflater();


            View view = inflater.inflate(R.layout.dialog_mercado_picker, null);

            final int mNum = getArguments().getInt("num");
            final String tipo = getArguments().getString("tipo");
            final int jugId = getArguments().getInt("jugador");

            final TextView tv = view.findViewById(R.id.tvDescMercado);

            mercanciaList = new Mercancia[((MainActivity)getActivity()).jugadores[jugId].getMercado().length];
            //mercanciaList = ((MainActivity)getActivity()).jugadores[jugId].getMercado().clone();

            for (int  i = 0; i< ((MainActivity)getActivity()).jugadores[jugId].getMercado().length; i++ )
            {
                mercanciaList[i] = new Mercancia(((MainActivity)getActivity()).jugadores[jugId].getMercado()[i].getNombre(),
                        ((MainActivity)getActivity()).jugadores[jugId].getMercado()[i].getCantidad(),
                        ((MainActivity)getActivity()).jugadores[jugId].getMercado()[i].getTotal());
            }

            //Set TextView text color
            //tv.setTextColor(Color.parseColor("#ffd32b3b"));
            //tv.setGravity(Gravity.CENTER);
            //tv.setText(getResources().getString(R.string.dialogoMercado));

            TableLayout parent = view.findViewById(R.id.ll_picker);

            for (int i = 0; i< mercanciaList.length; i++) {

                final int idMercancia = i;

                TableRow child = new TableRow(getContext());


                child.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT));
                //child.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                //child.setOrientation(LinearLayout.HORIZONTAL);

                TextView tv_mercancia = new TextView(getContext());

                tv_mercancia.setText(mercanciaList[i].getNombre());


                //@android:drawable/ic_media_ff
                ImageView btn_plus = new ImageView(getContext());
                btn_plus.setImageResource(R.drawable.ic_plus);

                ImageView btn_minus = new ImageView(getContext());
                btn_minus.setImageResource(R.drawable.ic_minus);

                //Button btn_plus = new Button(getContext());
                //btn_plus.setText(">");

                //Button btn_minus = new Button(getContext());
                //btn_minus.setText("<");

                final TextView tv_valor = new TextView(getContext());
                tv_valor.setText(String.valueOf(mercanciaList[i].getCantidad()));
                tv_valor.setTextSize(20);
                tv_valor.setTypeface(Typeface.DEFAULT_BOLD);
                tv_valor.setPadding(0,10,0,0);

                btn_plus.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    incrementar(tv_valor, idMercancia);
                                                }
                                            });

                btn_minus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        decrementar(tv_valor, idMercancia);
                    }
                });

                child.addView(tv_mercancia);
                child.addView(btn_minus);
                child.addView(tv_valor);
                child.addView(btn_plus);

                parent.addView(child);


            }



            Button btn_ok = view.findViewById(R.id.bt_aceptar);
            btn_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveMercado(jugId);
                    MercadoPickerFragment.this.getDialog().cancel();
                }
            });

            Button btn_cancel = view.findViewById(R.id.bt_cancel);
            btn_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MercadoPickerFragment.this.getDialog().cancel();
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
            d.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            return d;
        }

        @Override
        public void onResume() {
            super.onResume();
        };

        @Override
        public void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
        }

    public void incrementar(TextView tv1, int i){
        int num;


        num = Integer.parseInt(tv1.getText().toString());
        num++;
        if (num > mercanciaList[i].total) {
            num = mercanciaList[i].total;
        }
        tv1.setText(String.valueOf(num));
        mercanciaList[i].setCantidad(num);

    }

    public void decrementar(TextView tv1, int i){
        int num;

        num = Integer.parseInt(tv1.getText().toString());
        num--;
        if (num < 0) {
            num = 0;
        }

        tv1.setText(String.valueOf(num));
        mercanciaList[i].setCantidad(num);

    }

    public void saveMercado(int jugId){
            ((MainActivity)getActivity()).jugadores[jugId].setMercado(mercanciaList);
        ((MainActivity)getActivity()).UpdateTotal();

    }



}
