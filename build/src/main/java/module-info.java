module pricechecker {
    requires javafx.controls;
    requires javafx.fxml;

    opens pricechecker to javafx.fxml;
    exports pricechecker;
}
