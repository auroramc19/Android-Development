package com.example.barracirculoradio;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SeekBar sb = null;
    int radio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setContentView(R.layout.activity_main);
        sb = (SeekBar) findViewById(R.id.xsb2);
        FragmentManager fm = getFragmentManager();
        Fragment f = fm.findFragmentByTag("f");
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int i = 0;
            public void onProgressChanged(SeekBar sb, int n, boolean b){
                i = n;

            }
            public void onStartTrackingTouch(SeekBar sb) { }
            public void onStopTrackingTouch(SeekBar sb) {
                if(f == null){
                    FragLienzo fl = FragLienzo.newInstance();
                    fl.setParametros(i);
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.add(R.id.fragmentoL, fl, "f");
                    ft.commit();
                }
                Toast.makeText(MainActivity.this,"Radio: " + i, Toast.LENGTH_SHORT).show();
            }
        });

    }
}