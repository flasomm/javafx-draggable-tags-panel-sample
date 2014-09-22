package com.physalix.jfx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextBuilder;

/**
 * Draggable tag class.
 * 
 * @author Fabrice Sommavilla <fs@physalix.com>
 */
public class Tag extends Region {
    
    
    private final String name;
    
    /**
     *
     * @param name
     * @param actionHandler
     */
    public Tag(String name, EventHandler<ActionEvent> actionHandler) {
        this.name = name;
        
        getStyleClass().add("draggable-tag");

        Text label = new Text();
        label.setTextAlignment(TextAlignment.CENTER);
        label.setFill(Color.BLACK);
        label.setTextOrigin(VPos.CENTER);
        label.setFont(Font.font("Arial", 12));
        label.setX(25);
        label.setY(14);
        label.setText(name);

        Rectangle rect = new Rectangle(
                label.getLayoutBounds().getWidth() + 35, 25);
        rect.setFill(Color.web("D8D8D8"));
        rect.setX(2);
        rect.setY(1);
        rect.setArcWidth(10);
        rect.setArcHeight(10);

        Group group = new Group();
        group.getChildren().addAll(rect, label,
                closeButton(textCloseIcon(), actionHandler));

        setPrefHeight(28);
        getChildren().addAll(group);
    }

    /**
     * Create close button.
     *
     * @param closeIcon
     * @param actionHandler
     * @return
     */
    private Region closeButton(Node closeIcon,
            EventHandler<ActionEvent> actionHandler) {

        final double BUTTON_HEIGHT = 15;
        final double BUTTON_WIDTH = 15;

        StackPane closeButton = new StackPane();
        closeButton.getStyleClass().add("closetag");
        Button button = new Button();
        button.setOnAction(actionHandler);

        closeIcon.setMouseTransparent(true);

        closeButton.getChildren().addAll(button, closeIcon);
        button.setMinSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        closeButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        closeButton.setMaxSize(StackPane.USE_PREF_SIZE, StackPane.USE_PREF_SIZE);
        closeButton.setTranslateY(5);
        closeButton.setTranslateX(5);

        return closeButton;
    }

    /**
     * Add x as text in the Circle.
     *
     * @return
     */
    private Node textCloseIcon() {
        return TextBuilder.create().text("x")
                .styleClass("closetext").build();
    }

    /**
     * @return the tagLabel
     */
    public String getName() {
        return name;
    }

}
