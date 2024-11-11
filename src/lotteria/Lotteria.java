/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lotteria;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author monica ciuchetti
 */
public class Lotteria {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    // Avvio gioco
    System.out.println("Inizio del gioco\n");
    int numero = 0;
    
    // Scelta del numero dei numeri da estrarre
    System.out.println("Definisci la grandezza della matrice nxn. Scegli il numero n: ");
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    try {
        numero = Integer.parseInt(br.readLine());
    } catch (IOException ex) {
        Logger.getLogger(Lotteria.class.getName()).log(Level.SEVERE, null, ex);
        System.err.println("Errore nell'inserimento del numero");
    }
    
    // Istanza ed avvio del thread Estrazione
    Estrazione e = new Estrazione(numero);
    
    // Istanza di alcuni thread Giocatore
    Giocatore g1 = new Giocatore(1, "Paolo", e);
    Giocatore g2 = new Giocatore(2, "Marco", e);
    Giocatore g3 = new Giocatore(3, "Laura", e);
    
    // Avvio del thread Estrazione
    e.start();
    
    try {
        e.join();
    } catch (InterruptedException ex) {
        Logger.getLogger(Lotteria.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    // Avvio e join dei giocatori uno alla volta
    g1.start();
    try {
        g1.join();
    } catch (InterruptedException ex) {
        Logger.getLogger(Lotteria.class.getName()).log(Level.SEVERE, null, ex);
    }

    g2.start();
    try {
        g2.join();
    } catch (InterruptedException ex) {
        Logger.getLogger(Lotteria.class.getName()).log(Level.SEVERE, null, ex);
    }

    g3.start();
    try {
        g3.join();
    } catch (InterruptedException ex) {
        Logger.getLogger(Lotteria.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    // Stampa dei vincitori
    e.stampaVincitori();
    
    // Comunicazione fine gioco
    System.out.println("\nFine del gioco");
    }
}