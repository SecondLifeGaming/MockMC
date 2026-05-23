import java.net.URL;
import java.util.Enumeration;
public class FindService {
    public static void main(String[] args) throws Exception {
        Enumeration<URL> res = FindService.class.getClassLoader().getResources("META-INF/services/net.kyori.adventure.bossbar.BossBarImplementation$Provider");
        while (res.hasMoreElements()) {
            System.out.println(res.nextElement());
        }
    }
}
