package com.flash.EE.compnent.audio;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@Deprecated
public class AudioExecutor extends Thread{
	
	private static volatile List<AudioExecutor> executors = new ArrayList<>();
	
	private static volatile List<EAudioClip>  audios = new Vector<>();
	
	static {
		AudioExecutor executor = new AudioExecutor();
		executors.add(executor);
		executor.start();
		
		executor = new AudioExecutor();
		executors.add(executor);
		executor.start();
		
		executor = new AudioExecutor();
		executors.add(executor);
		executor.start();
	}
	
	public synchronized static EAudioClip get() {
		if(!audios.isEmpty()) {
			return audios.remove(0);
		}
		return null;
	}
	
	public synchronized static void remove(EAudioClip clip) {
		audios.remove(clip);
	}
	
	public synchronized static void submit(EAudioClip clip) {
		audios.add(clip);
		if(audios.size()>1) {
			AudioExecutor executor = new AudioExecutor();
			executors.add(executor);
			executor.start();
		}
	}
	
	private EAudioClip clip = null;
	
	public void run() {
		
		while(true) {
			
			if(clip==null) {
				clip = get();
				if(clip==null) {
					try {Thread.sleep(100);}catch (Exception e) {}
					continue;
				}
			}
			
			if(clip.isStop()) {
				AudioManager.close(clip.getAudioStream());
		    	clip.dead();
		    	clip = null;
		    	continue;
			}
			
			if(clip.isLoad()) {
				AudioManager.load(clip);
				clip.setLoad(false);
			}
				
			try {
			    int numRead = 0;
			    if ((numRead = clip.getAudioStream().read(clip.getBuff(), 0, clip.getBuff().length)) >= 0) {
			       int offset = 0;
			       while (offset < numRead) {
			    	   offset += clip.getDataLineine().write(clip.getBuff(), offset, numRead-offset);
			       }
			    }else {
			    	AudioManager.close(clip.getAudioStream());
			    	clip.dead();
			    	clip = null;
			    }
			    
			}catch (Exception e) {}
		}
		
	}
	

}
