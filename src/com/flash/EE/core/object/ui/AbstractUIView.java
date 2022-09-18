package com.flash.EE.core.object.ui;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;
import com.flash.EE.core.object.behaviour.UIBehaviour;
import com.flash.EE.core.object.renderer.ERenderer;
import com.flash.EE.core.object.renderer.Renderer;
import com.flash.EE.core.object.scene.EScene;
import com.flash.EE.core.object.sprite.ESprite2D;
import com.flash.EE.core.object.sprite.Sprite2D;
import com.flash.EE.core.object.transform.ETransform;
import com.flash.EE.core.object.transform.Transform;


public abstract class AbstractUIView{
	
	/**
	 * 	To remind the default image.
	 * 	If this UI view did not assign a image, the image of this view will be this.
	 */
	private static Image UI_DEFAULT = null;
	
	static {
		if(UI_DEFAULT==null) {
			try {
				UI_DEFAULT = ImageIO.read(AbstractUIView.class.getResource("/private/picture/UI_DEFAULT.png"));
			} catch (IOException e) {
				
			}
		}
	}
	
	public AbstractUIView(String name) {
		this.name = name;
		this.sprite.setImage(UI_DEFAULT);
	}
	
	protected String name = "";

	/**
	 * 	To get the only name of this view.
	 * 	'The only' means the value of name will not change from it init on.
	 * @return The only name of this view.
	 */
	public String getName() {
		return name;
	}
	
	private final Sprite2D sprite = new ESprite2D();

	/**
	 * 	To get the sprite of this view.
	 * 	The sprite is what will view in the frame on behalf of this view.
	 * @return
	 */
	public Sprite2D getSprite2D() {
		return sprite;
	}
	
	private final Renderer renderer = new ERenderer();
	
	/**
	 * 	To get the renderer of this UIView.
	 * @return
	 */
	public Renderer getRenderer() {
		return renderer;
	}
	
	private final Transform transform = new ETransform();

	/**
	 * 	To get the transform of this view.
	 * 	The transform include position,rotation and size of this view.
	 * @return
	 */
	public Transform getTransform() {
		return transform;
	}
	
	private List<UIBehaviour> behaviours = new ArrayList<>();
	
	/**
	 * 	To get all of behaviours in this view.
	 * @return
	 */
	public List<UIBehaviour> getBehaviours() {
		return behaviours;
	}

	/**
	 * 	To add a behaviour to the container which can store all of behaviours in this view.
	 * 	You can add only one behaviour belong to the same class,
	 * 		otherwise,the old one will be instead of the new one.
	 * 	All of those behaviours will be run in every frame.(:fps)
	 * @param behaviour
	 */
	public void addBehaviour(UIBehaviour behaviour) {
		behaviour.setUIView(this);
		behaviours.add(behaviour);
	}

	/**
	 * 	To remove the behaviours of specified class in this view.
	 * 	You can remove any of them in this view.
	 * 		, and the behaviour will not run in next frame.
	 */
	@SuppressWarnings("rawtypes")
	public void destoryBehaviour(Class className) {
		for(int i=0;i<behaviours.size();i++) {
			UIBehaviour behaviour = behaviours.get(i);
			if(behaviour.getClass().equals(className)) {
				behaviours.remove(i);
			}
		}
	}

	/**
	 * 	To get the behaviour of specified class in this view.
	 * 	You can remove any of them in this list, and the behaviour will not run in next frame.
	 * @return Behaviours of this view.
	 */
	@SuppressWarnings("rawtypes")
	public UIBehaviour getBehaviour(Class className) {
		for(int i=0;i<behaviours.size();i++) {
			UIBehaviour behaviour = behaviours.get(i);
			if(behaviour.getClass().equals(className)) {
				return behaviour;
			}
		}
		return null;
	}
	
	
	protected String text = "";

	/**
	 *  To get the text of this view to show.
	 * @return
	 */
	public String getText() {
		return text;
	}
	
	protected int textSize = 10;
	
	/**
	 *  To get the size of text in this view to show.
	 * @return
	 */
	public int getTextSize() {
		return textSize;
	}
	
	protected Color textColor = Color.BLACK;

	/**
	 *  To get the color of text in this view to show.
	 * @return
	 */
	public Color getTextColor() {
		return textColor;
	}
	
	private List<AbstractUIView> children = new Vector<>();

	/**
	 * 	To get all of child of this view.
	 * @return
	 */
	public List<AbstractUIView> getChildren() {
		return children;
	}
	
	private AbstractUIView root;
	
	/**
	 * 	To get the parent view.
	 * @return
	 */
	public AbstractUIView getRoot() {
		return root;
	}

	/**
	 * 	To set the parent view.
	 * 	Do not designate a NULL value for it , otherwise , the view will dead.
	 * 	Ps:can only set the value once.
	 * @param view
	 */
	public void setRoot(AbstractUIView view) {
		if(view==null || root!=null) {
			return;
		}
		this.root = view;
	}

	
	
	
	/**
	 * 	保存该view所在的场景
	 */
	private EScene scene ;
	public void setScene(EScene scene) {
		this.scene = scene;
	}
	
	public void setEnable(boolean enable) {
		if(scene==null) {
			return;
		}
		if(enable==true) {
			if(this.enable == false) {
				scene.enableView(this);
			}
		}else {
			if(this.enable == true) {
				scene.disableView(this);
			}
		}
	}
	
	private boolean enable = true;
	
	public void enable() {
		this.enable = true;
	}
	
	public void disable() {
		this.enable = false;
	}

	
	public boolean isEnable() {
		return this.enable;
	}
	
	
}
