module com.example.projetinfov1 {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.projetinfov1 to javafx.fxml;
    exports com.example.projetinfov1;
}