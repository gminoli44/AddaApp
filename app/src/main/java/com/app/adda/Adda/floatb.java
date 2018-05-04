package com.app.adda.Adda;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;


import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

public class floatb extends AppCompatActivity {

    String arrayName[] = {"Información",
                          "Enviar denuncia"
                         };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floatb);

        CircleMenu circleMenu =  (CircleMenu)findViewById(R.id.circle_menu);
        circleMenu.setMainMenu(Color.parseColor("#CDCDCD"),R.drawable.mas, R.drawable.equis)
                .addSubMenu(Color.parseColor("#258CFF"), R.drawable.infoicon)
                .addSubMenu(Color.parseColor("#258CFF"), R.drawable.senddos)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {
                    @Override
                    public void onMenuSelected(int i) {
                        Toast.makeText(floatb.this, "Seleccionaste "+ arrayName[i], Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
