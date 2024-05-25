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
        assertTrue(SILab2.checkCart(Collections.singletonList(banana1), 450));

        Item banana2 = new Item("banana2", "725", 500, 0.4);
        assertFalse(SILab2.checkCart(Collections.singletonList(banana2), 100));

        Item banana3 = new Item("banana3", "0725", 500, 0);
        assertFalse(SILab2.checkCart(Collections.singletonList(banana3), 100));

        Item banana4 = new Item("banana4", "725", 500, 0);
        assertFalse(SILab2.checkCart(Collections.singletonList(banana4), 100));

        Item banana5 = new Item("banana5", "0725", 100, 0.4);
        assertFalse(SILab2.checkCart(Collections.singletonList(banana5), 100));

        Item banana6 = new Item("banana6", "725", 100, 0.4);
        assertFalse(SILab2.checkCart(Collections.singletonList(banana6), 100));

        Item banana7 = new Item("banana7", "0725", 100, 0);
        assertFalse(SILab2.checkCart(Collections.singletonList(banana7), 100));

        Item banana8 = new Item("banana8", "725", 100, 0);
        assertFalse(SILab2.checkCart(Collections.singletonList(banana8), 100));

    }
}
