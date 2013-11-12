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
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
/*
 * This class list all the translations from English to Spanish on the database
 * and shows them in a ListView.
 */
public class ListarTraducciones extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_traduccion);
		
		
		ListView listView = (ListView) findViewById(R.id.lstTraducc);
		RenglonTradAdapter adaptador = new RenglonTradAdapter(this);
		
		//This list returns all the translations on the databse
		List<Traduccion> listaTraducciones = Traduccion.getAll();
		
		RenglonTrad row[] = new RenglonTrad[listaTraducciones.size()];
		int i = 0;
		
		//This for populates the array of rows
		for(Traduccion translate : listaTraducciones){
			row[i] = new RenglonTrad(translate.ingles, translate.espanol);
			adaptador.add(row[i]);
			i++;
		}
			listView.setAdapter(adaptador);
			listView.setDivider(null);
			listView.setDividerHeight(0);
			
			
			//Here the Home button is implemented
			ImageView regresar = (ImageView) findViewById(R.id.imgHomeTrad);
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
