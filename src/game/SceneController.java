package game;

import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.HashMap;

/**
 * CODE WRITTEN BY: MARY GOONERATNE (@mmg53)
 * SceneController class used to toggle between scenes in main game methods. Allows program to add scenes to HashMap
 * to be easily set as the main Stage scene. Separates scene toggling from main methods.
 *
 */
public class SceneController {
    private HashMap<String, Scene> scenes;
    private Stage mainStage;

    /** Initializes SceneController class that has instance variables HashMap scenes and Stage stage.
     * HashMap stores scenes rotated on the stage. HashMap is added to as scenes are created.
     *
     * @param mainStage main JavaFX tage for scenes to be set on
     */
    public SceneController(Stage mainStage) {
        this.scenes = new HashMap<String, Scene>();
        this.mainStage = mainStage;
    }

    /** Creates new scene in controller HashMap
     *
     * @param sceneName Name of scene in HashMap
     * @param scene JavaFX Scene object corresponding to name
     */

    public void newScene(String sceneName, Scene scene) {
        this.scenes.put(sceneName, scene);
    }

    /** Changes scene in controller stage to scene name in method call
     *
     * @param sceneName name of scene to change to
     */

    public void changeScene(String sceneName) {
        mainStage.setScene(scenes.get(sceneName));
    }

}