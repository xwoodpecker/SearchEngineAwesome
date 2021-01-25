import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SetupAdditionalTables
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

            statement.executeUpdate("drop table if exists dls");
            statement.executeUpdate("CREATE TABLE dls AS " +
                    "SELECT did, COUNT(did) AS len FROM tfs GROUP BY did;");

            statement.executeUpdate("drop table if exists dfs");
            statement.executeUpdate("CREATE TABLE dfs AS " +
                    "SELECT term, COUNT(*) AS df FROM tfs GROUP BY term;");
            statement.executeUpdate("CREATE INDEX idx_dfs ON dfs(term)");

            statement.executeUpdate("drop table if exists d");
            statement.executeUpdate("CREATE TABLE d AS " +
                    "SELECT COUNT(DISTINCT did) AS size FROM tfs;");

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