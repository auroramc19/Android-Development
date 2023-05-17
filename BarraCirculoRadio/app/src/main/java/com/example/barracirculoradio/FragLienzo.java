package com.example.barracirculoradio;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.*;
import android.widget.TextView;
import android.app.Fragment;

public class FragLienzo extends Fragment {
    Lienzo l;
    int radio;

    public static FragLienzo newInstance() {
        FragLienzo fragment = new FragLienzo();
        return fragment;
    }

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        l = new Lienzo(this.getContext(), radio);
    }

    @Override
    public View onCreateView(LayoutInflater li, ViewGroup vg, Bundle bn) {
        return l;
    }

    public void setParametros(Integer radio) {
        this.radio = radio;
    }
}