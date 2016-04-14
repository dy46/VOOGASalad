package game_data;

import com.thoughtworks.xstream.XStream;

import auth_environment.delegatesAndFactories.FileChooserDelegate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AuthSerializer {
	
	private FileChooserDelegate chooser = new FileChooserDelegate(); 
	
	// TODO: force write as XML
	
	public void SerializeData(Object o) {
		
		File f = this.chooser.save("Save");
//		File f = pickFile(true);
		XStream xstream = new XStream();
		String xml = xstream.toXML(o);

		try {
			FileWriter writer = new FileWriter(f);
			writer.write(xml);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			System.out.println("Error saving to file " + f.getAbsolutePath());
		}
	}
	
	public Object Deserialize() {
//		File f = pickFile(false);
		File f = this.chooser.chooseFile("Choose");
		XStream xstream = new XStream();

		return xstream.fromXML(f);
	}

	private static File pickFile(boolean amSaving) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter("xml files (*.xml)", "xml");
		fileChooser.addChoosableFileFilter(xmlFilter);
		fileChooser.setFileFilter(xmlFilter);

		int result = 0;
		if (amSaving) {
			System.out.println("trying to open?");
			result = fileChooser.showSaveDialog(null);
			System.out.println(result); 
		}
		else
			result = fileChooser.showOpenDialog(null);
		if (result != JFileChooser.APPROVE_OPTION)
			return null;

		return fileChooser.getSelectedFile();
	}
}