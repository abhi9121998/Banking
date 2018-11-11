
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class display {
    public static void main(String args[])throws SQLException, IOException
    {
        Scanner choice = new Scanner(System.in);
        System.out.println("1. New user make an Account");
        System.out.println("2. Existing user Login");
        System.out.print("Enter your choice : ");
        int n = choice.nextInt();
        if(n==2)
        {
            Bank_user BU=new Bank_user();
            String acc=BU.login_input();
            if(!acc.equals(""))
            {
                do{
                    System.out.println("1. Print passbook");
                    System.out.println("2. Withdraw Amount");
                    System.out.println("3. Deposit Amount");
                    System.out.print("Enter your choice : ");
                    n = choice.nextInt();
                    if(n==1)
                    {
                        BU.print_passbook(acc);
                    }
                    else if(n==2)
                    {
                        System.out.print("Enter the Amount(Rs.) : ");
                        int amt = choice.nextInt();
                        BU.withdraw(acc, amt);
                    }
                    System.out.println("Do you want to continue?");
                    System.out.print("Press '0' to continue : ");
                    n=choice.nextInt();
                }while(n==0);
            }
        }
        else if(n==1)
        {
            Bank B=new Bank();
            System.out.println("Want to Log in?");
            System.out.print("Press 0 to Log in : ");
            n=choice.nextInt();
            if(n==0)
            {
                Bank_user BU=new Bank_user();
                String acc=BU.login_input();
                if(!acc.equals(""))
                {
                    do{
                        System.out.println("1. Print passbook");
                        System.out.println("2. Windraw Amount");
                        System.out.println("3. Deposit Amount");
                        System.out.print("Enter your choice : ");
                        n = choice.nextInt();
                        if(n==1)
                        {
                            BU.print_passbook(acc);
                        }
                        else if(n==2)
                        {
                            System.out.print("Enter the Amount(Rs.) : ");
                            int amt = choice.nextInt();
                            BU.withdraw(acc, amt);
                        }
                        System.out.println("Do you want to continue?");
                        System.out.print("Press '0' to continue : ");
                        n=choice.nextInt();
                    }while(n==0);
                }
            }
        }
    }
}
