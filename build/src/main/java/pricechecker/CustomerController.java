package pricechecker;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class CustomerController {

    @FXML
    private Button firstButton ;

    @FXML
    private Button secondButton ;

    @FXML
    private GridPane shoppingCart;

    @FXML
    private Label label1;

    @FXML
    private ScrollPane cartScroll;

    @FXML
    private AnchorPane cartAnchor;

    //When the add button next to an item is clicked, add it to the cart
    @FXML
    public void addToCart(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == firstButton) {
            label1.setVisible(true);
        }
    }
}
