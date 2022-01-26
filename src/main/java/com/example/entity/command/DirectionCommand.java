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

import com.example.entity.RoomConnection;

/**
 * Représente une direction que le joueur peut emprunter pour se déplacer d'un lieu à l'autre.
 */
@Entity
@Table(name = "direction_command")
public class DirectionCommand
{
    /**
     * L'identifiant en base de données
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    /**
     * Le nom de la direction
     */
    @Column(name = "name")
    private String name;
    /**
     * La liste de tous les passages entre des lieux qui empruntent cette direction
     */
    @OneToMany
    @JoinColumn(name = "direction_id")
    private List<RoomConnection> connections;

    /**
     * @return L'identifiant en base de données
     */
    public int getId()
    {
        return id;
    }

    /**
     * @return Le nom de la direction
     */
    public String getName()
    {
        return name;
    }
}
