import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import org.jdesktop.swingx.JXMapKit;
import org.jdesktop.swingx.JXMapViewer;
import org.jdesktop.swingx.JXMapKit.DefaultProviders;
import org.jdesktop.swingx.mapviewer.GeoPosition;
import org.jdesktop.swingx.mapviewer.Waypoint;
import org.jdesktop.swingx.mapviewer.WaypointPainter;
import org.jdesktop.swingx.mapviewer.WaypointRenderer;

public class Main {

	public static final void main(String args[]) {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}

		JFrame frame = new JFrame("JXMapViewer with swingwaypoints");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JXMapKit mapkit = new JXMapKit();
		mapkit.setDefaultProvider(DefaultProviders.OpenStreetMaps);
		mapkit.setAddressLocationShown(true);
		frame.add(mapkit);

		mapkit.setAddressLocation(new GeoPosition(-7.802778, 111.263056));
		mapkit.setZoom(10);

		JComponent test1 = new JPanel() {
			@Override
			public void paint(Graphics g) {
				setSize(new Dimension(24, 24));
				Graphics2D g2d = (Graphics2D) g;
				g2d.setClip(null);
				g2d.setColor(Color.RED);
				setPreferredSize(new Dimension(100, 100));
				g2d.drawLine(0, 0, getWidth(), getHeight());
				g2d.drawLine(0, getHeight(), getWidth(), 0);
				setVisible(true);
			}
		};
		JButton test2 = new JButton("test2");
		JRadioButton test3 = new JRadioButton();

		test1.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				System.out.println("Clicked the panel");
			}

			public void mouseEntered(MouseEvent e) {
			};

			public void mouseExited(MouseEvent e) {
			};

			public void mousePressed(MouseEvent e) {
			};

			public void mouseReleased(MouseEvent e) {
			};
		});

		test2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("The test2 button was clicked");
			}
		});

		test3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("someone clicked the radio button");
			}
		});

		final Set<SwingWaypoint> waypoints = new HashSet<SwingWaypoint>();
		waypoints.add(new SwingWaypoint(test1, new GeoPosition(-7.502778,
				111.263056)));
		waypoints.add(new SwingWaypoint(test2, new GeoPosition(-8.02778,
				111.263056)));
		waypoints.add(new SwingWaypoint(test3,
				new GeoPosition(-7.502778, 110.5)));

		JXMapViewer map = mapkit.getMainMap();
		for (Waypoint wp : waypoints)
			map.add(((SwingWaypoint) wp).getComponent());

		WaypointPainter painter = new WaypointPainter() {
			@Override
			protected void doPaint(Graphics2D g, JXMapViewer map, int width,
					int height) {
				for (Waypoint wp : waypoints) {
					Point2D gp_pt = map.getTileFactory().geoToPixel(
							wp.getPosition(), map.getZoom());
					Rectangle rect = map.getViewportBounds();
					Point pt = new Point((int) gp_pt.getX() - rect.x,
							(int) gp_pt.getY() - rect.y);
					JComponent component = ((SwingWaypoint) wp).getComponent();
					component.setLocation(pt);
				}
			}
		};
		painter.setWaypoints(waypoints);

		map.setOverlayPainter(painter);
		frame.setBounds(100, 100, 400, 400);
		frame.setVisible(true);
	}
}