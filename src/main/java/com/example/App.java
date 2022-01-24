package com.example;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.example.entity.Room;
import com.example.game.Game;

/**
 * Hello world!
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        // Crée une nouvelle partie
        Game game = new Game();
        // Initialise la partie
        game.setup();
        // Tant que la partie est en cours d'exécution
        while (game.isRunning()) {
            // Exécute un tour de jeu
            game.update();
        }
    }
}
