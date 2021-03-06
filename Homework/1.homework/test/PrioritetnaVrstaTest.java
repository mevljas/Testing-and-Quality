
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

public class PrioritetnaVrstaTest {

    private PrioritetnaVrsta<String> pv;

    public PrioritetnaVrstaTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        pv = new PrioritetnaVrsta<>(10);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test metod razreda <PrioritetnaVrsta>
     */
    // testi dodajanja
    @Test
    public void testAddOne() {
        pv.add("Test");
    }

    @Test
    public void testAddMultiple() {
        pv.add("Test1");
        pv.add("Test2");
    }

    //@Ignore("To be implemented later...")
    @Test
    public void testAddOverflow() {
        pv = new PrioritetnaVrsta<>(2);
        pv.add("Test1");
        pv.add("Test2");
        pv.add("Test3");
    }

    // testi brisanja
    @Test(expected = java.util.NoSuchElementException.class)
    public void testRemoveFirstEmpty() {
        pv.removeFirst();
    }

    @Test
    public void testRemoveFirstOne() {
        pv.add("Test");
        assertEquals("Test", pv.removeFirst());
    }

    @Test
    public void testRemoveFirstAdditional() {
        pv.add("Test5");
        pv.add("Test3");
        pv.add("Test4");
        pv.add("Test1");
        pv.add("Test2");
        assertEquals("Test5", pv.removeFirst());
        assertEquals("Test4", pv.removeFirst());
        assertEquals("Test3", pv.removeFirst());
        assertEquals("Test2", pv.removeFirst());
        assertEquals("Test1", pv.removeFirst());
    }

    @Test
    public void testRemoveFirstMultiple() {
        pv.add("Test1");
        pv.add("Test5");
        pv.add("Test2");
        pv.add("Test4");
        pv.add("Test3");
        assertEquals("Test5", pv.removeFirst());
        assertEquals("Test4", pv.removeFirst());
        assertEquals("Test3", pv.removeFirst());
        assertEquals("Test2", pv.removeFirst());
        assertEquals("Test1", pv.removeFirst());
    }

    // metoda get
    @Test(expected = java.util.NoSuchElementException.class)
    public void testGetFirstEmpty() {
        pv.getFirst();
    }

    @Test
    public void testGetFirstOne() {
        pv.add("Test");
        assertEquals("Test", pv.getFirst());
    }

    @Test
    public void testGetFirstMultiple() {
        pv.add("Test1");
        assertEquals("Test1", pv.getFirst());
        pv.add("Test3");
        pv.add("Test2");
        assertEquals("Test3", pv.getFirst());
        assertEquals("Test3", pv.getFirst());
    }

    // testiranje metode za globino
    @Test
    public void testDepthEmpty() {
        assertEquals(0, pv.depth());
    }

    @Test
    public void testDepthOne() {
        pv.add("Test1");
        assertEquals(1, pv.depth());
    }

    @Test
    public void testDepthMultiple() {
        pv.add("Test1");
        assertEquals(1, pv.depth());
        pv.add("Test5");
        assertEquals(2, pv.depth());
        pv.add("Test2");
        assertEquals(2, pv.depth());
        pv.add("Test4");
        assertEquals(3, pv.depth());
        pv.add("Test3");
        assertEquals(3, pv.depth());
        pv.add("Test6");
        assertEquals(3, pv.depth());
        pv.add("Test8");
        assertEquals(3, pv.depth());
        pv.add("Test7");
        assertEquals(4, pv.depth());
    }

    // test metode size
    @Test
    public void testSizeEmpty() {
        assertEquals(0, pv.size());
    }

    @Test
    public void testSizeOne() {
        pv.add("Test");
        assertEquals(1, pv.size());
    }

    @Test
    public void testSizeMultiple() {
        assertEquals(0, pv.size());
        pv.add("Test");
        assertEquals(1, pv.size());
        pv.add("Test1");
        assertEquals(2, pv.size());
        pv.add("Test2");
        assertEquals(3, pv.size());
    }

    // test metode isEmpty
    @Test
    public void testIsEmptyEmpty() {
        assertTrue(pv.isEmpty());
    }

    @Test
    public void testIsEmptyOne() {
        pv.add("Test");
        assertFalse(pv.isEmpty());
    }

    @Test
    public void testIsEmptyMultiple() {
        pv.add("Test");
        pv.add("Test1");
        pv.add("Test2");
        assertFalse(pv.isEmpty());
    }

    // exists
    @Test()
    public void testExistsBasic() {
        pv.add("Test1");
        assertTrue(pv.exists("Test1"));
    }

    @Test
    public void testExistsFalse() {
        pv.add("Test");
        assertFalse(pv.exists("test2"));
    }

    @Test()
    public void testExistsMultiple() {
        pv.add("Test");
        pv.add("Test1");
        pv.add("Test2");
        pv.add("Test3");
        assertTrue(pv.exists("Test2"));
    }

    @Test
    public void testExistsEmpty() {
        assertFalse(pv.exists("test"));
    }

    // remove
    @Test()
    public void testRemoveBasic() {
        String name = "Test1";
        pv.add(name);
        assertEquals(name, pv.remove(name));
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testRemoveFalse() {
        pv.add("Test");
        assertEquals("Test2", pv.remove("tt"));
    }

    @Test()
    public void testRemoveMultiple() {
        String name1 = "Test1";
        String name2 = "Test4";
        pv.add("Test");
        pv.add(name1);
        pv.add("Test3");
        pv.add(name2);
        pv.add("Test5");
        assertEquals(name1, pv.remove(name1));
        assertEquals(name2, pv.remove(name2));
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testRemoveEmpty() {
        pv.remove("test");
    }

    // testiranje metode asList
    @Test
    public void testAsListOne() {
        pv.add("Test1");
        assertEquals("Test1", pv.asList().get(0));
        assertEquals(1, pv.asList().size());
    }

    @Test
    public void testAsListMultiple() {
        pv.add("Test1");
        pv.add("Test5");
        pv.add("Test2");
        pv.add("Test4");
        pv.add("Test3");
        pv.add("Test6");
        pv.add("Test8");
        pv.add("Test7");

        List l = pv.asList();
        assertEquals("Test8", l.get(0));
        assertEquals("Test7", l.get(1));
        assertEquals("Test6", l.get(2));
        assertEquals("Test4", l.get(3));
        assertEquals("Test3", l.get(4));
        assertEquals("Test2", l.get(5));
        assertEquals("Test5", l.get(6));
        assertEquals("Test1", l.get(7));
        assertEquals(8, pv.asList().size());
    }

    @Test
    public void testasListOnEmpty() {
        assertTrue(pv.asList().isEmpty());
    }

}
