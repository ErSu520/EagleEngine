package com.flash.EE.compnent.audio;


import com.flash.EE.util.ThreadPool;

public class AudioExecutorWithThreadPool implements Runnable{
	
	public static void submit(EAudioClip clip) {
		ThreadPool.getPool().execute(new AudioExecutorWithThreadPool(clip));
	}
	
	
	public AudioExecutorWithThreadPool(EAudioClip clip) {
		this.clip = clip;
	}
	
	
	private EAudioClip clip = null;
	
	public void run() {
		//执行音效数据流动代码
		while(true) {
			//判断音效播放是否需要停止
			if(clip.isStop()) {
				AudioManager.close(clip.getAudioStream());
		    	clip.dead();
		    	clip = null;
		    	break;
			}
			//判断音频是否需要重新加载
			if(clip.isLoad()) {
				AudioManager.load(clip);
				clip.setLoad(false);
			}
				
			try {
			    int numRead = 0;
			    //读取音频输入流的二进制数据
			    if ((numRead = clip.getAudioStream().read(clip.getBuff(), 0, clip.getBuff().length)) >= 0) {
			       int offset = 0;
			       while (offset < numRead) {
			    	   //将二进制音频数据写入系统输出流
			    	   offset += clip.getDataLineine().write(clip.getBuff(), offset, numRead-offset);
			       }
			    }else {
			    	//音频播放结束，关闭音效片段
			    	AudioManager.close(clip.getAudioStream());
			    	clip.dead();
			    	clip = null;
			    	break;
			    }
			    
			}catch (Exception e) {}
		}
		
	} 
	

}
