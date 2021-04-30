import javax.swing.JFrame;
public class Gui {

	private static void createGUI() {
		MyFrame frame = new MyFrame("GUI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 700);
		frame.setVisible(true);

	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				createGUI();

			}
		});

	}

}
