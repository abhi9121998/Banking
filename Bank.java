
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Abhishek
 */
public class Bank {
    Bank()throws SQLException, IOException
    {
        signup();
    }
    private void signup()throws SQLException, IOException
    {
        Scanner choice = new Scanner(System.in);
        BufferedReader reader= new BufferedReader(new InputStreamReader(System.in));
        System.out.println("<------Thank you for Sign up------>");
        System.out.println("Please enter your details");
        System.out.print("Firstname : ");
        String fname=reader.readLine();
        System.out.print("Lasstname : ");
        String lname=reader.readLine();
        System.out.print("Age : ");
        int age = choice.nextInt();
        System.out.print("Contact : ");
        String contact = reader.readLine();
        new_user_register(fname, lname, age, contact);
    }
    private void new_user_register(String fname, String lname, int age, String contact)throws SQLException, IOException
    {
        Bank_db_queries DB=new Bank_db_queries(fname, lname, age, contact);
        BufferedReader reader= new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter your new password : ");
        String new_pass=reader.readLine();
        System.out.println("Re-enter : ");
        String re_enter=reader.readLine();
        while(!new_pass.equals(re_enter))
        {
            System.out.println("<------Both fields should be same------>");
            System.out.print("Enter your new password : ");
            new_pass=reader.readLine();
            System.out.print("Re-enter : ");
            re_enter=reader.readLine();
        }
        DB.change_password(re_enter);
        System.out.println("<-----Password has been Re-set----->");
    }
}
