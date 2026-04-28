import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionEffectTypeCategory;

public class TestPotion {
    public void test(PotionEffectType type) {
        PotionEffectType.Category c1 = type.getCategory();
        PotionEffectTypeCategory c2 = type.getEffectCategory();
    }
}
