import java.awt.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.common.collect.ArrayListMultimap;

@SuppressWarnings("unused")
public class CSVReader {

	public static JSONArray readTable() {

		String csvFile = "\\\\ns-cnstvfs01\\everyone\\Symphony\\Share\\Test1.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		JSONArray jsonArray = new JSONArray();

		try {
			br = new BufferedReader(new FileReader(csvFile));
			int i = 0;
			String[] header = null;

			while ((line = br.readLine()) != null) {
				JSONObject obj = new JSONObject();
				if (i == 0) {
					header = line.split(cvsSplitBy);
					i++;
				} else {
					String[] list = line.split(cvsSplitBy);
					for (int j = 0; j < list.length; j++) {
						obj.put(header[j], list[j]);
					}
					jsonArray.put(obj);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return jsonArray;
	}

	public static String printRecords() throws JSONException {
		JSONArray array = readTable();
		String getReport = "";
		String header = "";
		String body = "";
		Boolean flag = true;
		for (int i = 0; i < array.length(); i++) {
			JSONObject objects = array.getJSONObject(i);
			String innerBody = "";

			String[] elementNames = JSONObject.getNames(objects);
			for (String elementName : elementNames) {
				if (flag)
					header = "<th>" + elementName + "</th>" + header;
				String value = objects.getString(elementName);
				innerBody = "<td>" + value + "</td>" + innerBody;
			}
			flag = false;
			body = body + "<tr>" + innerBody + "</tr>";
		}
		getReport = "<table><tr>" + header + "</tr>" + body + "</table>";

		return getReport;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static ArrayList ValueCheckSecurity(String parameter, String security, String quantity) {
		String valueToCheck = parameter;
		Double maxValue = Double.NEGATIVE_INFINITY;
		Double minValue = Double.POSITIVE_INFINITY;
		int rowIndex = 0;
		ArrayList result = new ArrayList();
		JSONArray array = readTable();
		// String result = "";
		String getReport = "";
		String header = "";
		String body = "";
		Boolean flag = true;

		try {

			for (int k = 0; k < array.length(); k++) {
				JSONObject obj = array.getJSONObject(k);
				if (array.getJSONObject(k).getString("Security").contains(security)) {
					Double val = Double.parseDouble(obj.getString(valueToCheck));
					if (quantity.contains("most")) {
						if (val > maxValue) {
							maxValue = val;
							rowIndex = k;
						}
					} else {
						quantity = "least";
						if (val < minValue) {
							minValue = val;
							rowIndex = k;
						}
					}
					String[] elementNames = JSONObject.getNames(obj);
					for (String elementName : elementNames) {
						if (flag)
							header = "<th>" + elementName + "</th>" + header;
					}
					flag = false;
				}

			}
			
			//TODO: Change format
			body = "<tr><td>" + array.getJSONObject(rowIndex).getString("Name") + "</td>\r\n<td>"
					+ array.getJSONObject(rowIndex).getString("Units") + "</td>\r\n<td>"
					+ array.getJSONObject(rowIndex).getString("Notional") + "</td>\r\n<td>"
					+ array.getJSONObject(rowIndex).getString("Security") + "</td>\r\n<td>"
					+ array.getJSONObject(rowIndex).getString("Price Purchased") + "</td>\r\n<td>"
					+ array.getJSONObject(rowIndex).getString("Market Price") + "</td></tr>";
			getReport = "<table><tr>" + header + "</tr>" + body + "</table>";
			
			//TODO: Change format
			String speech = "Person with the " + quantity + " " + security + " " + parameter + ": "
					+ array.getJSONObject(rowIndex).getString("Name");
			result.add(speech);
			result.add(getReport);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return result;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static ArrayList securityResut(String security, String action) throws JSONException {
		ArrayList result = new ArrayList();
		JSONArray array = readTable();
		Boolean flag = true;
		String getReport = "";
		String header = "";
		String body = "";
		String speech = "";
		String names = "";

		for (int k = 0; k < array.length(); k++) {
			JSONObject obj = array.getJSONObject(k);
			String innerBody = "";

			if (array.getJSONObject(k).getString("Security").contains(security)) {
				String[] elementNames = JSONObject.getNames(obj);
				for (String elementName : elementNames) {
					if (flag)
						header = "<th>" + elementName + "</th>" + header;
					String value = obj.getString(elementName);
					innerBody = "<td>" + value + "</td>" + innerBody;
				}
				flag = false;
				body = body + "<tr>" + innerBody + "</tr>";
				names = names + array.getJSONObject(k).getString("Name") + ", ";
			}
		}
		if (names.charAt(names.length() - 2) == ',')
			names = names.substring(0, names.length() - 2);
		if (names.contains(",")) {
			String sub1 = names.substring(0, names.lastIndexOf(","));
			String sub2 = names.substring(names.lastIndexOf(",") + 1, names.length());
			speech = "The following people bought " + security + ": " + sub1 + " and " + sub2;
		} else
			speech = "The following person bought " + security + ": " + names;

		getReport = "<table><tr>" + header + "</tr>" + body + "</table>";
		result.add(speech);
		result.add(getReport);
		return result;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static ArrayList TotalValueCheck(String parameter, String quantity)
			throws NumberFormatException, JSONException {
		String valueToCheck = parameter;
		int maxValue = (int) Double.NEGATIVE_INFINITY;
		int minValue = (int) Double.POSITIVE_INFINITY;
		String getReport = "";
		String header = "";
		String body = "";
		Boolean flag = true;
		String keyName = null;
		JSONArray array = readTable();
		ArrayList result = new ArrayList();
		HashMap<String, Integer> hmap = new HashMap<String, Integer>();
		try {
			for (int k = 0; k < array.length(); k++) {
				int notional = Integer.parseInt(array.getJSONObject(k).getString("Notional"));
				JSONObject obj = array.getJSONObject(k);
				if (!hmap.containsKey(array.getJSONObject(k).getString("Name")))
					hmap.put(array.getJSONObject(k).getString("Name"), notional);

				else {
					int keyVal = hmap.get(array.getJSONObject(k).getString("Name"));
					notional = notional + keyVal;
					int test = notional;

					hmap.put(array.getJSONObject(k).getString("Name"), notional);
				}
				String[] elementNames = JSONObject.getNames(obj);
				String innerBody = "";
				for (String elementName : elementNames) {
					if (flag)
						header = "<th>" + elementName + "</th>" + header;
				}
				flag = false;
			}
			Set set = hmap.entrySet();
			Iterator iterator = set.iterator();
			while (iterator.hasNext()) {
				Map.Entry mentry = (Map.Entry) iterator.next();
				if (quantity.contains("most")) {
					if ((int) mentry.getValue() > maxValue) {
						maxValue = (int) mentry.getValue();
						keyName = mentry.getKey().toString();
					}
				} else {
					quantity = "least";
					if ((int) mentry.getValue() < minValue) {
						minValue = (int) mentry.getValue();
						keyName = (String) mentry.getKey();
					}
				}
			}
			
			//TODO: Change format
			for (int i = 0; i < array.length(); i++) {
				if (array.getJSONObject(i).getString("Name").contains(keyName)) {
					body = "<tr><td>" + array.getJSONObject(i).getString("Name") + "</td>\r\n<td>"
							+ array.getJSONObject(i).getString("Units") + "</td>\r\n<td>"
							+ array.getJSONObject(i).getString("Notional") + "</td>\r\n<td>"
							+ array.getJSONObject(i).getString("Security") + "</td>\r\n<td>"
							+ array.getJSONObject(i).getString("Price Purchased") + "</td>\r\n<td>"
							+ array.getJSONObject(i).getString("Market Price") + "</td></tr>" + body;
				} else
					getReport = "<table><tr>" + header + "</tr>" + body + "</table>";
			}
			
			//TODO: Change format
			String speech = "Person with the " + quantity + " " + parameter + ": " + keyName;
			result.add(speech);
			result.add(getReport);

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return result;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static ArrayList TotalLossGain(String gainLoss, String quantity)
			throws NumberFormatException, JSONException {

		String valueToCheck = gainLoss;
		int maxValue = (int) Double.NEGATIVE_INFINITY;
		int minValue = (int) Double.POSITIVE_INFINITY;
		String getReport = "";
		String header = "";
		String keyName = null;
		String speech = null;

		JSONArray array = readTable();
		ArrayList result = new ArrayList();
		HashMap<String, Integer> hmapNotional = new HashMap<String, Integer>();
		HashMap<String, Integer> hmapMarket = new HashMap<String, Integer>();
		HashMap<String, Integer> hmap = new HashMap<String, Integer>();

		try {
			for (int k = 0; k < array.length(); k++) {
				int notional = (Integer.parseInt(array.getJSONObject(k).getString("Price Purchased"))
						* Integer.parseInt(array.getJSONObject(k).getString("Units")));
				int marketVal = (Integer.parseInt(array.getJSONObject(k).getString("Market Price"))
						* Integer.parseInt(array.getJSONObject(k).getString("Units")));

				if (!hmapNotional.containsKey(array.getJSONObject(k).getString("Name"))
						&& !hmapMarket.containsKey(array.getJSONObject(k).getString("Name"))) {
					hmapNotional.put(array.getJSONObject(k).getString("Name"), notional);
					hmapMarket.put(array.getJSONObject(k).getString("Name"), marketVal);
				}

				else {
					int addNotional = hmapNotional.get(array.getJSONObject(k).getString("Name"));
					notional = notional + addNotional;
					int addMarkVal = hmapMarket.get(array.getJSONObject(k).getString("Name"));
					marketVal = marketVal + addMarkVal;
					hmapNotional.put(array.getJSONObject(k).getString("Name"), notional);
					hmapMarket.put(array.getJSONObject(k).getString("Name"), marketVal);
				}

				int margin = hmapMarket.get(array.getJSONObject(k).getString("Name"))
						- hmapNotional.get(array.getJSONObject(k).getString("Name"));
				hmap.put(array.getJSONObject(k).getString("Name"), margin);

			}

			Set set = hmap.entrySet();
			Iterator iterator = set.iterator();

			while (iterator.hasNext()) {
				Map.Entry mentry = (Map.Entry) iterator.next();
				if ((quantity.contains("most") && valueToCheck.contains("made"))
						|| (quantity.contains("least") && valueToCheck.contains("lost"))) {
					if ((int) mentry.getValue() > maxValue) {
						maxValue = (int) mentry.getValue();
						keyName = mentry.getKey().toString();
						speech = keyName + " made the most profit!";
					}
				} else {
					if ((int) mentry.getValue() < minValue) {
						minValue = (int) mentry.getValue();
						keyName = (String) mentry.getKey();
					}
					speech = keyName + " lost the most!";
				}
			}
			result.add(speech);
			getReport = "<table>\r\n" + "  <tr>\r\n" +
						"    <th>Name</th>\r\n" + 
						"    <th>Market Price</th>\r\n" +
						"    <th>Price Purchased</th>\r\n" + 
						"    <th>Total Profit</th>\r\n" + "  </tr>\r\n" +
						"  <tr>\r\n" + "    <td>" + keyName + "</td>\r\n" + 
						"    <td>" + hmapMarket.get(keyName) + 
						"</td>\r\n" + "    <td>" + hmapNotional.get(keyName) + 
						"</td>\r\n" + "    <td>" + hmap.get(keyName) + "</td>\r\n" + 
						"  </tr>\r\n" + "  </table>\r\n";
			result.add(getReport);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return result;
	}
}