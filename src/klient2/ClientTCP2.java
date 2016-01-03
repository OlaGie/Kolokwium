package klient2;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;
 
public class ClientTCP2 {


	public static void main(String args[]) {
               //PrintWriter out = null;
              Socket socket = null;
              BufferedReader input = null;
              PrintWriter output = null;
              String niu;
               
               Scanner scanner=new Scanner(System.in);
		if (args.length < 2)
			System.out.println("Wprowadź adres serwera TCP oraz numer portu");
		else{
			int port = 0;
			try {
				port = Integer.parseInt(args[1]);
			} catch (NumberFormatException e) {
				System.err.println("Wprowadź poprawny numer portu: " + e);
				return;
			}
			try {
                            System.out.println("Klient podlaczony");
				socket = new Socket(InetAddress.getByName(args[0]), port);
                                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                                output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
                                
                                    String inputFromServer =input.readLine();
                                    System.out.println(inputFromServer);
                                    String dane=scanner.nextLine();
                                    output.println(dane);   
				String linia;
				while (true){
                                    for(int i=0;i<6;i++){
                                    inputFromServer =input.readLine();
                                    System.out.println(inputFromServer);                                    
                                }
                                    //inputFromServer =input.readLine();
                                    dane=scanner.nextLine();
                                    output.println(dane);                                
                                }

				//socket.close();
			} catch (Exception e) {
				System.err.println(e);
			}
		}
	}
}