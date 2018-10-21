package cz.milanhlinak.guiceautobinding;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.multibindings.MapBinder;
import cz.milanhlinak.guiceautobinding.item.AutoBindableItem;
import cz.milanhlinak.guiceautobinding.item.Item;
import cz.milanhlinak.guiceautobinding.item.ItemFactory;
import cz.milanhlinak.guiceautobinding.item.ItemFactoryImpl;
import org.reflections.Reflections;

public class Module extends AbstractModule {

    @Override
    protected void configure() {

        MapBinder<String, Item> itemMapBinder = MapBinder.newMapBinder(binder(), String.class, Item.class);
        new Reflections("cz.milanhlinak.guiceautobinding.item")
                .getTypesAnnotatedWith(AutoBindableItem.class)
                .stream()
                .filter(Item.class::isAssignableFrom)
                .forEach(typeAnnotatedWith -> itemMapBinder
                        .addBinding(typeAnnotatedWith.getSimpleName())
                        .to((Class<? extends Item>) typeAnnotatedWith)
                );

        bind(ItemFactory.class).to(ItemFactoryImpl.class).in(Singleton.class);
    }
}
