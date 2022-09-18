package com.flash.EE.compnent.physics;

public class CircularCollider extends AbstractCollider{
	
	public float radius;

	public CircularCollider(float radius) {
		this(false, radius);
	}
	
	public CircularCollider(boolean isTrigger , float radius) {
		super(isTrigger);
		this.radius = radius;
	}
	
	@Override
	public void beforeUpdate() {
		super.beforeUpdate();
		
		if(getGameObject().getTransform().getPosition().equals(prePosition)) {
			return;
		}
		
		if(preArea.getMinX()<getGameObject().getTransform().getPosition().getX() 
				&& preArea.getMaxX()>getGameObject().getTransform().getPosition().getX()
				&& preArea.getMinY()<getGameObject().getTransform().getPosition().getY()
				&& preArea.getMaxY()>getGameObject().getTransform().getPosition().getY()) {
			ergodicArea(preArea);
		}else {
			ergodicArea(area);
		}
		
		prePosition.setVec(getGameObject().getTransform().getPosition());
	}
	
	public void ergodicArea(Area area) {
		if(area.getChildren()==null) {
			if(preArea!=area) {
				preArea.getGameObjects().remove(getGameObject());
				area.getGameObjects().add(getGameObject());
				preArea = area;
			}
			return;
		}
		for(Area a : area.getChildren()) {
			if(a.getMinX()<getGameObject().getTransform().getPosition().getX()-radius
					&& a.getMaxX()>getGameObject().getTransform().getPosition().getX()+radius
					&& a.getMinY()<getGameObject().getTransform().getPosition().getY()-radius
					&& a.getMaxY()>getGameObject().getTransform().getPosition().getY()+radius) {
				ergodicArea(a);
				return;
			}
		}
		if(preArea!=area) {
			preArea.getGameObjects().remove(getGameObject());
			area.getGameObjects().add(getGameObject());
			preArea = area;
		}
	}
	
	@Override
	public void overrideArea() {
		if(getGameObject().getTransform().getPosition().equals(prePosition)) {
			return;
		}
		String[][] arr = AreaManager.getMap();
		
		//清除上次的记录
		clearArea();
		
		//开始本次的记录
		int r = (int)getGameObject().getTransform().getSize().getX()/2;
		int r2=r*r;
		int x = (int)getGameObject().getTransform().getPosition().getX()+arr.length/2;
		int y = (int)getGameObject().getTransform().getPosition().getY()+arr[0].length/2;
		int minX = x-r;
		int maxX = x+r;
		int minY = y-r;
		int maxY = y+r;
		for(int i=minX;i<=maxX;i++){
			for(int j=minY;j<=maxY;j++){
				int w = i-x;
				int h = j-y;
				int x2=w*w+h*h;
				
				if(x2<=r2 && arr[i][j]==null){
					arr[i][j]=getGameObject().getName();
				}
			}
		}
	}

	@Override
	public void clearArea() {
		String[][] arr = AreaManager.getMap();
		
		int r = (int)getGameObject().getTransform().getSize().getX()/2;
		int x = (int)prePosition.getX()+arr.length/2;
		int y = (int)prePosition.getY()+arr[0].length/2;
		int minX = x-r;
		int maxX = x+r;
		int minY = y-r;
		int maxY = y+r;
		for(int i=minX;i<=maxX;i++){
			for(int j=minY;j<=maxY;j++){
				if(getGameObject().getName().equals(arr[i][j])) {
					arr[i][j]=null;
				}
			}
		}
	}
	
}
