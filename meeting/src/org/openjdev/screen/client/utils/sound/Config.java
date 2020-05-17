package org.openjdev.screen.client.utils.sound;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line;
import javax.sound.sampled.Mixer;



public class Config {
	public static final AudioFormat.Encoding ENCODING_PCM_SIGNED = AudioFormat.Encoding.PCM_SIGNED;
	//public static final int[] PCM_RATES = calcPCMRates();
	public static final int PCM_DEFAULT_RATE = 44100;
	public static final boolean PCM_DEFAULT_BIG_ENDIAN = false;
	public static final int[] PCM_SAMPLE_SIZES = { 16, 8 };
	public static final int PCM_DEFAULT_SAMPLE_SIZE = 16;
	public static final int[] PCM_CHANNELS = { 1, 2 };
	public static final int PCM_DEFAULT_CHANNELS = 1;
	public static final PcmFormat PCM_DEFAULT_FORMAT = new PcmFormat(PCM_DEFAULT_RATE, PCM_DEFAULT_SAMPLE_SIZE, PCM_DEFAULT_CHANNELS);

	public static void main(String args[]) {
		Config.displayMixerInfo();
	}
	
	
	public static AudioFormat getAudioFormat(){
		
		Mixer.Info [] mixersInfo = AudioSystem.getMixerInfo();
		Mixer.Info mixerInfo = mixersInfo[0];
		
		Mixer mixer = AudioSystem.getMixer(mixerInfo);
		Line.Info [] sourceLineInfo = mixer.getSourceLineInfo();
		
		Line.Info lineInfo = sourceLineInfo[0];
		
		if (lineInfo instanceof DataLine.Info){
		     DataLine.Info dataLineInfo = (DataLine.Info)lineInfo;

		     AudioFormat [] formats = dataLineInfo.getFormats();
		     return formats[0];
		}	
		
		return null;
	}
	
	
	public static void displayMixerInfo()
	{
	  Mixer.Info [] mixersInfo = AudioSystem.getMixerInfo();

	  for (Mixer.Info mixerInfo : mixersInfo)
	   {
	     System.out.println("Mixer: " + mixerInfo.getName());

	     Mixer mixer = AudioSystem.getMixer(mixerInfo);

	     Line.Info [] sourceLineInfo = mixer.getSourceLineInfo();
	     for (Line.Info info : sourceLineInfo)
	       showLineInfo(info);

	     Line.Info [] targetLineInfo = mixer.getTargetLineInfo();
	     for (Line.Info info : targetLineInfo)
	       showLineInfo(info);
	   }
	}


	private static void showLineInfo(final Line.Info lineInfo)
	{
	  System.out.println("  " + lineInfo.toString());

	  if (lineInfo instanceof DataLine.Info)
	   {
	     DataLine.Info dataLineInfo = (DataLine.Info)lineInfo;

	     AudioFormat [] formats = dataLineInfo.getFormats();
	     for (final AudioFormat format : formats)
	       System.out.println("    " + format.toString());
	   }
	}	
	
}
