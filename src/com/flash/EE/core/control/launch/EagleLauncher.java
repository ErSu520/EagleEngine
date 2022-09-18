package com.flash.EE.core.control.launch;

import javax.swing.JOptionPane;

import com.flash.EE.core.control.loader.ESceneLoader;
import com.flash.EE.core.object.behaviour.GameObjectBehaviour;
import com.flash.EE.core.object.behaviour.UIBehaviour;
import com.flash.EE.core.object.gameobject.EGameObject2D;
import com.flash.EE.core.object.gameobject.GameObject2D;
import com.flash.EE.core.object.scene.EScene;
import com.flash.EE.core.object.ui.AbstractUIView;
import com.flash.EE.core.system.Eagle;
import com.flash.EE.util.Ergodic;
import com.flash.EE.util.Vector2D;


public class EagleLauncher implements Launcher{

	
	private Furbisher view ;
	private Vector2D viewSize;
	
	public EagleLauncher(Furbisher view , Vector2D viewSize) {
		this.view = view;
		this.viewSize = viewSize;
		Eagle.getCamera().setPosition(viewSize.getX()/2,viewSize.getY()/2);
	}
	
	@Override
	public void launch() {
		try {
			if(Eagle.getCurrentScene()==null) {
				JOptionPane.showMessageDialog(null, "There is no scene exist in this game!!!", "ERROR"
							, JOptionPane.ERROR_MESSAGE);
				System.exit(0);
//				throw new Exception("There is no scene exist in this game!!!");
			}
			
			if(viewSize==null) {
				JOptionPane.showMessageDialog(null, "There is no view size to init this game!!!", "ERROR"
						, JOptionPane.ERROR_MESSAGE);
				System.exit(0);
//				throw new Exception("There is no view to show this game!!!");
			}
			
			if(view==null) {
				JOptionPane.showMessageDialog(null, "There is no view to show this game!!!", "ERROR"
						, JOptionPane.ERROR_MESSAGE);
				System.exit(0);
//				throw new Exception("There is no view to show this game!!!");
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Eagle.getViewControler().setGameViewSize(viewSize);

		new Thread() {
			public void run() {
				while(true) {
					long startTime = System.currentTimeMillis();
					
					view.refreshView();
					
					update();
					
					try {
						long time = System.currentTimeMillis()-startTime;
						if(time<3) {
							Thread.sleep(3-time);
						}
					} catch (InterruptedException e) {}
					
					Eagle.getTime().setFrameTime(System.currentTimeMillis()-startTime);
				}
			}
		}.start();
		
	}
	
	private GameObject2D gameObject2D ;
	private	AbstractUIView uiView;
	
	private void update() {
		//获取当前场景
		EScene scene = (EScene) Eagle.getCurrentScene();
		//取出物体树
		this.gameObject2D = scene.getGameTree();
		this.uiView = scene.getViewTree();
		
		//遍历物体树，执行逻辑行为方法
		Ergodic.ergodicGameObject(gameObject2D, new BeforeUpdateGameObject());
		Ergodic.ergodicUIView(uiView, new BeforeUpdateUIView());
		
		Ergodic.ergodicGameObject(gameObject2D, new UpdateGameObject());
		Ergodic.ergodicUIView(uiView, new UpdateUIView());
		
		Ergodic.ergodicGameObject(gameObject2D, new LaterUpdateGameObject());
		Ergodic.ergodicUIView(uiView, new LaterUpdateUIView());	
		
		//遍历物体的开始，销毁等队列
		refreshGameObjects(scene);
		refreshViews(scene);
		
		//检测是否需要加载下一场景
		ESceneLoader loader = (ESceneLoader)Eagle.getSceneLoader();
		if(loader.getNextScene()!=null) {
			//执行所有的破坏方法
			Ergodic.ergodicGameObject(gameObject2D, new DestroyGameObject());
			Ergodic.ergodicUIView(uiView, new DestroyUIView());	
			//替换场景
			loader.setCurrentScene(loader.getNextScene());
			loader.setNextScene(null);
		}
	}
	
	class BeforeUpdateGameObject implements Ergodic.ErgodicGameObjectCallback{
		@Override
		public void callBack(GameObject2D gameObject2D) {
			for(int i=0;i<gameObject2D.getBehaviours().size();i++) {
				gameObject2D.getBehaviours().get(i).beforeUpdate();
			}
		}
	}
	class UpdateGameObject implements Ergodic.ErgodicGameObjectCallback{
		@Override
		public void callBack(GameObject2D gameObject2D) {
			for(int i=0;i<gameObject2D.getBehaviours().size();i++) {
				gameObject2D.getBehaviours().get(i).update();
			}
		}
	}
	class LaterUpdateGameObject implements Ergodic.ErgodicGameObjectCallback{
		@Override
		public void callBack(GameObject2D gameObject2D) {
			for(int i=0;i<gameObject2D.getBehaviours().size();i++) {
				gameObject2D.getBehaviours().get(i).laterUpdate();
			}
		}
	}
	class DestroyGameObject implements Ergodic.ErgodicGameObjectCallback{
		@Override
		public void callBack(GameObject2D gameObject2D) {
			for(int i=0;i<gameObject2D.getBehaviours().size();i++) {
				gameObject2D.getBehaviours().get(i).destroy();
			}
		}
	}
	
	class BeforeUpdateUIView implements Ergodic.ErgodicUIViewCallback{
		@Override
		public void callBack(AbstractUIView view) {
			for(int i=0;i<view.getBehaviours().size();i++) {
				view.getBehaviours().get(i).beforeUpdate();
			}
		}
	}
	class UpdateUIView implements Ergodic.ErgodicUIViewCallback{
		@Override
		public void callBack(AbstractUIView view) {
			for(int i=0;i<view.getBehaviours().size();i++) {
				view.getBehaviours().get(i).update();
			}
		}
	}
	class LaterUpdateUIView implements Ergodic.ErgodicUIViewCallback{
		@Override
		public void callBack(AbstractUIView view) {
			for(int i=0;i<view.getBehaviours().size();i++) {
				view.getBehaviours().get(i).laterUpdate();
			}
		}
	}
	class DestroyUIView implements Ergodic.ErgodicUIViewCallback{
		@Override
		public void callBack(AbstractUIView view) {
			for(int i=0;i<view.getBehaviours().size();i++) {
				view.getBehaviours().get(i).destroy();
			}
		}
	}
	
	private void refreshGameObjects(EScene scene) {
		for(int i=0;i<scene.getStartObjects().size();i++) {
			GameObject2D gg = scene.getStartObjects().get(i);
			for(GameObjectBehaviour behaviour : gg.getBehaviours()) {
				behaviour.start();
			}
			gg.getRoot().getChildren().add(gg);
		}
		//将开始队列的所有物体加入显示队列 同时清空开始队列
		scene.getEnableObjects().addAll(scene.getStartObjects());
		scene.getStartObjects().clear();
		
		for(int i=0;i<scene.getEnableObjects().size();i++) {
			EGameObject2D gg = scene.getEnableObjects().get(i);
			gg.enable();
			Ergodic.ergodicGameObject(gg, new Ergodic.ErgodicGameObjectCallback() {
				@Override
				public void callBack(GameObject2D gameObject2D) {
					for(int i=0;i<gameObject2D.getBehaviours().size();i++) {
						gameObject2D.getBehaviours().get(i).enable();
					}
				}
			});
		}
		//清空显示队列
		scene.getEnableObjects().clear();
		
		for(int i=0;i<scene.getDisabletObjects().size();i++) {
			EGameObject2D gg = scene.getDisabletObjects().get(i);
			Ergodic.ergodicGameObject(gg, new Ergodic.ErgodicGameObjectCallback() {
				@Override
				public void callBack(GameObject2D gameObject2D) {
					for(int i=0;i<gameObject2D.getBehaviours().size();i++) {
						gameObject2D.getBehaviours().get(i).disable();
					}
				}
			});
			gg.disable();
		}
		//清空隐藏队列
		scene.getDisabletObjects().clear();
		
		for(int i=0;i<scene.getDestroyObjects().size();i++) {
			EGameObject2D gg = scene.getDestroyObjects().get(i);
			//删除哈希表内容
			scene.getGameMap().remove(gg.getName());
			Ergodic.ergodicGameObject(gg, new Ergodic.ErgodicGameObjectCallback() {
				@Override
				public void callBack(GameObject2D gameObject2D) {
					for(int i=0;i<gameObject2D.getBehaviours().size();i++) {
						gameObject2D.getBehaviours().get(i).destroy();
					}
				}
			});
			gg.getRoot().getChildren().remove(gg);
		}
		//清空隐藏队列
		scene.getDestroyObjects().clear();
		
	}
	
	private void refreshViews(EScene scene) {
		for(int i=0;i<scene.getStartViews().size();i++) {
			AbstractUIView vv = scene.getStartViews().get(i);
			for(UIBehaviour behaviour : vv.getBehaviours()) {
				behaviour.start();
			}
			vv.getRoot().getChildren().add(vv);
		}
		//将开始队列的所有物体加入显示队列 同时清空开始队列
		scene.getEnableViews().addAll(scene.getStartViews());
		scene.getStartViews().clear();
		
		for(int i=0;i<scene.getEnableViews().size();i++) {
			AbstractUIView vv = scene.getEnableViews().get(i);
			vv.enable();
			Ergodic.ergodicUIView(vv, new Ergodic.ErgodicUIViewCallback() {
				@Override
				public void callBack(AbstractUIView view) {
					for(int i=0;i<view.getBehaviours().size();i++) {
						view.getBehaviours().get(i).enable();
					}
				}
			});
		}
		//清空显示队列
		scene.getEnableViews().clear();
		
		for(int i=0;i<scene.getDisableViews().size();i++) {
			AbstractUIView vv = scene.getDisableViews().get(i);
			Ergodic.ergodicUIView(vv, new Ergodic.ErgodicUIViewCallback() {
				@Override
				public void callBack(AbstractUIView view) {
					for(int i=0;i<view.getBehaviours().size();i++) {
						view.getBehaviours().get(i).disable();
					}
				}
			});
			vv.disable();
		}
		//清空隐藏队列
		scene.getDisableViews().clear();
		
		for(int i=0;i<scene.getDestroyViews().size();i++) {
			AbstractUIView vv = scene.getDestroyViews().get(i);
			//删除哈希表内容
			scene.getViewMap().remove(vv.getName());
			Ergodic.ergodicUIView(vv, new Ergodic.ErgodicUIViewCallback() {
				@Override
				public void callBack(AbstractUIView view) {
					for(int i=0;i<view.getBehaviours().size();i++) {
						view.getBehaviours().get(i).destroy();
					}
				}
			});
			vv.getRoot().getChildren().remove(vv);
		}
		//清空隐藏队列
		scene.getDestroyViews().clear();
		
	}
	
	
}


