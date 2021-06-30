package com.dev.mud_backend.services;

import com.dev.mud_backend.exceptions.ResourceNotFoundException;
import com.dev.mud_backend.models.Cell;
import com.dev.mud_backend.models.Monster;
import com.dev.mud_backend.models.Player;
import com.dev.mud_backend.repository.MonsterRepository;
import com.dev.mud_backend.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service(value = "playerService")
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepo;

    @Autowired
    private MonsterService monsterService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private CellService cellService;

    @Autowired
    private MonsterRepository monsterRepo;


    @Override
    public Player updatePlayer(Player player,long id) {
        Player target_player = new Player();

        if (playerRepo.findByPlayerid(id) != null){
            target_player = playerRepo.findByPlayerid(id);


            if (player.getPlayerName() != null){
                target_player.setPlayerName(player.getPlayerName());
            }
            // Stats are below
            if (player.getPlayerHealth() != target_player.getPlayerHealth()) {
                target_player.setPlayerHealth(player.getPlayerHealth());
            }

            if (player.getMaxHealth() != target_player.getMaxHealth()) {
                target_player.setMaxHealth(player.getMaxHealth());
            }

            if (player.getPlayerAgility() != target_player.getPlayerAgility()){
                target_player.setPlayerAgility(player.getPlayerAgility());
            }

            if (player.getPlayerIntellect() != target_player.getPlayerIntellect()){
                target_player.setPlayerIntellect(player.getPlayerIntellect());
            }

            if (player.getPlayerStamina() != target_player.getPlayerStamina()) {
                target_player.setPlayerStamina(player.getPlayerStamina());

            }

            if (player.getPlayerStrength() != target_player.getPlayerStrength()) {
                target_player.setPlayerStrength(player.getPlayerStrength());
            }

//            if (target_player.getItemsList() != null ) {
//                if target.player.getItemsList
//                target_player.setItemsList(player.getItemsList());
//            }
            // Position
            if (player.getPlayerx() != target_player.getPlayerx()){
                target_player.setPlayerx(player.getPlayerx());
            }

            if (player.getPlayery() != target_player.getPlayery()){
                target_player.setPlayery(player.getPlayerx());
            }

            if (player.getCellId() != target_player.getCellId()) {
                target_player.setCellId(player.getCellId());
                // we are going to need to update the cell that it moved from and move into
                // first cell that we moved out of
                Cell prevCell = cellService.getCellById(target_player.getCellId());

                cellService.updateCell(prevCell, target_player.getCellId());
                // Second the cell that we moved into
                Cell nextCell = cellService.getCellById(player.getCellId());
                cellService.updateCell(prevCell, player.getCellId());

            }

            if (player.getStatus() != null ) {
                target_player.setStatus(player.getStatus());
            }


            playerRepo.save(target_player);

            return target_player;
        }
        else{
            throw new ResourceNotFoundException(player.getPlayerid() + " player not found");
        }
    }

    @Transactional
    @Override
    public Player findById(long id) throws ResourceNotFoundException {
        Player player = playerRepo.findByPlayerid(id);

        if (player == null) {
            throw new ResourceNotFoundException("The player by the id" + id + " was not found");
        }
        else {
            return player;
        }

    }
}
