package auth_environment.delegatesAndFactories;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

//import org.eclipse.core.resources.IProject;
//import org.eclipse.core.resources.IResource;
//import org.eclipse.core.resources.ResourcesPlugin;
//import org.eclipse.core.runtime.CoreException;
//import org.eclipse.core.runtime.NullProgressMonitor;


public class StateImageSaver {
	private String myLocation;
	private String suffix;
	/*
	 * Constructor for the StateImageSaver Object
	 * 
	 * @param	location is the file path for where to save the new files
	 */
	public StateImageSaver(String location, String suffix){
		this.myLocation = location;
		this.suffix = suffix;
	}
	/*
	 * Saves files with new names so that game-player can use the images
	 * 
	 * @param	type is the developer created string that determines the type of unit being created
	 * @param	unit is the developer create string that determines which speicific unit is being created
	 * @param	images is the list of file images to be renamed
	 */
	public void saveFiles(String prefix, String unit, List<File> images){
		if(images.size() > 0){
			saveFile(prefix, images.get(0));
		}
		for(int i = 1;i <= images.size();i++){
			saveFile( prefix+i, images.get(i-1));
		}
	}
	
	private void saveFile(String rename, File file){
		try{
			InputStream fin = new FileInputStream(file);
			OutputStream fout = new FileOutputStream(this.myLocation + rename + suffix);
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
		catch(IOException e){
			return;
		}
//		for(IProject project: ResourcesPlugin.getWorkspace().getRoot().getProjects()){
//			try {
//				project.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
//			} catch (CoreException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
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
	
}