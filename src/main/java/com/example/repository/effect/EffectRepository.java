package com.example.repository.effect;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.example.entity.Item;
import com.example.entity.command.ItemCommand;
import com.example.entity.effect.Effect;

/**
 * Service spécialisé dans les opérations en base de données concernant les objets MessageEffect
 */
public class EffectRepository
{
    /**
     * Le gestionnaire d'entités permettant l'accès effectif à la base de données
     */
    private EntityManager entityManager;

    /**
     * Crée un nouveau service
     */
    public EffectRepository()
    {
        // Crée une instance du gestionnaire d'entités
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("TextBasedAdventure");
        entityManager = factory.createEntityManager();
    }

    /**
     * @param command La commande qui déclenche les effets
     * @param item L'élément interactif qui déclenche les effets lorsque la commande est utilisée avec
     * @return La liste des effets correspondant à l'utilisation de la commande spécifiée sur l'élément interactif spécifié
     */
    public List<Effect> findByCommandAndItem(ItemCommand command, Item item)
    {
        return entityManager.createQuery("SELECT effect FROM Effect effect WHERE command = :command AND item = :item", Effect.class)
            .setParameter("command", command)
            .setParameter("item", item)
            .getResultList();
    }
}
