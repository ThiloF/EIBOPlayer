package business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Hilfsklasse für Minim. Wird benötigt, um Dateinamen als Streams darzustellen.
 * 
 * @author tfalk001, smerb001, ladam001, fkoen001
 *
 */
public class MinimHelper {

	public String sketchPath(String filename) {
		return "";
	}

	public InputStream createInput(String filename) {
		try {
			return new FileInputStream(new File(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
