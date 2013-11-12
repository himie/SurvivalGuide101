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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

/*
 * This class makes the activity in order to present the bookmarks of the user.
 * It access the database that the user populates interacting with the app
 * 
 */
public class ListarBookmarks extends Activity {
	
	RenglonAdapter adaptador;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		final String ciudad = getIntent().getStringExtra("Ciudad");
		final int ciutat = Integer.parseInt(ciudad);
		
		
		ImageView banner = (ImageView) findViewById(R.id.imgBanner);
		banner.setImageResource(R.drawable.headerbookmarks);
		
		final ListView listView = (ListView) findViewById(R.id.lstHome);
		
		//This is the list of all the info on the bookmarks table
		final List<Bookmark> listaBookmark = Bookmark.getAll(); 
		
		
		adaptador = new RenglonAdapter(this);
		
		//For this ListView we use the Item Renglon(Row).
		Renglon row[] = new Renglon[listaBookmark.size()];
		int i = 0;
		
		/*
		 * In this for we populate the Row array.
		 * It has a switch because the user can have different kinds of bookmarks.
		 * It uses the id_tabla (id_table) that defines what kind of bookmark it is:
		 * 
		 * 0 Lugar (Place) That defines Hospitals, NGOs or Consulates
		 * 1 Remedio (remedy) This defines home remedies
		 * 2 Derechos (rights) Defines inmigrant US rights
		 * 
		 *  Once the object is created, the data is extracted to generate the
		 *  row elements.
		 */
		for(Bookmark bookmark : listaBookmark){
			switch (bookmark.id_tabla) {
			case 0:
				Lugar objeto = Lugar.getById(bookmark.id_bookmark);
				row[i] = new Renglon(objeto.nombre, objeto.descripcion);
				adaptador.add(row[i]);
				break;
			case 1:
				Remedio object = Remedio.getById(bookmark.id_bookmark);
				row[i] = new Renglon(object.nombre, "");
				adaptador.add(row[i]);
				break;
			case 2:
				Derechos objecte = Derechos.getById(bookmark.id_bookmark);
				row[i] = new Renglon(objecte.nombre, "");
				adaptador.add(row[i]);
				break;
			default:
				break;
			}
			i++;
		}
		
		listView.setAdapter(adaptador);
		
		listView.setDivider(null);
		listView.setDividerHeight(0);
		/*
		 * Here we call each activity depending on what kind of bookmark it is,
		 * the switch is defined just as the past method, and the values are the same.
		 */
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> a, View v, int position,
					long id) {
				
				Bookmark eleccion =  listaBookmark.get(position);
				switch (eleccion.id_tabla) {
				case 0:
					Lugar objeto = Lugar.getById(eleccion.id_bookmark);
					Intent Int = new Intent(ListarBookmarks.this, VerLugar.class);
					Int.putExtra("Titulo", objeto.nombre);
					Int.putExtra("Ciudad", "" + objeto.ciudad);
					startActivity(Int);
					break;
				case 1:
					Remedio object = Remedio.getById(eleccion.id_bookmark);
					Intent Int2 = new Intent(ListarBookmarks.this, VerRemedio.class);
					Int2.putExtra("Titulo", object.nombre);
					Int2.putExtra("Ciudad", "" + ciutat);
					startActivity(Int2);
					break;
				case 2:
					Intent Int3 = new Intent(ListarBookmarks.this, VerDerecho.class);
					Derechos objecte = Derechos.getById(eleccion.id_bookmark);
					Int3.putExtra("Titulo", objecte.nombre);
					Int3.putExtra("Ciudad", "" + ciutat);
					startActivity(Int3);
					break;
				default:
					break;
				}

			}
		});
		
		
		//Here we define the home button
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
	
	@Override
	public void onRestart(){
		super.onRestart();
		adaptador.setNotifyOnChange(true);
		
	}

}
