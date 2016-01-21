package leoJosef;

import javax.swing.JOptionPane;

public class Point {

	private double x, y;
	
	
	
	
	public double getX(){
		return this.x;
	}
	
	
	public double getY(){
		return this.y;
	}
	
	
	public void read(){
		this.x = Double.parseDouble(JOptionPane.showInputDialog("Enter x: "));
		this.y = Double.parseDouble(JOptionPane.showInputDialog("Enter y: "));
	}
	
	
	public String toString(){
		return "(" + this.getX() + "," + this.getY() + ")";
	}
	
	
	
	
	public Point(double x, double y){
		this.x = x;
		this.y = y;
	}
	
}
