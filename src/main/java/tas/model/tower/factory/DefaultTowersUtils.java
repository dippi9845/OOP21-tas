package main.java.tas.model.tower.factory;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import org.json.JSONObject;

import main.java.tas.model.enemy.Enemy;
import main.java.tas.model.tower.Tower;
import main.java.tas.utils.JsonUtils;
import main.java.tas.utils.Position;

public class DefaultTowersUtils {
	
	private DefaultTowersUtils() {}
	
	/**
	 * Filed name in json, that describes the cost
	 */
	public static final String COSTFIELD = "cost";

	/**
	 * Filed name in json, that describes the tower name
	 */
	public static final String TOWERNAMEFIELD = "name";

	/**
	 * Filed name in json, that describes the delay
	 */
	public static final String DELAYFIELD = "delay";
	
	/**
	 * Filed name in json, that describes the radius
	 */
	public static final String RADIUSFIELD = "radius";

	/**
	 * Filed name in json, that describes the damage
	 */
	public static final String DAMAGEFIELD = "damage";
	
	/**
	 * Name of the file containing all default towers
	 */
	public static final String FILEJSONTOWERS = "res" + System.getProperty("file.separator") + "data"
	        + System.getProperty("file.separator") + "towers" + System.getProperty("file.separator") + "DefaultTowers.json";
	
	/**
	 * {@link org.json.JSONObject} containing the parsed content of {@link DefaultTowersUtils#FILEJSONTOWERS}
	 */
	public static final JSONObject JSONDATAFILE = JsonUtils.getJsonData(FILEJSONTOWERS);

	/**
	 * A {@link java.util.Map} that at every {@link DefaultTowers} value,
	 * associate the name inside the file {@link DefaultTowersUtils#FILEJSONTOWERS} 
	 */
	public static final Map<DefaultTowers, String> JSONNAMEMAP = initJsonNameMap();

	/**
	 * A {@link java.util.Map} that at every {@link DefaultTowers} value,
	 * associate an {@link org.json.JSONObject} containing all field necessary
	 */
	public static final Map<DefaultTowers, JSONObject> JSONOBJECTMAP = initJsonObjectMap();

	/**
	 * A {@link java.util.Map} that at every {@link DefaultTowers} value,
	 * associate the function that build that specific tower, every function that build is a 
	 * {@link java.util.function.BiFunction}, that takes in input a a Position and a List of enemies, and returns a Tower
	 */
	public static final Map<DefaultTowers, BiFunction<Position, List<Enemy>, Tower>> BUILDMAP = initBuildMap();

	/**
	 * This function build the constant {@link java.util.Map} {@link DefaultTowersUtils#JSONNAMEMAP}
	 * @return a {@link java.util.Map} that at every {@link DefaultTowers} value, 
	 * associate the name inside the file {@link DefaultTowersUtils#FILEJSONTOWERS}
	 */
	private static Map<DefaultTowers, String> initJsonNameMap() {
        Map<DefaultTowers, String> map = new HashMap<>();
        
        Arrays.stream(DefaultTowers.values())
        	  .forEach(x->map.put(x, x.toString().toLowerCase()));
        
        return Collections.unmodifiableMap(map);
    }
    
	/**
	 * This function build the constant {@link java.util.Map} {@link DefaultTowersUtils#JSONOBJECTMAP}
	 * @return a {@link java.util.Map} that at every {@link DefaultTowers} value,associate an 
	 * {@link org.json.JSONObject} containing all field necessary
	 */
    private static Map<DefaultTowers, JSONObject> initJsonObjectMap() {
    	Map<DefaultTowers, JSONObject> map = new HashMap<>();
    	
    	Arrays.stream(DefaultTowers.values())
    		.forEach(x->map.put(x, JSONDATAFILE.getJSONObject(JSONNAMEMAP.get(x))));
    	
		return Collections.unmodifiableMap(map);
	}

    /**
	 * This function build the constant {@link java.util.Map} {@link DefaultTowersUtils#BUILDMAP}
	 * @return A {@link java.util.Map} that at every {@link DefaultTowers} value,
	 * associate the function that build that specific tower,
	 * every function that build is a {@link java.util.function.BiFunction},
	 * that takes in input a {@link main.java.tas.utils.Position} and a {@link java.util.List} of
	 * {@link main.java.tas.model.enemy.Enemy} and returns a Tower
	 */
	private static Map<DefaultTowers, BiFunction<Position, List<Enemy>, Tower>> initBuildMap() {
		 Map<DefaultTowers, BiFunction<Position, List<Enemy>, Tower>> map = new HashMap<>();
		map.put(DefaultTowers.BASICARCHER, ArcherFactory::basicArcher);
		map.put(DefaultTowers.BIARCHER, ArcherFactory::biArcher);
		map.put(DefaultTowers.TRIARCHER, ArcherFactory::triArcher);
		map.put(DefaultTowers.QUADARCHER, ArcherFactory::quadArcher);
		map.put(DefaultTowers.BASICCANNON, CannonFactory::basicCannon);
		map.put(DefaultTowers.BICANNON, CannonFactory::biCannon);
		map.put(DefaultTowers.TRICANNON, CannonFactory::triCannon);
		map.put(DefaultTowers.QUADCANNON, CannonFactory::quadCannon);
		map.put(DefaultTowers.BASICFLAME, FlameFactory::basicFlame);
		map.put(DefaultTowers.BIFLAME, FlameFactory::biFlame);
		map.put(DefaultTowers.TRIFLAME, FlameFactory::triFlame);
		map.put(DefaultTowers.QUADFLAME, FlameFactory::quadFlame);
		map.put(DefaultTowers.GASTOWER, GasFactory::gasTower);
		map.put(DefaultTowers.BASICMORTAR, MortarFactory::basicMortar);
		map.put(DefaultTowers.SUPERMORTAR, MortarFactory::superMortar);
		map.put(DefaultTowers.GODMORTAR, MortarFactory::godMortar);
		map.put(DefaultTowers.BASICTESLA, TeslaFactory::basicTesla);
		map.put(DefaultTowers.SUPERTESLA, TeslaFactory::superTesla);
		map.put(DefaultTowers.GODTESLA, TeslaFactory::godTesla);
		return Collections.unmodifiableMap(map);
	}
}
