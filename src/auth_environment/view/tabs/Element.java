package auth_environment.view.tabs;

public class Element {
	    private String name;
	    private String value;
	 
	    public Element(String name, String value) {
	        this.name = name;
	        this.value = value;
	    }
	 
	    public String getName() {
	        return name;
	    }
	    public void setFirstName(String name) {
	        this.name = name;
	    }
	        
	    public String getValue() {
	        return value;
	    }
	    public void setValue(String value) {
	        this.value = value;
	    }
	        
}
