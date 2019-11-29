package server;

import java.io.*;
import java.net.*;
import java.util.*;
public class Server extends Thread{
	private static ArrayList<String> salida = new ArrayList<>();
	public String threadName;
	private Thread t;
	private boolean firstLine = true;
	private String titulo = "";
	
	public Server(String name) {
		threadName = name;
		System.out.println("Configurando el servidor: "+ threadName);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Configurando Almacenamiento de archivos");
		iniciar();
		System.out.println("Configuracion Almacenamiento de archivos completada");
	}
	public void start(){
		System.out.println("Creando sistemas de configuracion del servidor.");
		if (t == null) {
			t = new Thread(this, threadName);
			t.start();
		}
	}
	private void iniciar() {
		try {
		System.out.println("LocalHost" + InetAddress.getLocalHost().toString());
			
		} catch (UnknownHostException e) {
			System.out.println("Hubo un error al intentar acceder a la ip local del equipo" + e);
		}
		ServerSocket socket = null;
		try {
			socket = new ServerSocket(3000);
		} catch (IOException e) {
			System.out.println("El puerto: "+ socket+ "Esta ocupado.");
			System.exit(1);
		}
		while(true) {
			String entrada = "";
			try {
				Socket sock = socket.accept();
				BufferedReader lector = new BufferedReader(new InputStreamReader(sock.getInputStream()));
				String currentLine;
				while ((currentLine = lector.readLine()) != null) {
					entrada += currentLine + "\n";
					if(firstLine) {
						titulo = entrada.replace("ID: ","");
						titulo = titulo.replace("\n", "");
						firstLine = false;
					}
					//System.out.println(currentLine);
				}
				
				firstLine = true;
				salida.add(entrada);
				//dos.writeBytes("Recibido" + 'n');		
				if(sock.isInputShutdown() && sock.isOutputShutdown()) {
					//dos.close();
					sock.close();
					lector.close();	
				}
			} catch (Exception e) {
				System.out.println("Hubo un maldito error");
			}
		}
		
	}
	
	public void imprimir(){
		

		for(String factura : salida) {
			try {
				
				String directorio = new File(".").getCanonicalPath() + "\\src\\"+"factura-" + titulo +".txt";
				//System.out.println("\n"+factura);
				File texto = new File(directorio);
				FileWriter writer = new FileWriter(texto);
				BufferedWriter bw = new BufferedWriter(writer);
				bw.write(factura);
				bw.close();
			}catch(IOException e) {
				System.err.format("Error escribiendo el archivo" + e);
			}
		}	
	 }
}
