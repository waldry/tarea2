package server;

import java.net.*;
import java.io.*;
import java.util.*;

public class Flujo extends Thread {
	Socket nsfd;
	private static ArrayList<String> output = new ArrayList<>();
	DataInputStream lectura;
	DataOutputStream escritura;
	String title = "";
	private boolean firstline = true;
	public Flujo(Socket sfd) {
		nsfd = sfd;
		try {
			lectura =  new DataInputStream(new BufferedInputStream(sfd.getInputStream()));
			escritura = new DataOutputStream(new BufferedOutputStream(sfd.getOutputStream()));
		} catch (Exception e) {
			System.out.println("(Flujo)" + e);
		}
	}
	public void run() {
		String entrada = "";
		while (true) {
			try {
				BufferedReader lector = new BufferedReader(new InputStreamReader(nsfd.getInputStream()));
				String lineaActual;
				while ((lineaActual = lector.readLine()) != null) {
					entrada += lineaActual + "\n";
					if (firstline) {
						title = entrada.replace("ID: ", "");
						title = title.replace("\n", "");
						firstline = false;
					}
				}
				firstline = true;
				output.add(entrada);
				if (nsfd.isInputShutdown() && nsfd.isOutputShutdown()) {
					nsfd.close();
					lector.close();
				}
			} catch (Exception e) {
				System.out.println("Hubo un error en el backend." + e);
			}
		}
	}
	public void print() {
		for (String factura : output) {
			try {

				String src = new File(".").getCanonicalPath() + "\\src\\"+"factura-" + title +".txt";
				File txt = new File(src);
				FileWriter escritor = new FileWriter(txt);
				BufferedWriter lectorw = new BufferedWriter(escritor);
				lectorw.write(factura);
				lectorw.close();
			} catch (Exception e) {
				System.out.println("Hubo un error al tratar de escribir el archivo");
			}
		}
	}
}
