/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pepsoft.worldpainter.operations;

import org.pepsoft.util.IconUtils;

import java.awt.image.BufferedImage;

/**
 * An abstract base class for WorldPainter {@link Operation}s which provides
 * name and description getters and separate {@link #activate()} and
 * {@link #deactivate()} methods for convenience, and implements
 * {@link #toString()}.
 *
 * @author pepijn
 */
public abstract class AbstractOperation implements Operation {
    protected AbstractOperation(String name, String description) {
        this(name, description, name.toLowerCase().replaceAll("\\s", ""));
    }

    protected AbstractOperation(String name, String description, String iconName) {
        if ((name == null) || (description == null)) {
            throw new NullPointerException();
        }
        this.name = name;
        this.description = description;
        icon = IconUtils.loadImage(getClass().getClassLoader(), "org/pepsoft/worldpainter/icons/" + iconName + ".png");
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public final String getDescription() {
        return description;
    }

    @Override
    public final boolean isActive() {
        return active;
    }

    @Override
    public final void setActive(boolean active) {
        if (active != this.active) {
            this.active = active;
            if (active) {
                activate();
            } else {
                deactivate();
            }
        }
    }

    @Override
    public final BufferedImage getIcon() {
        return icon;
    }

    @Override
    public String toString() {
        return name;
    }

    protected abstract void activate();
    protected abstract void deactivate();

    private final String name, description;
    private final BufferedImage icon;
    private boolean active;
}