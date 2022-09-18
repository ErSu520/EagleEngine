package com.flash.EE.compnent.audio;


import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.SourceDataLine;


public class EAudioClip implements AudioClip{
	
	//引擎内调用的方法
	
	private boolean loop = false;
	public boolean isLoop() {
		return loop;
	}
	public void setLoop(boolean loop) {
		this.loop = loop;
	}

	private boolean alive = false;
	public boolean isAlive() {
		return alive;
	}
	
	private float volume = 100f;
	public void setVolume(float volume) {
		this.volume = volume;
	}
	
	public synchronized void start() {
		load = true;
		stop = false;
		if(alive==false) {
			AudioExecutorWithThreadPool.submit(this);	
			alive = true;
		}
	}
	
	private File file;
	
	@Override 
	public String getFilePath() {
		return file.getPath();
	}
	
	public EAudioClip(String filePath) {
		file = new File(filePath);
	}
	
	private boolean stop = false;
	public synchronized void stop() {
		this.stop = true;
	}
	
	//以下是音频处理器调用的方法
	
	public boolean isStop() {
		return stop;
	}
	
	public void dead() {
		alive = false;
	}
	
	public File getFile() {
		return file;
	}
	
	private boolean load = true;
	public boolean isLoad() {
		return load;
	}
	public void setLoad(boolean load) {
		this.load = load;
	}

	private AudioInputStream audioStream = null;
	public AudioInputStream getAudioStream() {
		return audioStream;
	}
	public void setAudioStream(AudioInputStream audioStream) {
		this.audioStream =  audioStream;
	}

	private SourceDataLine dataLineine = null;
	public SourceDataLine getDataLineine() {
		return dataLineine;
	}
	public void setDataLineine(SourceDataLine dataLineine) {
		this.dataLineine =  dataLineine;
	}
	
	private byte[] buf = new byte[1024];
	public byte[] getBuff() {
		return buf;
	}
	
	public float getVolume() {
		return volume;
	}
	
}






