package auth_environment.Models;

import auth_environment.IAuthEnvironment;

/**
 * Created by BrianLin on 4/20/16
 * Team member responsible: Brian
 *
 * This Tab is for placing Paths on the Map 
 */

import auth_environment.Models.Interfaces.IPathTabModel;

public class PathTabModel implements IPathTabModel {
	
	private IAuthEnvironment myAuthData;  
	
	private double myPathWidth; 
	
	public PathTabModel(IAuthEnvironment auth) {
		this.myAuthData = auth; 
	}

	// TODO: should all Paths have the same width? 
	@Override
	public void setPathWidth(double width) {
		this.myPathWidth = width; 
	}

	@Override
	public double getPathWidth() {
		return this.myPathWidth;
	}
	
	
}
