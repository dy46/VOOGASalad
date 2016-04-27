package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxFolder;
import com.box.sdk.BoxItem;
import com.box.sdk.BoxUser;
import com.twilio.sdk.TwilioRestException;


public class CloudStorage {

    private static final String RESOURCE_PATH = "utility/recvlist";
    private String devToken;
    private BoxFolder rootFolder;
    private BoxFolder currentFolder;
    private TextBot tb;

    public CloudStorage (String dt) throws FileNotFoundException {
        this.devToken = dt;
        BoxAPIConnection api = new BoxAPIConnection(devToken);
        BoxUser.Info userInfo = BoxUser.getCurrentUser(api).getInfo();
        System.out.format("Welcome, %s <%s>!\n\n", userInfo.getName(), userInfo.getLogin());
        this.rootFolder = BoxFolder.getRootFolder(api);
        this.currentFolder = this.rootFolder;
        tb = new TextBot(RESOURCE_PATH);
    }

    public void listFolders () {
        listFolder(this.currentFolder, 0);
    }

    private static void listFolder (BoxFolder folder, int depth) {
        for (BoxItem.Info itemInfo : folder) {
            String indent = "";
            for (int i = 0; i < depth; i++) {
                indent += "    ";
            }
            System.out.println(indent + itemInfo.getName());
            if (itemInfo instanceof BoxFolder.Info) {
                BoxFolder childFolder = (BoxFolder) itemInfo.getResource();
                listFolder(childFolder, depth + 1);
            }
        }
    }

    public void createNewFolder (String name) {
        this.currentFolder.createFolder(name);
    }

    public void uploadFileToFolder (String filePath,
                                    String folderName,
                                    String name) throws FileNotFoundException {
        BoxFolder f = this.getFolder(folderName);
        if (f == null) {
            throw new RuntimeException("This folder does not exist on your box");
        }
        InputStream is = new FileInputStream(new File(filePath));
        f.uploadFile(is, name);
    }

    public void uploadFile (String filePath, String name) throws FileNotFoundException {
        File f = new File(filePath);
        InputStream is = new FileInputStream(f);
        currentFolder.uploadFile(is, name);
    }

    private BoxFolder getFolder (String name) {
        return this.getFolder(rootFolder, name);
    }

    private BoxFolder getFolder (BoxFolder current, String name) {
        if (current.getInfo().getName().equals(name)) {
            return current;
        }
        else {
            for (BoxItem.Info info : current) {
                if (info instanceof BoxFolder.Info) {
                    BoxFolder res = getFolder((BoxFolder) info.getResource(), name);
                    if (res != null) {
                        return res;
                    }
                }
            }
            return null;
        }
    }

    public void goToFolder (String name) {
        BoxFolder f = this.getFolder(name);
        if (f == null) {
            throw new RuntimeException("This folder does not exist in your box account");
        }
        this.currentFolder = f;
    }

    public void uploadFolder (String folder) throws FileNotFoundException, TwilioRestException {
        File f = new File(folder);
        uploadFolder(this.currentFolder, f);
        tb.sendTextToAll("A new folder has been uploaded to box!!!");
    }

    private void uploadFolder (BoxFolder current, File folder) throws FileNotFoundException {
        if (folder.isDirectory()) {
            current.createFolder(folder.getName());
            for (File child : folder.listFiles()) {
                uploadFolder(this.getFolder(folder.getName()), child);
            }
        }
        else {
            current.uploadFile(new FileInputStream(folder), folder.getName());
        }
    }

    public void deleteFile (String name) {
        BoxFolder current = rootFolder;
        // TODO: finish deleteFile
    }

    public void deleteFolder (String name) {
        BoxFolder f = this.getFolder(name);
        if (f == null) {
            throw new RuntimeException("This folder is not located in your box account");
        }
        f.delete(true);
    }

}
