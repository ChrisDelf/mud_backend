package com.dev.mud_backend.services;

import com.dev.mud_backend.models.Monster;
import com.dev.mud_backend.models.Player;
import com.dev.mud_backend.models.Room;
import com.dev.mud_backend.repository.MonsterRepository;
import com.dev.mud_backend.repository.PlayerRepository;
import com.dev.mud_backend.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "monsterService")
public class MonsterServiceImpl implements MonsterService  {
    @Autowired
    private RoomRepository roomRepo;

    @Autowired
    private MonsterRepository monsterRepo;

    @Autowired
    private RoomService roomService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private PlayerRepository playerRepo;

    @Autowired
    MonsterService monsterService;

    @Override
    public Monster MonsterStats(long m_id, long room_id) {
        // instantiate the monster that we are going pull the stats int
        Monster target_monster = new Monster();
        // first we need to find if the room exist
        Room target_room = roomService.findById(room_id);
        // if we don't find a room
        if (target_room == null){
            return target_monster;
        }
        target_monster = roomService.getMonster(target_room, m_id);



        return target_monster;
    }

    @Override
    public long MonsterAttack(long monsterId, long playerId) {
        // find the select monster
        Monster target_monster = monsterService.findById(monsterId);
        // now we nee to gather the information from the player
        Player player = playerService.findById(playerId);

        //this where we would calculate the monsters damage

        //we now apply the damage to the player

        long d_result = playerService.playerDamaged(5,player);

        playerRepo.save(player);

        return d_result;
    }



    @Override
    public long MonsterHeal(long monsterId, long healAmount) {
        //Find the monster
        Monster monster = monsterService.findById(monsterId);
        // now we will adjust the monsters health
        monster.setMonsterHealth(monster.getMonsterHealth() + healAmount);

        monsterRepo.save(monster);

        return monster.getMonsterHealth();
    }

    @Override
    public String MonsterDeath(long monsterId) {
        //Find the monster
        Monster monster = monsterService.findById(monsterId);
        // mow we change the monster status to dead
        monster.setStatus("dead");
        monsterRepo.save(monster);
        
        return monster.getStatus();
    }

    @Override
    public ArrayList<Monster> GenerateMonsters(int monsterLimit) {
        return null;
    }




    @Transactional
    @Override
    public Monster findById(long monster_id) {

        Monster monster = monsterRepo.findByMonsterid(monster_id);


        return monster;
    }
}
