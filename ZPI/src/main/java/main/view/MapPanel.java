package main.view;



import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.event.MouseInputAdapter;

import main.controller.MapController;
import main.model.MapModel;

import org.jdesktop.swingx.JXMapKit;
import org.jdesktop.swingx.JXMapViewer;
import org.jdesktop.swingx.decorator.BorderHighlighter;
import org.jdesktop.swingx.mapviewer.GeoPosition;
import org.jdesktop.swingx.mapviewer.Waypoint;
import org.jdesktop.swingx.mapviewer.WaypointPainter;
import org.jdesktop.swingx.mapviewer.WaypointRenderer;

import java.awt.GridBagConstraints;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridBagLayout;

public class MapPanel extends JXMapKit {

	MapModel mapModel;
	MapController controller;
	WaypointPainter painter;
	private static final double DEFAULT_LATITUDE = 51.107885200000000000;
	private static final double DEFAULT_LONGITUDE = 17.038537600000040000;
	private static final int DEFAULT_ZOOM = 3;

	public MapPanel(MapController controller, MapModel mapModel) {
		GridBagLayout gridBagLayout = (GridBagLayout) getMainMap().getLayout();
		gridBagLayout.rowWeights = new double[]{0.0};
		this.controller = controller;
		this.mapModel = mapModel;
		JLabel contLabel = new JLabel("Open StreetMap contributors");
		contLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		contLabel.setBounds(0, 300, 10, 20);

		GridBagConstraints gbc_contLabel = new GridBagConstraints();
		gbc_contLabel.anchor = GridBagConstraints.SOUTHEAST;
		getMainMap().add(contLabel, gbc_contLabel);
		initialize();
	}

	public void initialize() {
		this.setCenterPosition(new GeoPosition(DEFAULT_LATITUDE,
				DEFAULT_LONGITUDE));
		// TileFactoryInfo tile = new TileFactoryInfo(0, 4, 4, 256, true, true,
		// "http://tile.openstreetmap.org",
		// "x","y","z"){
		// public String getTileUrl(int x, int y, int zoom) {
		// zoom = 4-zoom;
		// return this.baseURL +"/"+zoom+"/"+x+"/"+y+".png";
		// }
		// };
		// TileFactory tf = new DefaultTileFactory(tile);
		// this.setTileFactory(tf);
		this.setZoomButtonsVisible(false);
		this.setZoomSliderVisible(false);
		this.setMiniMapVisible(false);
		this.setDefaultProvider(DefaultProviders.OpenStreetMaps);
		this.setAddressLocation(new GeoPosition(DEFAULT_LATITUDE,
				DEFAULT_LONGITUDE));
		this.setZoom(DEFAULT_ZOOM);
		this.setMaximumSize(new Dimension(100, 200));
		this.setMinimumSize(new Dimension(50, 20));

		// WaypointPainter<JXMapViewer> painter = new
		// WaypointPainter<JXMapViewer>();
		// painter.setWaypoints(waypoints);
		// painter.setRenderer(new ObjectWaypointRenderer());
		// mainMap.setOverlayPainter(painter);
		// this.setAutoscrolls(false);
		// this.setEnabled(false);

	}


	public void drawWaypointsComponent(final Set<MapComponent> allWaypoints) {
//		final Set<SwingWaypoint> waypoints = new HashSet<SwingWaypoint>();
//		JLabel test2 = new JLabel("TESTTTTTTTTTTT");
//		test2.setBackground(Color.RED);
//		test2.addMouseListener(new WaypointMouseListener());
//		waypoints.add(new SwingWaypoint(test2, new GeoPosition(51.089858,
//				17.020802)));
//		waypoints.add(new SwingWaypoint(test2, new GeoPosition(51.088213,
//				17.064165)));
//		waypoints.add(new SwingWaypoint(test2,
//				new GeoPosition(-7.502778, 110.5)));

		JXMapViewer map = getMainMap();
		for (MapComponent wp : allWaypoints)
			map.add(wp);

		WaypointPainter painter = new WaypointPainter() {
			@Override
			protected void doPaint(Graphics2D g, JXMapViewer map, int width,
					int height) {
				for (MapComponent wp : allWaypoints) {
					Point2D gp_pt = map.getTileFactory().geoToPixel(
							wp.getWaypoint().getPosition(), map.getZoom());
					Rectangle rect = map.getViewportBounds();
					Point pt = new Point((int) gp_pt.getX() - rect.x,
							(int) gp_pt.getY() - rect.y);
					JLabel component = wp;
					component.addMouseListener(new WaypointMouseListener());
					component.setLocation(pt);
				}
			}
		};
		painter.setWaypoints(allWaypoints);
		map.setOverlayPainter(painter);
	}

	private class WaypointMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("Klikniêto waypoint!");
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}
}
//	public void addWaypoint() {
//		// create a Set of waypoints
//		Set<Waypoint> waypoints = new HashSet<Waypoint>();
//		waypoints.add(new Waypoint(51.2, 17.1));
//		waypoints.add(new Waypoint(51.1, 17.03));
//
//		// crate a WaypointPainter to draw the points
//		painter.setRenderer(new WaypointRenderer() {
//			public boolean paintWaypoint(Graphics2D g, JXMapViewer map,
//					Waypoint wp) {
//				Image img1 = Toolkit
//						.getDefaultToolkit()
//						.getImage(
//								"C:\\Users\\Ewelina\\Documents\\Semestr 7\\ZPI\\zpi_taxi_main\\ZPI\\src\\main\\resources\\waypoint_white.png");
//				g.drawImage(img1, -5, -5, +5, +5, null);
//				// g.setColor(Color.RED);
//				// g.drawLine(-5,-5,+5,+5);
//				// g.drawLine(-5,+5,+5,-5);
//				return true;
//			}
//		});
//		painter.setWaypoints(waypoints);
//		this.getMainMap().setOverlayPainter(painter);
//
//	}

