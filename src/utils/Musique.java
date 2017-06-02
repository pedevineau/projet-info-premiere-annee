package utils;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

import controleurs.Pandemia;
 
 
public class Musique extends Thread{
     
    AudioInputStream audioInputStream = null;
    SourceDataLine line;
    Pandemia pandemia;
    File fichier;
    
    public Musique(Pandemia pandemia){
    	this.pandemia = pandemia;
    }
     
    public void run(){
    	
    	while(true){
	        
	        try {
	        	 fichier = new File("musique/chore_1.wav");
	    		//	AudioFileFormat format = AudioSystem.getAudioFileFormat(fichier);
	        	 
	        	//le code ci-dessous vient du forum Openclassroom
		        audioInputStream = AudioSystem.getAudioInputStream(fichier);
		        AudioFormat audioFormat = audioInputStream.getFormat();
		        DataLine.Info info = new DataLine.Info(SourceDataLine.class,audioFormat);
	            line = (SourceDataLine) AudioSystem.getLine(info);
	            line.open(audioFormat);
	            line.start();
	            byte bytes[] = new byte[1024];
	            int bytesRead=0;
	            while(!pandemia.partieTerminee && (bytesRead = audioInputStream.read(bytes, 0, bytes.length)) != -1) {
	                 line.write(bytes, 0, bytesRead);
	            }
	        }catch(Exception e){
	        	e.printStackTrace();
	        }
        
    	}
    }
}