module coe528.bankingapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;

    opens coe528.bankingapp to javafx.fxml;
    opens coe528.bankingapp.templates to javafx.base;
    opens coe528.bankingapp.customer to javafx.base;
    opens coe528.bankingapp.manager to javafx.base;
    exports coe528.bankingapp.gui;
    opens coe528.bankingapp.gui to javafx.fxml;

}
