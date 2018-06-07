
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class MainFrame extends JFrame {
	ControlPanel controlPanel;
	DesignPanel designPanel;
	JPanel container;

	public MainFrame() {
		super("Swing Designer");
		init();
	}
	private void init() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        
		container = new JPanel();

		controlPanel = new ControlPanel(this);
		designPanel = new DesignPanel(this);

		container.add(controlPanel);
		container.add(designPanel);

		add(container);

		setSize(800, 600);
		setVisible(true);

		pack();
	}
    //create the main method
}