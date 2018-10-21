package cz.milanhlinak.guiceautobinding.item;

import com.google.inject.Inject;

import java.util.Map;

public class ItemFactoryImpl implements ItemFactory {

    private final Map<String, Item> items;

    @Inject
    public ItemFactoryImpl(Map<String, Item> items) {
        this.items = items;
    }

    @Override
    public Item getItem(String name) {
        if (name == null) throw new IllegalArgumentException("Name cannot be null");
        return items.get(name);
    }
}
