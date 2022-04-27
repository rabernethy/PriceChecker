module pricechecker {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;

    opens pricechecker to javafx.fxml;
    exports pricechecker;
}
