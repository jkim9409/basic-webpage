package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Item item = new Item("itemA", 10000, 10);

        //when
        Item savedItem = itemRepository.save(item);

        //then
        Item foundItem = itemRepository.findById(savedItem.getId());
        assertThat(foundItem).isEqualTo(savedItem);
    }

    @Test
    void findAll() {
        //given
        Item itemA = new Item("ItemA", 100000, 10);
        Item itemB = new Item("ItemB", 10000, 3);
        itemRepository.save(itemA);
        itemRepository.save(itemB);
        //when
        List<Item> allItems = itemRepository.findAll();

        //then

        assertThat(allItems.size()).isEqualTo(2);
        assertThat(allItems).contains(itemA, itemB);
    }

    @Test
    void update() {
        //given
        Item itemA = new Item("itemA", 10000, 20);

        Item savedItem = itemRepository.save(itemA);
        Long itemId = savedItem.getId();


        //when
        Item updateParam = new Item("itemB", 20000, 1);
        itemRepository.update(itemId,updateParam);
        Item foundItem = itemRepository.findById(itemId);

        //then
        assertThat(foundItem.getItemName()).isEqualTo("itemB");
        assertThat(foundItem.getPrice()).isEqualTo(20000);
        assertThat(foundItem.getQuantity()).isEqualTo(1);


    }

    @Test
    void clearStore() {

    }
}