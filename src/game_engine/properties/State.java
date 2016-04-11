package game_engine.properties;

public class State {
    
    private final String[] states = {"Stationary", "Disappearing", "Moving", "Initializing", "Receiving_Damage", "Dying"};
    private String state;
    
    public State(String state) {
        this.state = state;
    }
    
    public String getValue() {
        return state;
    }
    
    public void changeState(int index) {
        this.state = states[index];
    }
    
    public State copyState() {
        return new State(this.getValue());
    }
}
