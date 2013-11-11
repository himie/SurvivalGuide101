package com.urbanjungle.survivalguide101;

import com.urbanjungle.survivalguide101.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class SeleccionarCiudad extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home2);
		
		ImageView sf = (ImageView) findViewById(R.id.btnSanFrancisco);
		ImageView la = (ImageView) findViewById(R.id.btnLosAngeles);
		
		la.setClickable(true);
        la.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    	Intent intCiudad = new Intent(SeleccionarCiudad.this,Home.class);
                    	intCiudad.putExtra("Ciudad", "1");
                    	startActivity(intCiudad);
                    }
                });
		
		sf.setClickable(true);
        sf.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    	Intent intCiudad = new Intent(SeleccionarCiudad.this,Home.class);
                    	intCiudad.putExtra("Ciudad", "2");
                    	startActivity(intCiudad);
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
