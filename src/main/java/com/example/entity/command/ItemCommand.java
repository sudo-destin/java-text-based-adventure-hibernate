package com.example.entity.command;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.entity.effect.MessageEffect;

/**
 * Représente une action que le joueur peut utiliser sur un élément interactif
 */
@Entity
@Table(name = "item_command")
public class ItemCommand
{
    /**
     * L'identifiant en base de données
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    /**
     * Le nom de la commande
     */
    @Column(name = "name")
    private String name;
    /**
     * Le message par défaut à afficher lorsque la commande est utilisée avec un élément interactif non prévu pour
     */
    @Column(name = "default_message")
    private String defaultMessage;
    /**
     * La liste de tous les effets que cette commande peut produire
     */
    @OneToMany
    @JoinColumn(name = "command_id")
    private List<MessageEffect> effects;

    /**
     * @return Le nom de la commande
     */
    public String getName()
    {
        return name;
    }

    /**
     * @return Le message par défaut à afficher lorsque la commande est utilisée avec un élément interactif non prévu pour
     */
    public String getDefaultMessage()
    {
        return defaultMessage;
    }
}
