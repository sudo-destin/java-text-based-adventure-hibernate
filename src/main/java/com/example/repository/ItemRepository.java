package com.example.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.example.entity.Item;
import com.example.entity.Room;

/**
 * Service spécialisé dans les opérations en base de données concernant les objets Item
 */
public class ItemRepository
{
    /**
     * Le gestionnaire d'entités permettant l'accès effectif à la base de données
     */
    private EntityManager entityManager;

    /**
     * Crée un nouveau service
     */
    public ItemRepository()
    {
        // Crée une instance du gestionnaire d'entités
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("TextBasedAdventure");
        entityManager = factory.createEntityManager();
    }

    /**
     * @param room Le lieu dans lequel chercher les éléments
     * @param visible La visibilité des éléments recherchés
     * @return La liste des éléments présents dans un lieu avec une visibilité spécifiée
     */
    public List<Item> findByRoomAndVisible(Room room, boolean visible)
    {
        List<Item> items = entityManager.createQuery("SELECT item FROM Item item WHERE room = :room AND visible = :visible" , Item.class)
            .setParameter("room", room)
            .setParameter("visible", visible)
            .getResultList();
        return items;
    }
}
