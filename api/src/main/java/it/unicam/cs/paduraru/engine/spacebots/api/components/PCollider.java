package it.unicam.cs.paduraru.engine.spacebots.api.components;

import it.unicam.cs.paduraru.engine.PComponent;
import it.unicam.cs.paduraru.engine.PEntity;
import it.unicam.cs.paduraru.engine.PVector;
import it.unicam.cs.paduraru.engine.spacebots.api.shapes.PShape;

/**
 * Modello per la creazione di un Collider da usare dentro il sistema SysCollision.
 * Un collider rende tangibile un'entità. Di default l'origine del collider è quella del Parent.
 */
public abstract class PCollider extends PComponent {
    private PShape shape;

    public PCollider(PEntity parent, PShape shape) {
        super(parent);
        this.shape = shape;
    }
    protected PCollider(PComponent component, PShape shape) {
        super(component);
        this.shape = shape;
    }
    public PShape getShape() {
        return shape;
    }

    public void setShape(PShape newShape){
        this.shape = newShape;
    }

    public PVector getPosition() {
        return getParent().getPosition();
    }

    /**
     * Questo evento viene chiamato quando il collider esce da una collisione.
     * @param second Collider con cui è entrato in collisione.
     */
    public abstract void OnExit(PCollider second);

    /**
     * Questo evento viene chiamato mentre il collider è i in collisione.
     * @param second Collider con cui è entrato in collisione.
     */
    public abstract void OnColliding(PCollider second);
}
