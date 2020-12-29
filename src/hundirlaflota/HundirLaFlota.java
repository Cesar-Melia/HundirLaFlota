/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hundirlaflota;

/**
 *
 * @author cesar_melia
 */
import java.util.Scanner;

public class HundirLaFlota {

    /**
     * @param args the command line arguments
     */
    
    //funcion para obtener un número aleatorio.
    public static int numAleatorio(int min, int max) {
        int numAle = (int) (Math.random() * (max - min + 1) + min);
        return numAle;
    }
    
    //Función para crear un tablero vacio.
    public static char[][] crear_tablero_vacio(char nuevoTablero[][]) {

        for (int i = 0; i < nuevoTablero.length; i++) {
            for (int j = 0; j < nuevoTablero[i].length; j++) {
                nuevoTablero[i][j] = '-';
            }
        }
        return nuevoTablero;
    }
    
    //Función para comprobar si estan libres las casillas para el barco.
    public static boolean barcoOK (char tableroBarcos[][],String barco,int fila, int columna){
        boolean libre = true;
        
        //Diferenciamos si es un portaaviones para comprobar la vertical u horizontal.
        if (barco.length() < 5){
            for(int i = 0; i < barco.length();i++){
                if (tableroBarcos[fila][columna+i] == '-'){}
                else{ 
                    libre = false;
                }
            } 
        }
        else{
            for(int i = 0; i < barco.length();i++){
                if (tableroBarcos[fila+i][columna] == '-'){}
                else{ 
                    libre = false;
                }
            }      
        }
        return libre;
    }

    //Funcion para insertar barcos de forma aleatoria.
    public static char[][] insertar_barcos(char tableroBarcos[][], int lancha, int buque, int acorazado, int portaaviones) {
        
        //Definimos el tamaño de cada barco y creamos una variable para confirmar su colocación.
        String L = "L", B = "BBB", Z = "ZZZZ", P = "PPPPP";
        boolean test = false;
       
        for (int i =0;i < lancha;i++){
            int fila = numAleatorio(0,tableroBarcos.length-1);       
            int columna = numAleatorio(0,tableroBarcos[1].length-1);
                
            if (barcoOK(tableroBarcos,L,fila,columna) == true){
                tableroBarcos[fila][columna] = 'L';
            }
            else{
                i--;
            }
        }
        for (int i =0;i < buque;i++){
            int fila = numAleatorio(0,tableroBarcos.length-1);       
            int columna = numAleatorio(0,tableroBarcos[1].length-3);
                
            if (barcoOK(tableroBarcos,B,fila,columna) == true){
                for(int j = 0; j < B.length(); j++){
                    tableroBarcos[fila][columna+j] = 'B';
                }
            }
            else{
                i--;
            }
        }
        for (int i =0;i < acorazado;i++){
            int fila = numAleatorio(0,tableroBarcos.length-1);       
            int columna = numAleatorio(0,tableroBarcos[1].length-4);
                
            if (barcoOK(tableroBarcos,Z,fila,columna) == true){
                for(int j = 0; j < Z.length(); j++){
                    tableroBarcos[fila][columna+j] = 'Z';
                }
            }
            else{
                i--;
            }
        }
        for (int i =0;i < portaaviones;i++){
            int fila = numAleatorio(0,tableroBarcos.length-5);       
            int columna = numAleatorio(0,tableroBarcos[1].length-1);
                
            if (barcoOK(tableroBarcos,P,fila,columna) == true){
                for(int j = 0; j < P.length(); j++){
                    tableroBarcos[fila+j][columna] = 'P';
                }
            }
            else{
                i--;
            }
        }
        return tableroBarcos;
    }

    //Función para imprimir tablero.
    public static void imprimir_tablero(char tablero[][]) {

        char columnas[] = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        char filas[] = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};

        System.out.print(" ");
        for (int i = 0; i < columnas.length; i++) {
            System.out.print(" " + columnas[i]);
        }
        System.out.print("\n");
        for (int i = 0; i < tablero.length; i++) {
            System.out.print(filas[i]);
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.print(" " + tablero[i][j]);
            }
            System.out.print("\n");
        }

    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        char tableroEstandard[][] = new char[10][10];
        char tableroPersonalizado[][];
        char tableroOrdenador[][] = crear_tablero_vacio(tableroEstandard);
        
        //imprimir_tablero(tableroOrdenador);
        
        tableroOrdenador = insertar_barcos(tableroOrdenador, 4, 3, 2, 2);
        
        imprimir_tablero(tableroOrdenador);

        //System.out.println();

    }

}
