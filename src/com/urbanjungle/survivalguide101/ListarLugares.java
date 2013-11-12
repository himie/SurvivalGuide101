/*
 * American Data Fest 2013
 * ITESM Chihuahua
 * Chihuahua, Chihuahua Mexico
 * November 2013
 * SOS Latino California Edition
 * Team: Urban Jungle
 * 
 */

package com.urbanjungle.survivalguide101;

import java.util.List;

import com.urbanjungle.survivalguide101.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
/*
 * This class list all the places (Hospitals, NGOs, Consulates, by separate) on the database
 * and shows them in a ListView.
 */
public class ListarLugares extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		String tipo = getIntent().getStringExtra("Type");
		final String ciudad = getIntent().getStringExtra("Ciudad");
		int type = Integer.parseInt(tipo);
		
		final ListView listView = (ListView) findViewById(R.id.lstHome);
		String tipoLugar;
		ImageView banner = (ImageView) findViewById(R.id.imgBanner);

		RenglonAdapter adaptador = new RenglonAdapter(this);
		
		
		/*Here we make a Switch in order to know, what kind of Data we're going to show
		0 is for hospitals
		1 is for NGO
		4 is for Counsulate
		
		also we assign the banner image*/
		
		switch (type) {
		case 0:
			tipoLugar = "0";
			banner.setImageResource(R.drawable.headerhospitales);
			break;
		case 1:
			tipoLugar = "1";
			banner.setImageResource(R.drawable.headerong);
			break;
		case 4:
			tipoLugar = "4";
			banner.setImageResource(R.drawable.headerconsulado);
			break;
		default:
			tipoLugar = "";
			break;
		}
		
		List<Lugar> listaLugares = Lugar.getAll(tipoLugar, ciudad);
		Renglon row[] = new Renglon[listaLugares.size()];
		int i = 0;
		
		// Here we populate de adapter that is going to print the list view
		
		for(Lugar place : listaLugares){
			row[i] = new Renglon(place.nombre, place.descripcion);
			adaptador.add(row[i]);
			i++;
		}
		
		listView.setAdapter(adaptador);
		
		listView.setDivider(null);
		listView.setDividerHeight(0);
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> a, View v, int position,
					long id) {

				Intent Int3 = new Intent(ListarLugares.this, VerLugar.class);
				Renglon element = (Renglon) listView.getItemAtPosition(position);
				String dato = element.titulo;
				Int3.putExtra("Titulo", dato);
				Int3.putExtra("Ciudad", ciudad);
				startActivity(Int3);

			}
		});
		
		
		//This implements the Home button
		ImageView regresar = (ImageView) findViewById(R.id.imgReturn);
		regresar.setImageResource(R.drawable.homeazul);
		regresar.setClickable(true);
		regresar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent a = new Intent(ListarLugares.this,Home.class);
       		 	a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
       		 	a.putExtra("Ciudad", ciudad);
       		 	startActivity(a);
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
