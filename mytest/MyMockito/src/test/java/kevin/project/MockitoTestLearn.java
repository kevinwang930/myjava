package kevin.project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

public class MockitoTestLearn {

    @Test
    public void test_mockConstructionInsideStatic() {
        try (MockedConstruction<Animal> mocked = Mockito.mockConstruction(Animal.class, (mock, context) -> {
            Mockito.doReturn("mocked").when(mock).getName();
        })) {
            String res = AnimalUtil.getName();
            Assertions.assertEquals("mocked", res);
        }
    }

    @Test
    public void test_mockStaticMethod() {
        Mockito.mockStatic(AnimalUtil.class);
        Mockito.when(AnimalUtil.getName2()).thenReturn("mocked");
        Assertions.assertEquals("mocked",AnimalUtil.getName2());
    }

    @Test
    public void test_mockFinalClass() {
        AnimalUtil animalUtil = Mockito.mock(AnimalUtil.class);
        Mockito.when(animalUtil.getNameInstance()).thenReturn("mocked");
        Assertions.assertEquals("mocked",animalUtil.getNameInstance());
    }
}
