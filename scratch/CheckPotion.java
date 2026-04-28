import org.bukkit.potion.PotionEffectType;
import java.lang.reflect.Method;

public class CheckPotion {
    public static void main(String[] args) {
        for (Method m : PotionEffectType.class.getMethods()) {
            if (m.getName().contains("Category")) {
                System.out.println(m.getName() + " -> " + m.getReturnType().getName());
            }
        }
    }
}
