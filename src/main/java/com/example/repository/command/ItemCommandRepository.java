package com.example.repository.command;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.example.entity.command.ItemCommand;

/**
 * Service spécialisé dans les opérations en base de données concernant les objets ItemCommand
 */
public class ItemCommandRepository
{
    /**
     * Le gestionnaire d'entités permettant l'accès effectif à la base de données
     */
    private EntityManager entityManager;

    /**
     * Crée un nouveau service
     */
    public ItemCommandRepository()
    {
        // Crée une instance du gestionnaire d'entités
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("TextBasedAdventure");
        entityManager = factory.createEntityManager();
    }

    /**
     * @return La liste de toutes les commandes existantes
     */
    public List<ItemCommand> findAll()
    {
        return entityManager.createQuery("SELECT command FROM ItemCommand command", ItemCommand.class)
            .getResultList();
    }
}
