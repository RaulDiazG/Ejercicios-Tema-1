import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Random;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        // Comando y argumento para ejecutar el script bash
        String comando = "bash";
        String scriptPath = "esperarNsegundos.sh"; // Reemplaza con la ruta correcta
        String argumento = "10"; // Puedes cambiar el valor según sea necesario

        // Hilo para ejecutar el proceso
        Thread procesoThread = new Thread(() -> ejecutarProceso(comando, scriptPath, argumento));
        procesoThread.start();

        // Hilo para escribir letras aleatorias
        Thread escrituraThread = new Thread(() -> escribirLetrasAleatorias());
        escrituraThread.start();

        try {
            // Esperar a que ambos hilos terminen
            procesoThread.join();
            escrituraThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Programa principal finalizado.");
    }

    private static void ejecutarProceso(String comando, String scriptPath, String argumento) {
        ProcessBuilder procesoBuilder = new ProcessBuilder(comando, scriptPath, argumento);
        procesoBuilder.redirectErrorStream(true);

        try {
            Process proceso = procesoBuilder.start();
            proceso.waitFor();
            System.out.println("El proceso ha finalizado.");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void escribirLetrasAleatorias() {
        Random random = new Random();
        int duracionSegundos = 10; // Tiempo de ejecución del proceso

        for (int i = 0; i < duracionSegundos; i++) {
            char letra = (char) ('A' + random.nextInt(26));
            System.out.print(letra + " ");

            // Dormir durante un segundo
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\nFin de la escritura de letras aleatorias.");
    }
}
