package com.flash.EE.compnent.pathfinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import com.flash.EE.util.Vector2D;

public class Task {
	
	private String objectName = "";
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	public String getObjectName() {
		return objectName;
	}

	private boolean finding = false;
	public synchronized boolean isFinding() {
		return finding;
	}
	public synchronized void setFinding(boolean finding) {
		this.finding = finding;
	}

	/**
	 * 	起始地点和目的地
	 */
	private Node start , goal; 
	public Node getStart() {
		return start;
	}
	public void setStart(Node start) {
		this.start = start;
	}
	public Node getGoal() {
		return goal;
	}
	public void setGoal(Node goal) {
		this.goal = goal;
	}
	
	private int width = 1;
	public void setWidth(int width) {
		this.width = width;
	}
	public int getWidth() {
		return width;
	}
	
	/**
	 * 	世界地图
	 */
	private String[][] map; 
	public String[][] getMap() {
		return map;
	}
	public void setMap(String[][] map) {
		this.map = map;
	}
	
	/**
	 * 	重置任务
	 */
	public void initial() {
		openList.clear();
		openHash.clear();
		closeMap.clear();
	}
	
	/**
	 * 	open表
	 */
	private final Queue<Node> openList = new PriorityQueue<Node>();
	
	private final Map<String, Node> openHash = new HashMap<>();
	
	public void addToOpen(Node node) {
		openList.add(node);
		openHash.put(node.getPosition().getX()+":"+node.getPosition().getY(),node);
	}
	
	public Node poll() {
		Node node = openList.poll();
		if(node!=null) {
			openHash.remove(node.getPosition().getX()+":"+node.getPosition().getY());
		}
		return node;
	}
	
	public Node getFromOpen(String key) {
		return openHash.get(key);
	}
	
	
	private final Map<String, Node> closeMap = new HashMap<>();
	public void addToClose(Node node) {
		closeMap.put(node.getPosition().getX()+":"+node.getPosition().getY(),node);
	}
	public Node getFromClose(String key) {
		return closeMap.get(key);
	}
	
	private final List<Vector2D> trace = new ArrayList<>();

	public List<Vector2D> getTrace(){
		return trace;
	}
	
}






