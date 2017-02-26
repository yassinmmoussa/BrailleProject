package eecs2311.project;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import java.io.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import sun.audio.*;


public class Player {
	
	public Player()
	{
		
	}
	
	public void freeTTS(String text)
	{
		String VOICENAME_kevin = "kevin";
		Voice voice;
		VoiceManager vm = VoiceManager.getInstance();
		voice = vm.getVoice(VOICENAME_kevin);
		voice.allocate();
		voice.speak(text);
	}
	
	public void playAudio(String filePath) throws UnsupportedAudioFileException, IOException
	{
		InputStream in;
		in = new FileInputStream(filePath);
		AudioInputStream audio = AudioSystem.getAudioInputStream(in);
		
		
		
		
	}
}
