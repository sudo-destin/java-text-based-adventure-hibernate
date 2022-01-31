package com.example.repository.command;

import com.example.entity.command.ItemCommand;
import com.example.repository.Repository;

/**
 * Service spécialisé dans les opérations en base de données concernant les objets ItemCommand
 */
public class ItemCommandRepository extends Repository<ItemCommand>
{

    /**
     * Crée un nouveau service
     */
    public ItemCommandRepository()
    {
        super(ItemCommand.class);
    }

}
