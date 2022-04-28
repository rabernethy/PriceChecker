module pricechecker {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.google.gson;

    opens pricechecker to javafx.fxml;
    exports pricechecker;
}
