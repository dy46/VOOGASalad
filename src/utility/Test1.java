package utility;

import java.io.FileNotFoundException;

import com.twilio.sdk.TwilioRestException;

public class Test1 {
    private static final String DEVELOPER_TOKEN = "sea7P0kYz0PcuVJ4pcEoYWQRRNrNjkoA";
    private static final int MAX_DEPTH = 2;


    public static void main(String[] args) throws FileNotFoundException, TwilioRestException {
        CloudStorage c = new CloudStorage(DEVELOPER_TOKEN);
        c.uploadFolder("src/main");
        c.listFolders();
    }

    
}
