package com.example.entity.effect;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.entity.Item;
import com.example.entity.command.ItemCommand;
import com.example.game.EffectInterpreter;

/**
 * Représente un effet qui se déclenche lorsque le joueur utilise une commande sur un élément interactif
 */
@Entity
@Table(name = "effect")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public abstract class Effect
{
    /**
     * L'identifiant en base de données
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    /**
     * La commande qui déclenche l'effet
     */
    @ManyToOne
    @JoinColumn(name = "command_id")
    private ItemCommand command;
    /**
     * L'élément interactif qui déclenche l'effet lorsque la commande est utilisée avec
     */
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    /**
     * Déléguer l'exécution de l'effet à un interpréteur
     * @param interpreter L'interpréteur qui doit exécuter le comportement associé à l'effet
     */
    abstract public void delegateTo(EffectInterpreter interpreter);

    /**
     * @return L'identifiant en base de données
     */
    public int getId()
    {
        return id;
    }

    /**
     * @return L'élément interactif qui déclenche l'effet lorsque la commande est utilisée avec
     */
    public Item getItem()
    {
        return item;
    }

    /**
     * @return La commande qui déclenche l'effet
     */
    public ItemCommand getCommand()
    {
        return command;
    }
}
