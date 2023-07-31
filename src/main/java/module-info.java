module kr.kr {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens kr.kr to javafx.fxml;
    exports kr.kr;
}