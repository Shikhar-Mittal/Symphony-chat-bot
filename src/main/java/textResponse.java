import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;

import ai.api.AIConfiguration;
import ai.api.AIDataService;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;
import ai.api.model.Parameters;
import model.InboundMessage;

@SuppressWarnings("unused")
public class textResponse {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static ArrayList incomingMessage(InboundMessage inboundMessage) throws IOException {

		String output = "";
		String apikey = "2bcecd8976814f1886d38ee780769317"; //GirlsWhoCode: 2bcecd8976814f1886d38ee780769317  //InnovationChallenge: 6f041e5d7c2e4e11b69de041d2b86808
		AIConfiguration configuration = new AIConfiguration(apikey);
		AIDataService dataService = new AIDataService(configuration);
		ArrayList summary = new ArrayList();

		try {
			AIRequest request = new AIRequest(inboundMessage.getMessageText().trim());
			AIResponse response = dataService.request(request);
			Parameters check = response.getResult().getParameters();

			String jsonAction = check.getAction();
			String jsonQuant = check.getQuantity();
			String jsonComp = check.getCompany();
			String[] jsonParam = check.getParameter();
			String jsonDef = check.getFallBack();
			String[] jsonData = check.getDatabase();
			String jsonMark = check.getMarketPrice();
			String input = inboundMessage.getMessageText().trim();

			// inboundMessage.getMessageText() = inboundMessage.getMessageText().trim();
			
			if (input.startsWith("?") || input.startsWith("help") || input.startsWith("trader help")
					|| input.startsWith("trader ?") || input.startsWith("trader?")) {
				output = HTMLTable.createTable();
				summary.add("");
				summary.add(output);
			} else if (jsonAction != null) {
				summary = getIntent.getCSV(jsonQuant, jsonParam, jsonComp, jsonAction, jsonMark);
			} else if (jsonData != null) {
				output = CSVReader.printRecords();
				summary.add("Here are the requested trade details:");
				summary.add(output);
			} else {
				if (response.getResult().getFulfillment().getSpeech() != null
						&& StringUtils.isNotBlank(response.getResult().getFulfillment().getSpeech())) {
					output = response.getResult().getFulfillment().getSpeech();
					summary.add(output);
					
// -----------------------Innovation Challenge changes
					
//					if(input.startsWith("custom")) 
//						summary.add(InnovationTable.callTime());
//					if(input.startsWith("08/09"))
//							summary.add(InnovationTable.confirmation());
					
// -----------------------					
				} else {
					summary.add("Sorry! Request couldn't be processed;" + "\n"
							+ "Please try one of the following commands:");
					summary.add(HTMLTable.createTable());
				}
			}
			return summary;
		} catch (Exception ex) {
			ex.printStackTrace();
			summary.add("Sorry! Some internal error occured; Please try again.");
			return summary;
		}
	}
}