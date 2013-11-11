package com.urbanjungle.survivalguide101;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class RenglonImagenAdapter extends ArrayAdapter<Object> {
	// View lookup cache
	private static class ViewHolder {
		ImageView imagen;
	}

	public RenglonImagenAdapter(Context context) {
		super(context, R.layout.item_home);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// Get the data item for this position
		Context cxt = getContext();
		RenglonImagen item = (RenglonImagen) getItem(position);
		// Check if an existing view is being reused, otherwise inflate the view
		
		ViewHolder viewHolder; // view lookup cache stored in tag
		if (convertView == null) {
			viewHolder = new ViewHolder();
			LayoutInflater inflater = LayoutInflater.from(getContext());
			convertView = inflater.inflate(R.layout.item_home, null);
			viewHolder.imagen = (ImageView) convertView
					.findViewById(R.id.imgRenglon);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		// Populate the data into the template view using the data object

		int imageResource = cxt.getResources().getIdentifier(item.source, "drawable", cxt.getPackageName());

		Drawable res = cxt.getResources().getDrawable(imageResource);
		viewHolder.imagen.setImageDrawable(res);
		// Return the completed view to render on screen
		return convertView;
	}
	
	
	
}