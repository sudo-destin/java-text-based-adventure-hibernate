package com.example.repository.command;

import com.example.entity.command.ExitCommand;
import com.example.repository.Repository;

/**
 * Service spécialisé dans les opérations en base de données concernant les objets ExitCommand
 */
public class ExitCommandRepository extends Repository<ExitCommand>
{

    /**
     * Crée un nouveau service
     */
    public ExitCommandRepository()
    {
        super(ExitCommand.class);
    }

}
