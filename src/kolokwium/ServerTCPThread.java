package Kolokwium;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.regex.Pattern;

public class ServerTCPThread implements Callable<String> {

	  private Socket socket = null;
	  private BufferedReader input = null;
	  private PrintWriter output = null;
          String niu=null;
          int wynik=0;

	  public ServerTCPThread(Socket socket) {
	    this.socket = socket;  
	  }
	    @Override
	    public String call() throws Exception {
                
              try {
                  //inputFromClient
                  input=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                  // outputToClient
                  output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

                  output.println("Podaj swój numer NIU");
                  niu=input.readLine();
                  System.out.println("NIU podłączonego uzytkownika: "+niu);

	        int liczbaPytan=3;
                int wynik=0;
	        Kolokwium kolokwium=new Kolokwium("bazaPytan.txt",liczbaPytan,niu);
	        for(int i=0;i<liczbaPytan;i++){
                    for(int k=0;k<6;k++){ //6- laczna ilosc lini w pliku dotyczaca 1 pytania(1-pytanie, 2-5- odpowiedzi, 6=poprawna odp.
                        String trescPytan=kolokwium.wyswietl(i,k);  
                        if(k!=5)
                        output.println(trescPytan);
                        else output.println("Udziel odpowiedzi");
                    }

                    int odpPoprawna=Integer.parseInt(kolokwium.wyswietl(i,5));
                    int nrOdpowiedzi=Integer.parseInt(input.readLine());
                    System.out.println("NIU uzytkownika: "+niu+" Pytanie "+i+" odp: "+nrOdpowiedzi);
                        kolokwium.wczytajOdp(i,nrOdpowiedzi,niu);  
                        if(odpPoprawna==nrOdpowiedzi){
                            wynik++;
                        }
                }
                output.println("Test zakonczony");
                output.println("Twoj wynik wynosi:  "+wynik);
                String wynikDoPliku="Wynik uzytkownika o NIU: "+niu+" wynosi  "+wynik;
               
                System.out.println(wynikDoPliku);
                kolokwium.zapiszWynik(wynikDoPliku);
                } catch (Exception exc) {
                    exc.printStackTrace();
                    try { socket.close(); } catch(Exception e) {}
                    return "";
                }

	        return "";
	    }

	}
