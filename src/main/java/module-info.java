module com.example.examen {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;

    opens com.example.examen to javafx.fxml;
    exports com.example.examen;
    opens com.example.examen.domain to javafx.base;
}