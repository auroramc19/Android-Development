package com.example.calendario2app;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.*;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements FragmentoCita.FragmentoListener{
    CalendarView cv;
    TextView tv, tvCitas;
    Button jbnAgregar;
    SQLiteDatabase sqld;
    String descripcion, selectedDate;
    DbmsSQLiteHelper dsqlh;
    @Override
    protected void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        cv = (CalendarView) findViewById(R.id.calendarView);
        tv = (TextView) findViewById(R.id.date_display);
        tvCitas = (TextView) findViewById(R.id.citas);
        jbnAgregar = (Button) findViewById(R.id.btnAgregar);

        dsqlh = new DbmsSQLiteHelper(this, "DBCitas", null, 1);
        sqld = dsqlh.getWritableDatabase();

        tv.setText("Fecha: ");
        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                // Obtener la fecha seleccionada en formato String
                selectedDate = i2 + "/" + (i1+1)+ "/" + i;

                obtenerInformacionDesdeBaseDeDatos(selectedDate);

                tv.setText("Fecha: " + i2 + " / " + (i1+1) + " / " + i);

            }
        });

        jbnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("fecha", selectedDate);

                FragmentoCita fragmento = new FragmentoCita();
                fragmento.setArguments(bundle);

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.xfl, fragmento, "fragmento_etiqueta");
                fragmentTransaction.commit();
            }
        });
    }

    private void obtenerInformacionDesdeBaseDeDatos(String selectedDate) {
        String des;
        String[] args = {selectedDate};
        Cursor c = sqld.rawQuery("SELECT cita FROM Cita Where fecha = ?", args);

        tvCitas.setText("");
        if (c.moveToFirst()) {
            do {
                des = c.getString(0);
                tvCitas.append(" " + des + "\n");
            } while(c.moveToNext());
        }
    }

    public void digitado(int r, String s, String fecha) {
        tvCitas.append("\n" + s);

        if (r == FragmentoCita.OK) {
            descripcion = s;
            registrar(fecha);
        }
        FragmentManager fm = getFragmentManager();
        Fragment fe = fm.findFragmentByTag("fragmento_etiqueta");
        FragmentTransaction ft = fm.beginTransaction();
        ft.remove(fe);
        ft.commit();
    }

    private void registrar(String fecha){
        ContentValues cv = new ContentValues();
        cv.put("fecha",fecha);
        cv.put("cita",descripcion);
        sqld.insert("Cita",null,cv);
        Toast.makeText(getApplicationContext(), "Se agrego cita para el " + fecha, Toast.LENGTH_LONG).show();
    }
}
