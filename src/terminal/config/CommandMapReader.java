package terminal.config;

import com.shortydigital.io.FileReader;
import com.shortydigital.io.FileWriter;
import com.shortydigital.utils.logger.Logger;

import java.io.IOException;

/**
 * Created by Chris Coppola on 2/9/2017.
 */
public class CommandMapReader {
    public static final String COMMAND_MAP_PATH = "/json/command-map.json";

    public CommandMapReader() {
        String contents;
        try {
            /* Read File */
            contents = FileReader.read(COMMAND_MAP_PATH);

            Logger.print(contents);

            /* File Read Successfully */
        } catch (IOException | NullPointerException e) {
            /* File Not Found */
            try {
                /* Create New File */
                FileWriter.createFile(COMMAND_MAP_PATH);

                /* File Created */
            } catch (IOException e1) {
                /* Could Not Create File */
                e1.printStackTrace();
            }
        }
    }
}
