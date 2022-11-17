import java.io.ByteArrayOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;


public class ServidorUDP {
    public static void main(String[] args) {
        ImagenPixel imagenS= new ImagenPixel();
        int width= 500;
        int height = 500;

        int puerto = 2525;
        byte[] buffer = new byte[1024];

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
                imagenS.main(width,height);
                BufferedImage img = ImageIO.read(new File("compress.jpg"));
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(img, "jpg", baos);
                baos.flush();
                buffer = baos.toByteArray();
               
                DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, direccion, puertoCliente);
                socketUDP.send(respuesta);
                
            }
        } catch (Exception e) {
            e.getLocalizedMessage();
        }
    }
}
