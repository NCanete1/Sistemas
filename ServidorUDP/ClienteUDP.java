package Trabajo3.ServidorUDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class ClienteUDP {
    public static void main(String[] args) {

        int puerto_servidor = 2525;
        byte[] buffer = new byte[1024];
        

        try {

            InetAddress direccionServidor = InetAddress.getByName("localhost");
            DatagramSocket socketUDP = new DatagramSocket();
            

            String mensaje = "Conectado!";
            buffer = mensaje.getBytes();

            DatagramPacket pregunta = new DatagramPacket(buffer, buffer.length, direccionServidor, puerto_servidor);
            socketUDP.send(pregunta);

            while (true) {
                //"Limpia" el buffer
                mensaje = "             ";
                buffer = mensaje.getBytes();
                socketUDP.send(pregunta);

                //Recive el color
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
                socketUDP.receive(peticion);
                mensaje = new String(peticion.getData());
                System.out.println(mensaje);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }


        } catch (Exception e) {
            System.out.println("Error Catastrofico en Cliente :c");
        }
    }
}