package vault;

import model.Entry;
import java.util.ArrayList;
import java.util.List;

public class VaultService {
    private List<Entry> entries;

    public VaultService() {
        entries = new ArrayList<>();
    }

    public void addEntry(Entry entry) {
        entries.add(entry);
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public boolean removeEntry(Entry entry) {
        return entries.remove(entry);
    }
}
