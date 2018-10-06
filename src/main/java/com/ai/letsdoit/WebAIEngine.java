package com.ai.letsdoit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ai.api.AIConfiguration;
import ai.api.AIDataService;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;

/**
 * @author Shikhar Mittal
 *
 *
 */
@WebServlet("/AIEngine")
public class WebAIEngine extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest ,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doPost(req, resp);
	}

	public static String chatBot(String input) {

		String apikey = "2bcecd8976814f1886d38ee780769317";

		AIConfiguration configuration = new AIConfiguration(apikey);

		AIDataService dataService = new AIDataService(configuration);

		try {
			AIRequest request = new AIRequest(input);// symphony user input

			AIResponse response = dataService.request(request);

			String json = "";
			if (response.getStatus().getCode() == 200) {
				json = response.getResult().getFulfillment().getSpeech();
				return json;

			} else {

				System.err.println(response.getStatus().getErrorDetails());
				return "{\"speech\":\"Sorry! Some internal error occured.\"}";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return "{\"speech\":\"Sorry! Some internal error occured.\"}";
		}
	}

	public String getMovieDetails(String url)
			throws UnsupportedEncodingException, MalformedURLException, IOException, Exception {
		String json = "";
		json = invokeURL(url);
		return json;
	}

	public static String invokeURL(String url)
			throws UnsupportedEncodingException, MalformedURLException, IOException, Exception {
		disableSslVerification();
		String res;

		URL obj = new URL(url);
		// Proxy proxy = new Proxy(Proxy.Type.HTTP, new
		// InetSocketAddress("10.152.80.42", 80));

		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setConnectTimeout(6 * 1000);
		con.setReadTimeout(10 * 1000);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}

		in.close();
		// res = Utils.escapeHTML(response.toString());
		res = response.toString();

		return res;

	}

	public static void disableSslVerification() {
		try {
			// Create a trust manager that does not validate certificate chains
			System.out.println("SSL Check Disabled");
			TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
					// TODO Auto-generated method stub

				}

				public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
					// TODO Auto-generated method stub

				}

				// @Override
				// public void checkClientTrusted(java.security.cert.X509Certificate[] arg0,
				// String arg1)
				// throws CertificateException {
				// // TODO Auto-generated method stub
				//
				// }
				//
				// @Override
				// public void checkServerTrusted(java.security.cert.X509Certificate[] chain,
				// String authType)
				// throws CertificateException {
				// // TODO Auto-generated method stub
				//
				// }
			} };

			// Install the all-trusting trust manager
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

			// Create all-trusting host name verifier
			HostnameVerifier allHostsValid = new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			};

			// Install the all-trusting host verifier
			HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		} catch (NoSuchAlgorithmException e) {
			// e.printStackTrace();
		} catch (KeyManagementException e) {
			// e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String json = "";
		try {
			if (req.getParameter("page").equalsIgnoreCase("moviethisweek")) {
				json = getMovieDetails(
						"https://api.cinemalytics.com/v1/movie/releasedthisweek?auth_token=1D4B24082E3FD05DA930F815FEFE1A77");
			} else if (req.getParameter("page").equalsIgnoreCase("upcomingmovie")) {
				json = getMovieDetails(
						"https://api.cinemalytics.com/v1/movie/upcoming?auth_token=1D4B24082E3FD05DA930F815FEFE1A77");
			} else if (req.getParameter("page").equalsIgnoreCase("movienextweek")) {
				json = getMovieDetails(
						"https://api.cinemalytics.com/v1/movie/nextchange?auth_token=1D4B24082E3FD05DA930F815FEFE1A77");
			} else if (req.getParameter("page").equalsIgnoreCase("chatbot")) {
				// json = chatBot(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.getWriter().write(json);
	}
}
