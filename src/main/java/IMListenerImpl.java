import java.util.ArrayList;

import clients.SymBotClient;
import clients.symphony.api.StreamsClient;
import exceptions.SymClientException;
import listeners.IMListener;
import model.InboundMessage;
import model.OutboundMessage;
import model.Stream;

public class IMListenerImpl implements IMListener {

	private SymBotClient botClient;

	public IMListenerImpl(SymBotClient botClient) {
		this.botClient = botClient;
	}

	public void onIMMessage(InboundMessage inboundMessage) {

		String input = inboundMessage.getMessage();
		OutboundMessage messageOut = new OutboundMessage();
		String output = "";
		String alexaVoice = "";

		StreamsClient sclient = new StreamsClient(botClient);

		try {
			boolean multiRoomAndFriendlyName = !(sclient.getRoomMembers(inboundMessage.getStream().getStreamId())
					.size() > 2 && !inboundMessage.getMessageText().trim().startsWith("trader"));
			ArrayList setResponse = new ArrayList();
			setResponse = textResponse.incomingMessage(inboundMessage);
			if (setResponse.size() > 1) {
				output = setResponse.get(0) + "\n" + setResponse.get(1);

			} else {
				output = (String) setResponse.get(0);

			}
			alexaVoice = AlexaMessage.message((String) setResponse.get(0));
			messageOut.setMessage(output);
			messageOut.setData(alexaVoice);
			if (multiRoomAndFriendlyName) {

				this.botClient.getMessagesClient().sendMessage(inboundMessage.getStream().getStreamId(), messageOut);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void onIMCreated(Stream stream) {

	}
}