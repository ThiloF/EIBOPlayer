package Business;
import java.text.DateFormat;
import java.util.*;
import java.io.*;
import java.lang.reflect.Array;
import java.nio.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import GUI.PlayerGui;


public class Control {

	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MP3Player mp = new MP3Player();
		
		
	
		//playPlayList();
		PlayListManager plm = new PlayListManager();
		plm.print();
		PlayerGui pg = new PlayerGui(mp);
		
	}

	
	
	
	

		

}
