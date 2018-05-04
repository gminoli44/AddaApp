package com.app.adda.Adda;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;



public class llamadas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llamadas);
    }

    public void llamar_bomberos(View view) {

        try {
            final Intent intentLlamada = new Intent(Intent.ACTION_CALL, Uri.parse("tel:0313822500"));
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    if (ActivityCompat.checkSelfPermission(llamadas.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    Toast.makeText(getApplicationContext(), "Llamando, Espere por favor...", Toast.LENGTH_LONG).show();
                    startActivity(intentLlamada);
                }
            };
            Handler h = new Handler();
            h.postDelayed(r, 1); // <-- "1" es el tiempo de retraso en milisegundos.
        }catch (RuntimeException e){
            Toast.makeText(getApplicationContext(), "Llamada no realizada", Toast.LENGTH_LONG).show();
        }
    }

    public void llamar_fiscalia_ambiental(View view) {

        try {
            final Intent intentLlamada = new Intent(Intent.ACTION_CALL, Uri.parse("tel:199"));
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    if (ActivityCompat.checkSelfPermission(llamadas.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    Toast.makeText(getApplicationContext(), "Llamando, Espere por favor...", Toast.LENGTH_LONG).show();
                    startActivity(intentLlamada);
                }
            };
            Handler h = new Handler();
            h.postDelayed(r, 1); // <-- "1" es el tiempo de retraso en milisegundos.
        }catch (RuntimeException e){
            Toast.makeText(getApplicationContext(), "Llamada no realizada", Toast.LENGTH_LONG).show();
        }
    }



    public void llamar_proteccion_ambiental(View view) {

        try {
            final Intent intentLlamada = new Intent(Intent.ACTION_CALL, Uri.parse("tel:0315159581"));
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    if (ActivityCompat.checkSelfPermission(llamadas.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    Toast.makeText(getApplicationContext(), "Llamando, Espere por favor...", Toast.LENGTH_LONG).show();
                    startActivity(intentLlamada);
                }
            };
            Handler h = new Handler();
            h.postDelayed(r, 1); // <-- "1" es el tiempo de retraso en milisegundos.
        }catch (RuntimeException e){
            Toast.makeText(getApplicationContext(), "Llamada no realizada", Toast.LENGTH_LONG).show();
        }
    }

    public void llamar_min_amb(View view) {

        try {
            final Intent intentLlamada = new Intent(Intent.ACTION_CALL, Uri.parse("tel:0313323400"));
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    if (ActivityCompat.checkSelfPermission(llamadas.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    Toast.makeText(getApplicationContext(), "Llamando, Espere por favor...", Toast.LENGTH_LONG).show();
                    startActivity(intentLlamada);
                }
            };
            Handler h = new Handler();
            h.postDelayed(r, 1); // <-- "1" es el tiempo de retraso en milisegundos.
        }catch (RuntimeException e){
            Toast.makeText(getApplicationContext(), "Llamada no realizada", Toast.LENGTH_LONG).show();
        }
    }

    public void llamar_wwf(View view) {

        try {
            final Intent intentLlamada = new Intent(Intent.ACTION_CALL, Uri.parse("tel:0312178093"));
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    if (ActivityCompat.checkSelfPermission(llamadas.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    Toast.makeText(getApplicationContext(), "Llamando, Espere por favor...", Toast.LENGTH_LONG).show();
                    startActivity(intentLlamada);
                }
            };
            Handler h = new Handler();
            h.postDelayed(r, 1); // <-- "1" es el tiempo de retraso en milisegundos.
        }catch (RuntimeException e){
            Toast.makeText(getApplicationContext(), "Llamada no realizada", Toast.LENGTH_LONG).show();
        }
    }

    public void llamar_sec(View view) {

        try {
            final Intent intentLlamada = new Intent(Intent.ACTION_CALL, Uri.parse("tel:0313778899"));
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    if (ActivityCompat.checkSelfPermission(llamadas.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    Toast.makeText(getApplicationContext(), "Llamando, Espere por favor...", Toast.LENGTH_LONG).show();
                    startActivity(intentLlamada);
                }
            };
            Handler h = new Handler();
            h.postDelayed(r, 1); // <-- "1" es el tiempo de retraso en milisegundos.
        }catch (RuntimeException e){
            Toast.makeText(getApplicationContext(), "Llamada no realizada", Toast.LENGTH_LONG).show();
        }
    }

    public void llamar_policia(View view) {

        try {
            final Intent intentLlamada = new Intent(Intent.ACTION_CALL, Uri.parse("tel:122"));
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    if (ActivityCompat.checkSelfPermission(llamadas.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    Toast.makeText(getApplicationContext(), "Llamando, Espere por favor...", Toast.LENGTH_LONG).show();
                    startActivity(intentLlamada);
                }
            };
            Handler h = new Handler();
            h.postDelayed(r, 1); // <-- "1" es el tiempo de retraso en milisegundos.
        }catch (RuntimeException e){
            Toast.makeText(getApplicationContext(), "Llamada no realizada", Toast.LENGTH_LONG).show();
        }
    }

    public void llamar_CRC(View view) {

        try {
            final Intent intentLlamada = new Intent(Intent.ACTION_CALL, Uri.parse("tel:333"));
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    if (ActivityCompat.checkSelfPermission(llamadas.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    Toast.makeText(getApplicationContext(), "Llamando, Espere por favor...", Toast.LENGTH_LONG).show();
                    startActivity(intentLlamada);
                }
            };
            Handler h = new Handler();
            h.postDelayed(r, 1); // <-- "1" es el tiempo de retraso en milisegundos.
        }catch (RuntimeException e){
            Toast.makeText(getApplicationContext(), "Llamada no realizada", Toast.LENGTH_LONG).show();
        }
    }

    public void llamar_fiscalia(View view) {

        try {
            final Intent intentLlamada = new Intent(Intent.ACTION_CALL, Uri.parse("tel:0317910758"));
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    if (ActivityCompat.checkSelfPermission(llamadas.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    Toast.makeText(getApplicationContext(), "Llamando, Espere por favor...", Toast.LENGTH_LONG).show();
                    startActivity(intentLlamada);
                }
            };
            Handler h = new Handler();
            h.postDelayed(r, 1); // <-- "1" es el tiempo de retraso en milisegundos.
        }catch (RuntimeException e){
            Toast.makeText(getApplicationContext(), "Llamada no realizada", Toast.LENGTH_LONG).show();
        }
    }
}