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
    Label[][] gridLabels = new Label[10][10];
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


            gridLabels[numItems][1] = labelName;
            gridLabels[numItems][2] = labelPrice;
            gridLabels[numItems][3] = labelDist;
            gridLabels[numItems][4] = labelHealth;

        numItems++;
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


    public void sortDistance(MouseEvent mouseEvent) {
        System.out.println(distance);
        System.out.println(name);
        boolean status = true;
        do {
            int timesInIf = 0;
            double current = distance.getFirst();
            for(int i = 1; i < distance.size(); i++) {
                if (current >= distance.get(i)) {
                    timesInIf++;
                    swapAllLists(distance, distance.get(i-1), distance.get(i));
                }
                current = distance.get(i);
            }
            if(timesInIf == 0) //If the if statement is never entered, the list is sorted
                status = false;
        } while (status);

        System.out.println(distance);
        System.out.println(name);

        Label labelName, labelPrice, labelDist, labelHealth;
        //Remove items currently in list
        for (int i = 0; i < gridLabels.length; i++) {
            for (int j = 0; j < gridLabels[0].length; j++) {
                currentMarket.getChildren().remove(gridLabels[i][j]);
                numItems = 0;
            }
        }

        //Add items back into the list
        for (int i = 0; i < distance.size(); i++) {
            labelName = new Label("" + name.get(i));
            labelPrice = new Label("" + price.get(i));
            labelDist = new Label("" + distance.get(i));
            labelHealth = new Label("" + isHealthy.get(i));

            currentMarket.add(labelName, 1, numItems);
            currentMarket.add(labelPrice, 2, numItems);
            currentMarket.add(labelDist, 3, numItems);
            currentMarket.add(labelHealth, 4, numItems);
            numItems++;
        }

        System.out.println(distance);
    }

    public void swapAllLists(LinkedList<Double> list, double ele1, double ele2) {
        int index1 = list.indexOf(ele1);
        int index2 = list.indexOf(ele2);

        list.set(index1, ele2);
        list.set(index2, ele1);
        name.set(index1, name.get(index2));
        name.set(index2, name.get(index1));
        price.set(index1, price.get(index2));
        price.set(index2, price.get(index1));
        isHealthy.set(index1, isHealthy.get(index2));
        isHealthy.set(index2, isHealthy.get(index1));
    }
}
