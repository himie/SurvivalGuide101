package com.urbanjungle.survivalguide101;

import java.util.ArrayList;

import org.osmdroid.util.BoundingBoxE6;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.ItemizedIconOverlay.OnItemGestureListener;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MapasOffline extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		final String ciudad = getIntent().getStringExtra("Ciudad");
		int ciutat = Integer.parseInt(ciudad);
		
		 MapView mapView = new MapView(this, 256); //constructor
		 
	     mapView.setClickable(true);
	     mapView.setBuiltInZoomControls(true);
	     mapView.setMultiTouchControls(true);
	     
	     setContentView(mapView); //displaying the MapView
	     
	     mapView.getController().setZoom(14); //set initial zoom-level, depends on your need
 
	     switch (ciutat) {
		case 1:
			mapView.getController().setCenter(new GeoPoint(34.0522300, -118.2436800)); //(Latitud, Longitud)
			break;
		case 2:
			mapView.getController().setCenter(new GeoPoint(37.775, -122.4183333)); //(Latitud, Longitud)
			break;
		default:
			break;
		}
	   
	     ConnectivityManager connManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
	     NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

	     if (mWifi.isConnected()) {
	    	 mapView.setUseDataConnection(true);
	     } else {
	    	 mapView.setUseDataConnection(false);//keeps the mapView from loading online tiles using network connection.
		     mapView.setMinZoomLevel(13);
		     mapView.setMaxZoomLevel(15);
		     getBoundaries(mapView, ciutat);
	     }
	    	 
	     
	     
	     ///////////////////////////////////
	     
	     OnItemGestureListener<OverlayItem> myOnItemGestureListener = new OnItemGestureListener<OverlayItem>() {
			 
	 	    @Override
	 	    //Que haga algo si se deja presionado mucho el mark
	 	    public boolean onItemLongPress(int arg0, OverlayItem arg1) {
	 	        // TODO Auto-generated method stub
	 	        return false;
	 	    }
	 	 
	 	    @Override
	 	   //Que haga algo si se presiona el mark
	 	    public boolean onItemSingleTapUp(int index, OverlayItem item) {
	 	             
	 	        return true;
	 	    }
	 	 
	 	};
	     
	 	 //Lista de Marks
	     ArrayList<OverlayItem> anotherOverlayItemArray;
	     anotherOverlayItemArray = new ArrayList<OverlayItem>();
	      
	     //Marks
	     anotherOverlayItemArray.add(new OverlayItem("Los Angeles", "California", new GeoPoint(34.0522300, -118.2436800)));
	     anotherOverlayItemArray.add(new OverlayItem("San Francisco", "California", new GeoPoint(37.775, -122.4183333)));
	     
	     ItemizedOverlayWithFocus<OverlayItem> anotherItemizedIconOverlay = new ItemizedOverlayWithFocus<OverlayItem>(this, anotherOverlayItemArray, myOnItemGestureListener);
	     mapView.getOverlays().add(anotherItemizedIconOverlay);
	     
	     anotherItemizedIconOverlay.setFocusItemsOnTap(true);
	     
	   //Seleccionar Mark de la lista
	    //anotherItemizedIconOverlay.setFocusedItem(0);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}
	
	public void getBoundaries(MapView map, int ciudad){
		 switch (ciudad) {
			case 1:
				map.setScrollableAreaLimit(new BoundingBoxE6(34.089, -118.125, 33.72434, -118.5205));
				break;
			case 2:
				map.setScrollableAreaLimit(new BoundingBoxE6(37.808699, -122.356796, 37.709356, -122.515411));
				break;
			default:
				break;
			}
	}

}
