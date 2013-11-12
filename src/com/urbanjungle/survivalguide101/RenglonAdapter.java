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

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
/*
 * This is a custom adapter for the Renglon (Row) item
 */

public class RenglonAdapter extends ArrayAdapter<Object> {
	// View lookup cache
	private static class ViewHolder {
		TextView principal;
		@SuppressWarnings("unused")
		TextView secundario;
	}

	public RenglonAdapter(Context context) {
		super(context, R.layout.item_elemento);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// Get the data item for this position
		Renglon item = (Renglon) getItem(position);
		Context cxt = super.getContext();
		// Check if an existing view is being reused, otherwise inflate the view
		ViewHolder viewHolder; // view lookup cache stored in tag
		
		if (convertView == null) {
			viewHolder = new ViewHolder();
			LayoutInflater inflater = LayoutInflater.from(getContext());
			convertView = inflater.inflate(R.layout.item_elemento, null);
			viewHolder.principal = (TextView) convertView
					.findViewById(R.id.txtImagen);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		  
		Typeface font = Typeface.createFromAsset(cxt.getAssets(), "futura.ttc");  
		viewHolder.principal.setTypeface(font);
		// Populate the data into the template view using the data object
		viewHolder.principal.setText(item.titulo);
		// Return the completed view to render on screen
		return convertView;
	}
}