
public class Vertex {
	
	private double vX,vY,vZ;
	private int index;
	private static int publicIndex = 1;
	
	
	public Vertex(String[] rawInput) {
		
		this.vX = Double.parseDouble(rawInput[1]);
		this.vY = Double.parseDouble(rawInput[2]);
		this.vZ = Double.parseDouble(rawInput[3]);
		this.index = publicIndex;
		publicIndex++;
	}
	
	public double getvX() {
		return vX;
	}
	public void setvX(int vX) {
		this.vX = vX;
	}
	public double getvY() {
		return vY;
	}
	public void setvY(int vY) {
		this.vY = vY;
	}
	public double getvZ() {
		return vZ;
	}
	public void setvZ(int vZ) {
		this.vZ = vZ;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}

	@Override
	public String toString() {
		return "Vertex [vX=" + vX + ", vY=" + vY + ", vZ=" + vZ + ", index=" + index + "]";
	}
	
	
}
