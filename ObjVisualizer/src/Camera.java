
public class Camera {
	
	//La posición de la cámara
	private double x,y,z;
	//La inclinación de la cámara en cualquier sentido.
	//NO IMPLEMENTADO

	public Camera(int x, int y, int z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	@Override
	public String toString() {
		return "Camera [x=" + x + ", y=" + y + ", z=" + z + "]";
	}
	
	
}
