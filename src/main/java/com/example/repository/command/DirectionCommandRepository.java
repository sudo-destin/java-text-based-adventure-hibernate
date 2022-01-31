package com.example.repository.command;

import java.util.List;

import javax.persistence.NoResultException;

import com.example.entity.Room;
import com.example.entity.command.DirectionCommand;
import com.example.repository.Repository;

/**
 * Service spécialisé dans les opérations en base de données concernant les objets DirectionCommand
 */
public class DirectionCommandRepository extends Repository<DirectionCommand>
{

    /**
     * Crée un nouveau service
     */
    public DirectionCommandRepository()
    {
        super(DirectionCommand.class);
    }

    /**
     * @param name Le nom de la direction recherchée
     * @return La direction correspondant au nom spécifié
     */
    public DirectionCommand findByName(String name)
    {
        try {
            return entityManager.createQuery("SELECT direction FROM DirectionCommand direction WHERE name = :name", DirectionCommand.class)
                .setParameter("name", name)
                .getSingleResult();
        }
        // Si la requête ne renvoie aucun résultat, renvoie null au lieu d'arrêter l'application
        catch (NoResultException exception) {
            return null;
        }
    }

    /**
     * @param fromRoom Le lieu de départ
     * @return La liste de toutes les directions que l'on peut emprunter en partant du lieu spécifié
     */
    public List<DirectionCommand> findByFromRoom(Room fromRoom)
    {
        return entityManager.createQuery("SELECT direction FROM DirectionCommand direction JOIN direction.connections AS connection WHERE connection.fromRoom = :fromRoom", DirectionCommand.class)
            .setParameter("fromRoom", fromRoom)
            .getResultList();
    }
}
