module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires org.junit.jupiter.api;
    requires org.junit.platform.commons;
    requires org.junit.platform.launcher;

    exports com.example.demo.controller;
    exports com.example.demo.level;
    exports com.example.demo.level.levelview;
    exports com.example.demo.model;
    exports com.example.demo.projectile;
    exports com.example.demo.view;
    exports com.example.demo.audio;
    exports com.example.demo.controller.inGame;

    opens com.example.demo.audio to org.junit.platform.commons, org.junit.platform.launcher;
    opens com.example.demo.controller to org.junit.platform.commons, org.junit.platform.launcher;
    opens com.example.demo.controller.inGame to org.junit.platform.commons, org.junit.platform.launcher;
    opens com.example.demo.level to org.junit.platform.commons, org.junit.platform.launcher;
    opens com.example.demo.level.levelview to org.junit.platform.commons, org.junit.platform.launcher;
    opens com.example.demo.model to org.junit.platform.commons, org.junit.platform.launcher;
    opens com.example.demo.projectile to org.junit.platform.commons, org.junit.platform.launcher;
    opens com.example.demo.view to org.junit.platform.commons, org.junit.platform.launcher;
}