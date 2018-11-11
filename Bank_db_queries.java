
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Abhishek
 */
public class Bank_db_queries {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost/";
    
    static final String USER = "root";
    static final String PASS = "123";
    
    String account="01150015";
    String pass="random";
    
    Connection conn = null;
    Statement stmt = null;

    public Bank_db_queries() {
    }

    
    Bank_db_queries(String account, String password)
    {
        try
        {
            login_query(account, password);
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
        try{
            if(stmt!=null)
                stmt.close();
        }catch(SQLException se2){
            se2.printStackTrace();
        }
        try{
            if(conn!=null)
                conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }
        }
    }
    
    Bank_db_queries(String fname, String lname, int age, String contact) {
        try
        {
            insert_user(fname, lname, age, contact);
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
        try{
            if(stmt!=null)
                stmt.close();
        }catch(SQLException se2){
        }// nothing we can do
        try{
            if(conn!=null)
                conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }
        }
    }
    
    public void insert_user(String fname, String lname, int age, String contact)throws SQLException, Exception
    {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(DB_URL+"bank", USER, PASS);
        stmt = conn.createStatement();
        
        String sql="SELECT MAX(CAST(SUBSTRING(account, 9, length(account)) AS UNSIGNED)) as account FROM bank_all_users";
        
        ResultSet result = stmt.executeQuery(sql);
        //System.out.println(result);
        if(result.next())
        {
            //System.out.println("I am in");
            int acc=result.getInt("account");
            acc+=1;
            account=account+acc;
            //System.out.println(account);
            create_new_user_table();
            sql = "INSERT INTO bank_all_users (firstname, lastname, account, password, age, contact)" +
                   "VALUES ('"+fname+"', '"+lname+"', '"+account+"', '"+pass+"', '"+age+"', '"+contact+"')";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO user_login_table (account, password)" +
                   "VALUES ('"+account+"', '"+pass+"')";
            stmt.executeUpdate(sql);
        }
        
        System.out.println("<------You are registered------>");
        System.out.println("Account Number : " + account);
        System.out.println("Password : " + pass);
        System.out.println("<----Now you can change your password---->");
    }
    
    private void create_new_user_table()throws SQLException
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
        String date_time=dtf.format(now);
        int initial_amt=1000;
        
        String[] dt=date_time.split("\\s");
        String sql = "CREATE TABLE IF NOT EXISTS user1 "+
                   "(account VARCHAR(12), " + 
                   "today_date VARCHAR(12), " + 
                   "today_time VARCHAR(12), " +
                   "trans_type VARCHAR(7), "+
                   "amount INTEGER, " +
                   "balance INTEGER, " +
                   " PRIMARY KEY ( account ))";
        stmt.executeUpdate(sql);
        
        sql = "INSERT INTO user1 (account, today_date, today_time, trans_type, amount, balance)" +
                   "VALUES ('"+account+"', '"+dt[0]+"', '"+dt[1]+"', 'debit', '"+initial_amt+"', 1000)";
        stmt.executeUpdate(sql);    
    }
    
    protected void change_password(String pass)throws SQLException, IOException
    {
        conn = DriverManager.getConnection(DB_URL+"bank", USER, PASS);
        stmt = conn.createStatement();
        String sql="UPDATE bank_all_users " +
                   "SET password='"+pass+"' WHERE account='"+account+"'";
        stmt.executeUpdate(sql);
        sql="UPDATE user_login_table " +
                   "SET password='"+pass+"' WHERE account='"+account+"'";
        stmt.executeUpdate(sql);
    }
    
    protected boolean login_query(String acc, String pass)throws SQLException
    {
        conn = DriverManager.getConnection(DB_URL+"bank", USER, PASS);
        boolean flag=false;
        stmt = conn.createStatement();
        String sql = "SELECT * FROM user_login_table WHERE account='"+acc+"' AND '"+pass+"'";
        ResultSet result = stmt.executeQuery(sql);
        if(result.next())
        {
            if(result.getString("account").equalsIgnoreCase(acc) && result.getString("password").equalsIgnoreCase(pass))
            {
                flag=true;
            }
        }
        return flag;
    }
    
    protected void select_query(String acc)throws SQLException
    {
        conn = DriverManager.getConnection(DB_URL+"bank", USER, PASS);
        stmt = conn.createStatement();
        String sql = "SELECT * FROM user1";
        ResultSet result = stmt.executeQuery(sql);
        System.out.println("Account No. : " + acc);
        System.out.println("---Date---"+"  --Time--  "+"--TT--"+"  --Amt(Rs.)--  "+"--Balance(Rs.)--");
        while(result.next())
        {
            String date=result.getString("today_date");
            String time=result.getString("today_time");
            String trans=result.getString("trans_type");
            int amt=result.getInt("amount");
            int bal=result.getInt("balance"); 
            System.out.print(date+" ");
            System.out.print(" "+time+" ");
            System.out.print("  "+trans+" ");
            System.out.print("     "+amt+" ");
            System.out.println("           "+bal+" ");
        }
    }
    
    protected void withdraw_query(String acc, int amt)throws SQLException
    {
        conn = DriverManager.getConnection(DB_URL+"bank", USER, PASS);
        stmt = conn.createStatement();
        String sql = "SELECT max('today_time') as max_time, balance FROM user1 where today_date=(select max(today_date) from user1)" +
                "AND today_time= (select max(today_time) from user1) group by account" ;
        ResultSet result = stmt.executeQuery(sql);
        if(result.next())
        {
            System.out.println("max_time : "+result.getString("max_time"));
            int bal=result.getInt("balance");
            System.out.println("Before Balance : "+bal);
            if(bal>=amt)
            {
                bal=bal-amt;
                System.out.println("Balance : "+bal);
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
                LocalDateTime now = LocalDateTime.now();
                String date_time=dtf.format(now);
                String[] dt=date_time.split("\\s");
                
                sql = "INSERT INTO user1 (account, today_date, today_time, trans_type, amount, balance)" +
                   "VALUES ('"+acc+"', '"+dt[0]+"', '"+dt[1]+"', 'debit', '"+amt+"', '"+bal+"')";
                stmt.executeUpdate(sql);
                System.out.println("Transaction Complete!");
            }
            else
            {
                System.out.println("!!!Insufficient Balance!!!");
            }
        }
    }
}
