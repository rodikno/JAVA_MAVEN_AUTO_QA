import org.example.Main;
import org.example.UserLoader;
import org.example.UserProcessor;
import org.example.dto.User;
import org.example.helpers.ResourceLoader;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class MyFirstTest {

    List<User> filteredCollection;

    @BeforeTest
    public void prepareCollection() throws IOException {
        String inputFileName = Main.INPUT_FILENAME;
        String jsonFilePath = ResourceLoader.getResourcePath(inputFileName);

        List<User> importedUsers = UserLoader.loadUsersFromJson(jsonFilePath);
        filteredCollection = UserProcessor.filterAndSortUsers(importedUsers);
    }

    @Test
    public void resultCollectionIsNotEmptyOrNullTest() {
        Assert.assertFalse(filteredCollection.isEmpty());
    }

    @Test
    public void resultCollectionSizeHasCorrectLengthTest() {
        Assert.assertEquals(filteredCollection.size(), 35);
    }

    @Test
    public void collectionDoesntHaveNullElements() {
        for (User user : filteredCollection) {
            Assert.assertNotNull(user);
        }
    }

    @Test
    public void eachFieldOfEachUserHasValidValueTest() {
        for (User user : filteredCollection) {
            Assert.assertTrue(user.getId() > 0);
            Assert.assertTrue(user.getAge() > 0);
            Assert.assertFalse(user.getName().isEmpty());
            Assert.assertFalse(user.getEmail().isEmpty());
        }
    }

    @Test
    public void collectionIsSortedAlphabeticallyByNameTest() {
        for (int i = 0; i < filteredCollection.size() - 1; i++) {
            Assert.assertTrue(filteredCollection
                    .get(i).getName().compareTo(filteredCollection.get(i + 1).getName()) <= 0);
        }
    }
}
