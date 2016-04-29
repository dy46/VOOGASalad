package utility;

import java.io.FileNotFoundException;

import com.twilio.sdk.TwilioRestException;

public class Test1 {
    
    private static final String MESSAGE = "new files have been uploaded!";
	private static final String DEVELOPER_TOKEN = "lr976BMHY8Nm9NGys7J5KYV6Qy6PRNwJ";


    public static void main(String[] args) throws FileNotFoundException, TwilioRestException {
        CloudStorage c = new CloudStorage(DEVELOPER_TOKEN);
        c.uploadFolder("src/main");
        c.uploadFolder("src/utility");
        c.goToFolder("main");
        c.uploadFolder("src/utility");
        c.uploadFile("src/utility/Test1.java", "Test4.java");
        System.out.println(c.getCurrentFiles());
        c.listFolders();
        c.notify(MESSAGE);
    }

    
}
