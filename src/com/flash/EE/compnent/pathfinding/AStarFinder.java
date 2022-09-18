package com.flash.EE.compnent.pathfinding;

import com.flash.EE.util.Vector2DInt;


public class AStarFinder {

	public static void execute(Task task) {
		if(task==null) {
			return;
		}
		//清除上次的残留
		task.initial();
		
		//将起始节点加入open表
		task.addToOpen(task.getStart());
		
		//开始循环查找
		Node current = null;
		while ( (current=task.poll()) != null ){
			//判断是否已有路线
			if(task.getGoal().getParent()!=null) {
				Node end = task.getGoal();
				while (end != null)
				{ 
					if(end.getParent()!=null) {
						end.getParent().setChild(end);
					}
					end = end.getParent();
				}
				break;
			}
			
			//添加到close表
			task.addToClose(current);
			
			addNodeToOpen(task,current, current.getPosition().getX() - 1, current.getPosition().getY());
			addNodeToOpen(task,current, current.getPosition().getX(), current.getPosition().getY() - 1);
			addNodeToOpen(task,current, current.getPosition().getX() + 1, current.getPosition().getY());
			addNodeToOpen(task,current, current.getPosition().getX(), current.getPosition().getY() + 1);
		}
	}
	
	private static void addNodeToOpen(Task task , Node root , int x , int y) {
		if(x>=task.getMap().length || x<0
			||y>=task.getMap()[0].length || y<0) {
			return;
		}
		if(task.getMap()[x][y] != null && !task.getMap()[x][y].equals(task.getObjectName())) {
			return;
		}
		
		String key = x+":"+y;
		//判断是否在close表
		Node node = task.getFromClose(key);
		if(node!=null) {
			return;
		}
		
		//查找open表中是否有该节点
		node = task.getFromOpen(key);
		
		int step = root.getStep()+1;
		
		if(node!=null) {//该节点已在open表中
			if(node.getStep()>step) {
				node.setStep(step);
				node.setParent(root);
			}
		}else{//需要加入列表
			//创建点
			Vector2DInt vec = new Vector2DInt(x,y);
			//判断该点是否目标点位
			if(vec.equals((task.getGoal().getPosition()))) {
				task.getGoal().setParent(root);
				task.getGoal().setStep(root.getStep()+1);
			}
			
			int width = task.getWidth()/2;
			if(x==root.getPosition().getX()) {//判断横着是否有足够距离
				if(!judgeVertical(task,x,y,width)) {
					return;
				}
			}else {//判断竖着是否有足够距离
				if(!judgeHorizonta(task, x, y, width)) {
					return;
				}
			}
			
			int distance = vec.distanceFrom(task.getGoal().getPosition());
			node = new Node(vec, step, distance);
			node.setParent(root);
			task.addToOpen(node);
		}
	}
	
	/*
	 * 	判断竖直方向
	 */
	private static boolean judgeVertical(Task task , int x , int y , int width) {
		int min = x-width , max = x+width;
		if(min<0 || max>=task.getMap().length) {
			return false;
		}
		for(int i=min;i<=max;i++) {
			if(task.getMap()[i][y] !=null && !task.getMap()[i][y].equals(task.getObjectName()) ) {
				return false;
			}
		}
		return true;
	}
	
	private static boolean judgeHorizonta(Task task , int x , int y , int width) {
		int min = y-width , max = y+width;
		if(min<0 || max>=task.getMap()[0].length) {
			return false;
		}
		for(int i=min;i<=max;i++) {
			if(task.getMap()[x][i] !=null && !task.getMap()[x][i].equals(task.getObjectName()) ) {
				return false;
			}
		}
		return true;
	}
	
}
































