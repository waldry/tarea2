package logico;

public class CilindroHueco extends Cilindro{
	

	private float radio_int;

	public CilindroHueco(String id,float p_base, float p_unitario,float longitud, float radio_ext,float radio_int, String tipo) {
		super(id,p_base, p_unitario, longitud, radio_int,tipo);
		this.radio_int = radio_int;
		
	}
	@Override
	protected float volumen() {
		float volumen = 0;
		volumen = area() * largo;
		return volumen;
	}

	private float area() {
		float aux = 0;
		if (radio > radio_int) {
			aux = (float) (Math.PI*(Math.pow(radio, 2)-Math.pow(radio_int, 2)));
		}
		return aux;
	}
}
