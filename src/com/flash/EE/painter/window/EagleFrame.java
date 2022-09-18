package com.flash.EE.painter.window;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.flash.EE.core.control.launch.Furbisher;
import com.flash.EE.util.Vector2D;

@SuppressWarnings("serial")
public class EagleFrame extends JFrame implements Furbisher{
	
	public static Dimension SCREEM_SIZE = Toolkit.getDefaultToolkit().getScreenSize(); 
	
	/**
	 * 	是否显示该窗口的标题栏
	 */
	private boolean isDecorated = true;
	
	/**
	 * 	The size of this frame that can be viewed.
	 */
	private Vector2D frameSize = new Vector2D(SCREEM_SIZE.width/2, SCREEM_SIZE.height/2);
	
	public EagleFrame(boolean isDecorated , Vector2D frameSize) {
		this.isDecorated = isDecorated;
		if(frameSize!=null && frameSize.getX()>0 && frameSize.getY()>0) {
			this.frameSize = frameSize;
		}
		init();
	}
	
	private JPanel paintPanel = new PaintPanel();
	
	/**
	 * 	To init this frame.
	 */
	public final void init() {
		this.setContentPane(paintPanel);
		
		//Make this frame full the screem
		this.setUndecorated(!this.isDecorated);
	    this.setSize((int)frameSize.getX() , (int)frameSize.getY()); 
	    this.setLocation((int)(SCREEM_SIZE.width - frameSize.getX()) / 2,  
	    		(int)(SCREEM_SIZE.height - frameSize.getY()) / 2); 
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    //注册监听器
		this.addKeyListener(new EagleKeyBoardListener());
		this.paintPanel.addMouseMotionListener(new EagleMouseListener());
		this.paintPanel.addMouseListener(new EagleMouseListener());
		
		this.setVisible(true);
		JOptionPane.showMessageDialog(null, "Game Start!\n@technology by flashgames", "EAGLE", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * 	Override from the interface named View.
	 * 	It will be called every frame.
	 */
	@Override
	public final void refreshView(){
		this.paintPanel.repaint();
	}
	
}
