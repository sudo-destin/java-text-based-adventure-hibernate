package com.example.game;

import com.example.entity.effect.ChangeCurrentRoomEffect;
import com.example.entity.effect.EndGameEffect;
import com.example.entity.effect.MessageEffect;

/**
 * Service spécialisé dans l'exécution des comportements associés aux effets
 */
public class EffectInterpreter
{
    /**
     * La partie en cours
     */
    private Game game;

    /**
     * Crée un nouveau service
     * @param game La partie en cours
     */
    public EffectInterpreter(Game game)
    {
        this.game = game;
    }

    /**
     * Exécute le comportement d'un effet permettant d'afficher un message
     * @param effect L'effet à déclencher
     */
    public void trigger(MessageEffect effect)
    {
        System.out.println(effect.getMessage());
    }

    /**
     * Exécute le comportement d'un effet permettant de changer de lieu
     * @param effect L'effet à déclencher
     */
    public void trigger(ChangeCurrentRoomEffect effect)
    {
        game.setCurrentRoom(effect.getTargetRoom());
    }

    /**
     * Exécute le comportement d'un effet permettant de terminer la partie en cours
     * @param effect L'effet à déclencher
     */
    public void trigger(EndGameEffect effect)
    {
        game.terminate();
    }
}
