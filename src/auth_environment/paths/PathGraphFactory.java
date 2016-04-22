package auth_environment.paths;

import java.util.List;
import game_engine.game_elements.Branch;
import game_engine.libraries.PathLibrary;
import game_engine.properties.Position;

public class PathGraphFactory {

	private PathLibrary myPathLibrary;
	private BranchHandler myBranchHandler;

	public PathGraphFactory(){
		myPathLibrary = new PathLibrary();
		myBranchHandler = new BranchHandler();
	}

	public PathGraphFactory(PathLibrary pathLibrary){
		this.myPathLibrary = pathLibrary;
		myBranchHandler = new BranchHandler();
	}

	/**
	 * @param newPath
	 * @param GraphID
	 * Inserts branch with newPath positions into path graph
	 */
	public void insertBranch(List<Position> branchPos){
		if(branchPos.size() == 0)
			return;
		Branch newBranch = new Branch(branchPos);
		PathNode currentPath = myPathLibrary.getPathGraph().getPathByPos(branchPos.get(0));
		if(currentPath != null){
			myBranchHandler.configureBranchInPath(newBranch, currentPath);
		}
		else{
			currentPath = myPathLibrary.getPathGraph().getPathByPos(branchPos.get(branchPos.size()-1));
			if(currentPath != null){
				myBranchHandler.configureBranchInPath(newBranch, currentPath);
			}
		}
		if(myPathLibrary.getPathGraph().getBranch(newBranch) == null){
			if(branchPos.size() > 0){
				createNewPath(newBranch);
			}
		}
	}

	private PathNode createNewPath(Branch branch){
		myPathLibrary.getPathGraph().addPath(new PathNode(branch));
		return myPathLibrary.getPathGraph().getLastPath();
	}

	public PathLibrary getPathLibrary() {
		return myPathLibrary;
	}

}