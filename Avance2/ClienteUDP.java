import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javax.imageio.ImageIO;
import javax.swing.*;


import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;


public class ClienteUDP extends JFrame {

    public static void main(String[] args) {
        int width= 500;
        int height = 500;

        int puerto_servidor = 2525;
        byte[] buffer = new byte[1024];

        JFrame frame = new JFrame("Cliente");
        frame.setPreferredSize(new Dimension( width, height));

        try {
            InetAddress direccionServidor = InetAddress.getByName("localhost");
            DatagramSocket socketUDP = new DatagramSocket();

            String mensaje = "Conectado!";
            buffer = mensaje.getBytes();

            DatagramPacket pregunta = new DatagramPacket(buffer, buffer.length, direccionServidor, puerto_servidor);
            socketUDP.send(pregunta);

            while (true) {
                pregunta = new DatagramPacket(buffer, buffer.length, direccionServidor, puerto_servidor);
                socketUDP.send(pregunta);
                buffer = new byte[10000];
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
                socketUDP.receive(peticion);
                byte[] data = peticion.getData();
                ByteArrayInputStream bis = new ByteArrayInputStream(data);
                BufferedImage img = ImageIO.read(bis);
                ImageIcon imagen = new ImageIcon(img);
                ventana(frame, imagen);
                

            }
        } catch (Exception e) {
            e.getLocalizedMessage();
        }
        // write image

    }

    public static void ventana(JFrame frame, ImageIcon img) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().removeAll();
        frame.getContentPane().invalidate();
        frame.getContentPane().validate();
        SwingUtilities.updateComponentTreeUI(frame);
        JLabel lbl = new JLabel();
        lbl.setIcon(img);
        frame.getContentPane().add(lbl, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }
}