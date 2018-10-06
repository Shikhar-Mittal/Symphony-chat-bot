import authentication.AuthEndpointConstants;
import authentication.SymBotAuth;
import clients.SymBotClient;
import configuration.SymConfig;
import configuration.SymConfigLoader;
import listeners.IMListener;
import listeners.RoomListener;
import model.*;
import services.DatafeedEventsService;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NoContentException;

import org.json.JSONException;

import com.ai.letsdoit.WebAIEngine;

import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.List;

public class BotExample {

	public static void main(String[] args) throws NumberFormatException, JSONException {

		// System.out.println(InnovationTable.confirmation());
				 
		BotExample app = new BotExample();
	}

	public BotExample() {
		
		URL url = getClass().getResource("config.json");
		SymConfigLoader configLoader = new SymConfigLoader();
		SymConfig config = configLoader.loadFromFile(url.getPath());
		SymBotAuth botAuth = new SymBotAuth(config);
		
		// ----------------------------------
		WebAIEngine.disableSslVerification();
		// ----------------------------------
		
		System.out.println(config.getSessionAuthHost() + ":" + config.getSessionAuthPort());
		System.out.println(config.getBotCertPath() + ":" + config.getBotCertName());
		
		botAuth.authenticate();
		SymBotClient botClient = SymBotClient.initBot(config, botAuth);
		DatafeedEventsService datafeedEventsService = botClient.getDatafeedEventsService();
		RoomListener roomListenerTest = new RoomListenerTestImpl(botClient);
		datafeedEventsService.addRoomListener(roomListenerTest);
		IMListener imListener = new IMListenerImpl(botClient);
		datafeedEventsService.addIMListener(imListener);
	//	createRoom(botClient);

	}
	private void createRoom(SymBotClient botClient){

        try {

            UserInfo userInfo = botClient.getUsersClient().getUserFromEmail("shikhar.mittal@metlife.com", true);
            //get user IM and send message
            String IMStreamId = botClient.getStreamsClient().getUserIMStreamId(userInfo.getId());
            OutboundMessage message = new OutboundMessage();
            message.setMessage("Hello. Welcome to the MetLife Claims Department Bot. You can use one of the following commands:" + "\n" + InnovationTable.createTable());
            botClient.getMessagesClient().sendMessage(IMStreamId, message);
            
            Room room = new Room();
                    room.setName("Testing Room Functionality");
                    room.setDescription("This a room for testing");
                    room.setDiscoverable(true);
                    room.setPublic(false);
                    room.setViewHistory(false);
                    room.setMembersCanInvite(false);
                    RoomInfo roomInfo = null;
                    roomInfo = botClient.getStreamsClient().createRoom(room);
                    botClient.getStreamsClient().addMemberToRoom(roomInfo.getRoomSystemInfo().getId(), userInfo.getId());

            Room newRoomInfo = new Room();
            newRoomInfo.setName("test generator");
            botClient.getStreamsClient().updateRoom(roomInfo.getRoomSystemInfo().getId(),newRoomInfo);

            List<RoomMember> members =  botClient.getStreamsClient().getRoomMembers(roomInfo.getRoomSystemInfo().getId());

            botClient.getStreamsClient().promoteUserToOwner(roomInfo.getRoomSystemInfo().getId(), userInfo.getId());
            

            botClient.getStreamsClient().deactivateRoom(roomInfo.getRoomSystemInfo().getId());


        } catch (NoContentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}