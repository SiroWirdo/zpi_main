package main.view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSlider;

import main.controller.MapController;
import main.model.MapModel;

import org.jdesktop.swingx.JXMapKit;
import org.jdesktop.swingx.JXMapViewer;
import org.jdesktop.swingx.mapviewer.GeoPosition;
import org.jdesktop.swingx.mapviewer.WaypointPainter;
import org.jdesktop.swingx.painter.Painter;

import settings.Settings;

import java.awt.Font;

/*
 * Tworzy i ustawia mapę, rysuje waypointy i komponenty na mapie
 */
public class MapPanel extends JXMapKit {

	private MapModel mapModel;
	private MapController mapCotroller;

	private JXMapViewer map;
	private Painter painter;
	
	private JButton defaultPositionBtn;
	private JLabel contLabel;
	private JButton zoomIn;
	private JButton zoomOut;
	private JSlider zoomSlider;

	public MapPanel(MapController controller, MapModel mapModel) {
		this.mapCotroller = controller;
		this.mapModel = mapModel;
		map = getMainMap();
		painter = map.getOverlayPainter();
		initialize();
	}
	
	public void initialize() {
		setMapSettings();
		initializeSwingComponent();
	}
	
	private void setMapSettings(){
		this.setDefaultProvider(DefaultProviders.OpenStreetMaps);
		this.setMiniMapVisible(false);
		this.setCenterPosition(new GeoPosition(Settings.DEFAULT_LATITUDE,
				Settings.DEFAULT_LONGITUDE));
		map.setZoom(Settings.DEFAULT_ZOOM);
		map.setRestrictOutsidePanning(true);
		map.setHorizontalWrapped(false);
	}
	
	private void initializeSwingComponent(){
		map.setLayout(null);
		
		defaultPositionBtn = new JButton();
		defaultPositionBtn.setLayout(null);
			JLabel line1 = new JLabel("Pozycja");
				Font font = new Font(line1.getFont().getFontName(), Font.PLAIN, 9);
				line1.setFont(font);
				line1.setBounds(7, 10, 60, 15);
			JLabel line2 = new JLabel("domyślna");
				line2.setFont(font);
				line2.setBounds(4, 27, 60, 15);
		defaultPositionBtn.add(line1);
		defaultPositionBtn.add(line2);
		defaultPositionBtn.setName("defaultPosition");
		defaultPositionBtn.addActionListener(mapCotroller.getButtonListener());
		defaultPositionBtn.setBounds(0, 532, 50, 50);
		
		contLabel = new JLabel(Settings.CONTRIBUTORS_TEXT);
		contLabel.setForeground(Color.BLACK);
		contLabel.setFont(new Font(contLabel.getFont().getFontName(), Font.BOLD, 11));
		contLabel.setBounds(5, 600, 180, 30);
		
		zoomIn = this.getZoomInButton();
		zoomIn.setToolTipText("przybli\u017C");
		zoomIn.setBounds(26, 500, 25, 25);
		
		zoomOut = this.getZoomOutButton();
		zoomOut.setToolTipText("oddal");
		zoomOut.setBounds(0, 500, 25, 25);

		zoomSlider = this.getZoomSlider();
		zoomSlider.setMaximum(Settings.MAX_ZOOM_MAP);
		zoomSlider.setMinimum(Settings.MIN_ZOOM_MAP);
		zoomSlider.setBounds(5, 200, 50, 300);
	}

	public void drawWaypointsComponent(final Set<MapComponent> allWaypoints) {
		for (MapComponent wp : allWaypoints) {
			map.add(wp);
		}
	painter = createWaypointPainter(allWaypoints);
	map.setOverlayPainter(painter);
	}

	public Painter createWaypointPainter(
			final Set<MapComponent> allWaypoints) {
		WaypointPainter painter = new WaypointPainter() {
			@Override
			protected void doPaint(Graphics2D g, JXMapViewer map, int width,
					int height) {
				
				/*
				 * Rysowanie waypointów
				 */
				for (MapComponent wp : allWaypoints) {
					Point2D gp_pt = map.getTileFactory().geoToPixel(
							wp.getWaypoint().getPosition(), map.getZoom());

					Rectangle rect = map.getViewportBounds();
					Point pt = new Point((int) gp_pt.getX() - rect.x,
							(int) gp_pt.getY() - rect.y);
					JLabel component = wp;
					int widthComponent = 30;
					int heightComponent = 40;
					component.setBounds(pt.x - widthComponent / 2, pt.y
							- heightComponent, widthComponent, heightComponent);
				}
				/*
				 * Rysowanie komponentów na mapie
				 */
				addComponentToOverlay(map);
			}
		};
		painter.setWaypoints(allWaypoints);
		return painter;
	}
	
	private void addComponentToOverlay(JXMapViewer map) {
		map.add(defaultPositionBtn);
		map.add(contLabel);
		map.add(zoomIn);
		map.add(zoomOut);
		map.add(zoomSlider);
	}

	public void cleanMap() {
		map.removeAll();
		map.setOverlayPainter(null);
	}
	
	public void setDefaultPosition(){
		this.setAddressLocation(new GeoPosition(Settings.DEFAULT_LATITUDE, Settings.DEFAULT_LONGITUDE));
		this.setZoom(Settings.DEFAULT_ZOOM);
	}
	
	public void setPosition(double latitude, double longitude){
		this.setAddressLocation(new GeoPosition(latitude, longitude));
//		this.setZoom(Settings.DEFAULT_ZOOM);
	}
}

