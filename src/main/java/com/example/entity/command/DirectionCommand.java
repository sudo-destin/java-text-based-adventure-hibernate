package com.example.entity.command;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.entity.RoomConnection;
import com.example.game.CommandInterpreter;

/**
 * Représente une direction que le joueur peut emprunter pour se déplacer d'un lieu à l'autre.
 */
@Entity
@Table(name = "direction_command")
public class DirectionCommand extends Command
{
    /**
     * La liste de tous les passages entre des lieux qui empruntent cette direction
     */
    @OneToMany
    @JoinColumn(name = "direction_id")
    private List<RoomConnection> connections;

    /**
     * Délègue l'exécution de la commande à un interpréteur
     * @param interpreter L'interpréteur qui doit exécuter le comportement associé à la commande
     */
    public void delegateTo(CommandInterpreter interpreter)
    {
        interpreter.execute(this);
    }
}
