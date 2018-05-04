package com.app.adda.Adda;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;



/**
 * Created by camiloandresibarrayepes on 21/03/18.
 */

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context = context;
    }

    public int[] slide_images = {
            R.drawable.montain,
            R.drawable.camera_icon,
            R.drawable.waiticon,
            R.drawable.checkone

    };

    public String[] slide_headings = {
            "REALIZA TU DENUNCIA",
            "TOMA UNA FOTO",
            "ESPERA UNOS SEGUNDOS",
            "LISTO...!!!"
    };

    public String[] slide_desc = {
            "Realiza tu denuncia ambiental con la primera opción que aparece en el menú principal",
            "Captura una foto con tu dispositivo, es importante que captures el momento indicado de la denuncia",
            "Espera unos segundos, puede que demore el envío o que no capture la ubicación al primer intento",
            "Tu denuncia ha sido enviada con éxito, una entidad se pondrá en contacto contigo de ser necesario"
    };

    @Override
    public int getCount(){
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object o){
        return view == (RelativeLayout) o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position){
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container,false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.slide_image);
        TextView slideHeading = (TextView) view.findViewById(R.id.slide_header);
        TextView slideDescription = (TextView) view.findViewById(R.id.slide_desc);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_desc[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object){
        container.removeView((RelativeLayout)object);
    }
}
