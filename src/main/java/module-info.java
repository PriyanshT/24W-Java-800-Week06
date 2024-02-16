module org.georgiancollege.week06 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens org.georgiancollege.week06 to javafx.fxml;
    exports org.georgiancollege.week06;
}