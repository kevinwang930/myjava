import kevin.project.Animal;
import kevin.project.AnimalUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Animal.class, AnimalUtil.class})
public class AnimalUtilTest {

    @Test
    public void testGetName_success() throws Exception {
        PowerMockito.spy(AnimalUtil.class);
        Animal animal = PowerMockito.mock(Animal.class);
        PowerMockito.whenNew(Animal.class).withNoArguments().thenReturn(animal);
        PowerMockito.when(animal.getName()).thenReturn("mocked");
        String result = AnimalUtil.getName();
        Assert.assertEquals("mocked",result);
    }
}
