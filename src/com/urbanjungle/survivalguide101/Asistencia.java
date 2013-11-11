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

/* This class implements the view of "Conoce tus derechos" (Know your rights)
 * This is the second item in the city main menu
 */

public class Asistencia extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		// Here we get in which city we are at the moment
		final String ciudad = getIntent().getStringExtra("Ciudad");
		int ciutat = Integer.parseInt(ciudad);

		ImageView banner = (ImageView) findViewById(R.id.imgBanner);
		banner.setImageResource(R.drawable.headerasistencia);

		/*
		 * Our TableView element is called RenglonImagen which is an Image Row
		 * located on drawable
		 */
		RenglonImagen[] arrayOfElementos = new RenglonImagen[3];

		RenglonImagen derecho = new RenglonImagen("btnderechos");
		arrayOfElementos[0] = derecho;
		RenglonImagen consulado;
		switch (ciutat) {
		case 1:
			consulado = new RenglonImagen("btnconsulados");
			break;
		case 2:
			consulado = new RenglonImagen("btnconsuladossf");
			break;
		default:
			consulado = new RenglonImagen("");
			break;
		}
		arrayOfElementos[1] = consulado;
		RenglonImagen ong = new RenglonImagen("btnong");
		arrayOfElementos[2] = ong;

		/*
		 * Here we populate the Custom Adapter for the listview, by adding the
		 * elements on the arrayOfElementos
		 */
		RenglonImagenAdapter adaptador = new RenglonImagenAdapter(this);
		for (int i = 0; i < arrayOfElementos.length; i++) {
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

				switch (position) {
				case 0:
					Intent tmp1 = new Intent(Asistencia.this,
							ListarDerechos.class);
					tmp1.putExtra("Nombre", "Derechos");
					tmp1.putExtra("Ciudad", ciudad);
					startActivity(tmp1);
					break;
				case 1:
					Intent tmp2 = new Intent(Asistencia.this,
							ListarLugares.class);
					tmp2.putExtra("Type", "" + 4);
					tmp2.putExtra("Nombre", "Consulados");
					tmp2.putExtra("Ciudad", ciudad);
					startActivity(tmp2);
					break;
				case 2:
					Intent Int1 = new Intent(Asistencia.this,
							ListarLugares.class);
					Int1.putExtra("Type", "" + 1);
					Int1.putExtra("Nombre", "ONG");
					Int1.putExtra("Ciudad", ciudad);
					startActivity(Int1);
					break;
				default:
					break;
				}
			}
		});

		// This is the HomeButton on the actionbar
		ImageView regresar = (ImageView) findViewById(R.id.imgReturn);
		regresar.setImageResource(R.drawable.homeazul);
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
