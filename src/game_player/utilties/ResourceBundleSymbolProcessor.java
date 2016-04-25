package game_player.utilties;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ResourceBundleSymbolProcessor {

    private Map<String, String> mySymbols;

    public ResourceBundleSymbolProcessor () {
        this.mySymbols = new HashMap<>();
    }

    public String getSymbol (String text) {
        return mySymbols.get(text);
    }

    public void addPatterns (String syntax) {
        ResourceBundle resources = ResourceBundle.getBundle(syntax);
        Enumeration<String> iter = resources.getKeys();
        while (iter.hasMoreElements()) {
            String key = iter.nextElement();
            String value = resources.getString(key);
            mySymbols.put(key, value);
        }
    }

}
