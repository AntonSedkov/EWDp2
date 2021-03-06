package test.epam.compositetext.reader;

import by.epam.compositetext.exception.ProjectException;
import by.epam.compositetext.reader.TextReader;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class TextReaderTest {

    @Test
    public void testReadFileData() {
        String expected = "    It has survived - not only (five) centuries, but also the leap into 13+1 electronic typesetting, " +
                "remaining 3+5 essentially 6+9*(3-4) unchanged. It was popularised in the 5*(1*2*(3*(4*(5-11+4)-3)-2)-1) with the release " +
                "of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software " +
                "like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                "    It is a long established fact that a reader will be distracted by the readable content of a page when " +
                "looking at its layout. The point of using (71-(2*11*(3*(2-1/2*2)-2)-10/2))*11 Ipsum is that it has a more or less " +
                "normal distribution of letters, as opposed to using (Content here), content here', making it look like readable English.\n" +
                "    It is a (-5+1/2*(2+5*2-1))*1200 established fact that a reader will be of a page when looking at its layout.\n" +
                "    Bye.";
        try {
            String actual = TextReader.readFileData("./data/story.txt");
            assertEquals(actual, expected);
        } catch (ProjectException e) {
            fail();
        }
    }

}