package principal;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
/**
 *
 * @author MASTER
 */
public class ChatClient {

    /**
     * @param args the command line arguments
     */
    private static final String SERVER_ADDRESS = "ssh.chauchuty.cf";
    private ClientSocket clientSocket;

    public ChatClient() {

    }

    public void start() throws IOException {

        try {
            
            clientSocket = new ClientSocket(
                    new Socket(SERVER_ADDRESS, 8089));
            System.out.println(clientSocket.getMessage());

            //Mesmo abrindo Thread aqui em cima o uso de memória aumenta, o gasto
            // não está relacionado a abertura de várias Threads
            new Thread(() -> clientMessageReturnLoop(clientSocket)).start();

        } catch (IOException ex) {
            Logger.getLogger(ChatClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        // messageLoop();
    }

    public void messageLoop(String msg) throws IOException {

        clientSocket.sendMsg(msg);

    }

    public void clientMessageReturnLoop(ClientSocket clientSocket) {

        String msg;

        while ((msg = clientSocket.getMessage()) != null) {
            if (msg.equalsIgnoreCase("Desconectado!")) {
                break;
            }
            System.out.println(msg);

        }
        System.out.println(msg);
        try {
            clientSocket.closeInOut();//função fecha o Socket
        } catch (IOException ex) {
            Logger.getLogger(ChatClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
