package com.example.repository;

import java.util.List;

import javax.persistence.NoResultException;


import com.example.entity.Item;
import com.example.entity.Room;

/**
 * Service spécialisé dans les opérations en base de données concernant les objets Item
 */
public class ItemRepository extends Repository<Item>
{
    /**
     * Crée un nouveau service
     */
    public ItemRepository()
    {
        super(Item.class);
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

    /**
     * @param room Le lieu dans lequel effectuer la recherche
     * @param name Le nom de l'élément interactif recherché
     * @return L'élément interactif visible correspondant au nom spécifié dans le lieu spécifié
     */
    public Item findByRoomAndName(Room room, String name)
    {
        try {
            return entityManager.createQuery("SELECT item FROM Item item WHERE name = :name AND room = :room AND visible = true", Item.class)
                .setParameter("name", name)
                .setParameter("room", room)
                .getSingleResult();
        }
        catch (NoResultException exception) {
            return null;
        }
    }
}
