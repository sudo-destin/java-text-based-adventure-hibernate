package com.example.repository.command;

import com.example.entity.command.Command;
import com.example.repository.Repository;

/**
 * Service spécialisé dans les opérations en base de données concernant les objets Command
 */
public class CommandRepository extends Repository<Command>
{
    /**
     * Crée un nouveau service
     */
    public CommandRepository()
    {
        super(Command.class);
    }

}
