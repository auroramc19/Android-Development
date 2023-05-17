package com.example.calendario2app;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FragmentoCita extends Fragment {

    public final static int OK = 0;
    public final static int CANCEL = 1;
    String dato;
    private FragmentoListener fl;
    @Override
    public void onAttach(Activity a) {
        super.onAttach(a);
        if (a instanceof FragmentoListener) {
            fl = (FragmentoListener) a;
        }
    }
    public interface FragmentoListener {
        public void digitado(int resultado, String texto, String dato);
    }
    @Override
    public View onCreateView(LayoutInflater li, ViewGroup vg, Bundle b) {
        View v = li.inflate(R.layout.cita_fragmento, vg, false);
        Bundle arguments = getArguments();
        if (arguments != null) {
            dato = arguments.getString("fecha"); // Reemplaza "datoClave" con tu clave
        }

        ((Button) v.findViewById(R.id.xbnGuardar)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                botonDigitado(v);
            }
        });

        ((Button) v.findViewById(R.id.xbnC)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                botonDigitado(v);
            }
        });

        return v;
    }
    public void botonDigitado(View v) {

        if (null == fl)
            return;
        if (((Button) v).getText().equals("Agregar"))
            fl.digitado(OK, ((EditText)
                    getActivity().findViewById(R.id.xet)).getText().toString(),dato);
        else
            fl.digitado(CANCEL, "", dato);
    }
}
