import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EasterTest {
    @Test
    void testEaster2010(){
        Easter e = new Easter(2010);
        assertEquals(e.getDay(), 4);
        assertEquals(e.getMonth(), 4);
    }

    @Test
    void testEaster2011(){
        Easter e = new Easter(2011);
        assertEquals(e.getDay(), 24);
        assertEquals(e.getMonth(), 4);
    }

    @Test
    void testEaster2012(){
        Easter e = new Easter(2012);
        assertEquals(e.getDay(), 8);
        assertEquals(e.getMonth(), 4);
    }

    @Test
    void testEaster2013(){
        Easter e = new Easter(2013);
        assertEquals(e.getDay(), 31);
        assertEquals(e.getMonth(), 3);
    }

    @Test
    void testEaster2014(){
        Easter e = new Easter(2014);
        assertEquals(e.getDay(), 20);
        assertEquals(e.getMonth(), 4);
    }


    @Test
    void testEaster2015(){
        Easter e = new Easter(2015);
        assertEquals(e.getDay(), 5);
        assertEquals(e.getMonth(), 4);
    }

    @Test
    void testEaster2016(){
        Easter e = new Easter(2016);
        assertEquals(e.getDay(), 27);
        assertEquals(e.getMonth(), 3);
    }

    @Test
    void testEaster2017(){
        Easter e = new Easter(2017);
        assertEquals(e.getDay(), 16);
        assertEquals(e.getMonth(), 4);
    }

    @Test
    void testEaster2018(){
        Easter e = new Easter(2018);
        assertEquals(e.getDay(), 1);
        assertEquals(e.getMonth(), 4);
    }

    @Test
    void testEaster2019(){
        Easter e = new Easter(2019);
        assertEquals(e.getDay(), 21);
        assertEquals(e.getMonth(), 4);
    }

    @Test
    void testEaster2020(){
        Easter e = new Easter(2020);
        assertEquals(e.getDay(), 12);
        assertEquals(e.getMonth(), 4);
    }

    @Test
    void testEaster2021(){
        Easter e = new Easter(2021);
        assertEquals(e.getDay(), 4);
        assertEquals(e.getMonth(), 4);
    }

    @Test
    void testEaster2022(){
        Easter e = new Easter(2022);
        assertEquals(e.getDay(), 17);
        assertEquals(e.getMonth(), 4);
    }

    @Test
    void testEaster2023(){
        Easter e = new Easter(2023);
        assertEquals(e.getDay(), 9);
        assertEquals(e.getMonth(), 4);
    }

    @Test
    void testEaster2024(){
        Easter e = new Easter(2024);
        assertEquals(e.getDay(), 31);
        assertEquals(e.getMonth(), 3);
    }

    @Test
    void testEaster2025(){
        Easter e = new Easter(2025);
        assertEquals(e.getDay(), 20);
        assertEquals(e.getMonth(), 4);
    }

    @Test
    void testEaster2026(){
        Easter e = new Easter(2026);
        assertEquals(e.getDay(), 5);
        assertEquals(e.getMonth(), 4);
    }

    @Test
    void testEaster2027(){
        Easter e = new Easter(2027);
        assertEquals(e.getDay(), 28);
        assertEquals(e.getMonth(), 3);
    }

    @Test
    void testEaster2028(){
        Easter e = new Easter(2028);
        assertEquals(e.getDay(), 16);
        assertEquals(e.getMonth(), 4);
    }

    @Test
    void testEaster2029(){
        Easter e = new Easter(2029);
        assertEquals(e.getDay(), 1);
        assertEquals(e.getMonth(), 4);
    }

}


// Source of dates for actual easter found from https://en.wikipedia.org/wiki/List_of_dates_for_Easter