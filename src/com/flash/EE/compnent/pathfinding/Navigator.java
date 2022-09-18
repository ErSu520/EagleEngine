package com.flash.EE.compnent.pathfinding;

import com.flash.EE.compnent.physics.AbstractCollider;
import com.flash.EE.compnent.physics.AreaManager;
import com.flash.EE.compnent.physics.CircularCollider;
import com.flash.EE.compnent.physics.RectangleCollider;
import com.flash.EE.compnent.physics.RigidBody;
import com.flash.EE.core.object.behaviour.GameObjectBehaviour;
import com.flash.EE.core.object.gameobject.GameObject2D;
import com.flash.EE.core.system.Eagle;
import com.flash.EE.util.ThreadPool;
import com.flash.EE.util.Vector2D;
import com.flash.EE.util.Vector2DInt;

public class Navigator extends GameObjectBehaviour{

	private Task task = new Task();
	
	public void moveTo(Vector2D position) {
		if(task.isFinding()) {
			return;
		}
		//设置运行状态
		this.running = true;
		//设置为正在寻路状态
		task.setFinding(true);
		
		int x = (int)position.getX();
		int y = (int)position.getY();
		
		//获取该位置在地图数组中的相对位置
		x = x+AreaManager.getMap().length/2;
		y = y+AreaManager.getMap()[0].length/2;
		
		if(x<0|| x>=AreaManager.getMap().length
				||y<0 || y>=AreaManager.getMap()[0].length) {
			return;
		}
		 
		int X = (int)getGameObject().getTransform().getPosition().getX()+AreaManager.getMap().length/2;
		int Y = (int)getGameObject().getTransform().getPosition().getY()+AreaManager.getMap()[0].length/2;
		
		Node start = new Node(new Vector2DInt(X,Y), 1, 0);
		Node end = new Node(new Vector2DInt(x,y), 0, 0);
		task.setStart(start);
		task.setGoal(end);
		task.setMap(AreaManager.getMap());
		task.setObjectName(getGameObject().getName());
		if(collider instanceof CircularCollider) {
			CircularCollider cc = (CircularCollider)collider;
			task.setWidth((int)(cc.radius*2));
		}else {
			task.setWidth((int)getGameObject().getTransform().getSize().getX());
		}
		
		//使用单独的线程 开始寻路
		ThreadPool.getPool().execute(new FindingThread(task));
	}
	
	private boolean running = false;
	
	public boolean isRunning() {
		return running;
	}
	public void stop() {
		running = false;
	}
	
	private Vector2D nextPosition;
	
	public float speed;
	
	public Navigator(float speed) {
		this.speed = speed;
	}
	
	private AbstractCollider collider ;
	
	private RigidBody rigid;
	
	@Override
	public void start() {
		collider = (AbstractCollider) getGameObject().findBehaviour(CircularCollider.class);
		if(collider==null) {
			collider = (AbstractCollider) getGameObject().findBehaviour(RectangleCollider.class);
		}
		if(collider==null) {
			try {
				throw new Exception("挂载寻路组件的物体必须挂载碰撞体组件");
			} catch (Exception e) {}
		}
		rigid = (RigidBody) getGameObject().findBehaviour(RigidBody.class);
		if(rigid==null) {
			try {
				throw new Exception("挂载寻路组件的物体必须挂载刚体组件");
			} catch (Exception e) {}
		}
	}
	
	@Override
	public void update() {
		if(running == false) {
			return;
		}
		if(task.isFinding()) {
			nextPosition = null;
			return;
		}
		if(nextPosition==null) {
			if(!task.getTrace().isEmpty()) {
				Vector2D vv = task.getTrace().remove(task.getTrace().size()-1);
				if(getGameObject().getTransform().getPosition().distanceFrom(vv)>1f) {
					nextPosition = vv;
					nextPosition.setVec(vv.getX()-AreaManager.getMap().length/2
							, vv.getY()-AreaManager.getMap()[0].length/2);
				}else {
					task.getTrace().clear();
				}
			}
		}else {
			if(getGameObject().getTransform().getPosition().distanceFrom(nextPosition)<0.1f) {
				if(!task.getTrace().isEmpty()) {
					Vector2D vv = task.getTrace().remove(task.getTrace().size()-1);
					nextPosition.setVec(vv.getX()-AreaManager.getMap().length/2
							, vv.getY()-AreaManager.getMap()[0].length/2);
				}else {
					nextPosition = null;
				}
			}else {
				getGameObject().forward(nextPosition);
				getGameObject().move(speed*Eagle.getTime().getDeltaTime());
			}
		}
	}
	
	@Override
	public void collision(GameObject2D collider) {
		nextPosition = null;
		stop();
	}
	
	
}
