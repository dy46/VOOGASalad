package auth_environment.backend;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class StateImageSaver {
	private String myLocation;
	/*
	 * Constructor for the StateImageSaver Object
	 * 
	 * @param	location is the file path for where to save the new files
	 */
	public StateImageSaver(String location){
		this.myLocation = location;
	}
	/*
	 * Saves files with new names so that game-player can use the images
	 * 
	 * @param	type is the developer created string that determines the type of unit being created
	 * @param	unit is the developer create string that determines which speicific unit is being created
	 * @param	images is the list of file images to be renamed
	 */
	public void saveFiles(String type, String unit, List<File> images) throws IOException{
		String prefix = type + unit;
		for(int i = 1;i <= images.size();i++){
			String rename = prefix + i;
			InputStream fin = new FileInputStream(images.get(i-1));
			OutputStream fout = new FileOutputStream(this.myLocation + rename);
			while(true){
				int readByte = fin.read();
				if(readByte != -1){
					fout.write(readByte);
				}
				else{
					break;
				}
			}
		}
	}
	/*
	 * Takes in a list of file locations instead of actual files and renames/saves them
	 */
	public void saveFileLocations(String type, String unit, List<String> imageLocations) throws IOException{
		List<File> files = new ArrayList<File>();
		for(String s :imageLocations){
			File f = new File(s);
			files.add(f);
		}
		this.saveFiles(type, unit, files);
	}
	/*
	 * demonstration of how to use the class
	 */
	public static void main(String [] args) throws IOException{
		// note that the string "src/auth_environment/backend/" is very specific 
		// you could also just give the files themselves, and that works as well
		StateImageSaver s = new StateImageSaver("src/auth_environment/backend/");
		String loc1 = "src/auth_environment/backend/ElementalFactory.java";
		String loc2 = "src/auth_environment/backend/FactoryController.java";
		String loc3 = "src/auth_environment/backend/MapDisplayModel.java";
		List<String> list = new ArrayList<String>();
		list.add(loc1);
		list.add(loc2);
		list.add(loc3);
		s.saveFileLocations("type", "unit", list);
		
	}
}
