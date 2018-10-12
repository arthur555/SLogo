package fake_model;

import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;

public class StorageModel {
    private ObservableMap<String, Double> storage;

    public StorageModel() { storage = FXCollections.observableHashMap(); }

    public void put(String key, Double value) { storage.put(key, value); }

    public void registerListener(MapChangeListener<String, Double> listener) { storage.addListener(listener); }
    public void deregisterListener(MapChangeListener<String, Double> listener) { storage.removeListener(listener);}
}
