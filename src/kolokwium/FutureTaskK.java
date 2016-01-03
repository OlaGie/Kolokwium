/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kolokwium;

import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 *
 * @author Ola
 */
public class FutureTaskK<T> extends FutureTask<T>{

    Socket socket;
    public FutureTaskK(Callable clbl, Socket socket) {
        super(clbl);
        this.socket=socket;
    }
    public void done() {
        if(isCancelled()){
            System.out.println("Cancelled");
        }
        else{
            try {
                System.out.println("Zakończony wątek: "+Thread.currentThread().getName()+" "+get());
                //get();
                //System.out.println(":done(): "getName());
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } catch (ExecutionException ex) {
                ex.printStackTrace();
            }
        }

    }
}