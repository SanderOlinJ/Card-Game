module edu.ntnu.poker_simulator_ntnu_edition {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens edu.ntnu.poker_simulator_ntnu_edition to javafx.fxml;
    exports edu.ntnu.poker_simulator_ntnu_edition;
    exports edu.ntnu.poker_simulator_ntnu_edition.Controllers;
    opens edu.ntnu.poker_simulator_ntnu_edition.Controllers to javafx.fxml;
}