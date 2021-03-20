package com.dev.mud_backend.services;

import com.dev.mud_backend.models.Item;

public interface ItemsService {

    Item addItemToInventory(long item_id);

    Long SellItem(long id);

    void DestroyItem(long id);

    Item DropItem(long id);



}
