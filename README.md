# mud_backend

# 
This is a backend that creates a dungeon with randomly generated rooms. Users can move 
around the dungeon.Users locations are tracked.

Tech use:
Java
h2database,
Spring Boot,
Swagger,
and Oauth2.


#Endpoints

<------------------------------------- Game related endpoints

game information request.

type:get
#
/game/monsterlist/{mapid}.
Description:.
Returns a list of monsters related to the map that is generated.
#

type:get
#
/game/generatemap/{userid}.
Description:.
Generates a dungeon map and returns the map and user object.


example output:
```{
    "mapid": 13,
    "width": 50,
    "height": 50,
    "grid":"[[{\"cellid\":16,\"x\":0,\"y\":0,\"roomType\":\"Wall\",\"roomid\":0,\"mapid\":13},{\"cellid\":17,\"x\":1,\"y\":0,\"roomType\":\"Wall\",\"roomid\":0,\"mapid\":13},{\"cellid\":18,\"x\":2,\"y\":0,\"roomType\":\"Wall\",\"roomid\":0,\"mapid\":13},{\"cellid\":19,\"x\":3,\"y\":0,\"roomType\":\"Wall\",\"roomid\":0,\"mapid\":13},{\"cellid\":20,\"x\":4,\"y\":0,\"roomType\":\"Wall\",\"roomid\":0,\"mapid\":13},{\"cellid\":21,\"x\":5,\"y\":0,\"roomType\":\"Wall\",\"roomid\":0,\"mapid\":13},{\"cellid\":22,\"x\":6,\"y\":0,\"roomType\":\"Wall\",\"roomid\":0,\"mapid\":13},{\"cellid\":23,\"x\":7,\"y\":0,\"roomType\":\"Wall\",\"roomid\":0,\"mapid\":13},{\"cellid\":24,\"x\":8,\"y\":0,\"roomType\":\"Wall\",\"roomid\":0,\"mapid\":13},{\"cellid\":25,\"x\":9,\"y\":0,\"roomType\":\"Wall\",\"roomid\":0,\"mapid\":13},{\"cellid\":26,\"x\":10,\"y\":0,\"roomType\":\"Wall\",\"roomid\":0,\"mapid\":13},{\"cellid\":27,\"x\":11,\"y\":0,\"roomType\":\"Wall\",\"roomid\":0,\"mapid\":13},{\"cellid\":28,\"x\":12,\"y\":0,\"roomType\":\"Wall\",\"roomid\":0,\"mapid\":13},{\"cellid\":29,\"x\":13,\"y\":0,\"roomType\":\"Wall\",\"roomid\":0,\"mapid\":13},{\"cellid\":30,\"x\":14,\"y\":0,\"roomType\":\"Wall\",\"roomid\":0,\"mapid\":13},]",
 "monsters": [
        {
            "monsterid": 2518,
            "monsterName": "Gobo",
            "mapid": 13,
            "monsterHealth": 5,
            "strength": 2,
            "agility": 2,
            "intellect": 1,
            "stamina": 1,
            "monsterX": 34,
            "monsterY": 28,
            "maxhealth": 10,
            "status": "standing"
        },
        {
            "monsterid": 2521,
            "monsterName": "Gobo",
            "mapid": 13,
            "monsterHealth": 5,
            "strength": 2,
            "agility": 2,
            "intellect": 1,
            "stamina": 1,
            "monsterX": 37,
            "monsterY": 32,
            "maxhealth": 10,
            "status": "standing"
        },
        {
            "monsterid": 2539,
            "monsterName": "Gobo",
            "mapid": 13,
            "monsterHealth": 5,
            "strength": 2,
            "agility": 2,
            "intellect": 1,
            "stamina": 1,
            "monsterX": 29,
            "monsterY": 22,
            "maxhealth": 10,
            "status": "standing"
        }
    ],
    "player": {
        "playerid": 14,
        "playerHealth": 50,
        "playerName": "Doofus",
        "playery": 32,
        "playerx": 32,
        "playerStrength": 5,
        "playerIntellect": 5,
        "playerAgility": 5,
        "playerStamina": 5,
        "maxHealth": 50,
        "status": "standing",
        "mapStatus": null,
        "itemsList": [],
        "map": null
    }
}```
    



