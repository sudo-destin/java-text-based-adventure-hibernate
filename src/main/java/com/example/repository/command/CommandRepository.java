package com.example.repository.command;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.example.entity.command.Command;

/**
 * Service spécialisé dans les opérations en base de données concernant les objets Command
 */
public class CommandRepository
{
    /**
     * Le gestionnaire d'entités permettant l'accès effectif à la base de données
     */
    private EntityManager entityManager;

    /**
     * Crée un nouveau service
     */
    public CommandRepository()
    {
        // Crée une instance du gestionnaire d'entités
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("TextBasedAdventure");
        entityManager = factory.createEntityManager();
    }

    /**
     * @return Toutes les commandes existantes
     */
    public List<Command> findAll()
    {
        return entityManager.createQuery("SELECT command FROM Command command", Command.class)
            .getResultList();
    }
}
