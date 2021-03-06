package pricechecker;
import java.io.*;

import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import java.io.IOException;
import java.net.*;
import java.util.LinkedList;
import com.google.gson.stream.JsonReader;

public class CustomerController {

    //Arrays to hold the various items
    LinkedList<String> name = new LinkedList<String>();
    LinkedList<Double> price = new LinkedList<Double>();
    LinkedList<Double> distance = new LinkedList<Double>();
    LinkedList<Boolean> isHealthy = new LinkedList<Boolean>();
    Label[][] gridLabels = new Label[10][5];
    int numItems = 0, numCart = 0;
    int maximumDistance;

    @FXML
    private Button BuRow0, BuRow1, BuRow2, BuRow3, BuRow4, BuRow5, BuRow6, BuRow7, BuRow8, BuRow9, BuRow10;

    @FXML
    private Button recipeBu0, recipeBu1, recipeBu2, recipeBu3, recipeBu4, recipeBu5, recipeBu6, recipeBu7, recipeBu8, recipeBu9, recipeBu10;

    @FXML
    private Button loadData;

    @FXML
    private TextField maxDist;

    @FXML
    public GridPane currentMarket, currentCart;

    @FXML
    private ScrollPane chickenScroll, pizzaScroll;

    @FXML
    private Button setChicken, setPizza;

    //When the add button next to an item is clicked, add it to the cart
    @FXML
    public void addToCart(MouseEvent mouseEvent) {
        Label itemName = null, itemPrice = null, dist = null, healthy = null;
        if (mouseEvent.getSource() == BuRow0) {
            itemName = new Label(name.get(0));
            itemPrice = new Label("" + price.get(0));
            dist = new Label("" + distance.get(0));
            healthy = new Label("" + isHealthy.get(0));
        }
        if (mouseEvent.getSource() == BuRow1) {
            itemName = new Label(name.get(1));
            itemPrice = new Label("" + price.get(1));
            dist = new Label("" + distance.get(1));
            healthy = new Label("" + isHealthy.get(1));
        }
        if (mouseEvent.getSource() == BuRow2) {
            itemName = new Label(name.get(2));
            itemPrice = new Label("" + price.get(2));
            dist = new Label("" + distance.get(2));
            healthy = new Label("" + isHealthy.get(2));
        }
        if (mouseEvent.getSource() == BuRow3) {
            itemName = new Label(name.get(3));
            itemPrice = new Label("" + price.get(3));
            dist = new Label("" + distance.get(3));
            healthy = new Label("" + isHealthy.get(3));
        }
        if (mouseEvent.getSource() == BuRow4) {
            itemName = new Label(name.get(4));
            itemPrice = new Label("" + price.get(4));
            dist = new Label("" + distance.get(4));
            healthy = new Label("" + isHealthy.get(4));
        }
        if (mouseEvent.getSource() == BuRow5) {
            itemName = new Label(name.get(5));
            itemPrice = new Label("" + price.get(5));
            dist = new Label("" + distance.get(5));
            healthy = new Label("" + isHealthy.get(5));
        }
        if (mouseEvent.getSource() == BuRow6) {
            itemName = new Label(name.get(6));
            itemPrice = new Label("" + price.get(6));
            dist = new Label("" + distance.get(6));
            healthy = new Label("" + isHealthy.get(6));
        }
        if (mouseEvent.getSource() == BuRow7) {
            itemName = new Label(name.get(7));
            itemPrice = new Label("" + price.get(7));
            dist = new Label("" + distance.get(7));
            healthy = new Label("" + isHealthy.get(7));
        }
        if (mouseEvent.getSource() == BuRow8) {
            itemName = new Label(name.get(8));
            itemPrice = new Label("" + price.get(8));
            dist = new Label("" + distance.get(8));
            healthy = new Label("" + isHealthy.get(8));
        }
        if (mouseEvent.getSource() == BuRow9) {
            itemName = new Label(name.get(9));
            itemPrice = new Label("" + price.get(9));
            dist = new Label("" + distance.get(9));
            healthy = new Label("" + isHealthy.get(9));
        }
        if (mouseEvent.getSource() == BuRow10) {
            itemName = new Label(name.get(10));
            itemPrice = new Label("" + price.get(10));
            dist = new Label("" + distance.get(10));
            healthy = new Label("" + isHealthy.get(10));
        }
        currentCart.add(itemName, 0, numCart);
        currentCart.add(itemPrice, 1, numCart);
        currentCart.add(dist, 2, numCart);
        currentCart.add(healthy, 3, numCart);
        numCart++;
    }
    public void loadDataIntoGrid(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.getSource() == loadData) {
            addToList("Acorn Squash",2.38, 2.3, true);
            addToList("Baby Carrots - 4 Pack",2.49, 3.1, true);
            addToList("Bob Evans Egg Whites",5.99, 4.5, true);
            addToList("Bowl & Basket Cheese Spread",7.99, 2.5, true);
            addToList("Daisy Sour Cream",1.79, 1.2, true);
            addToList("Bowl & Basket Chicken Wings",7.58, 1.3, true);
            addToList("ShopRite Turkey Wings",2.69, 0.8, true);
            addToList("Fresh Smoked Turkey Tails",9.18, 0.2, true);
            addToList("Fresh Pork Toes",2.59, 10.0, true);
            addToList("Fresh Pork Spare Ribs",10.33, 11.5, true);


            URL url = new URL("http://127.0.0.1:5000/products");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            con.disconnect();
            String items = content.toString();

            items = items.replace("[","");
            items = items.replace("]","");
/*
            int currentIndex = 0;
            Label currentName = null, currentPrice = null, currentDist = null, currentShop = null;
            String currentString = "";
            String currentNum = "";
            for(int i = 0; i < items.length(); i++) {
                char currentChar = items.charAt(i);
                if(currentChar == ',')
                    currentIndex++;

                if(currentChar != ',' && (currentIndex % 5 == 1 || currentIndex % 5 == 4))
                    currentString += currentChar;
                else if(currentIndex % 5 == 2 || currentIndex % 5 == 3)
                    currentNum += currentChar;

                if(currentChar == ',' && currentIndex % 5 == 1) {
                    currentName = new Label(currentString);
                    currentString = "";
                }
                else if(currentChar == ',' && currentIndex % 5 == 2) {
                    currentPrice = new Label(currentNum);
                    currentNum = "";
                }
                else if(currentChar == ',' && currentIndex % 5 == 3) {
                    currentDist = new Label(currentNum);
                    currentNum = "";
                }
                else if(currentChar == ',' && currentIndex % 5 == 4) {
                    currentShop = new Label(currentString);
                    currentString = "";
                }
            }*/
        }
    }

    @FXML
    public void addToList(String itemName, double itemPrice, double dist, boolean healthy) {
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

    public void updateMaxDist() {
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
        do {
            int timesInIf = 0;
            double current = price.getFirst();
            for(int i = 1; i < price.size(); i++) {
                if (current > price.get(i)) {
                    timesInIf++;
                    swapAllListsPrice(price, price.get(i-1), price.get(i));
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
        double price1 = price.get(index1);
        double price2 = price.get(index2);
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

    public void swapAllListsPrice(LinkedList<Double> list, double ele1, double ele2) {
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

    public void fromRecipeToCart(MouseEvent mouseEvent) {
        Label itemName = null, itemPrice = null, dist = null, healthy = null;
        if (mouseEvent.getSource() == recipeBu0) {
            itemName = new Label("Vegetable Oil");
            itemPrice = new Label("" + 3.99);
            dist = new Label("" + 1.0);
            healthy = new Label("" + true);
        }
        if (mouseEvent.getSource() == recipeBu1) {
            itemName = new Label("Minced Garlic");
            itemPrice = new Label("" + 2.50);
            dist = new Label("" + 1.0);
            healthy = new Label("" + false);
        }
        if (mouseEvent.getSource() == recipeBu2) {
            itemName = new Label("Diced Tomatoes");
            itemPrice = new Label("" + 1.49);
            dist = new Label("" + 2.3);
            healthy = new Label("" + true);
        }
        if (mouseEvent.getSource() == recipeBu3) {
            itemName = new Label("Assorted Vegetables");
            itemPrice = new Label("" + 5.99);
            dist = new Label("" + 2.3);
            healthy = new Label("" + true);
        }
        if (mouseEvent.getSource() == recipeBu4) {
            itemName = new Label("Cooked Brown Rice");
            itemPrice = new Label("" + 1.99);
            dist = new Label("" + 3.0);
            healthy = new Label("" + true);
        }
        if (mouseEvent.getSource() == recipeBu5) {
            itemName = new Label("Tomato Sauce");
            itemPrice = new Label("" + 2.49);
            dist = new Label("" + 0.8);
            healthy = new Label("" + true);
        }
        if (mouseEvent.getSource() == recipeBu6) {
            System.out.println("Pressed Chicken Breast");
            itemName = new Label("Chicken Breast");
            itemPrice = new Label("" + 6.63);
            dist = new Label("" + 2.0);
            healthy = new Label("" + true);
        }
        if (mouseEvent.getSource() == recipeBu7) {
            itemName = new Label("Cooked Broccoli");
            itemPrice = new Label("" + 2.99);
            dist = new Label("" + 2.0);
            healthy = new Label("" + true);
        }
        if (mouseEvent.getSource() == recipeBu8) {
            itemName = new Label("Parmesan Cheese");
            itemPrice = new Label("" + 4.49);
            dist = new Label("" + 3.6);
            healthy = new Label("" + false);
        }
        if (mouseEvent.getSource() == recipeBu9) {
            itemName = new Label("Fresh Basil");
            itemPrice = new Label("" + 1.25);
            dist = new Label("" + 3.6);
            healthy = new Label("" + true);
        }
        if (mouseEvent.getSource() == recipeBu10) {
            itemName = new Label("Whole Wheat Pitas");
            itemPrice = new Label("" + 8.99);
            dist = new Label("" + 3.9);
            healthy = new Label("" + true);
        }
        currentCart.add(itemName, 0, numCart);
        currentCart.add(itemPrice, 1, numCart);
        currentCart.add(dist, 2, numCart);
        currentCart.add(healthy, 3, numCart);
        numCart++;
    }

    public void setRecipe(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == setChicken) {
            pizzaScroll.setVisible(false);
            chickenScroll.setVisible(true);
        }
        else if(mouseEvent.getSource() == setPizza) {
            chickenScroll.setVisible(false);
            pizzaScroll.setVisible(true);
        }
    }
}
