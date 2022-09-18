package com.flash.EE.painter.window;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JPanel;

import com.flash.EE.core.object.gameobject.GameObject2D;
import com.flash.EE.core.object.scene.EScene;
import com.flash.EE.core.object.ui.AbstractUIView;
import com.flash.EE.core.system.Eagle;
import com.flash.EE.input.EMouseInput;
import com.flash.EE.input.Input;
import com.flash.EE.util.Ergodic;
import com.flash.EE.util.Vector2D;

@SuppressWarnings("serial")
public class PaintPanel extends JPanel{
	
	private GameObject2D gameObject = null;
	private AbstractUIView view = null;
	//摄像机离画面中心点的偏移
	private Vector2D offsetDeviation = Vector2D.ZERO();
	//画笔
	private Graphics2D g2 = null;
	//屏幕中画面放缩的比例
	private float rate = 1f;
	
	EMouseInput mouseInput = (EMouseInput)Input.getMouseInput();
	
	public void paintComponent(Graphics g){
		if(Eagle.getCurrentScene()==null) {
			return;
		}
		//取出当前场景数据
		EScene scene = (EScene)Eagle.getCurrentScene();
		gameObject = scene.getGameTree();
		view = scene.getViewTree();
		
		this.g2 = (Graphics2D)g;
		this.g2.setColor(Eagle.getCamera().getColor());
		this.g2.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		//获取屏幕真实大小
		Eagle.getViewControler().setRealViewSize(this.getWidth(),this.getHeight());
		//获取屏幕控制比例
		float rateX = this.getWidth()/Eagle.getViewControler().getGameViewSize().getX();
		float rateY = this.getHeight()/Eagle.getViewControler().getGameViewSize().getY();
		rate = rateX<rateY?rateX:rateY;
		//设置鼠标位置
		Point point = this.getMousePosition();
		if(point!=null) {
			mouseInput.setPosition((float)point.getX()/rate, (float)point.getY()/rate);
		}
		
		//Caculate the offset deviation of camera.
		offsetDeviation.setVec((Eagle.getCamera().getPosition().getX()-Eagle.getViewControler().getGameViewSize().getX()/2)*rate
					, (Eagle.getCamera().getPosition().getY()-Eagle.getViewControler().getGameViewSize().getY()/2)*rate);
		
		Ergodic.ergodicGameObject(gameObject, new DisplayGameObject());
		Ergodic.ergodicUIView(view, new DisplayUIView());
	}
	
	class DisplayGameObject implements Ergodic.ErgodicGameObjectCallback{
		@Override
		public void callBack(GameObject2D oo) {
			double rotation = Math.PI*(oo.getTransform().getRotation()/180);
			g2.rotate(rotation,oo.getTransform().getPosition().getX()*rate-offsetDeviation.getX()
					,oo.getTransform().getPosition().getY()*rate-offsetDeviation.getY());
			
			g2.drawImage(oo.getSprite2D().getImage()
					,(int)((oo.getTransform().getPosition().getX()-oo.getTransform().getSize().getX()/2)*rate-offsetDeviation.getX())
					, (int)((oo.getTransform().getPosition().getY()-oo.getTransform().getSize().getY()/2)*rate-offsetDeviation.getY())
					, (int)(oo.getTransform().getSize().getX()*rate), (int)(oo.getTransform().getSize().getY()*rate), PaintPanel.this);
			
			g2.rotate(-rotation,oo.getTransform().getPosition().getX()*rate-offsetDeviation.getX()
					,oo.getTransform().getPosition().getY()*rate-offsetDeviation.getY());
		}
	}
	
	class DisplayUIView implements Ergodic.ErgodicUIViewCallback{
		@Override
		public void callBack(AbstractUIView view) {
			double rotation = Math.PI*(view.getTransform().getRotation()/180);
			g2.rotate(rotation,view.getTransform().getPosition().getX()*rate
					,view.getTransform().getPosition().getY()*rate);
			
			g2.drawImage(view.getSprite2D().getImage()
					,(int)((view.getTransform().getPosition().getX()-view.getTransform().getSize().getX()/2)*rate)
					, (int)((view.getTransform().getPosition().getY()-view.getTransform().getSize().getY()/2)*rate)
					, (int)(view.getTransform().getSize().getX()*rate), (int)(view.getTransform().getSize().getY()*rate), PaintPanel.this);
			
			if(!view.getText().equals("")) {
				g2.setFont(new Font("", 0 ,(int)(view.getTextSize()*rate)));
				FontMetrics fm = g2.getFontMetrics();
				int stringWidth = fm.stringWidth(view.getText());
				int stringAscent = fm.getAscent();
				while(stringAscent>view.getTransform().getSize().getY()
						|| stringWidth>view.getTransform().getSize().getX()) {
					g2.setFont(new Font("", 0 ,g2.getFont().getSize()-1));
					fm = g2.getFontMetrics();
					stringWidth = fm.stringWidth(view.getText());
					stringAscent = fm.getAscent();
				}
				g2.setColor(view.getTextColor());
				g2.drawString(view.getText(), (view.getTransform().getPosition().getX()-stringWidth/2)*rate
							, (view.getTransform().getPosition().getY()+stringAscent/2)*rate);
				g2.setColor(getBackground());
			}
			
			g2.rotate(-rotation,view.getTransform().getPosition().getX()*rate
					,view.getTransform().getPosition().getY()*rate);
		}
	}
	
}
