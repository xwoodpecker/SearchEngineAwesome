import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper {

    private Connection connection;


    public DataBaseHelper()
    {
        connection = null;
    }


    public List<Posting> getPostings(String term){
        List<Posting> postings = new ArrayList<>();
        Statement statement = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:nyt.db");
            statement = connection.createStatement();

            rs = statement.executeQuery("SELECT did, tf FROM tfs WHERE term =" + term + "ORDER BY did;");
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
                if(rs != null)
                    rs.close();
                if(statement != null)
                    statement.close();
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
        int df = -1;

        try {
            Statement statement = connection.createStatement();
            ResultSet rs;

            rs = statement.executeQuery("SELECT df FROM dfs WHERE term =" + term + ";");
            while ( rs.next() ) {
                df = Integer.valueOf(rs.getString("df"));
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
        return df;
    }

    public int getSizeOfD() {
    }

    public int getDocumentLength(long did) {
        int dl = -1;

        try {
            Statement statement = connection.createStatement();
            ResultSet rs;

            rs = statement.executeQuery("SELECT len FROM dls WHERE did =" + did + ";");
            while ( rs.next() ) {
                dl = Integer.valueOf(rs.getString("len"));
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
        return dl;
    }
}
