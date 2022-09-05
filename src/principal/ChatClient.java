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
    private final Scanner scanner;
    private PrintWriter out;

    public ChatClient() {

        scanner = new Scanner(System.in);
    }

    public void start() throws IOException {

        try {
            clientSocket = new ClientSocket(
                    new Socket(SERVER_ADDRESS, 8089));
            System.out.println(clientSocket.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(ChatClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        // messageLoop();
    }

    public void messageLoop(String msg) throws IOException {

        //String msg;
        //do {
        new Thread(() -> clientMessageReturnLoop(clientSocket)).start();
        // System.out.println("Digite sua mensagem ou Sair para finalizar a aplicacao");
        // msg = this.scanner.nextLine();
        clientSocket.sendMsg(msg);

        // } while (!msg.equalsIgnoreCase("sair"));
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

    }

}
