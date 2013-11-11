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

public class Salud extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		final String tipo = getIntent().getStringExtra("Type");
		final String ciudad = getIntent().getStringExtra("Ciudad");
		
		
		ImageView banner = (ImageView) findViewById(R.id.imgBanner);
		banner.setImageResource(R.drawable.headersalud);
		
		RenglonImagen[] arrayOfElementos = new RenglonImagen[2];

		RenglonImagen hospital = new RenglonImagen("btnhospitales");
		arrayOfElementos[0] = hospital;
		RenglonImagen remedios = new RenglonImagen("btnremedios");
		arrayOfElementos[1] = remedios;

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
			 
			 	switch (position) {
				case 0:
					Intent tmp1 = new Intent(Salud.this,ListarLugares.class);
					tmp1.putExtra("Type", tipo);
					tmp1.putExtra("Nombre", "Hospitales");
					tmp1.putExtra("Ciudad", ciudad);
					startActivity(tmp1);
					break;
				case 1:
					Intent tmp2 = new Intent(Salud.this,ListarRemedios.class);
					tmp2.putExtra("Ciudad", ciudad);
					startActivity(tmp2);
					break;
				default:
					break;
				}
			}
		});
		 
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
