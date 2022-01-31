package com.example.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.example.entity.Item;
import com.example.entity.Room;
import com.example.entity.command.Command;
import com.example.entity.command.DirectionCommand;
import com.example.repository.ItemRepository;
import com.example.repository.RoomRepository;
import com.example.repository.command.CommandRepository;
import com.example.repository.command.DirectionCommandRepository;

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
    private List<Command> commands;

    private RoomRepository roomRepository;
    private ItemRepository itemRepository;
    private CommandRepository commandRepository;
    private DirectionCommandRepository directionRepository;

    /**
     * Crée une nouvelle partie
     */
    public Game()
    {
        scanner = new Scanner(System.in);
        roomRepository = new RoomRepository();
        itemRepository = new ItemRepository();
        commandRepository = new CommandRepository();
        directionRepository = new DirectionCommandRepository();
    }

    /**
     * Initialise la partie
     */
    public void setup()
    {
        // Définit que la partie est en cours d'exécution
        isRunning = true;
        // Initialise la liste de toutes les commandes existantes
        commands = commandRepository.findAll();
        // Définit le lieu de départ de jeu
        setCurrentRoom(roomRepository.findById(1));
    }

    /**
     * Décrit un cycle d'exécution de la partie
     */
    public void update()
    {
        System.out.println("");
        
        // Attend une saisie utilisateur
        String userInput = scanner.nextLine();
        
        // Cherche, parmi toutes les commandes existantes, laquelle est capable de traiter la saisie utilisateur
        for (Command command : commands) {
            if (command.match(userInput)) {
                // Délègue l'exécution du comportement associé à la commande à un interpréteur
                CommandInterpreter interpreter = new CommandInterpreter(this);
                command.delegateTo(interpreter);
                return;
            }
        }

        // Si aucun commande n'est capable de traiter la saisie utilisateur
        System.out.println("Invalid command!");
    }

    /**
     * Décrit le lieu actuel
     */
    public void describeCurrentRoom()
    {
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

    /**
     * @return Le lieu dans lequel le joueur se trouve actuellement
     */
    public Room getCurrentRoom()
    {
        return currentRoom;
    }

    /**
     * Modifie le lieu dans lequel le joueur se trouve actuellement
     * @param room Le nouveau lieu
     */
    public void setCurrentRoom(Room room)
    {
        this.currentRoom = room;
        describeCurrentRoom();
    }
}
