package it.unicam.cs.paduraru.engine;

public class EnvironmentBuilder {

    Environment currentEnvironment;

    public void AddEntity(Entity toAdd){
        currentEnvironment.AddEntity(toAdd);
    }

    public void AddSystem(System toAdd){
        currentEnvironment.AddSystem(toAdd);
    }

    public void AddComponent(Component toAdd){
        currentEnvironment.AddComponent(toAdd);
    }

}
