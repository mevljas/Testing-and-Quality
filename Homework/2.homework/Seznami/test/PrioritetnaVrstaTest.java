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
        pv = new PrioritetnaVrsta<>(10, new StringComparator());
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testAddOne() {
        pv.add("Test");
    }
    
    @Test
    public void testAddMultiple() {
        pv.add("Test1");
        pv.add("Test2");
    }
    
    @Test
    public void testAddOverflow() {
        pv = new PrioritetnaVrsta<>(2, new StringComparator());
        pv.add("Test1");
        pv.add("Test2");
        pv.add("Test3");
    }
    
    @Test(expected=java.util.NoSuchElementException.class)
    public void testRemoveFirstEmpty() {
        pv.removeFirst();
    }
    
    @Test
    public void testRemoveFirstOne() {
        pv.add("Test");
        assertEquals("Test", pv.removeFirst());
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

    
    @Test(expected=java.util.NoSuchElementException.class)
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
    
    @Test
    public void testDepthEmpty() {
        assertEquals(0,pv.depth());
    }
    
    @Test
    public void testDepthOne() {
        pv.add("Test1");
        assertEquals(1,pv.depth());
    }

    @Test
    public void testDepthMultiple() {
        pv.add("Test1");
        assertEquals(1,pv.depth());
        pv.add("Test5");
        assertEquals(2,pv.depth());
        pv.add("Test2");
        assertEquals(2,pv.depth());
        pv.add("Test4");
        assertEquals(3,pv.depth());
        pv.add("Test3");
        assertEquals(3,pv.depth());
        pv.add("Test6");
        assertEquals(3,pv.depth());
        pv.add("Test8");
        assertEquals(3,pv.depth());
        pv.add("Test7");
        assertEquals(4,pv.depth());
    }
    
    @Test
    public void testSizeEmpty() {
        assertEquals(0,pv.size());
    }
    
    @Test
    public void testSizeOne() {
        pv.add("Test");
        assertEquals(1,pv.size());
    }
    
    @Test
    public void testSizeMultiple() {
        assertEquals(0,pv.size());
        pv.add("Test");
        assertEquals(1,pv.size());
        pv.add("Test1");
        assertEquals(2,pv.size());
        pv.add("Test2");
        assertEquals(3,pv.size());
    }
    
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
    
     // Testi za remove in exists
    @Test
    public void testRemoveOK() {
        String a = "Test";
        pv.add(a);
        assertEquals(a, pv.remove(a));
        pv.add("Test1");
        pv.add("Test2");
        pv.add(a);
        pv.add("Test3");
        pv.add("Test4");
        assertEquals(a, pv.remove("Test"));
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testRemoveOnEmpty() {
        pv.add("Test1");
        pv.add("Test2");
        pv.add("Test3");
        pv.add("Test4");
        String a = pv.remove("Test");
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testRemoveNOK() {
        String a = pv.remove("Test");
    }

    @Test
    public void testExists() {
        String a = "Test";
        assertFalse(pv.exists(a));
        pv.add(a);
        pv.add("Test1");
        assertTrue(pv.exists(a));
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

   
}