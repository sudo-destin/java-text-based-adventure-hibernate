package com.example.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Représente un lieu de l'univers de jeu dans lequel le joueur peut se trouver.
 */
@Entity
@Table(name = "room")
public class Room
{
    /**
     * L'identifiant en base de données
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    /**
     * Le nom du lieu
     */
    @Column(name = "name")
    private String name;
    /**
     * La description du lieu  
     */
    @Column(name = "description")
    private String description;
    /**
     * La liste de tous les éléments interactifs présents dans ce lieu
     */
    @OneToMany
    @JoinColumn(name = "room_id")
    private List<Item> items;

    /**
     * @return L'identifiant en base de données
     */
    public int getId()
    {
        return id;
    }

    /**
     * @return Le nom du lieu
     */
    public String getName()
    {
        return name;
    }

    /**
     * @return La description du lieu
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * @return La liste de tous les éléments interactifs présents dans ce lieu
     */
    public List<Item> getItems()
    {
        return items;
    }
}
