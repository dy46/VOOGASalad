package utility;

import java.io.FileNotFoundException;

public class Test1 {
    private static final String DEVELOPER_TOKEN = "t6dPXbJ7aD3D6lthgqv0Zt0kGgo6msuI";
    private static final int MAX_DEPTH = 2;


    public static void main(String[] args) throws FileNotFoundException {
        CloudStorage c = new CloudStorage(DEVELOPER_TOKEN);
        c.uploadFolder("src/main");
        c.listFolders();
    }

    
}
