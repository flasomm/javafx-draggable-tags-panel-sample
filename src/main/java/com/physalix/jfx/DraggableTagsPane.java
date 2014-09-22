package com.physalix.jfx;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Control;

/**
 * Main control DraggableTagsPane class.
 *
 * @author Fabrice Sommavilla <fs@physalix.com>
 * @param <T>
 */
public class DraggableTagsPane<T> extends Control {
    
    private final String CSS = "/styles/" + this.getClass().getSimpleName() + ".css";
    
    private final DoubleProperty panelWidth = new SimpleDoubleProperty();
    
    private final DoubleProperty panelHeight = new SimpleDoubleProperty();

    /**
     * The available tags name list.
     */
    private final ObjectProperty<ObservableList<Tag>> tagNames 
            = new SimpleObjectProperty(FXCollections.<Tag>observableArrayList());
    
    
    /**
     * 
     * @param panelWidth
     * @param panelheight 
     */
    public DraggableTagsPane(double panelWidth, double panelheight) {
        // setup the CSS
        // the -fx-skin attribute in the CSS sets which Skin class is used
        this.getStyleClass().add(this.getClass().getSimpleName().toLowerCase());        
        
        setPanelWidth(panelWidth);
        setPanelHeight(panelheight);        
    }

    /**
     * Return the path to the CSS file so things are setup right
     *
     * @return
     */
    @Override
    protected String getUserAgentStylesheet() {
        return this.getClass().getResource(CSS).toString();
    }    
    
    /**
     * Returns the panelWidth property.
     *
     * @return the panelWidth property.
     */
    public DoubleProperty panelWidthProperty() {
        return panelWidth;
    }

    /**
     * Returns the control panelWidth value.
     *
     * @return the control panelWidth value.
     */
    public Double getPanelWidth() {
        return panelWidth.get();
    }

    /**
     * Sets the control panelWidth value.
     *
     * @param panelWidth the text to set.
     */
    public void setPanelWidth(Double panelWidth) {
        this.panelWidth.set(panelWidth);
    }
    
    /**
     * Returns the panelHeight property.
     *
     * @return the panelHeight property.
     */
    public DoubleProperty panelHeightProperty() {
        return panelHeight;
    }

    /**
     * Returns the control panelHeight value.
     *
     * @return the control panelHeight value.
     */
    public Double getPanelHeight() {
        return panelHeight.get();
    }

    /**
     * Sets the control panelHeight value.
     *
     * @param panelHeight the text to set.
     */
    public void setPanelHeight(Double panelHeight) {
        this.panelHeight.set(panelHeight);
    }    
    
    /**
     * The tag names.
     *
     * @return
     * @see #getTagNames()
     * @see #setTagNames(javafx.collections.ObservableList)
     */
    public ObjectProperty<ObservableList<Tag>> tagNamesProperty() {
        return tagNames;
    }

    /**
     * Gets the value of the property tagNames.
     *
     * @return
     */
    public ObservableList<Tag> getTagNames() {
        return tagNames.get();
    }

    /**
     * Sets the value of the property tagNames.
     *
     * @param tagNames
     */
    public void setTagNames(ObservableList<Tag> tagNames) {
        this.tagNames.set(tagNames);
    }    
}
