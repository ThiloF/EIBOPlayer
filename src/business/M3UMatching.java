package business;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class M3UMatching {

	public static void main(String[] args) {
		
		String text = "#EXTINF:221,Queen - Bohemian Rhapsody";
		
		Matcher m = Pattern.compile("^#EXTINF:([0-9]*),(.*?)$").matcher(text);
		
		m.find();
		System.out.println(m.group(1));
		System.out.println(m.group(2));
		
	}

}
