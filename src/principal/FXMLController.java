/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package principal;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author MASTER
 */
public class FXMLController implements Initializable {

    @FXML
    private Label lbTeste;
    @FXML
    private Button btnPalavra;
    @FXML
    private TextField textPalavra;
    @FXML
    private TextArea textAreaPalavra;
    @FXML
    private Button btnConectar;
    ChatClient cliente;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cliente = new ChatClient();

    }
    // TODO

    @FXML
    private void InserirPalavra(ActionEvent event) throws IOException {

        if (textPalavra.getText().equals("")) {
            System.out.println("Campo está vazio: Digite algo");
        } else {
            textAreaPalavra.appendText(textPalavra.getText() + "\n");
            cliente.messageLoop(textPalavra.getText());
        }
    }

    @FXML
    private void conectar(ActionEvent event) {

        try {

            cliente.start();
        } catch (IOException ex) {
            System.out.println("Erro ao conectar com o Servidor! " + ex.getMessage());
        }

    }

}
