import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {
	
	public static List<String> listaString(String s) {
		List<String> input = new ArrayList<String>();
		String name = "C:\\Users\\Andr�s\\workspace\\ObjVisualizer\\objects\\" + s + ".obj";
		Path path = Paths.get(name);
		try {
			input = Files.readAllLines(path);
		} catch (Exception e) {
			System.err.println("No se ha encontrado el archivo con ruta " + name);
		}
		return input;
	}
}
