package com.dev.mud_backend.services;

import com.dev.mud_backend.models.Monster;
import com.dev.mud_backend.models.Player;
import com.dev.mud_backend.repository.MonsterRepository;
import com.dev.mud_backend.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "playerService")
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    MonsterRepository monsterRepo;

    @Autowired
    PlayerRepository playerRepo;

    @Autowired
    MonsterService monsterService;

    @Override
    public long playerDamaged(int damage, Player player) {
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


        return playerHp;
    }

    @Override
    public long playerHealed(int heal_amount, Player player) {
        long playerHp = player.getPlayerHealth();

        playerHp = playerHp + heal_amount;

        // we need max hp attribute eventually

        player.setPlayerHealth(playerHp);

        return playerHp;
    }

    @Override
    public long playerAttack(long player_id, String weapon, long monster_id) {
        // we need to get find our monster and player in the data base
        Monster target_monster = monsterRepo.findMonsterById(monster_id);
        Player player = playerRepo.findPlayer(player_id);

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




        return monster_health;
    }


}
