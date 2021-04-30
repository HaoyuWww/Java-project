import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class MyFrame extends JFrame {
	
	public static String res;
	public static String stop;
	JComboBox<String> options = new JComboBox<>();
	JPanel cards = new JPanel();
	JComboBox<String> timeList = new JComboBox<>();

	JTextField textField = new JTextField(16);
	JButton show = new JButton("Click me");

	public MyFrame(String title) {

		super(title);
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		options.addItem("Search by time");
		options.addItem("Search by stop name");
		options.addItem("Search by path");

		contentPane.add(options, BorderLayout.PAGE_START);

		contentPane.add(cards, null);
		// zxl's work

		JPanel P1 = new JPanel();
		P1.setLayout(null);

		String min_Sec[] = { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14",
				"15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31",
				"32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48",
				"49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" };

		String hrs[] = { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15",
				"16", "17", "18", "19", "20", "21", "22", "23" };
		final JComboBox<String> hoursInput = new JComboBox<String>(hrs);
		final JComboBox<String> minInput = new JComboBox<String>(min_Sec);
		final JComboBox<String> secInput = new JComboBox<String>(min_Sec);
		P1.add(show);
		show.setBounds(new Rectangle(350, 50, 80, 25));

		JLabel showDetails = new JLabel("to show bus details");
		P1.add(showDetails);
		showDetails.setBounds(new Rectangle(430, 50, 150, 25));

		P1.add(secInput);
		secInput.setBounds(new Rectangle(250, 50, 80, 25));
		P1.add(minInput);
		minInput.setBounds(new Rectangle(150, 50, 80, 25));
		P1.add(hoursInput);
		hoursInput.setBounds(new Rectangle(50, 50, 80, 25));
		JLabel hours = new JLabel("Hours");
		P1.add(hours);
		hours.setBounds(new Rectangle(60, 30, 80, 25));

		JLabel minutes = new JLabel("Minutes");
		P1.add(minutes);
		minutes.setBounds(new Rectangle(160, 30, 80, 25));

		JLabel seconds = new JLabel("Seconds");
		P1.add(seconds);
		seconds.setBounds(new Rectangle(260, 30, 80, 25));

		JLabel tit = new JLabel();
		tit.setText(
				"trip_id   arrival_time   departure_time   stop_id   stop_sequence   stop_headsign   pickup_type   drop_off_type   shape_dist_traveled");
		P1.add(tit);
		tit.setBounds(20, 150, 1000, 20);

		JTextArea output = new JTextArea();
		output.append(MyFrame.res);
		P1.add(output);
		output.setBounds(20, 200, 1000, 400);

		// contentPane.add(label);
		// contentPane.add(textField);
		// contentPane.add(button);

		show.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String time = hoursInput.getItemAt(hoursInput.getSelectedIndex()) + ":"
						+ minInput.getItemAt(minInput.getSelectedIndex()) + ":"
						+ secInput.getItemAt(secInput.getSelectedIndex());
				// System.out.println(time);
				tripInfoSort(time);
			}
		});

		// yxw's work
		JPanel P2 = new JPanel();
		P2.setLayout(null);
		JTextField typeIn = new JTextField();
		JButton button = new JButton("Search");
		button.setBounds(260, 30, 80, 25);
		JLabel label = new JLabel("Searching for a bus stop by full name or by the first few characters in the name");
		P2.add(label);
		label.setBounds(new Rectangle(60, 5, 700, 25));
		P2.add(typeIn);
		typeIn.setBounds(new Rectangle(60, 30, 200, 25));
		P2.add(button);
		
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*TST t = TST.buildTST();
				String stopName = 
				List<String> stops = t.find(stopName);
				for (String allData : stops) {
					System.out.println(allData);
					//MyFrame.stop = allData;
				}
				System.out.println("Total stop(s)= " + stops.size());
				//System.out.println(stopName );*/
				
				TST t = TST.buildTST();
				if (!TST.source) {
					String stopName = typeIn.getText();
					List<String> stops = t.find(stopName);
					if (stops.size() == 0) {
						System.out.println("Wrong input!");
					} else {
						for (String allData : stops) {
							System.out.println(allData);
						}
						System.out.println("Total stop(s)= " + stops.size());
					}
				}}
			
		});
		/*JTextArea stopOutput = new JTextArea();
		stopOutput.append(MyFrame.stop);
		P2.add(stopOutput);
		stopOutput.setBounds(20, 200, 1000, 400);*/

		// why's work
		JPanel P3 = new JPanel();
		P3.setLayout(null);
		JTextField pathA = new JTextField();
		JTextField pathB = new JTextField();
		JButton b = new JButton("Search");
		b.setBounds(new Rectangle(520, 30, 80, 25));
		P3.add(pathA);
		pathA.setBounds(100, 30, 200, 25);
		P3.add(pathB);
		pathB.setBounds(310, 30, 200, 25);
		P3.add(b);
		JLabel e = new JLabel("Finding shortest paths between 2 bus stops");
		e.setBounds(100, 10, 300, 25);
		P3.add(e);
		JLabel sA = new JLabel("Bus Stop A");
		sA.setBounds(100, 50, 300, 25);
		P3.add(sA);
		JLabel sB = new JLabel("Bus Stop B");
		sB.setBounds(310, 50, 300, 25);
		P3.add(sB);

		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String stopA = pathA.getText();
				String stopB = pathB.getText();

				System.out.print(stopA);
				System.out.print(stopB);

			}
		});

		cards.setLayout(new CardLayout());
		cards.add(P1, "zxl");
		cards.add(P2, "yxw");
		cards.add(P3, "why");

		options.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {

				itemChanged();
			}
		});

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// onButtonOk();

			}
		});
		timeList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
	}

	private void itemChanged() {
		CardLayout cardLayout = (CardLayout) cards.getLayout();
		int index = options.getSelectedIndex();
		if (index == 0) {
			cardLayout.show(cards, "zxl");
		}
		if (index == 1) {
			cardLayout.show(cards, "yxw");
		}
		if (index == 2) {
			cardLayout.show(cards, "why");
		}

	}

//	private void onButtonOk() {
//		String str = textField.getText();
//
//		JOptionPane.showMessageDialog(this, "type into:" + str);
//
//	}
	private static void tripInfoSort(String oneArrivalTime) {
		BufferedReader br;
		HashMap<String, TreeMap<String, String>> infoMap = new HashMap<>();
		try {
			String line;
			br = new BufferedReader(new FileReader("stop_times.txt"));
			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] items = line.split(",");
				String id = items[0];
				String arrivalTime = items[1].trim();
				if (isValidTime(arrivalTime)) {
					TreeMap<String, String> detail;
					if (infoMap.containsKey(arrivalTime)) {
						detail = infoMap.get(arrivalTime);
					} else {
						detail = new TreeMap<>();
					}
					detail.put(id, line);
					infoMap.remove(arrivalTime);
					infoMap.put(arrivalTime, detail);
				}
			}

			if (isValidTime(oneArrivalTime)) {
				if (!infoMap.containsKey(oneArrivalTime)) {
					System.out.println("Zero item matches this time");
					return;
				}
				TreeMap<String, String> treeMap = infoMap.get(oneArrivalTime);
				Iterator<Map.Entry<String, String>> iterator = treeMap.entrySet().iterator();
				while (iterator.hasNext()) {

					// System.out.println(iterator.next().getValue());
					// res = iterator.next().getValue();
					MyFrame.res += iterator.next().getValue();

				}
			} else {
				System.out.println("The time format is wrong");
			}

		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static boolean isValidTime(String arrivalTime) {
		for (int i = 0; i < arrivalTime.length(); i++) {
			if (!Character.isDigit(arrivalTime.charAt(i)) && arrivalTime.charAt(i) != ':') {
				return false;
			}
		}
		String[] time = arrivalTime.trim().split(":");
		int[] timeNum = new int[3];
		for (int i = 0; i < 3; i++) {
			timeNum[i] = Integer.parseInt(time[i]);
		}
		if (timeNum[0] < 0 || timeNum[0] > 23)
			return false;
		if (timeNum[1] < 0 || timeNum[1] > 59)
			return false;
		if (timeNum[2] < 0 || timeNum[2] > 59)
			return false;

		return true;
	}

}
