package logico;

import java.util.ArrayList;

public class Empresa {
	private static Empresa emp;
	private ArrayList<Queso> cheese;
	private ArrayList<Factura> fact;
	private ArrayList<Cliente> user;
	private static int gen_factura = 1;
	private static int gen_cheese = 1;
	private static int gen_user = 1;
	
	public Empresa() {
		super();
		this.cheese = new ArrayList<Queso>();
		this.fact = new ArrayList<Factura>();
		this.user = new ArrayList<Cliente>();
	}
	
	public ArrayList<Queso> getCheese() {
		return cheese;
	}
	public void setCheese(ArrayList<Queso> cheese) {
		this.cheese = cheese;
	}
	public ArrayList<Factura> getFact() {
		return fact;
	}
	public void setFact(ArrayList<Factura> fact) {
		this.fact = fact;
	}
	public ArrayList<Cliente> getUser() {
		return user;
	}
	public void setUser(ArrayList<Cliente> user) {
		this.user = user;
	}

	public int getGen_factura() {
		return gen_factura;
	}

	public void setGen_factura(int gen_factura) {
		this.gen_factura = gen_factura;
	}

	public int getGen_user() {
		return gen_user;
	}

	public void setGen_user(int gen_user) {
		this.gen_user = gen_user;
	}

	public int getGen_cheese() {
		return gen_cheese;
	}

	public void setGen_cheese(int gen_cheese) {
		this.gen_cheese = gen_cheese;
	}

	public void addQueso(Queso cheeseToAdd) {
		cheese.add(cheeseToAdd);
		setGen_cheese(getGen_cheese() + 1);
	}
	public void deleteQueso(String id) {
		for (int j = 0; j < cheese.size(); j++) {
			if (cheese.get(j).getId().equalsIgnoreCase(id)) {
				cheese.remove(j);
			}
		}
		setGen_cheese(getGen_cheese()  - 1);
	}
	public void addFactura(Factura factToAdd) {
		fact.add(factToAdd);
		setGen_factura(getGen_factura() + 1);
	}
	public void addCliente(Cliente clienteToAdd) {
		user.add(clienteToAdd);
		setGen_user(getGen_user() + 1);
	}
	public static Empresa getInstance() {
		if (emp ==null) {
			emp = new Empresa();
		}
		return emp;
		
	}
	public float valorTotal(ArrayList<Queso> X) {
        float total = 0;
        for (Queso queso : X) {
            total+=queso.costoTotal();
        }
        return total;
    }
	public float precioByFactura(String code) {
        float fact = 0;
        Factura aux = buscarFacturaByCode(code);
        if(aux!=null) {
            fact = aux.facturar(aux.getItem());
        }
        return fact;
    }
	private Factura buscarFacturaByCode(String code) {
		for (Factura facturas : fact) {
			if (facturas.getCod_fact().equalsIgnoreCase(code)) {
				return facturas;
			}
		}
		return null;
	}
	public void quesoByTipo() {
        int cant = 0, cantEsf = 0, cantC = 0, cantCH = 0;
        for (Queso quesoAux: cheese) {
            if(quesoAux instanceof Esferico) {
                System.out.println("El queso "+quesoAux.getId()+" es Esférico.");
                cantEsf+=1;
            }
            if(quesoAux instanceof Cilindro && !(quesoAux instanceof Cilindro)) {
                System.out.println("El queso "+quesoAux.getId()+" es Cilíndrico.");
                cantC+=1;
            }
            if(quesoAux instanceof CilindroHueco) {
                System.out.println("El queso "+quesoAux.getId()+" es Cilíndrico Hueco.");
                cantCH+=1;
            }
            cant=cantEsf+cantC+cantCH;
        }
        System.out.println("La cantidad de quesos esfericos es: "+cantEsf);
        System.out.println("La cantidad de quesos cilindricos es: "+cantC);
        System.out.println("La cantidad de quesos cilindricos huecos es: "+cantCH);
        System.out.println("La cantidad total de quesos es: "+cant);
    }
	public float costoMayorVolumen(Factura fact) {
        float max = 0;
        for (Queso quesoAux : cheese) {
            if(quesoAux instanceof Esferico) {
                if(max<=quesoAux.volumen()) {
                    max=quesoAux.volumen();
                }
            }
        }
        return max;
    }
}
