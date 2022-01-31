package com.example.repository.command;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.example.entity.command.Command;
import com.example.game.CommandInterpreter;

/**
 * Représente une commande permettant d'afficher la description du lieu actuel
 */
@Entity
@Table(name = "look_command")
public class LookCommand extends Command
{
    /**
     * Délègue l'exécution de la commande à un interpréteur
     * @param interpreter L'interpréteur qui doit exécuter le comportement associé à la commande
     */
    public void delegateTo(CommandInterpreter interpreter)
    {
        interpreter.execute(this);
    }
}
