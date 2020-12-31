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
    //Funcion para obtener un número aleatorio.
    public static int numAleatorio(int min, int max) {
        int numAle = (int) (Math.random() * (max - min + 1) + min);
        return numAle;
    }

    //Función para convertir letra de fila a numero entero.
    public static int num_fila(String fila) {

        int numeroFila = -1;
        if (fila.matches("[A-J]")) {

            switch (fila) {
                case "A":
                    numeroFila = 0;
                    break;
                case "B":
                    numeroFila = 1;
                    break;
                case "C":
                    numeroFila = 2;
                    break;
                case "D":
                    numeroFila = 3;
                    break;
                case "E":
                    numeroFila = 4;
                    break;
                case "F":
                    numeroFila = 5;
                    break;
                case "G":
                    numeroFila = 6;
                    break;
                case "H":
                    numeroFila = 7;
                    break;
                case "I":
                    numeroFila = 8;
                    break;
                case "J":
                    numeroFila = 9;
                    break;
            }
        }

        return numeroFila;
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
    public static boolean barcoOK(char tableroBarcos[][], String barco, int fila, int columna) {
        boolean libre = true;

        //Diferenciamos si es un portaaviones para comprobar la vertical u horizontal.
        if (barco.length() < 5) {
            for (int i = 0; i < barco.length(); i++) {
                if (tableroBarcos[fila][columna + i] == '-') {
                } else {
                    libre = false;
                }
            }
        } else {
            for (int i = 0; i < barco.length(); i++) {
                if (tableroBarcos[fila + i][columna] == '-') {
                } else {
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

        for (int i = 0; i < lancha; i++) {
            int fila = numAleatorio(0, tableroBarcos.length - 1);
            int columna = numAleatorio(0, tableroBarcos[1].length - 1);

            if (barcoOK(tableroBarcos, L, fila, columna) == true) {
                tableroBarcos[fila][columna] = 'L';
            } else {
                i--;
            }
        }
        for (int i = 0; i < buque; i++) {
            int fila = numAleatorio(0, tableroBarcos.length - 1);
            int columna = numAleatorio(0, tableroBarcos[1].length - 3);

            if (barcoOK(tableroBarcos, B, fila, columna) == true) {
                for (int j = 0; j < B.length(); j++) {
                    tableroBarcos[fila][columna + j] = 'B';
                }
            } else {
                i--;
            }
        }
        for (int i = 0; i < acorazado; i++) {
            int fila = numAleatorio(0, tableroBarcos.length - 1);
            int columna = numAleatorio(0, tableroBarcos[1].length - 4);

            if (barcoOK(tableroBarcos, Z, fila, columna) == true) {
                for (int j = 0; j < Z.length(); j++) {
                    tableroBarcos[fila][columna + j] = 'Z';
                }
            } else {
                i--;
            }
        }
        for (int i = 0; i < portaaviones; i++) {
            int fila = numAleatorio(0, tableroBarcos.length - 5);
            int columna = numAleatorio(0, tableroBarcos[1].length - 1);

            if (barcoOK(tableroBarcos, P, fila, columna) == true) {
                for (int j = 0; j < P.length(); j++) {
                    tableroBarcos[fila + j][columna] = 'P';
                }
            } else {
                i--;
            }
        }
        return tableroBarcos;
    }
    
    //Función para imprimir tablero. 
    //Añadimos una variable booleana para mostrar el tablero completo (ordenador) o parcial (usuario).
    public static void imprimir_tablero(char tablero[][], boolean verTodo) {

        char filas[] = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', '\u00d1', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        String columnas[] = new String[100];
        
        for (int i = 0 ; i < columnas.length; i++){
            columnas[i] = Integer.toString(i);
        }
        
        //Imprimimos el tablero.
        //Tenemos en cuenta si el número de columna es de menos de dos cifras agregamos un espacio extra.
        System.out.print(" ");
        for (int i = 0; i < tablero[1].length; i++) {
            if (Integer.parseInt(columnas[i]) < 10){
                System.out.print("  " + columnas[i]);
            }else{
                System.out.print(" " + columnas[i]);
            }    
        }
        System.out.print("\n");
        for (int i = 0; i < tablero.length; i++) {
            System.out.print(filas[i]);

            for (int j = 0; j < tablero[i].length; j++) {

                //Comprovamos si la opcion verTodo esta activada.
                //Si el numero de la columna tiene dos cáracteres agregamos un espacio extra para cuadrar la tabla.
                if (verTodo == true) {
                    System.out.print("  " + tablero[i][j]);
                } else {
                    if (Character.toString(tablero[i][j]).matches("[L,B,Z,P]")) {
                        System.out.print(" -");
                    }else {
                        System.out.print("  " + tablero[i][j]);
                    }
                }
            }
            System.out.print("\n");
        }      
    }

    //Funcion para efectuar disparo.
    public static char[][] disparo(char tablero[][], int fila, int columna) {

        if (tablero[fila][columna] == '-') {
            tablero[fila][columna] = 'A';
            System.out.println("Agua");
        } else {
            tablero[fila][columna] = 'X';
            System.out.println("Tocado");
        }
        return tablero;
    }

    //Funcion Realiza la pregunta y comprueba que sea una respuesta correcta.
    public static String pregunta(String preguntaNormal, String condicion, String preguntaError){
        Scanner entrada = new Scanner(System.in);
        
        System.out.println(preguntaNormal);
        String respuesta = entrada.nextLine();
                
        while (respuesta.matches(condicion) == false){
            System.out.println("Tu respuesta no es correcta, por favor introduce una opcion correcta:\n");
            System.out.println(preguntaError);
            respuesta = entrada.nextLine();
        }
        return respuesta;
    }

    public static void main(String[] args) {

        //Preguntamos el nivel y distribuimos los barcos correspondientes.
        char tablero[][];
        
        String nivel = pregunta("Elige el número correspondiente al nivel de dificultad con el que quieres jugar: \n"
                + " 1. Fácil\n 2. Medio\n 3. Difícil\n 4. Personalizado", "[1-4]"," 1. Fácil\n 2. Medio\n 3. Difícil\n 4. Personalizada");
        
        if (nivel.matches("[1-3]")){
            tablero = new char[10][10];
            tablero = crear_tablero_vacio(tablero);
            
            switch(nivel){
                case "1":
                    tablero = insertar_barcos(tablero, 5, 3, 1, 1);
                    break;
                case "2":
                    tablero = insertar_barcos(tablero, 2, 1, 1, 1);
                    break;
                case "3":
                    tablero = insertar_barcos(tablero, 1, 1, 0, 0);
                    break;
            }    
        imprimir_tablero(tablero, true);/////////////////////////////////////////////////////////////////Cambiar a false

        //Preguntamos todos los parámetros necesarios para crear el nivel personalizado.        
        }else        
            if(nivel.equals("4")){
                
                //Creamos el tablero con los datos introducidos por consola.
                int filas = Integer.parseInt(pregunta("¿Cuántas filas quieres que tenga el tablero? \n(Máximo 27)","(100|[1-9]?[0-9])","Escribe un número de filas entre 1 y 100:"));
                int columnas = Integer.parseInt(pregunta("¿Cuántas columnas quieres que tenga el tablero? \n(Máximo 100)","(100|[1-9]?[0-9])","Escribe un número de columnas entre 1 y 99:"));     
                
                tablero = new char[filas][columnas];
                tablero = crear_tablero_vacio(tablero);
                
                imprimir_tablero(tablero, true);////////////////////////////////////////////////////Cambiar a false
               
                int L;
                int B;
                int A;
                int P;        
                int sumaBarcos;
                
                L = Integer.parseInt(pregunta("¿Cuántas lanchas quieres que aparezcan?","(100|[1-9]?[0-9])","Escribe un número de lanchas entre 0 y 100:"));
                B = Integer.parseInt(pregunta("¿Cuántos buques quieres que aparezcan?","(7[0-5]|[1-6][0-9]|[0-9])","Escribe un número de buques entre 0 y 75:"));
                A = Integer.parseInt(pregunta("¿Cuántos acorazados quieres que aparezcan?","(50|[1-4][0-9]|[0-9])","Escribe un número de acorazados entre 0 y 50:"));
                P = Integer.parseInt(pregunta("¿Cuántos portaaviones quieres que aparezcan?","(50|[1-4][0-9]|[0-9])","Escribe un número de portaaviones entre 0 y 50:"));
                sumaBarcos = L+B+A+P;
                
                //Mensaje de error y bucle para introducir un número correcto.
                while (sumaBarcos > filas * columnas){
                    System.out.print("La suma total de los barcos que has introducido ocupa más que el número de casillas disponibles.\n"
                            + "Porfavor, introduce de nuevo la cantidad de barcos:");
                    
                    L = Integer.parseInt(pregunta("¿Cuántas lanchas quieres que aparezcan?","(100|[1-9]?[0-9])","Escribe un número de lanchas entre 0 y 100:"));
                    B = Integer.parseInt(pregunta("¿Cuántos buques quieres que aparezcan?","(7[0-5]|[1-6][0-9]|[0-9])","Escribe un número de buques entre 0 y 75:"));
                    A = Integer.parseInt(pregunta("¿Cuántos acorazados quieres que aparezcan?","(50|[1-4][0-9]|[0-9])","Escribe un número de acorazados entre 0 y 50:"));
                    P = Integer.parseInt(pregunta("¿Cuántos portaaviones quieres que aparezcan?","(50|[1-4][0-9]|[0-9])","Escribe un número de portaaviones entre 0 y 50:"));
                    sumaBarcos = L+B+A+P;
                }
                
                
                
                insertar_barcos(tablero, L, B, A, P);
                
                imprimir_tablero(tablero, true);////////////////////////////////////////////////////Cambiar a false
            }
    }
        //tablero = disparo(tablero, 3, 9);
        //imprimir_tablero(tablero, false);

        //System.out.println();
        //Integer.parseInt(pregunta(""));
}
 
