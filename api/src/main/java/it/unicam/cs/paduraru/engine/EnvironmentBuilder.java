package it.unicam.cs.paduraru.engine;

public class EnvironmentBuilder {

    protected Environment environment;

    public EnvironmentBuilder(){
        environment = new Environment();
    }

    public void addEntity(Entity toAdd){
        environment.addEntity(toAdd);
    }

    public void addSystem(System toAdd){
        environment.addSystem(toAdd);
    }

    protected void addComponent(Component toAdd){
        environment.addComponent(toAdd);
    }

    public Environment getEnvironment() {
        return environment;
    }
}
