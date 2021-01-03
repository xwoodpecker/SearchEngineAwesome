import java.sql.*;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DataBaseHelper {

    private String sqlInsertIntoDocs = "insert into docs values(?, ?, ?)";
    private String sqlInsertIntoTfs = "insert into tfs values(?, ?, ?)";

    public boolean saveDocument(Document document){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:nyt.db");


            PreparedStatement insertIntoDocs = connection.prepareStatement(sqlInsertIntoDocs);
            insertIntoDocs.setLong(1, document.getId());
            insertIntoDocs.setString(2, document.getTitle());
            insertIntoDocs.setString(3, document.getUrl());
            insertIntoDocs.executeUpdate();

            Map<String, Long> tfs = Arrays.stream(document.getContent())
                                    .collect(Collectors.groupingBy(
                                    Function.identity(),
                                    Collectors.counting()));

            int i = 0;
            for(String term : tfs.keySet()) {
                i++;
                PreparedStatement insertIntoTfs = connection.prepareStatement(sqlInsertIntoTfs);
                insertIntoTfs.setLong(1, document.getId());
                insertIntoTfs.setString(2, term);
                insertIntoTfs.setLong(3, tfs.get(term));
                insertIntoTfs.addBatch();

                if(i % 10 == 0) {
                    insertIntoTfs.executeBatch();
                }
            }

            return true;
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
            return false;

        }
        finally
        {
            try
            {
                if(connection != null)
                    connection.close();
            }
            catch(SQLException e)
            {
                System.err.println(e.getMessage());
                return false;
            }
        }
    }
}
