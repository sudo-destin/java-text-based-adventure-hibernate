package com.example.entity.effect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.entity.Item;
import com.example.entity.command.ItemCommand;

/**
 * Représente un effet permettant d'afficher un message
 */
@Entity
@Table(name = "effect")
public class MessageEffect
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
     * Le message à afficher
     */
    @Column(name = "message")
    private String message;

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

    /**
     * @return Le message à afficher
     */
    public String getMessage()
    {
        return message;
    }
}
