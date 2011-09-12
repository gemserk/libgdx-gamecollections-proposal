import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;


public class RandomAccessMapTest {

	private RandomAccessMap<String, String> map;

	@Before
	public void setUp(){
		map = new RandomAccessMap<String, String>();
	}
	
	@Test
	public void aNewMapIsEmpty(){
		assertTrue("the map should be empty", map.isEmpty());
	}
	
	@Test
	public void ifYouAddAnElementToTheMapYouCanRetrieveIt(){
		map.put("key", "value");
		assertFalse("the map should not be empty", map.isEmpty());
		assertEquals("the map should have size 1",1,map.size());
		
		assertThat(map,Matchers.hasEntry("key", "value"));
		
		RandomAccessWithKey<String, String> random = map;
		
		assertThat(random.get(0), Matchers.equalTo("value"));
		assertThat(random.getKey(0), Matchers.equalTo("key"));
	}
	
	@Test
	public void ifYouRemoveAnElementItIsGone(){
		map.put("key1", "value1");
		map.put("key2", "value2");
		map.put("key3", "value3");
		assertFalse("the map should not be empty", map.isEmpty());
		assertEquals("the map should have size 3",3,map.size());
		
		assertThat(map,Matchers.hasEntry("key1", "value1"));
		assertThat(map,Matchers.hasEntry("key2", "value2"));
		assertThat(map,Matchers.hasEntry("key3", "value3"));
		
		map.remove("key1");
		
		assertThat(map,Matchers.not(Matchers.hasEntry("key1", "value1")));
		assertThat(map,Matchers.hasEntry("key2", "value2"));
		assertThat(map,Matchers.hasEntry("key3", "value3"));
		
		RandomAccessWithKey<String, String> random = map;
		
		for (int i = 0; i < map.size(); i++) {
			System.out.println("K: " + random.getKey(i) + " - V: " + random.get(i));
		}
	}
}
