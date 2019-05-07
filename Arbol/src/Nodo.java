
public class Nodo<T> {
	
	private T elemento;
	private Nodo<T> izquierda;
	private Nodo<T> derecha;
	private int altura;
	private double x,y;
	//private Text text;
	
	public Nodo(T elemento) {
		this.elemento=elemento;
	}

	
	public double getX(){
		return x;
	}

	public double getY(){
		return y;
	}

	public void setX(double x){
		this.x = x;
	}

	public void setY(double y){
		this.y = y;
	}

	public T getElemento() {
		return elemento;
	}
	public void setElemento(T elemento) {
		this.elemento = elemento;
	}
	public Nodo<T> getIzquierda() {
		return izquierda;
	}
	public void setIzquierda(Nodo<T> izquierda) {
		this.izquierda = izquierda;
	}
	public Nodo<T> getDerecha() {
		return derecha;
	}
	public void setDerecha(Nodo<T> derecha) {
		this.derecha = derecha;
	}
	public int getAltura() {
		return altura;
	}
	public void setAltura(int altura) {
		this.altura = altura;
	}
	
	
	
	

}
