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
 * Luis Fernando Mata Lic�n (wisimata@gmail.com)
 *
 * Jorge Arturo Alejos Loya (A00758689@itesm.mx)
 *
 * Gerardo Anselmo Torres P�rez (chemoatp@gmail.com)
 *
 * David Alejandro Jim�nez Jaramillo (himie.las@gmail.com)
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
 * This class list all the home remedies on the database
 * and shows them in a ListView.
 */
public class ListarRemedios extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		final String ciudad = getIntent().getStringExtra("Ciudad");
		
		ImageView banner = (ImageView) findViewById(R.id.imgBanner);
		banner.setImageResource(R.drawable.headerremedios);
		
		final ListView listView = (ListView) findViewById(R.id.lstHome);
		

		RenglonAdapter adaptador = new RenglonAdapter(this);
		
		//This list returns all the data of remedies on the database
		List<Remedio> listaRemedios = Remedio.getAll();
		Renglon row[] = new Renglon[listaRemedios.size()];
		int i = 0;
		
		/*
		 * This for populates the data on the row array in order to
		 * populate the ListView
		 */
		for(Remedio remedio : listaRemedios){
			row[i] = new Renglon(remedio.nombre, "");
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
				
					Intent Int3 = new Intent(ListarRemedios.this, VerRemedio.class);
					Renglon element = (Renglon) listView.getItemAtPosition(position);
					String dato = element.titulo;
					Int3.putExtra("Ciudad", ciudad);
					Int3.putExtra("Titulo", dato);
					startActivity(Int3);

			}
		});
		
		//Here we implement the Home button
		ImageView regresar = (ImageView) findViewById(R.id.imgReturn);
		regresar.setImageResource(R.drawable.homeazul);
		regresar.setClickable(true);
		regresar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent a = new Intent(ListarRemedios.this,Home.class);
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
