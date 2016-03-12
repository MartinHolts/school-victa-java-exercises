import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

public class DrawingPanel extends JPanel {

	private Color drawingColor = Color.BLACK;
	private Map map = null;

	public void updateColor(Color newColor) {
		drawingColor = newColor;
		repaint();
	}

	public void setMap(Map map) {
		this.map = map;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(drawingColor);
		g.fillRect(0, 0, getSize().width, getSize().height);
		//g.fillRect(0, 0, MainWindow.WINDOW_WIDTH, MainWindow.WINDOW_HEIGHT);

		if (map != null) {
			int x = (getSize().width / 2) - (map.getWidth() / 2);
			int y = (getSize().height / 2) - (map.getHeight() / 2);
			map.draw(g, x, y);
		}

		System.out.println("paint called!");	
	}

	}