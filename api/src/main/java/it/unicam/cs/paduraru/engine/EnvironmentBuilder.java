package it.unicam.cs.paduraru.engine;

public class EnvironmentBuilder {
    private PEnvironment environment;
    public EnvironmentBuilder(){
        environment = new PEnvironment();
    }
    public void addEntity(PEntity toAdd){
        environment.addEntity(toAdd);
    }
    public void addSystem(PSystem toAdd){
        environment.addSystem(toAdd);
    }
    protected void addComponent(PComponent toAdd){
        environment.addComponent(toAdd);
    }
    public PEnvironment getEnvironment() {
        return environment;
    }
    public void setEnvironment(PEnvironment newEnv){ environment = newEnv;}
}
