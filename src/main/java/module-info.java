module teste.jogotresemlinha {
    requires javafx.controls;
    requires javafx.fxml;


    opens teste.jogotresemlinha to javafx.fxml;
    exports teste.jogotresemlinha;
}