package game_engine.properties;

import java.util.Arrays;
import java.util.List;


public class Position extends Property {

	private double myX;
	private double myY;
	private double myZ;

	public Position (double x, double y) {
		this.myX = x;
		this.myY = y;
	}

	public Position (double x, double y, double z){
		this.myX = x;
		this.myY = y;
		this.myZ = z;
	}

	public double getX () {
		return myX;
	}

	public double getY () {
		return myY;
	}

	public double getZ() {
		return myZ;
	}

	public void setX (double x) {
		this.myX = x;
	}

	public void setY (double y) {
		this.myY = y;
	}

	public void setZ (double z) {
		this.myZ = z;
	}

	public void addToX (double x) {
		this.myX += x;
	}

	public void addToY (double y) {
		this.myY += y;
	}

	public void addToZ (double z) {
		this.myZ += z;
	}

	public void addToXY (double x, double y) {
		addToX(x);
		addToY(y);
	}

	public Position copyPosition () {
		return new Position(this.getX(), this.getY());
	}

	public double distanceTo (Position other) {
		double dx = this.myX - other.myX;
		double dy = this.myY - other.myY;
		double dz = this.myZ - other.myZ;
		return Math.sqrt(dx * dx + dy * dy + dz * dz);
	}

	@Override
	public boolean equals (Object o) {
		return (o instanceof Position &&
				(Math.abs(((Position) o).myX - this.myX)) < 0.0000001 &&
				(Math.abs(((Position) o).myY - this.myY)) < 0.0000001 &&
				(Math.abs(((Position) o).myZ - this.myZ)) < 0.0000001) ||
				(this == o);
	}

	@Override
	public String toString () {
		return "("+Double.toString(this.myX) + ", " + Double.toString(this.myY) + ", " + Double.toString(this.myZ)+")";
	}

	@Override
	public void setValues(List<Double> values) {
		this.myX = values.get(0);
		this.myY = values.get(1);
		this.myZ = values.get(2);
	}

	@Override
	public List<Double> getValues () {
		return Arrays.asList(myX, myY, myZ);
	}

	public Position getPosition () {
		return copyPosition();
	}

	@Override
	public int hashCode(){
		return 15;
	}


}