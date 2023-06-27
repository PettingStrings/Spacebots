package it.unicam.cs.paduraru.engine;

public class EnvironmentBuilder {

    protected PEnvironment environment;

    public EnvironmentBuilder(){
        environment = new PEnvironment();
    }

    public void addEntity(PEntity toAdd){
        environment.addEntity(toAdd);
    }

    public void addSystem(ASystem toAdd){
        environment.addSystem(toAdd);
    }

    protected void addComponent(PComponent toAdd){
        environment.addComponent(toAdd);
    }

    public PEnvironment getEnvironment() {
        return environment;
    }
}
