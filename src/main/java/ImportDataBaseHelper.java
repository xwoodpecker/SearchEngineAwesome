import java.sql.*;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ImportDataBaseHelper {

    private String sqlInsertIntoDocs = "insert into docs values(?, ?, ?)";
    private String sqlInsertIntoTfs = "insert into tfs values(?, ?, ?)";

    private PreparedStatement insertIntoDocs;
    private PreparedStatement insertIntoTfs;
    private Connection connection;

    public ImportDataBaseHelper()
    {
        connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:nyt.db");
            connection.setAutoCommit(false);

            insertIntoDocs = connection.prepareStatement(sqlInsertIntoDocs);
            insertIntoTfs = connection.prepareStatement(sqlInsertIntoTfs);
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());

        }
    }

    public boolean saveDocument(Document document){
        try {

            insertIntoDocs.setLong(1, document.getId());
            insertIntoDocs.setString(2, document.getTitle());
            insertIntoDocs.setString(3, document.getUrl());
            insertIntoDocs.executeUpdate();

            if(document.getContent() != null) {
                Map<String, Long> tfs = Arrays.stream(document.getContent())
                        .collect(Collectors.groupingBy(
                                Function.identity(),
                                Collectors.counting()));

                int i = 0;
                for (String term : tfs.keySet()) {
                    i++;
                    insertIntoTfs.setLong(1, document.getId());
                    insertIntoTfs.setString(2, term);
                    insertIntoTfs.setLong(3, tfs.get(term));
                    insertIntoTfs.addBatch();

                    if (i % 10 == 0) {
                        insertIntoTfs.executeBatch();
                    }
                }
            }

            return true;
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
            return false;

        }
    }

    public void commitAndClose() {
        try {
            connection.commit();

            if(insertIntoDocs != null)
                insertIntoDocs.close();

            if(insertIntoTfs != null)
                insertIntoTfs.close();

            if(connection != null)
                connection.close();

        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }
    }
}
