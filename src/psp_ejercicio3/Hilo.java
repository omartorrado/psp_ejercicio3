/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psp_ejercicio3;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author otorradomiguez
 */
public class Hilo extends Thread{
    Hilo t;
    static int numHilos=0;
    Random rd=new Random();
    public Hilo(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println("El "+this.getName()+" acaba de comenzar");
        /*Si el numero de hilos es menos a los que queremos crear, crea un nuevo
        hilo y lo inicia,tras lo cual aumentamos el contador de hilos*/
        if(numHilos>=0&&numHilos<4){
            t=new Hilo("Hijo del "+this.getName());
            t.start();            
        }
        numHilos++;
        
        try {
            t.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException exc){
            /*Esta excepcion salta al llegar al ultimo hilo porque no se crea un 
            nuevo hilo t, por lo que salta null, pero como no tiene que esperar por
            nadie, continua la ejecucion sin problemas*/
        }
        
        for(int i=1;i<11;i++){
            try {
                sleep((rd.nextInt(6)+1)*100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("El "+this.getName()+" se está procesando(vuelta "+i+")");
        }
        
        System.out.println("El "+this.getName()+" ha terminado");
    }
    
}
