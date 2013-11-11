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

public class ListarDerechos extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		final String ciudad = getIntent().getStringExtra("Ciudad");
		
		ImageView banner = (ImageView) findViewById(R.id.imgBanner);
		banner.setImageResource(R.drawable.headerderechos);
		
		
		final ListView listView = (ListView) findViewById(R.id.lstHome);
		

		RenglonAdapter adaptador = new RenglonAdapter(this);
		
		List<Derechos> listaDerechoss = Derechos.getAll();
		Renglon row[] = new Renglon[listaDerechoss.size()];
		int i = 0;
		
		for(Derechos derecho : listaDerechoss){
			row[i] = new Renglon(derecho.nombre, "");
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
				
					Intent Int3 = new Intent(ListarDerechos.this, VerDerecho.class);
					Renglon element = (Renglon) listView.getItemAtPosition(position);
					String dato = element.titulo;
					Int3.putExtra("Ciudad", ciudad);
					Int3.putExtra("Titulo", dato);
					startActivity(Int3);

			}
		});
		
		ImageView regresar = (ImageView) findViewById(R.id.imgReturn);
		regresar.setImageResource(R.drawable.homeazul);
		regresar.setClickable(true);
		regresar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent a = new Intent(ListarDerechos.this,Home.class);
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
