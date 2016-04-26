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

	public PathGraphFactory(List<Branch> branches){
		this.myPathLibrary = new PathLibrary(branches);
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
		Branch currentBranch = myPathLibrary.getPathGraph().getBranchByPos(branchPos.get(0));
		if(currentBranch != null){
			myBranchHandler.configureBranch(newBranch, myPathLibrary.getPathGraph());
		}
		else{
			currentBranch = myPathLibrary.getPathGraph().getBranchByPos(branchPos.get(branchPos.size()-1));
			if(currentBranch != null){
				myBranchHandler.configureBranch(newBranch, myPathLibrary.getPathGraph());
			}
		}
		if(myPathLibrary.getPathGraph().getBranch(newBranch) == null){
			if(branchPos.size() > 0){
				createNewBranch(newBranch);
			}
		}
	}

	private void createNewBranch(Branch branch){
		myPathLibrary.getPathGraph().addBranch(branch);
	}

	public PathLibrary getPathLibrary() {
		return myPathLibrary;
	}
	
	public List<Branch> getBranches(){
		return myPathLibrary.getBranches();
	}

}