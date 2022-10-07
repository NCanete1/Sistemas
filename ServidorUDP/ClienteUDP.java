import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;

public class ClienteUDP extends JFrame {
  
    public static void main(String[] args) {
        int row=3;
        int col=3;
        JFrame frame = new JFrame("Cliente");
        int puerto_servidor = 2525;
        byte[] buffer = new byte[1024];
        JLabel[][] grid = new JLabel[row][col];
        

        try {
            InetAddress direccionServidor = InetAddress.getByName("localhost");
            DatagramSocket socketUDP = new DatagramSocket();

            String mensaje = "Conectado!";
            buffer = mensaje.getBytes();

            DatagramPacket pregunta = new DatagramPacket(buffer, buffer.length, direccionServidor, puerto_servidor);
            socketUDP.send(pregunta);

            JPanel Ppanel = new JPanel(new GridLayout(row, col, 1, 1));
            while (true) {
                
                for (int i = 0; i < grid.length; i++) {
                    for (int j = 0; j < grid[0].length; j++) {
                        // "Limpia" el buffer
                        mensaje = "             ";
                        buffer = mensaje.getBytes();
                        socketUDP.send(pregunta);

                        // Recive el color
                        DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
                        socketUDP.receive(peticion);
                        mensaje = new String(peticion.getData());
                        String[] splitArray = mensaje.replace(" ", "").split(",");
                        int r = Integer.parseInt(splitArray[0]);
                        int g = Integer.parseInt(splitArray[1]);
                        int b = Integer.parseInt(splitArray[2]);

                        grid[i][j] = new JLabel("     ", SwingConstants.CENTER);
                        grid[i][j].setOpaque(true);
                        grid[i][j].setBackground(new Color(r, g, b));
                        Ppanel.add(grid[i][j]);

                    }
                }
                frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                frame.setPreferredSize(new Dimension(500, 500));
                frame.getContentPane().removeAll();
                frame.repaint();
                frame.add(Ppanel);
                frame.pack();
                frame.setVisible(true);
                Ppanel.removeAll();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}