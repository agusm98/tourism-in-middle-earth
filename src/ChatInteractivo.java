import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ChatInteractivo {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Bienvenido al Chat Interactivo!");
        System.out.println("Por favor ingresa tu nombre de usuario");
        
        String nombreUsuario = scanner.nextLine();
        
		Usuario usuario = new Usuario(nombreUsuario);
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("chat_log.txt"))) {
            while (true) {
                System.out.print(usuario.getNombreUsuario()+ ": ");
                String mensajeUsuario = scanner.nextLine();
                
                if (mensajeUsuario.equalsIgnoreCase("salir")) {
                    System.out.println("Chat finalizado. ¡Hasta luego!");
                    break;
                }
                
                String mensajeRespuesta = obtenerRespuestaChat(mensajeUsuario);
                System.out.println("Bot: " + mensajeRespuesta);
                
                // Escribir la interacción en el archivo de texto
                bw.write(usuario.getNombreUsuario()+": " + mensajeUsuario);
                bw.newLine();
                bw.write("Bot: " + mensajeRespuesta);
                bw.newLine();
            }
            
            System.out.println("El registro del chat se ha guardado en el archivo chat_log.txt.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        scanner.close();
    }
    
    private static String obtenerRespuestaChat(String mensaje) {
        return "Gracias por tu mensaje: \"" + mensaje + "\"";
    }
}

