package com.example.entity.effect;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.entity.Room;
import com.example.game.EffectInterpreter;

/**
 * Représente un effet permettant de changer de lieu
 */
@Entity
@DiscriminatorValue("ChangeRoom")
public class ChangeCurrentRoomEffect extends Effect
{
    /**
     * Le lieu dans lequel l'effet doit déplacer le joueur
     */
    @ManyToOne
    @JoinColumn(name = "target_room_id")
    private Room targetRoom;

    /**
     * Délègue l'exécution de l'effet à un interpréteur
     * @param interpreter L'interpréteur qui doit exécuter le comportement associé à l'effet
     */
    public void delegateTo(EffectInterpreter interpreter)
    {
        interpreter.trigger(this);
    }

    /**
     * @return Le lieu dans lequel l'effet doit déplacer le joueur
     */
    public Room getTargetRoom()
    {
        return targetRoom;
    }
}
