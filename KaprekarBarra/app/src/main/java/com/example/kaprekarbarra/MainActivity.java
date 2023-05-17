package com.example.kaprekarbarra;

import android.app.*;
import android.os.*;
import android.widget.SeekBar.*;
import android.widget.*;
public class MainActivity extends Activity {
    private SeekBar sb = null;
    TextView jtvResultado;
    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        sb = (SeekBar) findViewById(R.id.xsb2);
        jtvResultado = (TextView) findViewById(R.id.jtvResultado);

        sb.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            int i = 0, aux = 0,n1,n2;
            public void onProgressChanged(SeekBar sb, int n, boolean b){i = n;}
            public void onStartTrackingTouch(SeekBar sb) {
                jtvResultado.setText("");
            }
            public void onStopTrackingTouch(SeekBar sb) {
                jtvResultado.setText("");
                //kaprekar
                aux = i*i;
                jtvResultado.append("\n"+i+"^2 = " + aux);
                String numStr = Integer.toString(aux);
                int len = numStr.length();

                int half = len / 2;
                if(esPar(len)){
                    //par
                    String n1Str = numStr.substring(0, half);
                    String n2Str = numStr.substring(half);
                    n1 = Integer.parseInt(n1Str);
                    n2 = Integer.parseInt(n2Str);

                } else {
                    //impar
                    String n1Str = numStr.substring(0, half+1);
                    String n2Str = numStr.substring(half+1);
                    n1 = Integer.parseInt(n1Str);
                    n2 = Integer.parseInt(n2Str);
                }
                int res = n1 + n2;

                jtvResultado.append("\n"+n1+" + "+n2+" = " + res);

                if(res == i)
                    jtvResultado.append("\n\n"+i + " es un número Kaprekar!!!");
                else
                    jtvResultado.append("\n\n"+i + " no es un número Kaprekar");

                Toast.makeText(MainActivity.this,"Num: " + i, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static boolean esPar(int n) {
        if(n % 2 == 0)
            return true;
        else
            return false;
    }
}