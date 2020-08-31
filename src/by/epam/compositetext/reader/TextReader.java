package by.epam.compositetext.reader;

import by.epam.compositetext.exception.ProjectException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class TextReader {
    private static Logger logger = LogManager.getLogger(TextReader.class);

    private TextReader() {
    }

    public static String readFileData(String filepath) throws ProjectException {
        try (Stream<String> stream = Files.lines(Paths.get(filepath))) {
            String text = stream.reduce("", (a, b) -> a + "\n" + b);
            text = text.substring(1);
            logger.info("Text from file has been read successfully.");
            return text;
        } catch (IOException e) {
            throw new ProjectException("File has not been read. Wrong path: " + filepath, e);
        }
    }

}