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
    /*public static int num_fila(String fila) {

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
    }*/

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

        //Colocamos los barcos empezando por los más grandes para evitar fallos en la distribución.
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
        for (int i = 0; i < lancha; i++) {
            int fila = numAleatorio(0, tableroBarcos.length - 1);
            int columna = numAleatorio(0, tableroBarcos[1].length - 1);

            if (barcoOK(tableroBarcos, L, fila, columna) == true) {
                tableroBarcos[fila][columna] = 'L';
            } else {
                i--;
            }
        }

        return tableroBarcos;
    }

    //Función para imprimir tablero. 
    //Añadimos una variable booleana para mostrar el tablero completo (ordenador) o parcial (usuario).
    public static void imprimir_tablero(char tablero[][], char filas[], String columnas[], boolean verTodo) {

        //Imprimimos el tablero.
        //Tenemos en cuenta que si el número de columna es de una cifra agregamos un espacio extra.
        System.out.print(" ");
        for (int i = 0; i < tablero[1].length; i++) {
            if (Integer.parseInt(columnas[i]) < 10) {
                System.out.print("  " + columnas[i]);
            } else {
                System.out.print(" " + columnas[i]);
            }
        }
        System.out.print("\n");
        for (int i = 0; i < tablero.length; i++) {
            System.out.print(filas[i]);

            for (int j = 0; j < tablero[i].length; j++) {

                //Comprovamos si la opcion verTodo esta activada.
                if (verTodo == true) {
                    System.out.print("  " + tablero[i][j]);
                } else {
                    if (Character.toString(tablero[i][j]).matches("[L,B,Z,P]")) {
                        System.out.print(" -");
                    } else {
                        System.out.print("  " + tablero[i][j]);
                    }
                }
            }
            System.out.print("\n");
        }
    }

    //Funcion para efectuar disparo.
    public static char[][] disparo(char tablero[][], int fila, int columna,char filas[] ,String columnas[]){
        
        if (tablero[fila][columna] == '-') {
            tablero[fila][columna] = 'A';
            imprimir_tablero(tablero, filas, columnas,true);
            System.out.println("\nAgua");
        } else {
            tablero[fila][columna] = 'X';
            imprimir_tablero(tablero, filas, columnas,true);
            System.out.println("\n¡Tocado!");
        }
        return tablero;
    }

    //Funcion Realiza la pregunta y comprueba que sea una respuesta correcta.
    public static String pregunta(String preguntaNormal, String condicion, String preguntaError) {
        Scanner entrada = new Scanner(System.in);

        System.out.println(preguntaNormal);
        String respuesta = entrada.nextLine();

        while (respuesta.matches(condicion) == false) {
            System.out.println("Tu respuesta no es correcta, por favor introduce una opcion correcta:\n");
            System.out.println(preguntaError);
            respuesta = entrada.nextLine();
        }
        return respuesta;
    }

    public static void main(String[] args) {

        char tablero[][];
        //Creamos un array para identificar las filas y otro que almacenará el valor numerico.
        char filas[] = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', '\u00d1', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        
        int fila = 0;
        
        //Creamos un array para identificar las columnas.
        String columnas[] = new String[100];
        for (int i = 0; i < columnas.length; i++) {
            columnas[i] = Integer.toString(i);
        }
        //Creamos dos variables que delimitaran los limites del tablero.
        String filaMax = "J";
        String columnaMax = "9";
        
        //Creamos las variables de cada tipo de barcos.
        int L = 0;
        int B = 0;
        int A = 0;
        int P = 0;
        
        //Creamos una variable con la suma total de casillas que ocuparán los barcos.
        int sumaBarcos = 0;
        //Creamos una variable que almacenará los intentos restantes.
        int intentos = 0;

        //Preguntamos el nivel y creamos el tablero para distribuir los barcos correspondientes.
        String nivel = pregunta("Elige el número correspondiente al nivel de dificultad con el que quieres jugar: \n"
                + " 1. Fácil\n 2. Medio\n 3. Difícil\n 4. Personalizado", "[1-4]", " 1. Fácil\n 2. Medio\n 3. Difícil\n 4. Personalizada");

        if (nivel.matches("[1-3]")) {
            tablero = new char[10][10];
            tablero = crear_tablero_vacio(tablero);
            
            switch (nivel) {
                case "1":
                    L = 5; 
                    B = 3;
                    A = 1;
                    P = 1;
                    intentos = 50;
                    break;
                case "2":
                    L = 2; 
                    B = 1;
                    A = 1;
                    P = 1;
                    intentos = 30;
                    break;
                case "3":
                    L = 1; 
                    B = 1;
                    intentos = 10;
                    break;
            }
            tablero = insertar_barcos(tablero, L, B, A, P);
            sumaBarcos = L + B * 3 + A * 4 + P * 5;
            imprimir_tablero(tablero, filas, columnas, true);/////////////////////////////////////////////////////////////////Cambiar a false

            //Preguntamos todos los parámetros necesarios para crear el nivel personalizado.        
        } else {
            //Creamos el tablero con los datos introducidos por consola.
            //Establecemos un mínimo de 5 por cada lado por ser el tamaño del portaaviones y evitar errores.
            //Establecemos el máximo de fila en 27 (número de letras del alfabeto) y columnas en 100 por limitar el máximo.
            int filasTablero = Integer.parseInt(pregunta("¿Cuántas filas quieres que tenga el tablero? \n(Mínimo 5, máximo 27)", "(2[0-7]|[1][0-9]|[5-9])", "Escribe un número de filas entre 5 y 27:"));
            int columnasTablero = Integer.parseInt(pregunta("¿Cuántas columnas quieres que tenga el tablero? \n(Mínimo 5, máximo 99)", "([1-9][0-9]|[5-9])", "Escribe un número de columnas entre 5 y 99:"));

            tablero = new char[filasTablero][columnasTablero];
            tablero = crear_tablero_vacio(tablero);
            
            //Establecemos la fila y columna máxima para delimitar el tablero a la hora de disparar
            filaMax = Character.toString(filas[filasTablero-1]);
            columnaMax = Integer.toString(columnasTablero-1);    
            
            //Preguntamos el número de barcos de cada clase.
            L = Integer.parseInt(pregunta("¿Cuántas lanchas quieres que aparezcan?\n(Máximo 100)", "(100|[1-9]?[0-9])", "Escribe un número de lanchas entre 0 y 100:"));
            B = Integer.parseInt(pregunta("¿Cuántos buques quieres que aparezcan?\n(Máximo 75)", "(7[0-5]|[1-6][0-9]|[0-9])", "Escribe un número de buques entre 0 y 75:"));
            A = Integer.parseInt(pregunta("¿Cuántos acorazados quieres que aparezcan?\n(Máximo 50)", "(50|[1-4][0-9]|[0-9])", "Escribe un número de acorazados entre 0 y 50:"));
            P = Integer.parseInt(pregunta("¿Cuántos portaaviones quieres que aparezcan?\n(Máximo 50)", "(50|[1-4][0-9]|[0-9])", "Escribe un número de portaaviones entre 0 y 50:"));
            sumaBarcos = L + B * 3 + A * 4 + P * 5;
            
            //Mensaje de error y bucle para introducir un número correcto de barcos.
            //Establecemos que al menos quede libre un 25% del tablero.
            while (sumaBarcos + sumaBarcos / 100 * 50 > filasTablero * columnasTablero) {
                System.out.print("La suma total de los barcos que has introducido ocupa más que el número de casillas permitidas.\n"
                        + "Porfavor, introduce de nuevo la cantidad de barcos:\n");

                L = Integer.parseInt(pregunta("¿Cuántas lanchas quieres que aparezcan?\n(Máximo 100)", "(100|[1-9]?[0-9])", "Escribe un número de lanchas entre 0 y 100:"));
                B = Integer.parseInt(pregunta("¿Cuántos buques quieres que aparezcan?\n(Máximo 75)", "(7[0-5]|[1-6][0-9]|[0-9])", "Escribe un número de buques entre 0 y 75:"));
                A = Integer.parseInt(pregunta("¿Cuántos acorazados quieres que aparezcan?\n(Máximo 50)", "(50|[1-4][0-9]|[0-9])", "Escribe un número de acorazados entre 0 y 50:"));
                P = Integer.parseInt(pregunta("¿Cuántos portaaviones quieres que aparezcan?\n(Máximo 50)", "(50|[1-4][0-9]|[0-9])", "Escribe un número de portaaviones entre 0 y 50:"));
                sumaBarcos = L + B * 3 + A * 4 + P * 5;
            }
            insertar_barcos(tablero, L, B, A, P);

            intentos = Integer.parseInt(pregunta("Escribe el número máximo de intentos. \n(Máximo 100)", "(100|[1-9][0-9]?)", "Escribe un número de intentos máximo entre 1 y 100:"));

            imprimir_tablero(tablero, filas, columnas, true);////////////////////////////////////////////////////Cambiar a false
        }
        
        
        System.out.println("\nComienza el juego...\n");
        
        //Creamos un bucle para desarrollar la partida.
        for (int i = 0; i <= intentos; i++) {
            
            String preguntaDisparo = "\nEfectua un disparo marcando las coordenadas(Fila)(Columna):\n";
            
            if (columnaMax.length() > 1){
                
                String coordenadas = pregunta(preguntaDisparo,"([A-"+filaMax+"]"+columnaMax.substring(0,1)+"[0-"+columnaMax.substring(1,2)+"]|[A-"+filaMax+"][0-"+Integer.toString(Integer.parseInt(columnaMax.substring(0,1))-1)+"]?[0-9])",preguntaDisparo);
                
                for (int j = 0; j < filas.length; j++){
                    if (filas[j] == coordenadas.charAt(0)){
                        fila = j;
                    }
                }
                tablero = disparo(tablero, fila, Integer.parseInt(coordenadas.substring(1,coordenadas.length())),filas,columnas);
            }else{
                String coordenadas = pregunta(preguntaDisparo,"([A-"+filaMax+"][0-"+columnaMax+"])",preguntaDisparo);
                
                for (int j = 0; j < filas.length; j++){
                    if (filas[j] == coordenadas.charAt(0)){
                        fila = j;
                    }
                }
                tablero = disparo(tablero, fila, Integer.parseInt(coordenadas.substring(1,2)),filas,columnas);
            }
            
            //Comprobamos si ha finalizado el juego y si no es asi imprimimos los intentos restantes
            int numeroAciertos = 0;
            for (int j = 0; j < tablero.length;j++){
                for (int k = 0;k < tablero[j].length; k++ ) {
                    if (tablero[j][k] == 'X'){
                        numeroAciertos++;  
                    }
                }
            }
            String game = "";
            if (sumaBarcos == numeroAciertos){
                game = "win";
                System.out.println("\n¡HAS GANADO!");
                break;
            }
            if (intentos == 0 && game != "win"){
                System.out.println("\nGAME OVER");
            }else{
                System.out.println("Te quedan " + (intentos-i+1) + " intentos.");
                System.out.println("Te faltan "+(sumaBarcos - numeroAciertos)+" aciertos para ganar.");
            }
        }

        
    }
    //"+ columnaMax.substring(0,1)+"[0-"columnaMax.substring(1,2)+"]|[0-"+(columnaMax.substring(0,1)-1)+"-9]?)"
    //imprimir_tablero(tablero, false);
    //System.out.println();
    //Integer.parseInt(pregunta(""));
}
