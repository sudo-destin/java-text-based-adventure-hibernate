package com.example.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.entity.Item;
import com.example.entity.Room;
import com.example.entity.command.DirectionCommand;
import com.example.entity.command.ItemCommand;
import com.example.entity.effect.MessageEffect;
import com.example.repository.ItemRepository;
import com.example.repository.RoomRepository;
import com.example.repository.command.DirectionCommandRepository;
import com.example.repository.command.ItemCommandRepository;
import com.example.repository.effect.MessageEffectRepository;


/**
 * Représente une partie jouée par le joueur.
 */
public class Game
{
    /**
     * Le gestionnaire qui surveille les saisies dans la console
     */
    private Scanner scanner;
    /**
     * Le jeu est-il en cours d'exécution?
     */
    private boolean isRunning;
    /**
     * Le lieu dans lequel le joueur se trouve actuellement
     */
    private Room currentRoom;
    /**
     * La liste de toutes les commandes existantes
     */
    private List<ItemCommand> commands;

    private RoomRepository roomRepository;
    private ItemRepository itemRepository;
    private DirectionCommandRepository directionRepository;
    private ItemCommandRepository itemCommandRepository;
    private MessageEffectRepository effectRepository;

    /**
     * Crée une nouvelle partie
     */
    public Game()
    {
        scanner = new Scanner(System.in);
        roomRepository = new RoomRepository();
        itemRepository = new ItemRepository();
        directionRepository = new DirectionCommandRepository();
        itemCommandRepository = new ItemCommandRepository();
        effectRepository = new MessageEffectRepository();
    }

    /**
     * Initialise la partie
     */
    public void setup()
    {
        // Définit que la partie est en cours d'exécution
        isRunning = true;
        // Définit le lieu de départ de jeu
        currentRoom = roomRepository.findById(1);
        // Initialise la liste de toutes les commandes existantes
        commands = itemCommandRepository.findAll();
    }

    /**
     * Décrit un cycle d'exécution de la partie
     */
    public void update()
    {
        System.out.println("");

        // Affiche la description du lieu actuel
        System.out.println(currentRoom.getDescription());

        // Affiche la liste de tous les éléments interactifs disponibles dans le lieu actuel
        List<Item> items = itemRepository.findByRoomAndVisible(currentRoom, true);
        if (items.isEmpty()) {
            System.out.println("No available items.");
        } else {
            System.out.print("Available items: ");
            List<String> itemNames = new ArrayList<>();
            for (Item item : items) {
                itemNames.add(item.getName());
            }
            System.out.println(String.join(", ", itemNames) + ".");
        }

        // Affiche la liste de toutes les éléments directions disponibles à partir du lieu actuel
        List<DirectionCommand> directions = directionRepository.findByFromRoom(currentRoom);
        if (directions.isEmpty()) {
            System.out.println("No available directions.");
        } else {
            System.out.print("Available directions: ");
            List<String> directionNames = new ArrayList<>();
            for (DirectionCommand direction : directions) {
                directionNames.add(direction.getName());
            }
            System.out.println(String.join(", ", directionNames) + ".");
        }
        
        // Attend une saisie utilisateur
        String userInput = scanner.nextLine();

        // Cherche une correspondance avec toutes les commandes existantes
        for (ItemCommand command : commands) {
            // Vérifie si la saisie utilisateur contient le nom de la commande suivi d'un nom d'objet
            Pattern pattern = Pattern.compile("^" + command.getName() + "\\s+(.+)$");
            Matcher matcher = pattern.matcher(userInput);
            // Si la saisie utilisateur correspond à cette commande
            if (matcher.matches()) {
                // Récupère le nom de l'élément interactif qui suit la commande
                String itemName = matcher.group(1);
                // Cherche l'élément interactif correspondant au nom saisi par l'utilisateur
                Item item = itemRepository.findByRoomAndName(currentRoom, itemName);
                // Si le nom fourni ne correspond à aucun élémént interactif présent dans le lieu actuel et visible
                if (item == null) {
                    System.out.println("There is no such item here!");
                    return;
                }
                // Récupère la liste des effets prévus lorsque l'on utilise cette commande sur cet élément
                List<MessageEffect> effects = effectRepository.findByCommandAndItem(command, item);
                // S'il n'est pas possible d'utiliser cette commande sur cet élément interactif
                if (effects.isEmpty()) {
                    // Affiche le message par défaut de la commande
                    System.out.println(command.getDefaultMessage());
                    return;
                }
                // Sinon, déclenche tous les effets successivement
                for (MessageEffect effect : effects) {
                    System.out.println(effect.getMessage());
                }
                return;
            }
        }

        // Cherche la direction portant le nom saisi par l'utilisateur
        DirectionCommand direction = directionRepository.findByName(userInput);
        // Si aucune direction ne porte le nom saisi par l'utilisateur
        if (direction == null) {
            System.out.println("This direction doesn't exist!");
            return;
        }

        // Cherche le lieu dans lequel on arrive lorsqu'on part du lieu actuel
        // et qu'on emprunte la direction saisie par l'utilisateur
        Room targetRoom = roomRepository.findByFromRoomAndDirection(currentRoom, direction);
        // Si la direction saisie par l'utilisateur n'amène à aucun lieu en partant du lieu actuel
        if (targetRoom == null) {
            System.out.println("You cannot go into that direction!");
            return;
        }
        // Chqnge le lieu actuel
        currentRoom = targetRoom;
    }

    /**
     * Permet de savoir si la partie est en cours
     * @return
     */
    public boolean isRunning()
    {
        return isRunning;
    }

    /**
     * Arrête la partie
     */
    public void terminate()
    {
        isRunning = false;
    }
}
