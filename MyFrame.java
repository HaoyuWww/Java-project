import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MyFrame extends JFrame {

	public static String res;
	public static String stop;

	JComboBox<String> options = new JComboBox<>();
	JPanel cards = new JPanel();
	JComboBox<String> timeList = new JComboBox<>();
	JTextArea output = new JTextArea();
	JTextArea stopOutput = new JTextArea();
	JTextArea pathOutput = new JTextArea();
	
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
		show.setBounds(new Rectangle(350, 50, 100, 25));

		JLabel showDetails = new JLabel("to show bus details");
		P1.add(showDetails);
		showDetails.setBounds(new Rectangle(450, 50, 150, 25));

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

		JLabel trip_id = new JLabel();
		trip_id.setText("trip_id");
		P1.add(trip_id);
		trip_id.setBounds(65, 180, 80, 20);
		// arrival_time "
		JLabel arrival_time = new JLabel();
		arrival_time.setText("arrival_time");
		P1.add(arrival_time);
		arrival_time.setBounds(160, 160, 80, 20);

		JLabel departure_time = new JLabel();
		departure_time.setText("departure_time");
		P1.add(departure_time);
		departure_time.setBounds(250, 180, 100, 20);

		JLabel stop_id = new JLabel();
		stop_id.setText("stop_id");
		P1.add(stop_id);
		stop_id.setBounds(370, 160, 80, 20);

		JLabel stop_sequence = new JLabel();
		stop_sequence.setText("stop_sequence");
		P1.add(stop_sequence);
		stop_sequence.setBounds(430, 180, 100, 20);

		JLabel stop_headsign = new JLabel();
		stop_headsign.setText("stop_headsign");
		P1.add(stop_headsign);
		stop_headsign.setBounds(510, 160, 100, 20);

		JLabel pickup_type = new JLabel();
		pickup_type.setText("pickup_type");
		P1.add(pickup_type);
		pickup_type.setBounds(590, 180, 100, 20);

		JLabel drop_off_type = new JLabel();
		drop_off_type.setText("drop_off_type");
		P1.add(drop_off_type);
		drop_off_type.setBounds(670, 160, 100, 20);

		JLabel shape_dist_traveled = new JLabel();
		shape_dist_traveled.setText("  shape_dist_traveled");
		P1.add(shape_dist_traveled);
		shape_dist_traveled.setBounds(740, 180, 180, 20);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(20, 200, 900, 400);
		P1.add(scrollPane_1);

		// scrollPane_1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		// output.append(MyFrame.res);
		// P1.add(output);
		output.setBounds(20, 200, 900, 400);
		scrollPane_1.setViewportView(output);

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
				/*
				 * TST t = TST.buildTST(); String stopName = List<String> stops =
				 * t.find(stopName); for (String allData : stops) { System.out.println(allData);
				 * //MyFrame.stop = allData; } System.out.println("Total stop(s)= " +
				 * stops.size()); //System.out.println(stopName );
				 */

				TST t = TST.buildTST();
				if (!TST.source) {
					String stopName = typeIn.getText();
					List<String> stops = t.find(stopName);
					if (stops.size() == 0) {
						stopOutput.append(
								"Wrong input! Please enter bus stop full name or first few characters in the name!");
						stopOutput.append("\n");
						// System.out.println("Wrong input!");
					} else {
						for (String allData : stops) {
							// System.out.println(allData);
							stopOutput.append(allData);
							stopOutput.append("\n");
						}
						// System.out.println("Total stop(s)= " + stops.size());
						stopOutput.append("Total stop(s)= " + stops.size());
						stopOutput.append("\n");
					}
				}
			}

		});
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(20, 200, 900, 400);
		P2.add(scrollPane_2);
		stopOutput.setBounds(20, 200, 900, 400);
		scrollPane_2.setViewportView(stopOutput);

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
				File costfile = new File("transfers.txt");
				File EdgeFile = new File("stop_times.txt");

				List<List<String>> EdgeCost = ShortestStop.getCost(costfile);
				List<List<String>> Edge = ShortestStop.getEdge(EdgeFile);
				//int costSize = EdgeCost.size();
				//int edgeSize = Edge.size();
				List<String> ans = new ArrayList();
				String startStopId = pathA.getText();;// start ID
				String endStopId = pathB.getText();// end ID
				ans = ShortestStop.CostShortestPath(Edge, EdgeCost, startStopId, endStopId);// OUTPUT ANS；
				// Stop and COST is all in ans String.
				// print
				pathOutput.append("From StartStopId " + startStopId);
				pathOutput.append(" To EndStopId " + endStopId + " is");
				pathOutput.append("\n");
				
				//System.out.printf("From StartStopId %s " , startStopId);
				//System.out.printf("To EndStopId %s is \n", endStopId);
				
				// hash remove element
				List<String> ansNew = new ArrayList<String>(new HashSet(ans));
				for (int i = 0; i < ansNew.size(); i++) {
					//System.out.println(ansNew.get(i));
					pathOutput.append(ansNew.get(i));
					pathOutput.append("\n");
				
				//String stopA = pathA.getText();
			//	String stopB = pathB.getText();

				//System.out.print(stopA);
				//System.out.print(stopB);
				}
			}
			}
		);
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(20, 200, 900, 400);
		P3.add(scrollPane_3);
		pathOutput.setBounds(20, 200, 900, 400);
		scrollPane_3.setViewportView(pathOutput);

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

	private void tripInfoSort(String oneArrivalTime) {
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
					this.output.append("Zero item matches this time");
					this.output.append("\n");
					return;
				}
				TreeMap<String, String> treeMap = infoMap.get(oneArrivalTime);
				Iterator<Map.Entry<String, String>> iterator = treeMap.entrySet().iterator();
				// this.output.append("trip_id arrival_time departure_time stop_id stop_sequence
				// pickup_type drop_off_type shape_dist_traveled\n");
				while (iterator.hasNext()) {

					// System.out.println(iterator.next().getValue());
					// res = iterator.next().getValue();
					// MyFrame.res += iterator.next().getValue();
					// System.out.println(iterator.next().getValue());
//					this.output.append(iterator.next().getValue());
//					this.output.append("\n");
					String[] values = iterator.next().getValue().split(",");
					for (int i = 0; i < values.length; i++) {
						this.output.append(String.format("%18s", values[i]));
					}
					this.output.append("\n");
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
