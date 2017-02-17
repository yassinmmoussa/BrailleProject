package eecs2311.project;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class Player {
	
	public Player()
	{
		
	}
	
	/**
	 * Outputs sound based on the input string parameter.
	 * @param text - The text to be converted to audio.
	 */
	public void textToSpeech(String text)
	{
		String VOICENAME_kevin = "kevin";
		Voice voice;
		VoiceManager vm = VoiceManager.getInstance();
		voice = vm.getVoice(VOICENAME_kevin);
		voice.allocate();
		voice.speak(text);
	}
	
	public void textFileToSpeech(String fileName) throws Exception
	{
		try
		{
			Logger.getGlobal().info("Opening file");
			
			File file = new File(fileName);
			
			Logger.getGlobal().info(""+file.exists());
		
			Scanner s = new Scanner(new File(fileName));
			
			Logger.getGlobal().info("Read file");
			
			while (s.hasNextLine())
			{
				textToSpeech(s.nextLine());
			}
			s.close();
		}
		catch (IOException e)
		{
			throw new IOException("File can not be opened.");
		}
		
	}
}
