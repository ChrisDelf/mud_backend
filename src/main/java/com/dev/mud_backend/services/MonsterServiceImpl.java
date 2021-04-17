package com.dev.mud_backend.services;

import com.dev.mud_backend.exceptions.ResourceNotFoundException;
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
    public Monster updateMonster(Monster monster, long id) {

        if (monsterService.findById(id) != null) {
            Monster temp_monster = monsterService.findById(monster.getMonsterid());
            temp_monster = monster;

            if (monster.getMonsterName() != null){
                temp_monster.setMonsterName(monster.getMonsterName());

            }

            if (monster.getMonsterHealth() != temp_monster.getMonsterHealth()){
                temp_monster.setMonsterHealth(monster.getMonsterHealth());

            }

            if(monster.getMaxhealth() != temp_monster.getMaxhealth()){
                temp_monster.setMaxhealth(monster.getMaxhealth());
            }

            if (monster.getStrength() != temp_monster.getStrength()){
                temp_monster.setStrength(monster.getStrength());
            }

            if (monster.getAgility() != temp_monster.getAgility()){
                temp_monster.setAgility(monster.getAgility());
            }

            if (monster.getIntellect() != temp_monster.getIntellect()){
                temp_monster.setIntellect(monster.getIntellect());
            }

            if (monster.getStamina() != temp_monster.getStamina()){
                temp_monster.setStamina(monster.getStamina());
            }

            if (monster.getStatus() != temp_monster.getStatus()){
                temp_monster.setStatus(monster.getStatus());
            }

            // Position

            if (monster.getMonsterX() != temp_monster.getMonsterX()){
                temp_monster.setMonsterX(monster.getMonsterX());
            }

            if (monster.getMonsterY() != temp_monster.getMonsterY()){
                temp_monster.setMonsterY(monster.getMonsterY());
            }

            monsterRepo.save(temp_monster);

            return temp_monster;

        }else
        {  throw new ResourceNotFoundException(monster.getMonsterid() + " monster not found");}

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
