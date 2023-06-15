package it.unicam.cs.paduraru.engine;
import java.util.ArrayList;
import java.util.List;
public final class GameController {

    public static Environment DefaultEnvironment;
    static List<Environment> environments = new ArrayList<Environment>();
    static int currentEnvironment;

    private GameController(){};
    public static void Initialize(){
        for (Environment env: environments)
            env.Initialize();
    }

    public static void createEnvironment() {
        environments.add(new Environment());
    }
    public static void createEnvironment(Environment defaultEnvironment) {
        environments.add(defaultEnvironment);
    }


    public static void changeEnvironment(int idEnv) throws Exception {
        if(idEnv >= environments.stream().count())
            throw new Exception("Environment not exists");

        currentEnvironment = idEnv;

    }

    public static void StepCurrentEnvironment() {
    }

    public static List<Entity> GetCurrentEntities() {
        return environments.get(currentEnvironment).getEntities();
    }
}
