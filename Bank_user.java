
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class Bank_user {
    public String login_input()throws SQLException, IOException
    {
        BufferedReader reader= new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter your Account Number : ");
        String account=reader.readLine();
        System.out.print("Enter your Password : ");
        String password=reader.readLine();
        
        Bank_db_queries DB=new Bank_db_queries(account, password);
        if(login(DB, account, password))
        {
            return account;
        }
        else
        {
            return "";
        }
    }
    public boolean login(Bank_db_queries DB, String account, String password)throws SQLException
    {
        boolean login_flag=DB.login_query(account, password);
        if(login_flag)
        {
            System.out.println("<----Yor are logged in---->");
            return login_flag;
        }
        else
        {
            System.out.println("<----Credenials are incorrect---->");
            return login_flag;
        }
    }
    public void print_passbook(String acc)throws SQLException
    {
        Bank_db_queries DB=new Bank_db_queries();
        DB.select_query(acc);
    }
    public void withdraw(String acc, int amt)throws SQLException
    {
        Bank_db_queries DB=new Bank_db_queries();
        DB.withdraw_query(acc, amt);
    }
}
