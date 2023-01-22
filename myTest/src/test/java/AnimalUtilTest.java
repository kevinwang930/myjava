import kevin.project.Animal;
import kevin.project.AnimalUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

import java.util.concurrent.atomic.AtomicReference;


public class AnimalUtilTest {

    @Test
    public void testShow_approval_null() {
        try (MockedConstruction<Animal> mocked = Mockito.mockConstruction(Animal.class, (mock, context) -> {
            Mockito.doReturn("mocked").when(mock).getName();

        })) {
            String res = AnimalUtil.getName();
            Assertions.assertEquals("mocked",res);
        }
    }
}
