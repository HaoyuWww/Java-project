
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

public class DataResolver {

	private static Set<String> flagStops = new HashSet<>();
	private static List<String> list = new ArrayList<>();
	static {
		flagStops.add("WB");
		flagStops.add("NB");
		flagStops.add("SB");
		flagStops.add("EB");
		flagStops.add("FLAGSTOP");
	}

	/**
	 * Resolve the data file and fill this data into instances
	 * 
	 * @return return all the stop data with Stop instances
	 */
	public List<String> resolveDataFrom(String filePath) throws FileNotFoundException {
		InputStream is = new FileInputStream(filePath);
		Scanner reader = new Scanner(is);
		// indicate whether the line is the first line
		// because it would ignore the first header line
		boolean isFirstLine = true;
		// resolve each line data
		while (reader.hasNext()) {
			if (isFirstLine) {
				isFirstLine = false;
				// read the first line and ignore this line
				reader.nextLine();
				continue;
			}
			String line = reader.nextLine();
			if (line != null) {
				String partLine[] = line.split(",");
				StringBuilder sb = new StringBuilder();
				sb.append(partLine[2]);
				sb = moveSpecificFlagStopToEnd(sb);
				String stopName = sb.toString();
				StringBuilder lineData = new StringBuilder();
				lineData.append("stop_id= " + partLine[0]);
				lineData.append(", stop_code= " + partLine[1]);
				lineData.append(", stop_name= " + stopName);
				lineData.append(", stop_desc= " + partLine[3]);
				lineData.append(", stop_lat= " + partLine[4]);
				lineData.append(", stop_lon= " + partLine[5]);
				lineData.append(", zone_id= " + partLine[6]);
				lineData.append(", location_type= " + partLine[8]);
				if (partLine.length > 9) {
					lineData.append(", parent_station= " + partLine[9]);
				}
				String stopInfo = lineData.toString();
				list.add(stopInfo);
			}
		}
		reader.close();
		return list;
	}

	/**
	 * Move the specific words in stop name with wb,nb,sb,eb,flagstop to the end of
	 * the stop name
	 * 
	 * @return a new stop name which contains specific words, such as
	 *         wb,nb,sb,eb,etc. and set its at the end of stop name
	 */
	public StringBuilder moveSpecificFlagStopToEnd(StringBuilder sb) {
		/*
		 * check whether the first or first two words is eb,nb,sb,etc switch the two
		 * position of the flag word and the end elements if the elements of first and
		 * second position satisfy the condition above, then recombine the every word
		 * return as a new stop name
		 */

		if (flagStops.contains(sb.substring(0, 8)) && !flagStops.contains(sb.substring(9, 11))) {
			String partName = sb.substring(0, 8);
			sb.delete(0, 9);
			sb.append(" " + partName);
		} else if (flagStops.contains(sb.substring(0, 8)) && flagStops.contains(sb.substring(9, 11))) {
			String partName = sb.substring(0, 11);
			sb.delete(0, 12);
			sb.append(" " + partName);
		} else if (flagStops.contains(sb.substring(0, 2))) {
			String partName = sb.substring(0, 2);
			sb.delete(0, 3);
			sb.append(" " + partName);
		}
		return sb;
	}
}
