package pricechecker;
import java.io.IOException;
import javafx.fxml.FXML;

public class TitleController {

    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("login");
    }
}
