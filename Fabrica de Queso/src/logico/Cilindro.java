package logico;

public class Cilindro extends Queso{
	
	
	protected float largo;
	protected float radio;
	
	public Cilindro(String id, float p_base, float p_unitario, float radio, float largo, String tipo) {
		super(id,p_base, p_unitario, tipo);
		this.largo = largo;
		this.radio = radio;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected float volumen() {
		float volumen = 0;
		volumen = area() * largo;
		return volumen;
	}
	private float area() {
		float aux = 0;
		aux = (float) (Math.PI*Math.pow(radio, 2));
		return aux;
	}
}
