package pricechecker;
import java.io.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import java.util.LinkedList;

public class CustomerController {

    //Arrays to hold the various items
    LinkedList<String> name = new LinkedList<String>();
    LinkedList<Integer> price = new LinkedList<Integer>();
    LinkedList<Double> distance = new LinkedList<Double>();
    LinkedList<Boolean> isHealthy = new LinkedList<Boolean>();
    int numItems = 0;
    int maximumDistance;


    @FXML
    private Button firstButton;

    @FXML
    private Button loadData;

    @FXML
    private TextField maxDist;

    @FXML
    private Label label1;

    @FXML
    public GridPane currentMarket;

    //When the add button next to an item is clicked, add it to the cart
    @FXML
    public void addToCart(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == firstButton) {
            label1.setVisible(true);
        }
    }

    public void loadDataIntoGrid(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == loadData) {
            addToList("Apple",2, 2.3, true);
            addToList("Banana",3, 3.0, true);
            addToList("Banana",2, 4.5, true);
            addToList("Carrots",4, 2.5, true);
        }
    }

    @FXML
    public void addToList(String itemName, int itemPrice, double dist, boolean healthy) {
        name.add(itemName);
        Label labelName = new Label(itemName);
        price.add(itemPrice);
        Label labelPrice = new Label("" + itemPrice);
        distance.add(dist);
        Label labelDist = new Label("" + dist);
        isHealthy.add(healthy);
        Label labelHealth = new Label("" + healthy);

        //currentMarket.add(firstButton, 0, numItems);
        currentMarket.add(labelName, 1, numItems);
        currentMarket.add(labelPrice, 2, numItems);
        currentMarket.add(labelDist, 3, numItems);
        currentMarket.add(labelHealth, 4, numItems);
        numItems++;
    }

    @FXML
    public void updateMaxDist(int dist) {
        maximumDistance = dist;
    }

    public void updateMaxDist(KeyEvent keyEvent) {
        try {
            maximumDistance = Integer.parseInt(maxDist.getText());
            System.out.println(maximumDistance);
        }
        catch (NumberFormatException e) {
            System.out.println("Invalid String");
        }
    }
}
