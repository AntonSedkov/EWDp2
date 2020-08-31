package test.epam.compositetext.reader;

import by.epam.compositetext.exception.ProjectException;
import by.epam.compositetext.reader.TextReader;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TextReaderTest {

    @Test
    public void testReadFileData() {
        String expected = "    He presented his Documento Nacional de Identidad to the employees\n" +
                "manning the guest list. For a moment, Ávila’s pulse quickened when his name\n" +
                "could not be located on the list. Finally, they found it at the bottom—a last-minute\n" +
                "addition—and Ávila was allowed to enter.\n" +
                "    Exactly as the Regent promised me. How he had accomplished this feat,\n" +
                "Ávila had no idea. Tonight’s guest list was said to be ironclad.\n" +
                "    He continued to the metal detector, where he removed his cell phone and\n" +
                "placed it in the dish. Then, with extreme care, he extracted an unusually heavy set\n" +
                "of rosary beads from his jacket pocket and laid it over his phone.\n" +
                "    Gently, he told himself. Very gently.\n" +
                "    The security guard waved him through the metal detector and carried the dish\n" +
                "of personal items around to the other side.";
        try {
            String actual = TextReader.readFileData("./data/story.txt");
            assertEquals(actual, expected);
        } catch (ProjectException e) {
            fail();
        }
    }

}