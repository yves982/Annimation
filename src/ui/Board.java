package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Board extends JPanel {
	private static final String RESSOURCE_PATH = "/ressource/";
	private Image star;
	private static int startX=50, startY=50;
	private static int maxX = 200, maxY = 500;
	private final Rectangle starBounds;
	private AffineTransform at;
	private Point rotateCenter;
	private Rectangle bounds;
	private Rectangle newBounds;
	
	public Board() {
		initUI();
		starBounds = new Rectangle(startX, startY, star.getWidth(null), star.getHeight(null));
		bounds = new Rectangle();
		newBounds = new Rectangle();
		at = new AffineTransform();
		at.translate(startX, startY);
	}

	public void display(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		applyRenderingHints(g2d);
		
		applyTransformation(star, at, g2d);
		
		if(rotateCenter != null) {
			int x = (int)rotateCenter.getX();
			int y = (int)rotateCenter.getY();
			Color c = g2d.getColor();
			g2d.setColor(Color.pink);
			Rectangle fillRect = new Rectangle(x , y, 2, 2);
			g2d.fill(at.createTransformedShape(fillRect));
			g2d.setColor(c);
		}
	}
	
	public void translate(int dX, int dY) {
		updateBounds();
		if(bounds.intersects(newBounds))
		{
			at.translate(dX,  dY);
		}
		else
		{
			at.setToIdentity();
			at.translate(startX, startY);
		}
		
		repaint();
	}

	public void rotate() {
		
		double rotateX = star.getWidth(null)/2;
		double rotateY = star.getHeight(null)/2;
		rotateCenter = new Point((int)rotateX, (int) rotateY);
		at.rotate(Math.toRadians(15.0d), rotateX, rotateY);
		updateBounds();
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		super.paintBorder(g);
		display(g);
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}

	private void updateBounds() {
		bounds.setBounds(getBounds());
		newBounds.setBounds(at.createTransformedShape(starBounds).getBounds());
	}
	
	private void initUI() {
		Dimension minSize = new Dimension(maxX, maxY);
		setPreferredSize(minSize);
		setMinimumSize(minSize);
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		loadStar("star.png");
	}

	private void loadStar(String img) {
		ImageIcon ii = new ImageIcon(getClass().getResource(
				RESSOURCE_PATH + img));
		star = ii.getImage();
	}
	
	private void applyTransformation(Image src, AffineTransform at, Graphics2D dest) {
		dest.drawImage(src, at, null);
	}
	
	private void applyRenderingHints(Graphics2D g) {
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		rh.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHints(rh);
	}
}
