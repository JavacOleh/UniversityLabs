package common;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileService {
    private final Path filePath;

    public FileService(Path filePath) {
        if(!Files.exists(filePath)) {
            try {
                Files.createFile(filePath);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.filePath = filePath;
    }

    public List<String> readDataFromFilePath() {
        List<String> data;

        try {
            data = Files.readAllLines(filePath);
            return data;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void writeDataToFilePath(String data) {
        try {
            Files.writeString(filePath, data);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Да я знаю о существовании .property .json и .xml файлов но захотел на более низком уровне поработать с такой задачей)
    public String getPropertyFromFile(String property) {
        var result = readDataFromFilePath().
                stream()
                .filter(s -> s.contains(property))
                .findFirst()
                .map(String::new)
                .orElse("");

        return result.substring(result.indexOf(" ")).trim();
    }
}
