import org.bukkit.entity.Armadillo;
import java.util.Arrays;

public class TestEnum {
    public static void main(String[] args) {
        try {
            System.out.println(Arrays.toString(Armadillo.State.values()));
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
