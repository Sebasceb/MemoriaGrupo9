module grupo9.mavenproject1 {
    requires javafx.controls;
    requires javafx.fxml;

    opens grupo9.mavenproject1 to javafx.fxml;
    exports grupo9.mavenproject1;
}
