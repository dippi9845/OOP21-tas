package main.java.tas.model.enemies;

import java.awt.Dimension;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.json.JSONObject;

import main.java.tas.utils.Position;

/**
 * Class that implements {@link EnemyBuilder}.
 */
public class EnemyBuilderImpl implements EnemyBuilder {

    private final List<Position> nodesPosition;
    private final JSONObject enemiesSetup;

    private static String RESOURCE_PATH = "res";
    private final String enemiesJsonSetup = RESOURCE_PATH + System.getProperty("file.separator") + "data"
            + System.getProperty("file.separator") + "enemies" + System.getProperty("file.separator")
            + "basicEnemies.json";

    /**
     * Constructor that creates a factory for the enemies .
     * 
     * @param nodesPosition is a list with the nodes that the enemies will have to
     *                      travel
     * @throws IllegalArgumentException if the @param nodesPosition is empty
     */
    public EnemyBuilderImpl(List<Position> nodesPosition) throws IllegalArgumentException {
        if (nodesPosition.isEmpty()) {
            throw new IllegalArgumentException("@nodesPosition can't be an empty array!");
        }
        this.nodesPosition = nodesPosition;

        this.enemiesSetup = setupJsonData(enemiesJsonSetup);
    }

    /**
     * Reads data from a JSON file and returns the relative JSONObject
     * 
     * @param jsonPath where the file is
     * @return the relative JSONObject
     */
    private JSONObject setupJsonData(String jsonPath) {
        String content = "";
        try {
            content = Files.readString(Paths.get(jsonPath));
        } catch (IOException e) {
            System.out.println("Error: No enemy json file detected!");
            System.out.println(e);
        }
        return new JSONObject(content);
    }

    /**
     * Generates an enemy by the given JsonObject
     * 
     * @param eStats the JsonObject where the informations are stored
     * @return the generated enemy
     */
    private Enemy spawnGenericEnemy(JSONObject eStats) {
        return new GenericEnemy(this.nodesPosition, eStats.getDouble("health"), eStats.getInt("money"),
                eStats.getInt("damage"), eStats.getDouble("speed"),
                new Dimension(eStats.getJSONObject("bodyDimension").getInt("width"),
                        eStats.getJSONObject("bodyDimension").getInt("height")),
                eStats.getString("image"));
    }

    /** {@inheritDoc} */
    @Override
    public Enemy spawnRedEnemy() {
        return spawnGenericEnemy(this.enemiesSetup.getJSONObject("RedEnemy"));
    }

    /** {@inheritDoc} */
    @Override
    public Enemy spawnGreenEnemy() {
        return spawnGenericEnemy(this.enemiesSetup.getJSONObject("GreenEnemy"));
    }

    /** {@inheritDoc} */
    @Override
    public Enemy spawnPinkEnemy() {
        return spawnGenericEnemy(this.enemiesSetup.getJSONObject("PinkEnemy"));
    }

}
