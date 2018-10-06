import clients.SymBotClient;
import clients.symphony.api.StreamsClient;
import exceptions.SymClientException;
import listeners.RoomListener;
import model.InboundMessage;
import model.OutboundMessage;
import model.RoomProperties;
import model.Stream;
import model.events.*;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RoomListenerTestImpl implements RoomListener {

	private SymBotClient botClient;

	public RoomListenerTestImpl(SymBotClient botClient) {
		this.botClient = botClient;
	}

	private final Logger logger = LoggerFactory.getLogger(RoomListenerTestImpl.class);

	public void onRoomMessage(InboundMessage inboundMessage) {
		OutboundMessage messageOut = new OutboundMessage();
		String input = inboundMessage.getMessage();
		String output = "";
		String alexaVoice = "";
		StreamsClient sclient = new StreamsClient(botClient);

		try {
			boolean multiRoomAndFriendlyName = !(sclient.getRoomMembers(inboundMessage.getStream().getStreamId())
					.size() > 2 && !inboundMessage.getMessageText().trim().startsWith("trader"));
			if (input.contains("trader") && multiRoomAndFriendlyName) {
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
				this.botClient.getMessagesClient().sendMessage(inboundMessage.getStream().getStreamId(), messageOut);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public void onRoomCreated(RoomCreated roomCreated) {

	}

	public void onRoomDeactivated(RoomDeactivated roomDeactivated) {

	}

	public void onRoomMemberDemotedFromOwner(RoomMemberDemotedFromOwner roomMemberDemotedFromOwner) {

	}

	public void onRoomMemberPromotedToOwner(RoomMemberPromotedToOwner roomMemberPromotedToOwner) {

	}

	public void onRoomReactivated(Stream stream) {

	}

	public void onRoomUpdated(RoomUpdated roomUpdated) {

	}

	public void onUserJoinedRoom(UserJoinedRoom userJoinedRoom) {
		OutboundMessage messageOut = new OutboundMessage();
		messageOut.setMessage("Welcome " + userJoinedRoom.getAffectedUser().getFirstName() + "!");
		try {
			this.botClient.getMessagesClient().sendMessage(userJoinedRoom.getStream().getStreamId(), messageOut);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onUserLeftRoom(UserLeftRoom userLeftRoom) {

	}
}
