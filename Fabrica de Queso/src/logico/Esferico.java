package logico;

public class Esferico extends Queso {
	private float radio;
	
	public Esferico(String id,float p_base, float p_unitario, float radio, String tipo) {
		super(id,p_base, p_unitario, tipo);
		this.radio = radio;
	}
	public float getRadio() {
		return radio;
	}
	public void setRadio(float radio) {	
		this.radio = radio;
	}
	@Override
	protected float volumen() {
		float volumen=0;
		volumen = (float)(4* Math.PI * Math.pow(radio, 3))/3;
		return volumen;
	}
	
}
