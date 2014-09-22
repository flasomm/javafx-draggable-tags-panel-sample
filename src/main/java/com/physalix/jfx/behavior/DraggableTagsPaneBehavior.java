package com.physalix.jfx.behavior;

import com.physalix.jfx.DraggableTagsPane;
import com.physalix.jfx.Tag;
import com.physalix.jfx.skin.DraggableTagsPaneSkin;
import com.sun.javafx.scene.control.behavior.BehaviorBase;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

/**
 * Main behavior DraggableTagsPane class.
 *
 * @author Fabrice Sommavilla <fs@physalix.com>
 * @param <T>
 */
public class DraggableTagsPaneBehavior<T> extends BehaviorBase<DraggableTagsPane<T>> {

    /**
     * Tags name list .
     */
    private final ObservableList<Tag> tagsList = FXCollections.observableArrayList();

    /**
     * Control skin.
     */
    private DraggableTagsPaneSkin<T> skin;

    /**
     * Control properties.
     */
    private DraggableTagsPane control;

    /**
     *
     * @param tagsControl
     */
    public DraggableTagsPaneBehavior(DraggableTagsPane tagsControl) {
        super(tagsControl);
    }

    /**
     * Tags data list change listener.
     */
    private final ListChangeListener<Tag> dataChangeListener = new ListChangeListener<Tag>() {
        @Override
        public void onChanged(
                ListChangeListener.Change<? extends Tag> c) {

            while (c.next()) {
                tagsList.removeAll(c.getRemoved());
                control.getTagNames().removeAll(c.getRemoved());
                control.getTagNames().addAll(c.getAddedSubList());

                for (Tag item : c.getAddedSubList()) {
                    skin.getTagsPane().getChildren().add(item);
                }
            }
        }
    };

    /**
     * Create the onDragOver tags event. After the drag-and-drop gesture is
     * started, any node or scene that the mouse is dragged over is a potential
     * target to drop the data. You specify which object accepts the data by
     * implementing the DRAG_OVER event handler.
     */
    private final EventHandler<DragEvent> onDragOverTagsEventHandler
            = new EventHandler<DragEvent>() {

                @Override
                public void handle(DragEvent event) {
                    // data is dragged over the target 
                    Dragboard db = event.getDragboard();

                    if (event.getGestureSource() != skin.getScrollPane()
                    && db.hasString()) {
                        event.acceptTransferModes(TransferMode.COPY);
                    }
                    event.consume();
                }
            };

    /**
     * Create the onDragDrop tags event. When the mouse button is released on
     * the gesture target, which accepted previous DRAG_OVER events with a
     * transfer mode supported by the gesture source, then the DRAG_DROPPED
     * event is sent to the gesture target.
     */
    private final EventHandler<DragEvent> onDragDropTagsEventHandler
            = new EventHandler<DragEvent>() {

                @Override
                public void handle(DragEvent event) {
                    Dragboard db = event.getDragboard();

                    if (event.getGestureSource() != skin.getScrollPane()
                    && db.hasString()) {
                        event.acceptTransferModes(TransferMode.COPY);
                        tagsList.add(new Tag(db.getString(),
                                        new CloseTagEventHandler()));
                    }
                    event.consume();
                }
            };

    /**
     *
     * @param skin
     */
    public void initialize(DraggableTagsPaneSkin<T> skin) {
        this.skin = skin;
        control = getControl();
        tagsList.addListener(dataChangeListener);
        skin.getScrollPane().setOnDragOver(onDragOverTagsEventHandler);
        skin.getScrollPane().setOnDragDropped(onDragDropTagsEventHandler);
    }

    /**
     * Close button event handler class.
     */
    private class CloseTagEventHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(final ActionEvent event) {

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Node tag = ((Node) event.getSource()).getParent()
                            .getParent().getParent();

                    skin.getTagsPane().getChildren().remove((Tag) tag);
                    // update tagsList
                    tagsList.remove((Tag) tag);
                }
            });
        }

    }

}
