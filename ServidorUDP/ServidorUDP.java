import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

import java.awt.*;

public class ServidorUDP {
    public static void main(String[] args) {

        int puerto = 2525;
        byte[] buffer = new byte[1024];

        Scanner teclado = new Scanner(System.in);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x, y;

        try {
            System.out.println("Servidor UDP: Iniciado!");
            DatagramSocket socketUDP = new DatagramSocket(puerto);
            DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);

            socketUDP.receive(peticion);

            String mensaje = new String(peticion.getData());
            System.out.println("Estado del cliente: " + mensaje);

            int puertoCliente = peticion.getPort();
            InetAddress direccion = peticion.getAddress();

            while (true) {
                socketUDP.receive(peticion);
                for (x = 500; x < 511; x++) {
                    for (y = 500; y < 511; y++) {
                        String color = new String(peticion.getData());
                        Pixel pixel = new Pixel();
                        color = pixel.PixelColor(x, y);
                        buffer = color.getBytes();
                        DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, direccion, puertoCliente);
                        socketUDP.send(respuesta);

                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error Catastrofico :c");
        }
    }
}
