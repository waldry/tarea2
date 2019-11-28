package logico;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		Queso queso1 = new Esferico("01",70,25,7,"esferico");
		Queso queso2 = new Cilindro("c21", 100,20,5,3,"cilindro");
		Queso queso3 = new CilindroHueco("ch77", 50, 17, 2, 4 ,2,"cilindrohueco");
		Queso queso4 = new Esferico("02",70,25,9,"esferico");
		Cliente user = new Cliente("Waldry","Padre lasasas","805056", "01");
		ArrayList<Queso> quesos = new ArrayList<Queso>();
		quesos.add(queso1);
		quesos.add(queso2);
		Factura fav = new Factura(user, quesos, "01");
		
		fav.getItem().add(queso1);
//		fav.getItem().add(queso4);
//		System.out.println(fav.getItem().get(0).getTipo());
//		System.out.println(fav.getItem().get(1).getTipo());
		Empresa.getInstance().addQueso(queso1);
		Empresa.getInstance().addQueso(queso2);
		Empresa.getInstance().addQueso(queso3);
		Empresa.getInstance().addQueso(queso4);
		Empresa.getInstance().addFactura(fav);
		System.out.println(Empresa.getInstance().costoMayorVolumen(fav));
		
//		main.quesoByTipo();
//		System.out.println(main.getFact().get(0).getItem().get(0).getP_unitario());
		
//		System.out.println("el costo de la factura es : "+main.precioByFactura(fav.getCod_fact()));
//		float y = 0;
//		y = main.valorTotal(quesos);
	}

}	
