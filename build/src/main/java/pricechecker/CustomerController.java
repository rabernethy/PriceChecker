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
    Label[][] gridLabels = new Label[5][5];
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
            addToList("Banana",3, 3.1, true);
            addToList("Banana",6, 4.5, true);
            addToList("Carrots",4, 2.5, true);
            addToList("Snickers",1, 1.2, false);
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
        }
        catch (NumberFormatException e) {
        }
        for (int i = 0; i < distance.size(); i++) {
            if(distance.get(i) > maximumDistance) {
                for (int j = 0; j < 5; j++) {
                    currentMarket.getChildren().remove(gridLabels[i][j]);
                    gridLabels[i][j] = null;
                }
                //This is to remove any empty rows in the gridLabels array
                Label tempLabel1 = gridLabels[numItems-1][1];
                Label tempLabel2 = gridLabels[numItems-1][2];
                Label tempLabel3 = gridLabels[numItems-1][3];
                Label tempLabel4 = gridLabels[numItems-1][4];
                Label swapLabel;
                gridLabels[numItems-1][1] = null;
                gridLabels[numItems-1][2] = null;
                gridLabels[numItems-1][3] = null;
                gridLabels[numItems-1][4] = null;
                for (int k = distance.size() - 1; k > i; k--) {
                    swapLabel = gridLabels[k-1][1];
                    gridLabels[k-1][1] = tempLabel1;
                    tempLabel1 = swapLabel;

                    swapLabel = gridLabels[k-1][2];
                    gridLabels[k-1][2] = tempLabel2;
                    tempLabel2 = swapLabel;

                    swapLabel = gridLabels[k-1][3];
                    gridLabels[k-1][3] = tempLabel3;
                    tempLabel3 = swapLabel;

                    swapLabel = gridLabels[k-1][4];
                    gridLabels[k-1][4] = tempLabel4;
                    tempLabel4 = swapLabel;
                }
                for (int j = 0; j < gridLabels.length; j++) {
                    for (int k = 0; k < gridLabels[0].length; k++) {
                        System.out.print(gridLabels[j][k] + " ");
                    }
                    System.out.println("\n");

                }
                numItems--;
                distance.remove(i);
            }
        }
        //Remove items currently in list
        for (int i = 0; i < gridLabels.length; i++) {
            for (int j = 0; j < gridLabels[0].length; j++) {
                currentMarket.getChildren().remove(gridLabels[i][j]);
                numItems = 0;
            }
        }
        for (int i = 0; i < distance.size(); i++) {
            Label labelName = gridLabels[i][1];
            Label labelPrice = gridLabels[i][2];
            Label labelDist = gridLabels[i][3];
            Label labelHealth = gridLabels[i][4];

            currentMarket.add(labelName, 1, numItems);
            currentMarket.add(labelPrice, 2, numItems);
            currentMarket.add(labelDist, 3, numItems);
            currentMarket.add(labelHealth, 4, numItems);
            numItems++;
        }

    }


    public void sortDistance(MouseEvent mouseEvent) {
        boolean status = true;
        do {
            int timesInIf = 0;
            double current = distance.getFirst();
            for(int i = 1; i < distance.size(); i++) {
                if (current > distance.get(i)) {
                    timesInIf++;
                    swapAllLists(distance, distance.get(i-1), distance.get(i));
                }
                current = distance.get(i);
            }
            if(timesInIf == 0) //If the if statement is never entered, the list is sorted
                status = false;
        } while (status);

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

            gridLabels[numItems][1] = labelName;
            gridLabels[numItems][2] = labelPrice;
            gridLabels[numItems][3] = labelDist;
            gridLabels[numItems][4] = labelHealth;
            numItems++;
        }
    }

    public void sortPrice(MouseEvent mouseEvent) {
        boolean status = true;
        System.out.println(price);
        do {
            int timesInIf = 0;
            int current = price.getFirst();
            for(int i = 1; i < price.size(); i++) {
                if (current > price.get(i)) {
                    timesInIf++;
                    swapAllLists(price, price.get(i-1), price.get(i));
                }
                current = price.get(i);
            }
            if(timesInIf == 0) //If the if statement is never entered, the list is sorted
                status = false;
        } while (status);

        Label labelName, labelPrice, labelDist, labelHealth;
        //Remove items currently in list
        for (int i = 0; i < gridLabels.length; i++) {
            for (int j = 0; j < gridLabels[0].length; j++) {
                currentMarket.getChildren().remove(gridLabels[i][j]);
                numItems = 0;
            }
        }

        //Add items back into the list
        for (int i = 0; i < price.size(); i++) {
            labelName = new Label("" + name.get(i));
            labelPrice = new Label("" + price.get(i));
            labelDist = new Label("" + distance.get(i));
            labelHealth = new Label("" + isHealthy.get(i));

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
    }

    public void swapAllLists(LinkedList<Double> list, double ele1, double ele2) {
        int index1 = list.indexOf(ele1);
        int index2 = list.indexOf(ele2);
        String name1 = name.get(index1);
        String name2 = name.get(index2);
        int price1 = price.get(index1);
        int price2 = price.get(index2);
        boolean health1 = isHealthy.get(index1);
        boolean health2 = isHealthy.get(index2);

        list.set(index1, ele2);
        list.set(index2, ele1);
        name.set(index1, name2);
        name.set(index2, name1);
        price.set(index1, price2);
        price.set(index2, price1);
        isHealthy.set(index1, health2);
        isHealthy.set(index2, health1);
    }

    public void swapAllLists(LinkedList<Integer> list, int ele1, int ele2) {
        int index1 = list.indexOf(ele1);
        int index2 = list.indexOf(ele2);
        String name1 = name.get(index1);
        String name2 = name.get(index2);
        double dist1 = distance.get(index1);
        double dist2 = distance.get(index2);
        boolean health1 = isHealthy.get(index1);
        boolean health2 = isHealthy.get(index2);

        list.set(index1, ele2);
        list.set(index2, ele1);
        name.set(index1, name2);
        name.set(index2, name1);
        distance.set(index1, dist2);
        distance.set(index2, dist1);
        isHealthy.set(index1, health2);
        isHealthy.set(index2, health1);
    }
}
