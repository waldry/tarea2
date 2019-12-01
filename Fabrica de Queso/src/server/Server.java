package server;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.*;

import logico.Empresa;
public class Server extends Thread{
	private static DataInputStream entrada;
	private static DataOutputStream salida;
	private static DataInputStream entradaF;
	private static DataOutputStream salidaF;
	private static Socket soc;
	private static Socket soc2;
	
	public static void main(String[] args) {
		try {
			int port = 3002;
			ServerSocket soc = new ServerSocket(port);
			while(true) {
				Setsockets(soc.accept());
				System.out.println("Esto me tiene cansado.");
				String name = entrada.readUTF();
				System.out.println(name);
				File ficheroSalida = new File("Facts/"+name+".txt");
				receiveFile(ficheroSalida);
				System.out.println("Archivo" +name+"Guardado correctamente.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static  void Setsockets (Socket soc) throws IOException {
		soc = soc;
		soc2 = soc;
		entrada = new DataInputStream(soc.getInputStream());
		salida = new DataOutputStream(soc.getOutputStream());
		entradaF = new DataInputStream(soc2.getInputStream());
		salidaF = new DataOutputStream(soc2.getOutputStream());
	}
	public static void receiveFile(File file) throws IOException {
		FileOutputStream fileOut = new FileOutputStream(file);
//  convierte el fichero en bytes.
		byte[] buf = new byte[Short.MAX_VALUE];
		int bytesSent;        
		while( (bytesSent = entrada.readShort()) != -1 ) {
			entrada.readFully(buf,0,bytesSent);
			fileOut.write(buf,0,bytesSent);
		}
		fileOut.close();


	} 
}
