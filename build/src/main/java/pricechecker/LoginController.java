package pricechecker;
import java.io.IOException;
import javafx.fxml.FXML;
import java.io.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import java.io.IOException;
import java.net.*;
import java.util.LinkedList;

public class LoginController {

    @FXML
    private Button Login, Return;

    @FXML
    private void Back() throws IOException {
        App.setRoot("title");
    }

    @FXML
    private void switchToVendorAdd() throws IOException {

        App.setRoot("vendoradd");
    }
}
