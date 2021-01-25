import java.sql.*;

public class SetupDatabase
{
    public static void main(String[] args)
    {
        Connection connection = null;
        try
        {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:nyt.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            statement.executeUpdate("drop table if exists docs");
            statement.executeUpdate("CREATE TABLE docs (" +
                    "    did INT(11) NOT NULL," +
                    "    title VARCHAR(255) NOT NULL," +
                    "    url VARCHAR(255) NOT NULL," +
                    "    PRIMARY KEY (did)" +
                    ");");
            statement.executeUpdate("CREATE INDEX idx_docs ON docs(did)");

            statement.executeUpdate("drop table if exists tfs");
            statement.executeUpdate("CREATE TABLE tfs (" +
                    "    did INT(11) NOT NULL," +
                    "    term VARCHAR(255) NOT NULL," +
                    "    tf INT(11) NOT NULL," +
                    "    PRIMARY KEY (did, term)" +
                    ");");
            statement.executeUpdate("CREATE INDEX idx_tfs ON tfs(term, did)");



        }
        catch(SQLException e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
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
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
    }
}