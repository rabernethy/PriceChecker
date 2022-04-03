package pricechecker;
import java.io.IOException;
import javafx.fxml.FXML;

public class TitleController {

    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("login");
    }

    @FXML
    private void switchToCustomerView() throws IOException {
        App.setRoot("customer_view");
    }
}

