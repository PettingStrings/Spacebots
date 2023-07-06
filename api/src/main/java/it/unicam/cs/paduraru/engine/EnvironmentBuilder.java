package it.unicam.cs.paduraru.engine;

/**
 * E' una classe ausiliaria che incapsula i metodi per creare e gestire un Environment.
 */
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
