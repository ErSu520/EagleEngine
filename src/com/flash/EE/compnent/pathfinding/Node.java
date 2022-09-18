package com.flash.EE.compnent.pathfinding;

import com.flash.EE.util.Vector2DInt;

public class Node implements Comparable<Node>{
	
	public Node(Vector2DInt position , int step , int distance) {
		this.position = position;
		this.step = step;
		this.distance = distance;
	}

	private Vector2DInt position;
	
	public Vector2DInt getPosition() {
		return position;
	}
	
	private Node parent ;
	public void setParent(Node parent) {
		this.parent = parent;
	}
	public Node getParent() {
		return parent;
	}

	private Node child ;
	public void setChild(Node child) {
		this.child = child;
	}
	public Node getChild() {
		return child;
	}
	
	
	/**
	 * 当前已经走了多少步
	 */
	private int step = 0;

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}
	
	
	private int distance = 0;
	
	public int getDistance() {
		return distance;
	}
	@Override
	public int compareTo(Node o) {
		if((this.step+this.distance)<(o.step+o.distance)) {
			return -1;
		}
		return 1;
	}
	
	
	
	
}
