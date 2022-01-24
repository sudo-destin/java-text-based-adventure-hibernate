package com.example.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import com.example.entity.Room;

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
     * @param name Le nom du lieu recherché
     * @return Le lieu correspondant au nom demandé
     */
    public Room findRoomByName(String name)
    {
        try {
            Room room = entityManager.createQuery("SELECT room FROM Room room WHERE name = :name" , Room.class)
                .setParameter("name", name)
                .getSingleResult();
            return room;
        }
        // Si la requête ne renvoie aucun résultat, renvoie null au lieu d'arrêter l'application
        catch (NoResultException exception) {
            return null;
        }
    }
}
