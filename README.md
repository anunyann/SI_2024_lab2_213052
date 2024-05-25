# SI_2024_lab2_213052
Control Flow Graph
![Lab2_CFG](https://github.com/anunyann/SI_2024_lab2_213052/assets/162722641/06c73fc3-e1d0-42eb-8987-196cebc7fc94)


Цикломатска Комплексност
Ако одиме според формулата Р + 1, каде што Р е бројот на предикатни јазли, добиваме дека 9+1 = 10 односно цикломатска комплексност е 10.

Тест случаи според Every Branch

![Lab2_CFG_withNumbers](https://github.com/anunyann/SI_2024_lab2_213052/assets/162722641/cd12db16-0366-41be-a822-bef345cc3fab)

[baranje3.pdf](https://github.com/anunyann/SI_2024_lab2_213052/files/15444563/baranje3.pdf) 
Во овој пдф има табела направена во Excel од сите branches заедно со тестовите.
За оваа метода ми требаше 5 тест случаи. Во табелата може да видите кој тест кои гранки ги опфаќа.

Multiple Condition 
1. item.getPrice() > 300
2. item.getDiscount() > 0
3. item.getBarcode().charAt(0)=='0'

T T T
Item(banana, 0725, 500, 0.4)

T T F
Item(banana, 725, 500, 0.4)

T F T 
Item(banana, 0725, 500, 0)

T F F 
Item(banana, 725, 500, 0)

F T T
Item(banana, 0725, 100, 0.4)

F T F
Item(banana, 725, 100, 0.4)

F F T
Item(banana, 0725, 100, 0)

F F F
Item(banana, 725, 100, 0)

Кодот од Gradle: 
import org.junit.jupiter.api.Test;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

class SILab2Test {

    @Test
    void testEveryBranch() {

        RuntimeException exception = assertThrows(RuntimeException.class, () -> SILab2.checkCart(null, 100));
        assertEquals("allItems list can't be null!", exception.getMessage()); //prazen cart

        Item banana1 = new Item(null, null, 70, 0.2);
        assertTrue(SILab2.checkCart(Collections.singletonList(banana1), 100));
        assertEquals("unknown", banana1.getName()); //ne postoi produktot i ne e validen barkodot

        Item banana2 = new Item("banana2", "#", 450, 0.2);
        exception = assertThrows(RuntimeException.class, () -> SILab2.checkCart(Collections.singletonList(banana2), 100));
        assertEquals("Invalid character in item barcode!", exception.getMessage()); //nevaliden karakter vo barkod

        Item banana3 = new Item("banana3", "0725", 550, 0.5) ;
        assertTrue(SILab2.checkCart(Collections.singletonList(banana3), 450)); //sum <= payment

        Item banana4 = new Item("banana4", "725", 550, 0);
        assertFalse(SILab2.checkCart(Collections.singletonList(banana4), 500)); //sum > payment
    }

    @Test
    void testMultipleCondition() {

        Item banana1 = new Item("banana1", "0725", 500, 0.4);
        assertTrue(SILab2.checkCart(Collections.singletonList(banana1), 450)); //site uslovi se ispolneti

        Item banana2 = new Item("banana2", "725", 500, 0.4);
        assertFalse(SILab2.checkCart(Collections.singletonList(banana2), 100)); //ne e ispolnet uslovot za barkodot

        Item banana3 = new Item("banana3", "0725", 500, 0);
        assertFalse(SILab2.checkCart(Collections.singletonList(banana3), 100)); //uslovot za popust ne e ispolnet

        Item banana4 = new Item("banana4", "725", 500, 0);
        assertFalse(SILab2.checkCart(Collections.singletonList(banana4), 100)); //ne e ispolnet uslov za barkod i uslov za popust

        Item banana5 = new Item("banana5", "0725", 100, 0.4);
        assertFalse(SILab2.checkCart(Collections.singletonList(banana5), 100)); //ne e ispolnet uslov za cena 

        Item banana6 = new Item("banana6", "725", 100, 0.4);
        assertFalse(SILab2.checkCart(Collections.singletonList(banana6), 100)); //ne e ispolnet uslov za barkod i uslov za cena

        Item banana7 = new Item("banana7", "0725", 100, 0);
        assertFalse(SILab2.checkCart(Collections.singletonList(banana7), 100)); //ne e ispolnet uslov za cena i uslov za popust

        Item banana8 = new Item("banana8", "725", 100, 0);
        assertFalse(SILab2.checkCart(Collections.singletonList(banana8), 100)); //ne e ispolneto nisto

    }
}

