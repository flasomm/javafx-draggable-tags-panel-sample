package com.physalix.jfx.skin;

import com.physalix.jfx.DraggableTagsPane;
import com.physalix.jfx.behavior.DraggableTagsPaneBehavior;
import com.sun.javafx.scene.control.skin.SkinBase;
import javafx.geometry.Orientation;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

/**
 * Main skin DraggableTagsPane class.
 *
 * @author Fabrice Sommavilla <fs@physalix.com>
 * @param <T>
 */
public class DraggableTagsPaneSkin<T> extends SkinBase<DraggableTagsPane<T>, DraggableTagsPaneBehavior<T>> {

    
    private final FlowPane tagsPane = new FlowPane(Orientation.HORIZONTAL);
    
    private final ScrollPane scrollPane = new ScrollPane();
    
    /**
     *
     * @param control
     */
    public DraggableTagsPaneSkin(DraggableTagsPane control) {
        super(control, new DraggableTagsPaneBehavior(control));
        initialize();
    }

    /**
     * Initializes this skin.
     */    
    private void initialize() {
        final DraggableTagsPane control = getSkinnable();
        
        getBehavior().initialize(this);
        
        getScrollPane().getStyleClass().add("tags-scroll");
        getScrollPane().setFitToHeight(false);
        getScrollPane().setFitToWidth(true);
        getScrollPane().setPrefHeight(control.getPanelHeight());
        getScrollPane().setPrefWidth(control.getPanelWidth());

        getTagsPane().getStyleClass().add("tags-region");

        AnchorPane.setBottomAnchor(getTagsPane(), 0.0);
        AnchorPane.setRightAnchor(getTagsPane(), 0.0);
        AnchorPane.setTopAnchor(getTagsPane(), 0.0);
        AnchorPane.setLeftAnchor(getTagsPane(), 0.0);

        AnchorPane tagsScrollPane = new AnchorPane();
        tagsScrollPane.setPrefHeight(control.getPanelHeight());
        tagsScrollPane.setPrefWidth(control.getPanelWidth());
        tagsScrollPane.getStyleClass().add("tags-scrollpane");

        AnchorPane.setBottomAnchor(tagsScrollPane, 0.0);
        AnchorPane.setRightAnchor(tagsScrollPane, 0.0);
        AnchorPane.setTopAnchor(tagsScrollPane, 0.0);
        AnchorPane.setLeftAnchor(tagsScrollPane, 0.0);

        tagsScrollPane.getChildren().add(getTagsPane());
        getScrollPane().setContent(tagsScrollPane);
        
        getChildren().add(getScrollPane());
    }

    /**
     * @return the tagsPane
     */
    public FlowPane getTagsPane() {
        return tagsPane;
    }

    /**
     * @return the scrollPane
     */
    public ScrollPane getScrollPane() {
        return scrollPane;
    }
    
    
}
