
public class Camera {
	
	//La posici�n de la c�mara
	private double x,y,z;
	//La inclinaci�n de la c�mara en cualquier sentido.
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
