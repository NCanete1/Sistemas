package Trabajo3.ServidorUDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

import Trabajo3.Pixel.Pixel;

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

            System.out.println("Tu resolución es de " + screenSize.width + "x" + screenSize.height);
            do {
                System.out.println("Escriba la coordenada X del pixel que desea monitorear.");
                x = teclado.nextInt();
            } while (0 >= x && x <= screenSize.width);
            do {
                System.out.println("Escriba la coordenada Y del pixel que desea monitorear.");
                y = teclado.nextInt();
            } while (0 >= y && y <= screenSize.height);
            System.out.println("El pixel que va a monitorear es: " + x + "," + y);

            while (true) {
                socketUDP.receive(peticion);
                String color = new String(peticion.getData());
                Pixel pixel = new Pixel();
                color = pixel.PixelColor(x, y);
                buffer = color.getBytes();
                DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, direccion, puertoCliente);
                socketUDP.send(respuesta);


                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        } catch (Exception e) {
            System.out.println("Error Catastrofico :c");
        }
    }
}
