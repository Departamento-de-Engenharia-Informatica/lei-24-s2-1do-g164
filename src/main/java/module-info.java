module pt.ipp.isep.dei.esoft.project.ui.gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires AuthLib;
    requires org.apache.commons.lang3;
    requires java.logging;
    requires org.jfree.jfreechart;
    requires java.desktop;
    requires reload4j;
    requires guru.nidi.graphviz;
    requires svgSalamander;
    requires jdk.jfr;

//    requires commons.math3;

    //opens fxml to javafx.fxml;
    opens pt.ipp.isep.dei.esoft.project.application.controller.authorization to javafx.fxml;
    opens pt.ipp.isep.dei.esoft.project.application.controller to javafx.fxml;
    opens pt.ipp.isep.dei.esoft.project.application.session to javafx.fxml;
    opens pt.ipp.isep.dei.esoft.project.domain to javafx.fxml;
    //opens pt.ipp.isep.dei.esoft.project.DTO to javafx.fxml;
    opens pt.ipp.isep.dei.esoft.project.repository to javafx.fxml;
    opens pt.ipp.isep.dei.esoft.project.ui.gui to javafx.fxml;
    opens pt.ipp.isep.dei.esoft.project.ui.console to javafx.fxml;
    opens pt.ipp.isep.dei.esoft.project.ui.console.authorization to javafx.fxml;
    opens pt.ipp.isep.dei.esoft.project.ui.console.menu to javafx.fxml;
    opens pt.ipp.isep.dei.esoft.project.ui.console.utils to javafx.fxml;
    opens pt.ipp.isep.dei.esoft.project.ui.gui.controllers to javafx.fxml;
    exports pt.ipp.isep.dei.esoft.project.ui.gui;
    exports pt.ipp.isep.dei.esoft.project.ui.gui.controllers;
    opens pt.ipp.isep.dei.esoft.project.repository.enums to javafx.fxml;
    opens pt.ipp.isep.dei.esoft.project.application.session.emailService to javafx.fxml;
    opens pt.ipp.isep.dei.esoft.project.application.session.emailService.adapters to javafx.fxml;
    opens pt.ipp.isep.dei.esoft.project.ui to javafx.fxml;

}