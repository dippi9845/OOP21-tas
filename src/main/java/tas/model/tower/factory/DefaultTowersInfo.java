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

public class DefaultTowersInfo {
	
	public static final String COSTFIELD = "cost";

	public static final String IMAGENAMEFIELD = "image name";

	public static final String DELAYFIELD = "delay";

	public static final String RADIUSFIELD = "radius";

	public static final String DAMAGEFIELD = "damage";

	private DefaultTowersInfo() {}
	
	// json file with all preset
	public static final String TOWERSJSONFILE = "res" + System.getProperty("file.separator") + "data"
	        + System.getProperty("file.separator") + "towers" + System.getProperty("file.separator") + "DefaultTowers.json";
	
	// object containing already the contet of TOWERS JSON FILE
	public static final JSONObject JSONDATAFILE = JsonUtils.getJsonData(TOWERSJSONFILE);

	// name inside the json file
	public static final Map<DefaultTowers, String> TOWERSJSONNAME = initMapName();

	public static final Map<DefaultTowers, JSONObject> TOWERSJSONOBJECT = initMapJsonObject();

	public static final Map<DefaultTowers, BiFunction<Position, List<Enemy>, Tower>> BUILDMAP = initBuildMap();

	// init map with all json file name
	private static Map<DefaultTowers, String> initMapName() {
        Map<DefaultTowers, String> map = new HashMap<>();
        
        Arrays.stream(DefaultTowers.values())
        	  .forEach(x->map.put(x, x.toString().toLowerCase()));
        
        return Collections.unmodifiableMap(map);
    }
    
    private static Map<DefaultTowers, JSONObject> initMapJsonObject() {
    	Map<DefaultTowers, JSONObject> map = new HashMap<>();
    	
    	Arrays.stream(DefaultTowers.values())
    		.forEach(x->map.put(x, JSONDATAFILE.getJSONObject(TOWERSJSONNAME.get(x))));
    	
		return Collections.unmodifiableMap(map);
	}

    /**
	 * Initialize the map with all functions that build a tower by the given
	 * enumeration code
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
