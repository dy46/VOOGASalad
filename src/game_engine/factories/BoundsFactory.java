package game_engine.factories;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import game_engine.handlers.PositionHandler;
import game_engine.properties.Bounds;
import game_engine.properties.Position;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

/**
 * This class is a factory for constructing bounds of an enemy based on the non-transparent pixels of its image file.
 * 
 * @author adamtache
 *
 */

public class BoundsFactory {
	
	private PositionHandler myPH;
	
	public BoundsFactory(){
		myPH = new PositionHandler();
	}

	public Bounds createImageBounds(Image image){
		boolean[][] transparency = getTransparencyData(image);
		List<Integer> maxYs = getMaxTransparentYs(transparency);
		List<Integer> minYs = getMinTransparentYs(transparency);
		return createJaggedBounds(maxYs, minYs, getTransparencyCellWidth(image, transparency));
	}
	
	public Bounds createJaggedBounds(List<Integer> maxYs, List<Integer> minYs, double cellWidth){
		List<Position> jaggedBounds = new ArrayList<>();
		double currX = 0;
		for(Integer y : maxYs){
			jaggedBounds.add(new Position(cellWidth, y));
			currX += cellWidth;
		}
		for(int i=minYs.size() -1; i>=0; i--){
			jaggedBounds.add(new Position(currX, minYs.get(i)));
			currX -= cellWidth;
		}
		jaggedBounds.add(new Position(0, maxYs.get(0)));
		return new Bounds(myPH.getInterpolatedPositions(jaggedBounds, true));
	}
	
	public Bounds createDiamondBounds(double top, double bottom, double right, double left){
		double centerX = (left+right)/2;
		double centerY = (bottom+top)/2;
		List<Position> edgePos = new ArrayList<>();
		Position topPos = new Position(centerX, centerY - top);
		Position bottomPos = new Position(centerX, bottom);
		Position leftPos = new Position(left, centerY);
		Position rightPos = new Position(right, centerY);
		edgePos = Arrays.asList(topPos, rightPos, bottomPos, leftPos);
		return createInterpolatedBounds(edgePos, true);
	}
	
	private List<Integer> getMaxTransparentYs(boolean[][] grid){
		List<Integer> maxYs = new ArrayList<>();
		for(int x=0; x<grid.length; x++){
			int maxY = Integer.MIN_VALUE;
			for(int y=0; y<grid[x].length; y++){
				if(grid[x][y]){
					if(y > maxY){
						maxY = y;
					}
				}
			}
			maxYs.add(maxY);
		}
		return maxYs;
	}
	
	private List<Integer> getMinTransparentYs(boolean[][] grid){
		List<Integer> minYs = new ArrayList<>();
		for(int x=0; x<grid.length; x++){
			int minY = Integer.MAX_VALUE;
			for(int y=0; y<grid[x].length; y++){
				if(grid[x][y]){
					if(y < minY){
						minY = y;
					}
				}
			}
			minYs.add(minY);
		}
		return minYs;
	}
	
	private Bounds createInterpolatedBounds(List<Position> pos, boolean cycle){
		return new Bounds(myPH.getInterpolatedPositions(pos, cycle));
	}

	private boolean[][] getTransparencyData(Image image){
		int[][] pixelData = getPixelData(image);
		boolean[][] transparency = new boolean[pixelData.length][pixelData[0].length];
		for(int x=0; x<pixelData.length; x++){
			for(int y=0; y<pixelData[x].length; y++){
				if(pixelData[x][y] == 0xFF000000){
					transparency[x][y] = true;
				}

			}
		}
		return transparency;
	}
	
	private double getTransparencyCellWidth(Image image, boolean[][] grid){
		return image.getWidth()/grid.length;
	}

	private BufferedImage imageToBufferedImage(Image image){
		return SwingFXUtils.fromFXImage(image, null);
	}

	private int[][] getPixelData(Image image){
		BufferedImage img = imageToBufferedImage(image);
		int[][] pixelData = new int[img.getHeight() * img.getWidth()][3];
		int[] rgb;
		int counter = 0;
		for(int i = 0; i < img.getWidth(); i++){
			for(int j = 0; j < img.getHeight(); j++){
				rgb = getRGBData(img, i, j);
				for(int k = 0; k < rgb.length; k++){
					pixelData[counter][k] = rgb[k];
				}
				counter++;
			}
		}
		return pixelData;
	}

	private int[] getRGBData(BufferedImage img, int x, int y) {
		int argb = img.getRGB(x, y);
		int rgb[] = new int[] {
				(argb >> 16) & 0xff, //red
				(argb >>  8) & 0xff, //green
				(argb      ) & 0xff  //blue
		};
		return rgb;
	}
	
}