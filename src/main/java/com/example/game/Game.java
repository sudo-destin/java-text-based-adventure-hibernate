package com.example.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.example.entity.Item;
import com.example.entity.Room;
import com.example.entity.command.DirectionCommand;
import com.example.repository.ItemRepository;
import com.example.repository.RoomRepository;
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
    private RoomRepository roomRepository;
    private ItemRepository itemRepository;
    private DirectionCommandRepository directionRepository;

    /**
     * Crée une nouvelle partie
     */
    public Game()
    {
        scanner = new Scanner(System.in);
        roomRepository = new RoomRepository();
        itemRepository = new ItemRepository();
        directionRepository = new DirectionCommandRepository();
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
