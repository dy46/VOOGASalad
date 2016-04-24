package utility;

import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.HashMap;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;
import java.util.ArrayList;
import java.util.Enumeration;

public class TextBot {
	private static final String MY_NUMBER = "+12056600267";
	private static final String ACCOUNT_SID = "AC7fc35c31998ec586534822716579d284";
	private static final String AUTH_TOKEN = "09a2ae71719093f892ee57a320eba40f";
	private ResourceBundle myResources;
	private Account account;
	public TextBot(String resources){
		myResources = ResourceBundle.getBundle(resources);
		TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
		this.account = client.getAccount();
	}

	public void sendText(String message, List<String> names) throws TwilioRestException{
		MessageFactory messageFactory = account.getMessageFactory();
		for(String name : names){
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("To", myResources.getString(name.toLowerCase()))); // Replace with a valid phone number for your account.
			params.add(new BasicNameValuePair("From", MY_NUMBER)); // Replace with a valid phone number for your account.
			params.add(new BasicNameValuePair("Body", message));
			Message sms = messageFactory.create(params);
			System.out.println(sms.getSid());
		}
	}
	public void sendTextToAll(String message) throws TwilioRestException{
		Enumeration<String> names = myResources.getKeys();
		List<String> list = new ArrayList<String>();
		while(names.hasMoreElements()){
			list.add(names.nextElement());
		}
		sendText(message, list);
	}
	public static void main(String[] args) throws TwilioRestException{
		TextBot t = new TextBot("utility/recvlist");
		List<String> names = new ArrayList<String>();
//		names.add("Andy");
		names.add("Paul");
//		names.add("David");
		t.sendText("ok", names);
	}
}
