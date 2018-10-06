
public class InnovationTable {
	public static String createTable() {

		String table = "<table>\r\n" + "  <tr>\r\n" + "    <th><b>Command Help:</b></th>\r\n" + "  </tr>\r\n"
				+ "  <tr>\r\n" + "    <td><b>help/?</b> : to get help</td>\r\n" + "  </tr>\r\n" + "  <tr>\r\n"
				+ "    <td>You can <b>write your claim issue</b> as detailed as possible, and we will direct it to the <b>appropriate department</b></td>\r\n"
				+ "  </tr>\r\n" + "  <tr>\r\n"
				+ "    <td>If the department seems incorrect, please say <b>request call</b> and a claim's agent will contact you at your convinience.</td>\r\n"
				+ "  </tr>\r\n" + "  <tr>\r\n" + "    <td>Address bot as <b>claim</b> in chat-room</td>\r\n"
				+ "  </tr>\r\n" + "  <tr>\r\n" + "    <td>You can also do<b> simple talk </b>with the bot</td>\r\n"
				+ "  </tr>\r\n" + "</table>";

		return table;
	}

	public static String callTime() {
		String table = "<table>\r\n" + "  <tr>\r\n" + "    <th>Available Times</th>\r\n" + "  </tr>\r\n" + "  <tr>\r\n"
				+ "    <td><b>08/07(Tuesday): 3:30pm; 4:30pm</b></td>\r\n" + "  </tr>\r\n" + "  <tr>\r\n"
				+ "    <td><b>08/08(Wednesday): 12:30pm; 1:30pm; 2:30pm; 3:30pm; 4:30pm</b></td>\r\n" + "  </tr>\r\n"
				+ "  <tr>\r\n" + "    <td><b>08/09(Thursday): 12:30pm; 1:30pm; 2:30pm; 3:30pm; 4:30pm</b></td>\r\n"
				+ "  </tr>\r\n" + "  <tr>\r\n"
				+ "    <td>Please enter your response in the following format: <b>MM/DD 00:00am/pm</b></td>\r\n"
				+ "  </tr>\r\n" + "</table>";
		return table;
	}
	public static String confirmation() {
		String table = "<table>\r\n" + 
				"  <tr>\r\n" + 
				"    <th>Client Name</th>\r\n" + 
				"    <th>Confirmation Number</th>\r\n" + 
				"    <th>Department</th>\r\n" + 
				"    <th>Department Contact</th>\r\n" + 
				"    <th>Contact Method</th>\r\n" + 
				"    <th>Contact Time</th>\r\n" + 
				"  </tr>\r\n" + 
				"  <tr>\r\n" + 
				"    <td>Shikhar Mittal</td>\r\n" + 
				"    <td>80468236482</td>\r\n" + 
				"    <td>Auto Insaurance</td>\r\n" + 
				"    <td>+1(878)543-1837</td>\r\n" + 
				"    <td>Phone</td>\r\n" + 
				"    <td>08/08; 1:30pm</td>\r\n" + 
				"  </tr>\r\n" + 
				"  </table>";
		return table;
	}
}
