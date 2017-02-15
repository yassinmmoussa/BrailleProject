package eecs2311.project;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

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
}
