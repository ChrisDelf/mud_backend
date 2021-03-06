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
/game/monsterlist/{mapid}

Description:

Returns a list of monsters related to the map that is generated

example output:
```
[
    {
        "monsterid": 2518,
        "monsterName": "Gobo",
        "mapid": 0,
        "monsterHealth": 5,
        "strength": 2,
        "agility": 2,
        "intellect": 1,
        "stamina": 1,
        "monsterX": 32,
        "monsterY": 27,
        "maxhealth": 10,
        "status": "standing"
    },
    {
        "monsterid": 2521,
        "monsterName": "Gobo",
        "mapid": 0,
        "monsterHealth": 5,
        "strength": 2,
        "agility": 2,
        "intellect": 1,
        "stamina": 1,
        "monsterX": 39,
        "monsterY": 34,
        "maxhealth": 10,
        "status": "standing"
    },
    {
        "monsterid": 2524,
        "monsterName": "Gobo",
        "mapid": 0,
        "monsterHealth": 5,
        "strength": 2,
        "agility": 2,
        "intellect": 1,
        "stamina": 1,
        "monsterX": 25,
        "monsterY": 33,
        "maxhealth": 10,
        "status": "standing"
    },
    {
        "monsterid": 2527,
        "monsterName": "Gobo",
        "mapid": 0,
        "monsterHealth": 5,
        "strength": 2,
        "agility": 2,
        "intellect": 1,
        "stamina": 1,
        "monsterX": 32,
        "monsterY": 36,
        "maxhealth": 10,
        "status": "standing"
    }
]

```

#

type:get
#
/game/generatemap/{userid}

Description:

Generates a dungeon map and returns the map and user object


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
    "players": {[
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
        ]
    }
}
```

#
<------------------------------------- Player related endpoints
type: PUT

game/player/update/{playerid}

#
request body:
```
 {
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
            "itemsList": []
 }
```
#
 ```
 return body:
  {
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
            "itemsList": []
        }
```
#
#
type: Get

game/player/{playerid}

Description:

Requesting player information using the player's id


 ```
 return body:
  {
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
            "itemsList": []
        }
```
#

<------------------------------------- Monster related endpoints
#
type: Get

game/monster/{monsterid}

Description:

Requesting monster information using the monster's id


 ```
 return body:
{
    "monsterid": 2545,
    "monsterName": "Goblin",
    "monsterHealth": 5,
    "strength": 2,
    "agility": 2,
    "intellect": 1,
    "stamina": 1,
    "monsterX": 39,
    "monsterY": 21,
    "maxhealth": 10,
    "status": "standing"
}
```
#
#
type: Put

game/monster/update/{monsterid}

Description:

Updating a monster's data using json


 ```
 Rquest body:
{
    "monsterid": 2545,
    "monsterName": "HobGoblin",
    "monsterHealth": 5,
    "strength": 2,
    "agility": 2,
    "intellect": 1,
    "stamina": 1,
    "monsterX": 39,
    "monsterY": 21,
    "maxhealth": 10,
    "status": "standing"
}
```
 ```
 Return body:
{
    "monsterid": 2545,
    "monsterName": "HobGoblin",
    "monsterHealth": 5,
    "strength": 2,
    "agility": 2,
    "intellect": 1,
    "stamina": 1,
    "monsterX": 39,
    "monsterY": 21,
    "maxhealth": 10,
    "status": "standing"
}
```
#

