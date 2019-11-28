package logico;

import java.util.ArrayList;

public class Factura {
	private Cliente p;
	private ArrayList<Queso> item;
	private String cod_fact;
	
	public Factura(Cliente p, ArrayList<Queso> item, String cod_fact) {
		super();
		this.p = p;
		this.item = new ArrayList<Queso>();
		this.cod_fact = cod_fact;
	}
	public Cliente getP() {
		return p;
	}
	public void setP(Cliente p) {
		this.p = p;
	}
	public ArrayList<Queso> getItem() {
		return item;
	}
	public void setItem(ArrayList<Queso> item) {
		this.item = item;
	}
	public String getCod_fact() {
		return cod_fact;
	}
	public void setCod_fact(String cod_fact) {
		this.cod_fact = cod_fact;
	}
	public float facturar(ArrayList<Queso> cheese) {
		float total = 0;
		for (Queso queso : cheese) {
			total = total + queso.costoTotal();
		}
		return total;
	}
	public float facturarByEach(Queso item) {
		float total = 0;
			total = total + item.costoTotal();
		return total;
	}
}
