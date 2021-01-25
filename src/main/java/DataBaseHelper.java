import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class DataBaseHelper {

    private Connection connection;


    public DataBaseHelper()
    {
        connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:nyt.db");
        }
        catch(SQLException e)
        {
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
                System.err.println(e.getMessage());
            }
        }
    }


    public List<Posting> getPostings(){

    }
}
