package com.flash.EE.util;

import com.flash.EE.core.object.gameobject.GameObject2D;
import com.flash.EE.core.object.ui.AbstractUIView;

public class Ergodic {
	
	public static interface ErgodicGameObjectCallback{ void callBack(GameObject2D gameObject2D);}
	public static void ergodicGameObject(GameObject2D gameObject2D ,ErgodicGameObjectCallback ergodicGameObjectCallback) {
		if(ergodicGameObjectCallback==null) {
			return;
		}
		//更新自身的方法
		if(gameObject2D!=null && gameObject2D.isEnable()) {
			ergodicGameObjectCallback.callBack(gameObject2D);
			//更新孩子
			for(int i=0;i<gameObject2D.getChildren().size();i++) {
				ergodicGameObject(gameObject2D.getChildren().get(i) , ergodicGameObjectCallback);
			}
		}
	}
	
	
	public static interface ErgodicUIViewCallback{ void callBack(AbstractUIView view);}
	public static void ergodicUIView(AbstractUIView view,ErgodicUIViewCallback ergodicUIViewCallback) {
		if(ergodicUIViewCallback==null) {
			return;
		}
		//更新自身的方法
		if(view!=null && view.isEnable()) {
			ergodicUIViewCallback.callBack(view);
			
			//更新孩子
			for(int i=0;i<view.getChildren().size();i++) {
				ergodicUIView(view.getChildren().get(i) , ergodicUIViewCallback);
			}
		}
	}
	
	
}
