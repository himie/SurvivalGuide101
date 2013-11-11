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

public class VerDerecho extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.datos);
		final String ciudad = getIntent().getStringExtra("Ciudad");
		
		String cabecera = getIntent().getStringExtra("Titulo");
		
		
		ImageView banner = (ImageView) findViewById(R.id.ImageView01);
		banner.setImageResource(R.drawable.headerlugaresasist);
		
		//Here we declare the bookmark button, that we're going to use for adding or deleting bookmarks
		final ImageView bookmark = (ImageView) findViewById(R.id.imgBookmark);
		bookmark.setClickable(true);
		
		TextView titulo = (TextView) findViewById(R.id.txtTitleSimple);
		Typeface font = Typeface.createFromAsset(getAssets(), "futura.ttc");  
		titulo.setTypeface(font);
		
		final Derechos actual = Derechos.getDerechos(cabecera);
		
		TextView descripcion = (TextView) findViewById(R.id.txtDescr);
		
		descripcion.setTypeface(font);
		
		String title = cabecera.toUpperCase(Locale.getDefault());
		titulo.setText(title);
		
		descripcion.setMovementMethod(new ScrollingMovementMethod());
		
		descripcion.setText(actual.descripcion);
		
		//Here, we check if the element exists on our bookmark table
		
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
		
		ImageView regresar = (ImageView) findViewById(R.id.btnHomeDato);
		regresar.setImageResource(R.drawable.homeazul);
		regresar.setClickable(true);
		regresar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent a = new Intent(VerDerecho.this,Home.class);
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
	
	public void addBookmark(Derechos actual){
		List<Bookmark> listaConteo = Bookmark.getAll();
		int count = listaConteo.size();
		int id = 1;
		
		try {  
			id = listaConteo.get(count-1).id + 1;
		} catch (ArrayIndexOutOfBoundsException e) {  
			
		} 
		
		if(count < 25){
			Bookmark nuevo = new Bookmark(id , actual.id, 2);
			nuevo.save();
		}
		else
		{
			AlertDialog.Builder msg = new AlertDialog.Builder(VerDerecho.this);
			msg.setTitle("Limite alcanzado");
			msg.setMessage("Limite de Bookmarks alcanzado, por favor elimine.");
			msg.setPositiveButton("Ok", null);
			msg.show();
		}
	}
	
	public void deleteBookmark(Derechos actual){
		Bookmark viejo = Bookmark.getBookmark(actual.id, 2);
		viejo.delete();
		
	}
	
	public boolean existe(Derechos actual){
		try{
			Bookmark nuevo = Bookmark.getBookmark(actual.id, 2);
			@SuppressWarnings("unused")
			int i = nuevo.id_tabla;
			return true;
    	}catch(NullPointerException e){
    		return false;
    	}
	}

}
