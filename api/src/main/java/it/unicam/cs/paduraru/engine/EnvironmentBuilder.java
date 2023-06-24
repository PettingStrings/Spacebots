package it.unicam.cs.paduraru.engine;

public class EnvironmentBuilder {

    protected Environment environment;

    public EnvironmentBuilder(){
        environment = new Environment();
    }

    protected void AddEntity(Entity toAdd){
        environment.addEntity(toAdd);
    }

    protected void AddSystem(System toAdd){
        environment.addSystem(toAdd);
    }

    protected void AddComponent(Component toAdd){
        environment.addComponent(toAdd);
    }

    public Environment getEnvironment() {
        return environment;
    }
}
