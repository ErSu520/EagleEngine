package com.flash.EE.compnent.pathfinding;

public class FindingThread implements Runnable{
	
	private Task task;
	
	public FindingThread(Task task) {
		this.task = task;
	}
	

	@Override
	public void run() {
		
		task.getTrace().clear();
		
		AStarFinder.execute(task);
		
		Node end = task.getGoal();
		while (end != null)
		{
			task.getTrace().add(end.getPosition().toVector2D());
			end = end.getParent();
		} 
		
		//搜索结束
		task.setFinding(false);

	}
	
	

}
