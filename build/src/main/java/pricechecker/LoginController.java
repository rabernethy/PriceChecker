package pricechecker;
import java.io.IOException;
import javafx.fxml.FXML;
public class LoginController {
    @FXML
    private void Back() throws IOException {
        App.setRoot("title");
    }
}
