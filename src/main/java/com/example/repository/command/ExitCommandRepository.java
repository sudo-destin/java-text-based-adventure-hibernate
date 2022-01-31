package com.example.repository.command;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.example.entity.command.ExitCommand;

/**
 * Service spécialisé dans les opérations en base de données concernant les objets ExitCommand
 */
public class ExitCommandRepository
{
    /**
     * Le gestionnaire d'entités permettant l'accès effectif à la base de données
     */
    private EntityManager entityManager;

    /**
     * Crée un nouveau service
     */
    public ExitCommandRepository()
    {
        // Crée une instance du gestionnaire d'entités
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("TextBasedAdventure");
        entityManager = factory.createEntityManager();
    }

    /**
     * @return Toutes les commandes existantes permettant de sortir du jeu
     */
    public List<ExitCommand> findAll()
    {
        return entityManager.createQuery("SELECT command FROM ExitCommand command", ExitCommand.class)
            .getResultList();
    }
}
