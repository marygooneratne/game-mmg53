package game;

import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.HashMap;

public class SceneController {
    private HashMap<String, Scene> scenes;
    private Stage mainStage;

    public SceneController(Stage mainStage) {
        this.scenes = new HashMap<String, Scene>();
        this.mainStage = mainStage;
    }

    public void newScene(String sceneName, Scene scene) {
        this.scenes.put(sceneName, scene);
    }

    public void changeScene(String sceneName) {
        mainStage.setScene(scenes.get(sceneName));
    }

}