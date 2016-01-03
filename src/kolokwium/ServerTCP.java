package Kolokwium;

import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerTCP {
	public static void main(String args[]) {
		if (args.length == 0)
			System.out.println("Wprowadz numer portu, na ktorym serwer bedzie oczekiwac na klientow");
		else {
			int port = 0;
			try {
				port = Integer.parseInt(args[0]);
			} catch (NumberFormatException e) {
				System.err.println("Wprowadz poprawny numer portu: " + e);
				return;
			}
			try {
				// tworzymy socket
				ServerSocket serverSocket = new ServerSocket(port);
				System.out.println("Serwer oczekuj na porcie: "+args[0]);
                                int threadPool=100;
				while (true) {
				       Socket socket = serverSocket.accept();
				       System.out.println("Connection established");
				       
				       ExecutorService exec = Executors.newFixedThreadPool(threadPool);
				      
				       exec.execute(new FutureTaskK((new ServerTCPThread(socket)),socket));
				        
				       //exec.shutdown();
				       
				}
                                //zamkni�cie strumieni i po��czenia
			} catch (Exception e) {
				System.err.println(e);
			}
		}
	}
}