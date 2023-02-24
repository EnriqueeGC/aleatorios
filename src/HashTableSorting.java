/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.Hashtable;
import java.io.*;
import java.util.*;

 
public class HashTableSorting {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        int opcion;
        do {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Generar números aleatorios y guardarlos en un archivo.");
            System.out.println("2. Leer los números del archivo, ordenarlos y guardarlos en otro archivo.");
            System.out.println("0. Salir.");
            opcion = input.nextInt();

            switch (opcion) {
                case 1:
                    generarNumerosAleatorios();
                    break;
                case 2:
                    ordenarNumeros();
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
                    break;
            }
        } while (opcion != 0);
    }

    public static void generarNumerosAleatorios() {
        Random rand = new Random();

        // Generar un millón de números aleatorios
        int[] numeros = new int[1000000];
        for (int i = 0; i < numeros.length; i++) {
            numeros[i] = rand.nextInt(20000001) - 10000000; // Generar números entre -10,000,000 y 10,000,000
        }

        // Guardar los números en un archivo de texto utilizando la estructura de datos de tabla hash
        Hashtable<Integer, Integer> tablaHash = new Hashtable<>();
        for (int num : numeros) {
            tablaHash.put(num, num);
        }
        try {
            FileWriter fileWriter = new FileWriter("numeros.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            Enumeration<Integer> enumeration = tablaHash.keys();
            while (enumeration.hasMoreElements()) {
                int num = enumeration.nextElement();
                printWriter.println(num);
            }
            printWriter.close();
            System.out.println("Se han generado los números y se han guardado en el archivo 'numeros.txt'");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo.");
        }
    }

    public static void ordenarNumeros() {
        int[] numerosOrdenados = new int[1000000];
        int i = 0;
        try {
            FileReader fileReader = new FileReader("numeros.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                numerosOrdenados[i] = Integer.parseInt(linea);
                i++;
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo.");
        }

        // Ordenar los números utilizando el algoritmo de Shell
        int n = numerosOrdenados.length;
        for (int intervalo = n/2; intervalo > 0; intervalo /= 2) {
            for (int j = intervalo; j < n; j += 1) {
                int temp = numerosOrdenados[j];
                int k;
                for (k = j; k >= intervalo && numerosOrdenados[k - intervalo] > temp; k -= intervalo) {
                    numerosOrdenados[k] = numerosOrdenados[k - intervalo];
                }
                numerosOrdenados[k] = temp;
            }
        }

        // Guardar los números ordenados en un archivo de texto
        try {
            FileWriter fileWriter = new FileWriter("numeros_ordenados.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (int num : numerosOrdenados) {
                printWriter.println(num);
            }
            printWriter.close();
            System.out.println("Se han ordenado los números y se han guardado en el archivo 'numeros_ordenados.txt'");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo.");
        }
    }

       

