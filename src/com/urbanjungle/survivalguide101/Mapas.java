/*
 * American Data Fest 2013
 * ITESM Chihuahua
 * Chihuahua, Chihuahua Mexico
 * November 2013
 * SOS Latino California Edition
 * Team: Urban Jungle
 * 
 * Designer: 
 *  Fernanda Gonzalez Jaquez (s.fernandagonzalez@gmail.com)
 *
 *Developers:
 *
 * Luis Fernando Mata Licón (wisimata@gmail.com)
 *
 * Jorge Arturo Alejos Loya (A00758689@itesm.mx)
 *
 * Gerardo Anselmo Torres Pérez (chemoatp@gmail.com)
 *
 * David Alejandro Jiménez Jaramillo (himie.las@gmail.com)
 * 
 */
package com.urbanjungle.survivalguide101;


import com.urbanjungle.survivalguide101.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

/*
 * This class defines and draw the different kind of maps, but the MapOffline.
 * It draws public transport maps and risk zones maps.
 */
public class Mapas extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		final String ciudad = getIntent().getStringExtra("Ciudad");
		int ciutat = Integer.parseInt(ciudad);
		
		ImageView banner = (ImageView) findViewById(R.id.imgBanner);
		banner.setImageResource(R.drawable.headermapas);
		
		RenglonImagen[] arrayOfElementos = new RenglonImagen[4];

		RenglonImagen autobuses = new RenglonImagen("btnautobuses");
		arrayOfElementos[0] = autobuses;
		RenglonImagen metro = new RenglonImagen("btnmetro");
		arrayOfElementos[1] = metro;
		RenglonImagen mapa;
		switch (ciutat) {
		case 1:
			mapa = new RenglonImagen("btnmapala");
			break;
		case 2:
			mapa = new RenglonImagen("btnmapasf");
			break;
		default:
			mapa = new RenglonImagen("");
			break;
		}
		arrayOfElementos[2] = mapa;
		RenglonImagen peligroso = new RenglonImagen("btnzonasconflic");
		arrayOfElementos[3] = peligroso;

		RenglonImagenAdapter adaptador = new RenglonImagenAdapter(this);
		for(int i = 0; i < arrayOfElementos.length; i++){
			adaptador.add(arrayOfElementos[i]);
		}
		ListView listView = (ListView) findViewById(R.id.lstHome);
		listView.setAdapter(adaptador);
		
		listView.setDivider(null);
		listView.setDividerHeight(0);

		
		 listView.setOnItemClickListener(new OnItemClickListener() {
		 
		 @Override 
			public void onItemClick(AdapterView<?> a, View v, int position,
					long id) {
			 
			 String datos = "" + position;
			
			 
			 	switch (position) {
				case 0:
					Intent tmp1 = new Intent(Mapas.this,VerMapa.class);
					tmp1.putExtra("Imagen", datos);
					tmp1.putExtra("Name", "Bus");
					tmp1.putExtra("Ciudad", ciudad);
					startActivity(tmp1);
					break;
				case 1:
					Intent tmp2 = new Intent(Mapas.this,VerMapa.class);
					tmp2.putExtra("Imagen", datos);
					tmp2.putExtra("Name", "Metro");
					tmp2.putExtra("Ciudad", ciudad);
					startActivity(tmp2);
					break;
				case 2:
					Intent tmp4 = new Intent(Mapas.this, MapasOffline.class);
					tmp4.putExtra("Ciudad", ciudad);
					startActivity(tmp4);
					break;
				case 3:
					Intent tmp3 = new Intent(Mapas.this,VerMapa.class);
					tmp3.putExtra("Imagen", datos);
					tmp3.putExtra("Name", "Zonas Peligrosas");
					tmp3.putExtra("Ciudad", ciudad);
					startActivity(tmp3);
					break;
				default:
					break;
				}
			}
		});
		 
		 ImageView regresar = (ImageView) findViewById(R.id.imgReturn);
			regresar.setImageResource(R.drawable.homeamarilla);
			regresar.setClickable(true);
			regresar.setOnClickListener(new OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	
	            	finish();
	            }
	        });
		 

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

}
