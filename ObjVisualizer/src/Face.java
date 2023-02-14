import java.util.ArrayList;
import java.util.List;

/**
 * Las líneas de caras van marcadas por el caracter inicial f.
 * Tienen el siguiente formato:
 * EJEMPLO: f 6/4/1 3/5/3 7/6/5
 * vertex_index/texture_index/normal_index
 * @author Andrés
 *
 */
public class Face {
	
	private List<Vertex> vertices = new ArrayList();
	
	

	public Face(String[] rawInput) {
		for (int i = 1; i<rawInput.length; i++) {
			//Con esto cogemos el primer caracter de cada posición del array.
			String s = "" + rawInput[i].charAt(0);
			int indexVertex = Integer.parseInt(s);
			vertices.add(Main.vertices.get(indexVertex));
		}
	}



	public List<Vertex> getVertices() {
		return vertices;
	}



	public void setVertices(List<Vertex> vertices) {
		this.vertices = vertices;
	}



	@Override
	public String toString() {
		return "Face [vertices=" + vertices + "]";
	}
	
	
	
}
