import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        try {
            // Crear el proceso hijo ejecutando el script bash
            ProcessBuilder processBuilder = new ProcessBuilder("bash", "lista.sh");
            Process process = processBuilder.start();

            // Capturar la salida estándar del proceso hijo
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            // Leer la salida y almacenarla en una lista
            String linea;
            StringBuilder output = new StringBuilder();
            while ((linea = reader.readLine()) != null) {
                output.append(linea).append("\n");
            }

            // Esperar a que el proceso hijo termine
            int exitCode = process.waitFor();

            // Imprimir los últimos 20 números
            String[] numbers = output.toString().trim().split("\n");
            int start = Math.max(0, numbers.length - 20);
            for (int i = start; i < numbers.length; i++) {
                System.out.println(numbers[i]);
            }

            // Imprimir el código de salida del proceso hijo
            System.out.println("Código de salida: " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}