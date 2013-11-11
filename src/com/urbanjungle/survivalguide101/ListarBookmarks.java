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

public class ListarBookmarks extends Activity {
	
	RenglonAdapter adaptador;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		
		ImageView banner = (ImageView) findViewById(R.id.imgBanner);
		banner.setImageResource(R.drawable.headerbookmarks);
		
		final ListView listView = (ListView) findViewById(R.id.lstHome);
		
		final List<Bookmark> listaBookmark = Bookmark.getAll();
		
		
		adaptador = new RenglonAdapter(this);
		
		
		Renglon row[] = new Renglon[listaBookmark.size()];
		int i = 0;
		
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
					startActivity(Int2);
					break;
				case 2:
					Intent Int3 = new Intent(ListarBookmarks.this, VerDerecho.class);
					Derechos objecte = Derechos.getById(eleccion.id_bookmark);
					Int3.putExtra("Titulo", objecte.nombre);
					startActivity(Int3);
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
	
	@Override
	public void onRestart(){
		super.onRestart();
		adaptador.setNotifyOnChange(true);
		
	}

}
