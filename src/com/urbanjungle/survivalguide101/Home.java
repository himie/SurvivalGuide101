/*
 * American Data Fest 2013
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

/*
 * This class implements the City Home, that works as the main view of the app.
 * Here the user can choose between, any of the principal items of the app or to see
 * their bookmarks.
 */
public class Home extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		// Here we get the city we're right know
		final String ciudad = getIntent().getStringExtra("Ciudad");
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.LinearLayout2);
		linearLayout.setBackgroundResource(R.drawable.fondociudades);

		int city = Integer.parseInt(ciudad);
		
		//Here we select the banner for each city
		ImageView banner = (ImageView) findViewById(R.id.imgBanner);
		switch (city) {
		case 1:
			banner.setImageResource(R.drawable.headerla);
			break;
		case 2:
			banner.setImageResource(R.drawable.headersf);
			break;
		default:
			break;
		}
		
		/*
		 * Here we populate the array of elements for the ListView
		 * For the rows of the ListView we use the item RenglonImagen
		 * that is a row that uses a Image as element.
		 */
		RenglonImagen[] arrayOfElementos = new RenglonImagen[5];

		RenglonImagen transp = new RenglonImagen("btnmapa");
		arrayOfElementos[0] = transp;
		RenglonImagen ong = new RenglonImagen("btnasistencia");
		arrayOfElementos[1] = ong;
		RenglonImagen salud = new RenglonImagen("btnsalud");
		arrayOfElementos[2] = salud;
		RenglonImagen traducc = new RenglonImagen("btntraduccion");
		arrayOfElementos[3] = traducc;
		RenglonImagen bookmarks = new RenglonImagen("btnbookmarks");
		arrayOfElementos[4] = bookmarks;
		
		//Here we populate the custom adapter for
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

				String datosEnviar = "" + id;

				switch (position) {
				case 0:
					Intent Int0 = new Intent(Home.this, Mapas.class);
					Int0.putExtra("Ciudad", ciudad);
					startActivity(Int0);
					break;
				case 1:
					Intent Int1 = new Intent(Home.this, Asistencia.class);
					Int1.putExtra("Nombre", "Asistencia");
					Int1.putExtra("Ciudad", ciudad);
					startActivity(Int1);
					break;
				case 2:
					Intent Int2 = new Intent(Home.this, Salud.class);
					datosEnviar = "" + 0;
					Int2.putExtra("Type", datosEnviar);
					Int2.putExtra("Ciudad", ciudad);
					startActivity(Int2);
					break;
				case 3:
					Intent Int3 = new Intent(Home.this,
							ListarTraducciones.class);
					startActivity(Int3);
					break;
				case 4:
					Intent Int4 = new Intent(Home.this, ListarBookmarks.class);
					startActivity(Int4);
					break;
				default:
					break;
				}

			}
		});

		ImageView regresar = (ImageView) findViewById(R.id.imgReturn);
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
