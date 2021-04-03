package com.dev.mud_backend.services;

import com.dev.mud_backend.exceptions.ResourceNotFoundException;
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
    private MonsterRepository monsterRepo;

    @Override
    public long playerDamaged(long damage, long playerid) {
        Player player = playerRepo.findByPlayerid(playerid);

        long playerHp = player.getPlayerHealth();

        playerHp = playerHp - damage;

        // now we update the players hp

        if (playerHp > 0)
        {
            player.setPlayerHealth(playerHp);
        }
        else{
            // set player status to dead
        }

        playerRepo.save(player);

        return playerHp;
    }

    @Override
    public long playerHealed(long heal_amount, long playerid) {
        Player player = playerRepo.findByPlayerid(playerid);

        long playerHp = player.getPlayerHealth();

        playerHp = playerHp + heal_amount;

        // we need max hp attribute eventually

        player.setPlayerHealth(playerHp);

        playerRepo.save(player);

        return playerHp;
    }

    @Override
    public long playerAttack( long damage_num, long player_id, long monster_id) {
        // we need to get find our monster and player in the data base
        Monster target_monster = monsterService.findById(monster_id);
        Player player = playerService.findById(player_id);

        // this is were we would calculate the players attack damage
        long player_damage = 5;

        //now we set the monsters new health and check if it is dead
        long monster_health = target_monster.getMonsterHealth();

        monster_health = monster_health - player_damage;

        if (monster_health < 0){
            // we change the monster status to dead
            monsterService.MonsterDeath(monster_id);
        }else{
            target_monster.setMonsterHealth(monster_health);
        }

        monsterRepo.save(target_monster);


        return monster_health;
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
