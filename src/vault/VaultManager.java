
package vault;
import model.Entry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VaultManager{
    private List<Entry> entries;
    public VaultManager(){
        entries = new ArrayList<>();
    }
    public void addEntry(Entry entry){
        entries.add(entry);

    }
    public List<Entry> getEntries(){
        return entries;
    }
    public boolean removeEntry(Entry entry){
        return entries.remove(entry);
    }
    
}
