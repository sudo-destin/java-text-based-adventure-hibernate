package com.example.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Service spécialisé dans les opérations en bases de données
 */
public abstract class Repository<T>
{

    protected Class<T> entityType;

    protected EntityManager entityManager;

    public Repository(Class<T> entityType)
    {
        this.entityType = entityType;
        // Crée une instance du gestionnaire d'entités
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("TextBasedAdventure");
        entityManager = factory.createEntityManager();
    }

    public List<T> findAll()
    {
        return entityManager.createQuery(String.format("SELECT entity FROM %s entity", entityType.getName()), entityType)
            .getResultList();
    }

    public T findById(int id)
    {
        return entityManager.find(entityType, id);
    }
}
