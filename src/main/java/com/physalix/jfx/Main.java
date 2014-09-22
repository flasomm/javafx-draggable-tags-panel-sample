package com.physalix.jfx;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main class.
 *
 * @author Fabrice Sommavilla <fs@physalix.com>
 */
public class Main extends Application {

    private static final String STYLESHEET_PATH = "/styles/styles.css";

    private static final ObservableList<String> data
            = FXCollections.observableArrayList(
                    "chocolate", "salmon", "gold", "coral", "darkorchid",
                    "darkgoldenrod", "lightsalmon", "black", "rosybrown", "blue",
                    "blueviolet", "brown");

    private final ListView<String> listView = new ListView();

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        scene.getStylesheets().add(STYLESHEET_PATH);
        stage.setTitle("Draggable Tags Panel Sample");
        stage.setWidth(600);
        stage.setHeight(400);

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefHeight(400);
        anchorPane.setPrefWidth(600);

        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listView.setLayoutX(14.0);
        listView.setLayoutY(14.0);
        listView.setPrefHeight(300.0);
        listView.setPrefWidth(165.0);

        listView.setItems(data);
        listView.setOnDragDetected(onDragListDetectedEventHandler);

        DraggableTagsPane tagPane = new DraggableTagsPane(371.0, 300.0);
        tagPane.setLayoutX(200.0);
        tagPane.setLayoutY(14.0);

        tagPane.getTagNames().addListener(itemChangeListener);

        anchorPane.getChildren().addAll(listView, tagPane);

        ((Group) scene.getRoot()).getChildren().addAll(anchorPane);

        stage.setScene(scene);
        stage.show();
    }

    /**
     *
     */
    private final ListChangeListener<Tag> itemChangeListener = new ListChangeListener<Tag>() {
        @Override
        public void onChanged(
                ListChangeListener.Change<? extends Tag> c) {

            while (c.next()) {
                for (Tag tag : c.getRemoved()) {
                    System.out.println("REMOVED: " + tag.getName());
                }
                for (Tag tag : c.getAddedSubList()) {
                    System.out.println("ADDED: " + tag.getName());
                }
            }
        }
    };

    /**
     * Create the onDragDetected tags event. The drag-and-drop gesture can only
     * be started by calling the startDragAndDrop method inside the handler of
     * the DRAG_DETECTED event on a gesture source. It is here that transfer
     * modes supported by the gesture source are defined, and the data to be
     * transferred is placed onto the dragboard.
     */
    private final EventHandler<MouseEvent> onDragListDetectedEventHandler
            = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    // drag was detected, start drag-and-drop gesture
                    String item = listView.getItems().get(listView.getSelectionModel().getSelectedIndex());

                    if (item != null) {
                        Dragboard db = listView.startDragAndDrop(TransferMode.COPY);

                        ClipboardContent content = new ClipboardContent();
                        content.putString(item);
                        db.setContent(content);
                        event.consume();
                    }
                }
            };

// ---------- ---------- ---------- ---------- ----------
    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
