package com.app.adda.Adda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;



public class Mas_info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mas_info);

        TextView textView = (TextView) findViewById(R.id.usar_adda);
        SpannableString mitextoU = new SpannableString("Cómo usar Adda");
        mitextoU.setSpan(new UnderlineSpan(), 0, mitextoU.length(), 0);
        textView.setText(mitextoU);

        TextView textView_terminos = (TextView) findViewById(R.id.terminos);
        SpannableString mitextoU_terminos = new SpannableString("Terminos");
        mitextoU_terminos.setSpan(new UnderlineSpan(), 0, mitextoU_terminos.length(), 0);
        textView_terminos.setText(mitextoU_terminos);


    }

    public void usar_adda(View view) {

        Intent intent = new Intent(getApplicationContext(), Slider.class)/*.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP)*/;
        startActivity(intent);
    }

    public void terminos(View view) {

        Intent intent = new Intent(getApplicationContext(), terminos.class)/*.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP)*/;
        startActivity(intent);
    }
}
