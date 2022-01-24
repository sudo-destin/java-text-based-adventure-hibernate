package com.example.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.example.entity.Item;
import com.example.entity.Room;
import com.example.repository.ItemRepository;
import com.example.repository.RoomRepository;


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
    private RoomRepository roomRepository;
    private ItemRepository itemRepository;

    /**
     * Crée une nouvelle partie
     */
    public Game()
    {
        scanner = new Scanner(System.in);
        roomRepository = new RoomRepository();
        itemRepository = new ItemRepository();
    }

    /**
     * Initialise la partie
     */
    public void setup()
    {
        // Définit que la partie est en cours d'exécution
        isRunning = true;
    }

    /**
     * Décrit un cycle d'exécution de la partie
     */
    public void update()
    {
        System.out.println("");

        // Attend une saisie utilisateur
        String userInput = scanner.nextLine();

        // Cherche le lieu portant le nom saisi par l'utilisateur
        Room room = roomRepository.findRoomByName(userInput);
        // Si aucun lieu ne porte le nom saisi par l'utilisateur
        if (room == null) {
            System.out.println("This room doesn't exist!");
            return;
        }
        
        // Affiche la description du lieu
        System.out.println(room.getDescription());

        // Affiche la liste de tous les éléments interactifs disponibles dans le lieu
        List<Item> items = itemRepository.findByRoomAndVisible(room, true);
        
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
