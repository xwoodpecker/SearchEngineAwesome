import java.sql.*;
import java.util.ArrayList;
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


    public List<Posting> getPostings(String term){
        List<Posting> postings = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs;

            rs = statement.executeQuery("SELECT did, tf FROM Customers WHERE term =" + term + "ORDER BY did;");
            while ( rs.next() ) {
                String did = rs.getString("did");
                String tf = rs.getString("tf");
                Posting posting = new Posting(Long.valueOf(did), Integer.valueOf(tf));
                postings.add(posting);
            }
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
        return postings;
    }

    public int getDF(String term) {
    }

    public int getSizeOfD() {
        int size = 0;
        try {
            Statement statement = connection.createStatement();
            ResultSet rs;


            rs = statement.executeQuery("select size from d;");
            while ( rs.next() ) {
                size = rs.getInt("size");
            }
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
        return size;
    }

    public int getDocumentLength(long did) {
    }
}
