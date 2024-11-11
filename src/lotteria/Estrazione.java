/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lotteria;

import java.util.Random;

/**
 *
 * @author monica ciuchetti
 */
public class Estrazione extends Thread {
    // Attributi
    int n;
    private int numeri[][];
    private int vincitori[];
    private boolean verifica = false;
    private Random random = new Random();
    private int contawin = 0;
    
    //temporaneo
    // int numero = 3;
    /**
     * 
     * Metodo costruttore
     * @param n
     */   
    public Estrazione(int n) {
        // popolamento matrice numeri estratti
        this.n = n;
        this.numeri = new int[n][n];
        for (int i=0; i<n; i++){
            for (int j=0; j<n; j++){
                numeri[i][j] = random.nextInt(0, 100);
            }
        }
        // inizializzazione array vincitori
        this.vincitori = new int[3];
       }

    /**
    * 
    * Metodo per visualizzare la matrice dei numeri estratti
    */
    public void stampaMatrice() {
        // stampa matrice dei numeri estratti
        System.out.println("Matrice nxn dei numeri estratti: ");
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                System.out.print(String.format("%4d", numeri[i][j]) + " ");  // Stampa ogni numero con uno spazio fisso
            }
        System.out.println();  // Va a capo alla fine di ogni riga
        }
    }

    /**
    * 
    * Metodo per verificare il numero scelto dal giocatore e determinare i vincitori
    */
    public void verifica(int numero, int idGiocatore) {
        for (int i=0; i<n; i++){
            for (int j=0; j<n; j++){
                if (numero==numeri[i][j]){
                    if (contawin<3){
                        vincitori[contawin] = idGiocatore;
                        contawin++;
                        System.out.println("Bravo, hai indovinato!");
                    }
                verifica=true;
                }
            }
        }
        if (verifica==false){
            System.out.println("Peccato, non hai vinto");
        }
    }
    
    /**
    * 
    * Metodo per visualizzare i vincitori dell'estrazione
    */
    public void stampaVincitori() {
        for (int i=0; i<3; i++){
              if(vincitori[i] !=0){
                  System.out.println("Complimenti giocatore "+ vincitori[i]+ " hai vinto!");
                }
            }
        }

    /**
    * 
    * Metodo per eseguire il thread
    */
    public void run() {
        stampaMatrice();
    }
}