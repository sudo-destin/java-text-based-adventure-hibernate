package com.example.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import com.example.entity.Room;
import com.example.entity.command.DirectionCommand;

/**
 * Service spécialisé dans les opérations en base de données concernant les objets Room
 */
public class RoomRepository
{
    /**
     * Le gestionnaire d'entités permettant l'accès effectif à la base de données
     */
    private EntityManager entityManager;

    /**
     * Crée un nouveau service
     */
    public RoomRepository()
    {
        // Crée une instance du gestionnaire d'entités
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("TextBasedAdventure");
        entityManager = factory.createEntityManager();
    }

    /**
     * @param id L'identifiant en base de données du lieu recherché
     * @return Le lieu correspondant à l'identifiant spécifié
     */
    public Room findById(int id)
    {
        return entityManager.find(Room.class, id);
    }

    /**
     * @param fromRoom Le lieu de départ
     * @param direction La direction à emprunter
     * @return Le lieu dans lequel on arrive lorsqu'on part du lieu spécifié et qu'on emprunte la direction spécifiée
     */
    public Room findByFromRoomAndDirection(Room fromRoom, DirectionCommand direction)
    {
        try {
            return entityManager.createQuery("SELECT room FROM Room room JOIN room.connectionsTo AS connection WHERE connection.fromRoom = :fromRoom AND connection.direction = :direction", Room.class)
                .setParameter("fromRoom", fromRoom)
                .setParameter("direction", direction)
                .getSingleResult();
        }
        catch (NoResultException exception) {
            return null;
        }
    }
}
