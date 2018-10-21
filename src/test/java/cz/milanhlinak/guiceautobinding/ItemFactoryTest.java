package cz.milanhlinak.guiceautobinding;

import com.google.inject.Guice;
import com.google.inject.Injector;
import cz.milanhlinak.guiceautobinding.item.FirstItem;
import cz.milanhlinak.guiceautobinding.item.ItemFactory;
import cz.milanhlinak.guiceautobinding.item.SecondItem;
import org.junit.Assert;
import org.junit.Test;

public class ItemFactoryTest {

    private final ItemFactory itemFactory;

    public ItemFactoryTest() {
        Injector injector = Guice.createInjector(new Module());
        itemFactory = injector.getInstance(ItemFactory.class);
    }

    @Test
    public void getItem_withFirstItemName_shouldReturnFirstItem() {
        Assert.assertTrue(itemFactory.getItem(FirstItem.class.getSimpleName()) instanceof FirstItem);
    }

    @Test
    public void getItem_withSecondItemName_shouldReturnSecondItem() {
        Assert.assertTrue(itemFactory.getItem(SecondItem.class.getSimpleName()) instanceof SecondItem);
    }

    @Test
    public void getItem_withUnknownItemName_shouldReturnNull() {
        Assert.assertNull(itemFactory.getItem(""));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getItem_withNullName_shouldThrowIllegalArgumentException() {
        itemFactory.getItem(null);
    }
}
