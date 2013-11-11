package com.urbanjungle.survivalguide101;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class RenglonTradAdapter extends ArrayAdapter<Object> {
	// View lookup cache
	private static class ViewHolder {
		TextView principal;
		TextView secundario;
	}

	public RenglonTradAdapter(Context context) {
		super(context, R.layout.item_traduccion);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// Get the data item for this position
		RenglonTrad item = (RenglonTrad) getItem(position);
		// Check if an existing view is being reused, otherwise inflate the view
		ViewHolder viewHolder; // view lookup cache stored in tag
		if (convertView == null) {
			viewHolder = new ViewHolder();
			LayoutInflater inflater = LayoutInflater.from(getContext());
			convertView = inflater.inflate(R.layout.item_traduccion, null);
			viewHolder.principal = (TextView) convertView
					.findViewById(R.id.txtIngl);
			viewHolder.secundario = (TextView) convertView
					.findViewById(R.id.txtEspanol);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		// Populate the data into the template view using the data object
		viewHolder.principal.setText(item.ingles);
		viewHolder.secundario.setText(item.espanol);
		// Return the completed view to render on screen
		return convertView;
	}
}