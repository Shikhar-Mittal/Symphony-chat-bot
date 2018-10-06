import java.io.IOException;

import org.json.JSONObject;

public class AlexaMessage {

	public static String message(String text) throws IOException, Exception {

		JSONObject jsonObj = new JSONObject();
		jsonObj.put("voiceHeader", text);
		String response = jsonObj.toString();

		return response;
	}
}
