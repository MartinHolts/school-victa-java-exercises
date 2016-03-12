import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;

public class MainWindow extends JFrame implements ActionListener, KeyListener {


	private static final int WINDOW_WIDTH = 900;
	private static final int WINDOW_HEIGHT = 600;

	private JButton updateButton;
	private DrawingPanel drawingPanel;
	private Map map;

	public static void main(String[] args) {

		String fileName = null;
		if (args.length > 0) {
			fileName = args[0];
    	}

		MainWindow mainWindow = new MainWindow(fileName);
		mainWindow.setVisible(true);

		
		System.out.println("Running!");

	}

	public MainWindow(String fileName) {
		initializeGUI();
		initializeEvents();
		initializeMap();
		if (fileName != null) {
			map = new Map(fileName);
			drawingPanel.setMap(map);
		}
	}

	private void initializeGUI() {
		setTitle("Graphics Test");
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(new BorderLayout());

		drawingPanel = new DrawingPanel();
		add(drawingPanel, BorderLayout.CENTER);

		JPanel buttonsPanel = new JPanel();
		updateButton = new JButton();
		updateButton.setText("Update");
		buttonsPanel.add(updateButton);		
		add(buttonsPanel, BorderLayout.SOUTH);
	}

	private void initializeEvents() {
		updateButton.addActionListener(this);
		addKeyListener(this);
		setFocusable(true); // in order to make KeyListener work!
	}

	private void initializeMap() {
		// Instanciate a map object from the class Map. 
		map = new Map(10, 10);
		drawingPanel.setMap(map);
	}

	public void actionPerformed(ActionEvent event) {		
		if (event.getSource() == updateButton) {
			System.out.println("Action happened!");
			drawingPanel.updateColor(Color.BLUE);
		}
	}

	public void keyReleased(KeyEvent event) {
		if (map != null) {
			map.update(event.getKeyCode());
			drawingPanel.repaint();
		}
	}

	public void keyPressed(KeyEvent event) {
		//System.out.println("keyPressed=" + event.getKeyCode());
	}

	public void keyTyped(KeyEvent event) {
		//System.out.println("keyTyped=" + event.getKeyCode());
	}

}