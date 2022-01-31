package com.example.repository.effect;

import java.util.List;


import com.example.entity.Item;
import com.example.entity.command.ItemCommand;
import com.example.entity.effect.Effect;
import com.example.repository.Repository;

/**
 * Service spécialisé dans les opérations en base de données concernant les objets MessageEffect
 */
public class EffectRepository extends Repository<Effect>
{
    public EffectRepository()
    {
        super(Effect.class);
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
