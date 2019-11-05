package com.tyjohtech.gol.view;

import javafx.geometry.Pos;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class KeyValuePane extends VBox {

    public void add(String key, Control control) {
        HBox row = createRow();
        Label keyLabel = getLabel(key);
        configureControl(control);

        row.getChildren().addAll(keyLabel, control);
    }

    private HBox createRow() {
        HBox row = new HBox();
        row.setMaxHeight(30);
        row.setAlignment(Pos.CENTER);
        VBox.setVgrow(row, Priority.ALWAYS);
        this.getChildren().add(row);
        return row;
    }

    private Label getLabel(String key) {
        Label keyLabel = new Label(key);
        keyLabel.getStyleClass().addAll("font-primary");
        keyLabel.setMaxWidth(Integer.MAX_VALUE);
        HBox.setHgrow(keyLabel, Priority.ALWAYS);
        return keyLabel;
    }

    private void configureControl(Control control) {
        control.maxWidthProperty().bind(this.widthProperty().divide(2));
    }

}