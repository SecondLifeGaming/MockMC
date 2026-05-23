import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
public class TestProp {
    public static void main(String[] args) {
        System.setProperty("net.kyori.adventure.bossbar.BossBarImplementation$Provider", "org.mockmc.mockmc.boss.BossBarImplementationProvider");
        try {
            BossBar.bossBar(Component.text("hi"), 1f, BossBar.Color.RED, BossBar.Overlay.PROGRESS);
            System.out.println("SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
