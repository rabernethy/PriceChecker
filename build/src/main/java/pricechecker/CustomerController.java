package pricechecker;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class CustomerController {

    @FXML
    private Button firstButton ;

    @FXML
    private Button secondButton ;

    //When the add button next to an item is clicked, add it to the cart
    public void addToCart(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == firstButton) {
            ListView myList = new ListView();
            myList.setId("rightList");
            System.out.println(myList.getHeight());
        }
    }
}
