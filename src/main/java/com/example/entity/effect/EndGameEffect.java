package com.example.entity.effect;

import com.example.game.EffectInterpreter;

/**
 * Représente un effet permettant de terminer la partie en cours
 */
public class EndGameEffect extends Effect
{
    /**
     * Délègue l'exécution de l'effet à un interpréteur
     * @param interpreter L'interpréteur qui doit exécuter le comportement associé à l'effet
     */
    public void delegateTo(EffectInterpreter interpreter)
    {
        interpreter.trigger(this);
    }
}
