package com.urbanjungle.survivalguide101;

import java.util.List;
import java.util.Locale;

import com.urbanjungle.survivalguide101.R;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class VerLugar extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.datoslugares);
		final String ciudad = getIntent().getStringExtra("Ciudad");
		
		
		String cabecera = getIntent().getStringExtra("Titulo");
		
		TextView titulo = (TextView) findViewById(R.id.txtImagen);
		Typeface font = Typeface.createFromAsset(getAssets(), "futura.ttc");  
		titulo.setTypeface(font);
		
		TextView telefon = (TextView) findViewById(R.id.txtTelefon);
		TextView direccion = (TextView) findViewById(R.id.txtDirecci);
		TextView especial = (TextView) findViewById(R.id.txtEspecialTi);
		
		telefon.setTypeface(font);
		direccion.setTypeface(font);
		especial.setTypeface(font);
		
		//Here we declare the bookmark button, that we're going to use for adding or deleting bookmarks
		final ImageView bookmark = (ImageView) findViewById(R.id.imgBookmark);
		bookmark.setClickable(true);

		ImageView banner = (ImageView) findViewById(R.id.imgBanner);
		
		TextView especialidad = (TextView) findViewById(R.id.txtEspecialidad);
		especialidad.setMovementMethod(new ScrollingMovementMethod());
		
		TextView telef = (TextView) findViewById(R.id.txtTelefono);
		TextView direcc = (TextView) findViewById(R.id.txtAddr);
		
		telef.setTypeface(font);
		direcc.setTypeface(font);
		especialidad.setTypeface(font);
		
		final Lugar actual = Lugar.getLugar(cabecera, ciudad);
		
		switch (actual.tipo) {
		case 0:
			banner.setImageResource(R.drawable.headerlugar);
			break;
		case 1:
			banner.setImageResource(R.drawable.headerlugaresasist);
			break;
		case 4:
			banner.setImageResource(R.drawable.headerlugaresasist);
			break;
		default:
			break;
		}
		
		direcc.setText(actual.direccion);
		telef.setText("" + actual.tel);
		especialidad.setText(actual.descripcion);
		
		String title = cabecera.toUpperCase(Locale.getDefault());
		titulo.setText(title);
		
		
		if (existe(actual)) {
			bookmark.setImageResource(R.drawable.bookmarkazul);
		}

		bookmark.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(existe(actual)){
				deleteBookmark(actual);
				bookmark.setImageResource(R.drawable.bookmarkvacio);
				}
				else{
					addBookmark(actual);
					bookmark.setImageResource(R.drawable.bookmarkazul);
				}
			}
		});
		
		ImageView regresar = (ImageView) findViewById(R.id.btnHomeLugar);
		regresar.setImageResource(R.drawable.homeazul);
		regresar.setClickable(true);
		regresar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent a = new Intent(VerLugar.this,Home.class);
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
	
	public void addBookmark(Lugar actual){
		List<Bookmark> listaConteo = Bookmark.getAll();
		int count = listaConteo.size();
		int id = 1;
		
		try {  
			id = listaConteo.get(count-1).id + 1;
		} catch (ArrayIndexOutOfBoundsException e) {  
			
		} 
		
		if(count < 25){
			Bookmark nuevo = new Bookmark(id , actual.id, 0);
			nuevo.save();
		}
		else
		{
			AlertDialog.Builder msg = new AlertDialog.Builder(VerLugar.this);
			msg.setTitle("Limite alcanzado");
			msg.setMessage("Limite de Bookmarks alcanzado, por favor elimine.");
			msg.setPositiveButton("Ok", null);
			msg.show();
		}
	}
	
	public void deleteBookmark(Lugar actual){
		Bookmark viejo = Bookmark.getBookmark(actual.id, 0);
		viejo.delete();
		
	}
	
	public boolean existe(Lugar actual){
		try{
			Bookmark nuevo = Bookmark.getBookmark(actual.id, 0);
			@SuppressWarnings("unused")
			int i = nuevo.id_tabla;
			return true;
    	}catch(NullPointerException e){
    		return false;
    	}
	}

}
