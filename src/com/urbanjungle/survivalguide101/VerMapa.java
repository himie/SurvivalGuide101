package com.urbanjungle.survivalguide101;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TextView;

/*
 * This class defines and draw the different kind of maps, but the MapOffline.
 * It draws public transport maps and risk zones maps.
 */

public class VerMapa extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imagenes);
        final String ciudad = getIntent().getStringExtra("Ciudad");
       
        String info = getIntent().getStringExtra("Imagen");
        int dato = Integer.parseInt(info);
        String nombre = getIntent().getStringExtra("Name");
        
        TouchImageView img = (TouchImageView) findViewById(R.id.imgImagen);
        
        TextView name = (TextView) findViewById(R.id.txtImagen);
        name.setText(nombre);
        Resources res = getResources();
        img.setMaxZoom(7.0f);
        try{
        int id = 0;
        
        switch (dato) {
		case 0:
			id = getResources().getIdentifier("bus_" + ciudad, "drawable", getPackageName());
			break;
		case 1:
			id = getResources().getIdentifier("metro_" + ciudad, "drawable", getPackageName());
			break;
		case 3:
			id = getResources().getIdentifier("peligroso_" + ciudad, "drawable", getPackageName());
			break;
		default:
			break;
		}
        
        if(id != 0)
        img.setImageDrawable(res.getDrawable(id));}
        catch(OutOfMemoryError e){
        	finish();
        	startActivity(getIntent());
        }
        
        
        
    }
}