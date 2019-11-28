package logico;

public abstract class Queso {
	protected float p_base;
	protected float p_unitario;
	protected String id;
	protected String tipo;

	public Queso(String id, float p_base, float p_unitario, String type) {
		super();
		this.id = id;
		this.p_base = p_base;
		this.p_unitario = p_unitario;
		this.tipo = type;
	}

	public float getP_base() {
		return p_base;
	}

	public void setP_base(float p_base) {
		this.p_base = p_base;
	}

	public float getP_unitario() {
		return p_unitario;
	}

	public void setP_unitario(float p_unitario) {
		this.p_unitario = p_unitario;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getId() {
		return id;
	}

	public float costoTotal() {
		return p_base + p_unitario * volumen();
	}

	protected abstract float volumen();
	
}