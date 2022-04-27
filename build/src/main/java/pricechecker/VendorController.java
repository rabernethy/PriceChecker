package pricechecker;

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
import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.LinkedList;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.net.http.HttpClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VendorController {

    private static HttpRequest.BodyPublisher buildFormDataFromMap(Map<Object, Object> data) {
        var builder = new StringBuilder();
        for (Map.Entry<Object, Object> entry : data.entrySet()) {
            if (builder.length() > 0) {
                builder.append("&");
            }
            builder.append(URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8));
            builder.append("=");
            builder.append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
        }
        System.out.println(builder.toString());
        return HttpRequest.BodyPublishers.ofString(builder.toString());
    }

    String pname, vname;
    Integer pid, vid;
    Double price;
    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();
    @FXML
    private Button AddButton;
    public TextField ProductName, ProductId, Price, VendorName, VendorId;

    @FXML void addProduct(MouseEvent mEvent) throws Exception {

        


        if(mEvent.getSource() == AddButton) {

            pname = ProductName.getText();
            vname = VendorName.getText();
            pid = Integer.valueOf(ProductId.getText());
            vid = Integer.valueOf(VendorId.getText());
            price = Double.valueOf(Price.getText());
            
            Map<Object, Object> data = new HashMap<>();
            data.put("ProductName",pname);
            data.put("ProductId",pid);
            data.put("Price",price);
            data.put("VendorName",vname);
            data.put("VendorId",vid);

            HttpRequest request = HttpRequest.newBuilder().POST(buildFormDataFromMap(data))
                .uri(URI.create("http://127.0.0.1:5000/add"))
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());

            System.out.println("here");
        
        }
        
    }





}
