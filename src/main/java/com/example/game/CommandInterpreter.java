package com.example.game;

import java.util.List;

import com.example.entity.Item;
import com.example.entity.Room;
import com.example.entity.command.DirectionCommand;
import com.example.entity.command.ExitCommand;
import com.example.entity.command.ItemCommand;
import com.example.entity.effect.Effect;
import com.example.repository.ItemRepository;
import com.example.repository.RoomRepository;
import com.example.repository.command.LookCommand;
import com.example.repository.effect.EffectRepository;

/**
 * Service spécialisé dans l'exécution des comportements associés aux commandes
 */
public class CommandInterpreter
{
    /**
     * La partie en cours
     */
    private Game game;
    private RoomRepository roomRepository;
    private ItemRepository itemRepository;
    private EffectRepository effectRepository;

    /**
     * Crée un nouveau service
     * @param game La partie en cours
     */
    public CommandInterpreter(Game game)
    {
        this.game = game;
        roomRepository = new RoomRepository();
        itemRepository = new ItemRepository();
        effectRepository = new EffectRepository();
    }

    /**
     * Exécute le comportement d'une commande permettant de changer de lieu
     * @param command La commande à exécuter
     */
    public void execute(DirectionCommand command)
    {
        // Cherche le lieu dans lequel on arrive lorsqu'on part du lieu actuel
        // et qu'on emprunte cette direction
        Room targetRoom = roomRepository.findByFromRoomAndDirection(game.getCurrentRoom(), command);
        // Si cette direction n'amène à aucun lieu en partant du lieu actuel
        if (targetRoom == null) {
            System.out.println("You cannot go into that direction!");
            return;
        }
        // Change le lieu actuel
        game.setCurrentRoom(targetRoom);
    }

    /**
     * Exécute le comportement d'une commande permettant d'utiliser une action sur un élément interactif
     * @param command La commande à exécuter
     */
    public void execute(ItemCommand command)
    {
        // Récupère le nom de l'élément interactif qui suit la commande
        String itemName = command.getCurrentItemName();
        // Cherche l'élément interactif correspondant au nom saisi par l'utilisateur
        Item item = itemRepository.findByRoomAndName(game.getCurrentRoom(), itemName);
        // Si le nom fourni ne correspond à aucun élémént interactif présent dans le lieu actuel et visible
        if (item == null) {
            System.out.println("There is no such item here!");
            return;
        }
        // Récupère la liste des effets prévus lorsque l'on utilise cette commande sur cet élément
        List<Effect> effects = effectRepository.findByCommandAndItem(command, item);
        // S'il n'est pas possible d'utiliser cette commande sur cet élément interactif
        if (effects.isEmpty()) {
            // Affiche le message par défaut de la commande
            System.out.println(command.getDefaultMessage());
            return;
        }
        // Sinon, déclenche tous les effets successivement
        EffectInterpreter interpreter = new EffectInterpreter(game);
        for (Effect effect : effects) {
            effect.delegateTo(interpreter);
        }
        return;
    }

    /**
     * Exécute le comportement d'une commande permettant de sortir du jeu
     * @param command La commande à exécuter
     */
    public void execute(ExitCommand command)
    {
        game.terminate();
    }

    /**
     * Exécute le comportement d'une commande permettant de décrire le lieu actuel
     * @param command La commande à exécuter
     */
    public void execute(LookCommand command)
    {
        game.describeCurrentRoom();
    }
}
