package baisc.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach(){
        itemRepository.clearStore();
    }
    @Test
    void save() {
        //given
        Item item = new Item("itemA", 10000,4);

        //when
        Item saveItem = itemRepository.save(item);
        //then
        Item findItem = itemRepository.findById(saveItem.getId());
         assertThat(saveItem).isEqualTo(item);

    }

    @Test
    void findAll() {
        //given
        Item item1 = new Item("itemA", 30000, 5);
        Item item2 = new Item("itemB", 200000, 6);

        itemRepository.save(item1);
        itemRepository.save(item2);
        //when
        List<Item> itemList = itemRepository.findAll();
        //then
        assertThat(itemList.size()).isEqualTo(2);
        assertThat(itemList).contains(item1, item2);
    }

    @Test
    void update() {
        Item item1 = new Item("itemA", 30000, 5);
        Item savedItem = itemRepository.save(item1);
        Long itemId = savedItem.getId();

        Item updateItem = new Item("itemB", 200000, 6);

        itemRepository.update(itemId, updateItem);

        assertThat(item1.getName()).isEqualTo(updateItem.getName());


    }

}