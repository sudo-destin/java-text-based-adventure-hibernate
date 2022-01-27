package com.example.entity.effect;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.example.game.EffectInterpreter;

/**
 * Représente un effet permettant d'afficher un message
 */
@Entity
@DiscriminatorValue("Message")
public class MessageEffect extends Effect
{
    /**
     * Le message à afficher
     */
    @Column(name = "message")
    private String message;

    /**
     * Délègue l'exécution de l'effet à un interpréteur
     * @param interpreter L'interpréteur qui doit exécuter le comportement associé à l'effet
     */
    public void delegateTo(EffectInterpreter interpreter)
    {
        interpreter.trigger(this);
    }
    
    /**
     * @return Le message à afficher
     */
    public String getMessage()
    {
        return message;
    }
}
