package com.example.repository;

import javax.persistence.NoResultException;

import com.example.entity.Room;
import com.example.entity.command.DirectionCommand;

/**
 * Service spécialisé dans les opérations en base de données concernant les objets Room
 */
public class RoomRepository extends Repository<Room>
{
    /**
     * Crée un nouveau service
     */
    public RoomRepository()
    {
        super(Room.class);
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
