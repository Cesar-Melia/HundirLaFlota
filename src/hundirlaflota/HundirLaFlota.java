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
    
    //Función para imprimir tablero.
    public static void imprimir_tablero(char tablero[][]){
        
        char columnas[] = new char[]{'0','1','2','3','4','5','6','7','8','9'};
        char filas [] = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        
        System.out.print(" ");
        for (int i = 0; i < columnas.length; i++){
            System.out.print(" "+columnas[i]);
        }
        System.out.print("\n");
        for (int i = 0; i < tablero.length; i++){
            System.out.print(filas[i]);
            for (int j = 0; j < tablero[i].length; j++){
                System.out.print(" "+tablero[i][j]);
            }
            System.out.print("\n");    
        }
        
    }
    
    public static void main(String[] args) {
        
        Scanner sc =new Scanner(System.in);
        
        char tableroEstandard[][] = new char[10][10];
        char tableroPersonalizado [][];
        char tableroOrdenador[][] = crear_tablero_vacio(tableroEstandard);
    }
    
}
