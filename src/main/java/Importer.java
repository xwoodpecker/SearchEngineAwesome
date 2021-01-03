import java.io.File;
import java.io.FilenameFilter;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.io.FileUtils.listFiles;

public class Importer {

    private Parser parser = new Parser();
    private DataBaseHelper dataBaseHelper = new DataBaseHelper();

    public void importFile(File file){
        if(file == null || !file.isDirectory()) {
            System.out.println("No Directory provided!");
            return;
        }

        String[] extensions = new String[] {"xml", "XML"};
        List<File> files = listFiles(file, extensions, true).stream().collect(Collectors.toList());

        for(File f : files) {
            String name = f.getName();
            Long size = f.length();
            System.out.println(String.format("Name: %s Size: %d Bytes", name, size));
            Document document = parser.parse(f);
            dataBaseHelper.saveDocument(document);

        }

    }
}
