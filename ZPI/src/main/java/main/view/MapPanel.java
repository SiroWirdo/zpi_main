package main.view;

import java.awt.Dimension;

import org.jdesktop.swingx.JXMapKit;
import org.jdesktop.swingx.mapviewer.DefaultTileFactory;
import org.jdesktop.swingx.mapviewer.GeoPosition;
import org.jdesktop.swingx.mapviewer.TileFactory;
import org.jdesktop.swingx.mapviewer.TileFactoryInfo;

public class MapPanel extends JXMapKit  {
	
	private final GeoPosition startPosition;
	
	public MapPanel() {
		startPosition = new GeoPosition(51.107885200000000000,17.038537600000040000);
		this.setCenterPosition(startPosition);
//		TileFactoryInfo  tile = new TileFactoryInfo(0, 4, 4, 256, true, true, "http://tile.openstreetmap.org",
//                "x","y","z"){
//			public String getTileUrl(int x, int y, int zoom) {
//				zoom = 4-zoom;
//				return this.baseURL +"/"+zoom+"/"+x+"/"+y+".png";
//			}
//		};
//		TileFactory tf = new DefaultTileFactory(tile);
//		this.setTileFactory(tf);
		this.setZoomButtonsVisible(false);
		this.setZoomSliderVisible(false);
		this.setMiniMapVisible(false);
		this.setDefaultProvider(DefaultProviders.OpenStreetMaps);
		this.setAddressLocation(startPosition);
		this.setZoom(3);
		this.setMaximumSize(new Dimension(100, 200));
		this.setMinimumSize(new Dimension(50, 20));
//		this.setAutoscrolls(false);
//		this.setEnabled(false);
		this.getMainMap().setOverlayPainter(null);
	}
}
