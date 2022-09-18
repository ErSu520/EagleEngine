package com.flash.EE.compnent.audio;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

public class AudioManager {

	
	public static void load(EAudioClip clip) {
		//首先关闭上次的流
		close(clip.getAudioStream());
		
		//开始重新载入流
		try {
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(clip.getFile());
	        
	        AudioFormat format = audioStream.getFormat();
	        if (format.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
	            format = new AudioFormat(
	              AudioFormat.Encoding.PCM_SIGNED,
	              format.getSampleRate(),
	              16,
	              format.getChannels(),
	              format.getChannels() * 2,
	              format.getSampleRate(),
	               false);        
	            audioStream = AudioSystem.getAudioInputStream(format, audioStream);
	        }
	        
	        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioStream.getFormat(), AudioSystem.NOT_SPECIFIED);
	        SourceDataLine dataLine = (SourceDataLine) AudioSystem.getLine(info);
	        dataLine.open(audioStream.getFormat(),dataLine.getBufferSize());
	        dataLine.start();
	        
	        clip.setAudioStream(audioStream);
	        clip.setDataLineine(dataLine);
		}catch (Exception e) {
			
		}
	} 
	
	public static void close(AudioInputStream audioStream) {
		if(audioStream != null) {
			try {
			    audioStream.close();
			}catch (Exception e) {}
		}
	}
	

}
