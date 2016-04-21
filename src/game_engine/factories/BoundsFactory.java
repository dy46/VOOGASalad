package game_engine.factories;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import auth_environment.paths.PositionHandler;
import game_engine.properties.Bounds;
import game_engine.properties.Position;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class BoundsFactory {
	
	private PositionHandler myPH;
	
	public BoundsFactory(){
		myPH = new PositionHandler();
	}

	public Bounds createImageBounds(Image image){
		boolean[][] transparencyData = getTransparencyData(image);
		int leftMostFilledPixel = Integer.MAX_VALUE;
		int rightMostFilledPixel = Integer.MIN_VALUE;
		int topMostFilledPixel = Integer.MAX_VALUE;
		int bottomMostFilledPixel = Integer.MIN_VALUE;
		for(int x=0; x<transparencyData.length; x++){
			for(int y=0; y<transparencyData[x].length; y++){
				if(transparencyData[x][y]){
					if(x > rightMostFilledPixel){
						rightMostFilledPixel = x;
					}
					if(x < leftMostFilledPixel){
						leftMostFilledPixel = x;
					}
					if(y > bottomMostFilledPixel){
						bottomMostFilledPixel = y;
					}
					if(y < topMostFilledPixel){
						topMostFilledPixel = y;
					}
				}
			}
		}
		return createDiamondBounds(topMostFilledPixel, bottomMostFilledPixel, rightMostFilledPixel, leftMostFilledPixel);
	}
	
	public Bounds createDiamondBounds(double top, double bottom, double right, double left){
		double centerX = (left+right)/2;
		double centerY = (bottom+top)/2;
		List<Position> edgePositions = new ArrayList<>();
		Position topPos = new Position(centerX, centerY - top);
		Position bottomPos = new Position(centerX, bottom);
		Position leftPos = new Position(left, centerY);
		Position rightPos = new Position(right, centerY);
		edgePositions = Arrays.asList(topPos, rightPos, bottomPos, leftPos);
		List<Position> diamond = myPH.getInterpolatedPositions(edgePositions, true);
		return new Bounds(diamond);
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

		System.out.println("rgb: " + rgb[0] + " " + rgb[1] + " " + rgb[2]);
		return rgb;
	}

}