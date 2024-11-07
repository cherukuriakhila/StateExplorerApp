import java.util.*;
import stateinfo.WestBengal;
import stateinfo.Jharkhand;
import stateinfo.Maharashtra;
import stateinfo.Rajasthan;
import stateinfo.Goa;
import stateinfo.Gujarat;
import stateinfo.Haryana;
import stateinfo.HimachalPradesh;
import stateinfo.Karnataka;
import stateinfo.Uttarakhand;
import stateinfo.Kerala;
import stateinfo.AndhraPradesh;
import stateinfo.Telangana;
import stateinfo.Manipur;
import stateinfo.Meghalaya;
import stateinfo.Mizoram;
import stateinfo.Nagaland;
import stateinfo.ArunachalPradesh;
import stateinfo.MadhyaPradesh;
import stateinfo.Assam;
import stateinfo.Bihar;
import stateinfo.Chhattisgarh;
import stateinfo.Odisha;
import stateinfo.Punjab;
import stateinfo.Sikkim;
import stateinfo.UttarPradesh;
import stateinfo.Tripura;
import stateinfo.TamilNadu;
import stateinfo.States;
class UserData
 {
    private String[] usernames = new String[5];  
    private String[] passwords = new String[5];  
    private String[] emails = new String[5];     
    private int userC = 0;

    public boolean signup(String username, String password, String email)
    {
        if (userIndexByUsername(username) != -1)
        {
            return false;  
        }
        if (userC == usernames.length)
        {
            resizeArrays();  
        }
        usernames[userC] = username;
        passwords[userC] = password;
        emails[userC] = email;
        userC++;
        return true; 
    }

    public boolean login(String username, String password) 
    {
        int indexno = userIndexByUsername(username);
        if (indexno != -1 && passwords[indexno].equals(password))
        {
            return true;  
        }
        return false;  
    }

    public boolean resetPassword(String username, String email, String newPassword)
    {
        int indexno = userIndexByUsername(username);
        if (indexno != -1 && emails[indexno].equals(email))
        {
            passwords[indexno] = newPassword;
            return true; 
        }
        return false; 
    }
    public String getEmailByUsername(String username)
    {
        int indexno = userIndexByUsername(username);
        if (indexno != -1)
        {
            return emails[indexno];  
        }
        return null;  
    }

    public int userIndexByUsername(String username)
    {
        for (int i = 0; i < userC; i++)
        {
            if (usernames[i] != null && usernames[i].equals(username))
            {
                return i; 
            }
        }
        return -1; 
    }

    private void resizeArrays()
    {
        String[] newUsernames = new String[usernames.length * 2];
        String[] newPasswords = new String[passwords.length * 2];
        String[] newEmails = new String[emails.length * 2];

        for (int i = 0; i < usernames.length; i++)  
        {
            newUsernames[i] = usernames[i];
            newPasswords[i] = passwords[i];
            newEmails[i] = emails[i];
        }

        usernames = newUsernames;
        passwords = newPasswords;
        emails = newEmails;
    }
}
class Login 
{
    private Scanner scan = new Scanner(System.in);
    private UserData userdata = new UserData();

    public void displayMenu() 
    {
        while (true)
         {
            System.out.println("************** Menu ************");
            System.out.println("1. Signup");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scan.nextInt();
            scan.nextLine(); 

            switch (choice)
            {
                case 1:
                    handleSignup();
                    break;
                case 2:
                    displayLoginMenu(); 
                    break;
                case 3:
                    System.out.println("Exiting... Thank you!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void displayLoginMenu()
    {
        System.out.println("************** Login Menu ************");
        System.out.println("1. Login");
        System.out.println("2. Forget Password");
        System.out.print("Choose an option: ");
        int choice = scan.nextInt();
        scan.nextLine(); 

        switch (choice) 
        {
            case 1:
                handleLogin();
                break;
            case 2:
                handleForgetPassword();
                break;
            default:
                System.out.println("Invalid option. Returning to main menu...");
        }
    }

    private void handleSignup() 
    {
        System.out.println("************** Signup ************");
        System.out.print("Enter username: ");
        String username = scan.nextLine();
        System.out.print("Enter password: ");
        String password = scan.nextLine();
        System.out.print("Enter email: ");
        String email = scan.nextLine();

        if (!email.endsWith("@gmail.com"))
        {
            System.out.println("Invalid Email ");
            return;
        }

        if (userdata.signup(username, password, email))
        {
            System.out.println("Signup successful! You can now log in.");
        } 
        else
        {
            System.out.println("Username already exists. Try logging in.");
        }
    }

    private void handleLogin()
    {
        System.out.println("************** Login ************");
        System.out.print("Enter username: ");
        String username = scan.nextLine();
        System.out.print("Enter password: ");
        String password = scan.nextLine();

        if (userdata.login(username, password)) 
        {
            System.out.println("Login successful! Welcome, " + username);
            String email = userdata.getEmailByUsername(username);
            Menu m= new Menu(username, email);
            m.showMenu();
            return;
        } 
        else 
        {
            System.out.println("Invalid username or password. Would you like to try again or reset your password?");
            System.out.print("1. Try Again\n2. Forget Password\nChoose an option: ");
            int choice = scan.nextInt();
            scan.nextLine();
            if (choice == 1) 
            {
              handleLogin();
            } 
            else if (choice == 2) 
            {
                handleForgetPassword(); 
                return; 
            } 
            else 
            {
                System.out.println("Invalid option. Returning to login...");
            }
        }
    }

    private void handleForgetPassword()
    {
        System.out.println("********** Forget Password ********");
        System.out.print("Enter your username: ");
        String username = scan.nextLine();

        if (userdata.userIndexByUsername(username) != -1)
        {
            System.out.print("Enter your recovery email: ");
            String email = scan.nextLine();
            System.out.print("Enter new password: ");
            String newPassword = scan.nextLine();

            if (userdata.resetPassword(username, email, newPassword)) 
            {
                System.out.println("Password reset successful! You can now log in.");
            } 
            else 
            {
                System.out.println("Email does not match our records.");
            }
        } 
        else
        {
            System.out.println("User does not exist.");
        }
    }
}


class Menu 
{
    private Scanner scan = new Scanner(System.in);
    private String username;
    private String email;
    private boolean isPremium = false;

    public Menu(String username, String email)
    {
        this.username = username;
        this.email = email;
    }

    public void showMenu()
    {
        while (true)
         {
            System.out.println("************** User Menu ************");
            System.out.println("1. View Profile");
            System.out.println("2. Check Subscription");
            System.out.println("3. Upgrade Subscription");
            System.out.println("4. Explore States");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
            int choice = scan.nextInt();
            scan.nextLine(); 

            switch (choice)
            {
                case 1:
                    viewProfile();
                    break;
                case 2:
                    checkSubscription();
                    break;
                case 3:
                    upgradeSubscription();
                    break;
                case 4:
                    exploreStates();
                    break;
                case 5:
                    System.out.println("Logging out...");
                    return;  
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void viewProfile() 
    {
        System.out.println("************** Profile ************");
        System.out.println("Username: " + username);
        System.out.println("Email: " + email);
        System.out.println("Subscription: " + (isPremium ? "Premium" : "Basic"));
    }

    private void checkSubscription()
    {
        System.out.println("********* Subscription Status ******");
        if (isPremium) 
        {
            System.out.println("You are on the Premium plan.");
        } 
       else
        {
            System.out.println("You are on the Basic plan.");
        }
    }

    private void upgradeSubscription() 
    {
        System.out.println("********* Upgrade Subscription ********");
        if (isPremium)
        {
            System.out.println("You are already on the Premium plan.");
        } 
        else 
        {
            System.out.println("For Premium Content you need to pay 799 rupess");
            System.out.print("Do you want to upgrade to Premium (1 for yes/2 for no)? ");
            int choice=scan.nextInt();
            if (choice==1) 
            {
                Payment obj=new Payment();
                if(obj.debiting())
                {
                 isPremium = true;
                 System.out.println("You have successfully upgraded to Premium!");
                }
            } 
            else if(choice==2)
            {
                System.out.println("Upgrade cancelled.");
            }
            else
            {
                System.out.println("Invalid Choice");
            }
        }
    }

    private void exploreStates() 
     {
        System.out.println("*******************************************");
        System.out.println("*              Explore States             *");
        System.out.println("*******************************************");
        System.out.println("            1. Andhra Pradesh             ");
        System.out.println("            2. Arunachal Pradesh          ");
        System.out.println("            3. Assam                      ");
        System.out.println("            4. Bihar                      ");
        System.out.println("            5. Chhattisgarh               ");
        System.out.println("            6. Goa                        ");
        System.out.println("            7. Gujarat		      ");
        System.out.println("            8. Haryana                    ");
        System.out.println("            9. Himachal Pradesh           ");
        System.out.println("            10. Jharkhand                 ");
        System.out.println("            11. Karnataka                 ");
        System.out.println("            12. Kerala                    ");
        System.out.println("            13. Madhya Pradesh            ");
        System.out.println("            14. Maharashtra               ");
        System.out.println("            15. Manipur                   ");
        System.out.println("            16. Meghalaya                 ");
        System.out.println("            17. Mizoram                   ");
        System.out.println("            18. Nagaland                  ");
        System.out.println("            19. Odisha                    ");
        System.out.println("            20. Punjab                    ");
        System.out.println("            21. Rajasthan                 ");
        System.out.println("            22. Sikkim                    ");
        System.out.println("            23. Tamil Nadu                ");
        System.out.println("            24. Telangana                 ");
        System.out.println("            25. Tripura                   ");
        System.out.println("            26. Uttar Pradesh             ");
        System.out.println("            27. Uttarakhand               ");
        System.out.println("            28. West Bengal               ");
        System.out.print("          Choose a state to explore:");
        int state = scan.nextInt();
        scan.nextLine();  

       States stateObj = null;
    switch (state)
     {
        case 1:
            System.out.println("Exploring State Andhra Pradesh");
            stateObj = new AndhraPradesh();
            break;
        case 2:
            System.out.println("Exploring State Arunachal Pradesh");
            stateObj = new ArunachalPradesh();
            break;
        case 3:
            System.out.println("Exploring State Assam");
            stateObj=new Assam();
            break;
        case 4:
            System.out.println("Exploring State Bihar");
            stateObj=new Bihar();
            break;
        case 5:
           System.out.println("Exploring State Chhattisgarh");
           stateObj= new Chhattisgarh();
           break;
        case 6:
            System.out.println("Exploring State Goa");
            stateObj=new Goa();
            break;
        case 7:
            System.out.println("Exploring State Gujarat");
            stateObj=new Gujarat();
            break;
        case 8:
             System.out.println("Exploring State Haryana");
             stateObj=new Haryana();
            break;
        case 9:
             System.out.println("Exploring State Himachal Pradesh");
             stateObj=new HimachalPradesh();
             break;
        case 10:
            System.out.println("Exploring State Jharkhand");
            stateObj=new Jharkhand();
            break;
        case 11:
            System.out.println("Exploring State Karnataka");
            stateObj= new Karnataka();
            break;
        case 12:
            System.out.println("Exploring State Kerala");
             stateObj= new Kerala();
             break;
        case 13:
              System.out.println("Exploring State Madhya Pradesh");
              stateObj= new MadhyaPradesh();
            break;
        case 14:
            System.out.println("Exploring State Maharashtra");
            stateObj=new Maharashtra();
            break;
        case 15:
             System.out.println("Exploring State Manipur");
             stateObj= new Manipur();
             break;
        case 16:
           System.out.println("Exploring State Meghalaya");
           stateObj= new Meghalaya();
            break;
        case 17:
            System.out.println("Exploring State Mizoram ");
            stateObj= new Mizoram();
            break;
        case 18:
             System.out.println("Exploring State Nagaland");
             stateObj= new Nagaland();
             break;
        case 19:
            System.out.println("Exploring State Odisha");
            stateObj=new Odisha();
            break;
        case 20:
            System.out.println("Exploring State Punjab");
            stateObj=new Punjab();
            break;
        case 21:
            System.out.println("Exploring State Rajasthan");
            stateObj=new Rajasthan();
            break;
        case 22:
            System.out.println("Exploring State Sikkim");
            stateObj=new Sikkim();
            break;
        case 23:
            System.out.println("Exploring State Tamil Nadu");
            stateObj=new TamilNadu();
            break;
         case 24:
           System.out.println("Exploring State Telangana");
           stateObj=new Telangana();
           break;
        case 25:
           System.out.println("Exploring State Tripura");
           stateObj=new Tripura(); 
           break;
        case 26:
           System.out.println("Exploring State Uttar Pradesh");
           stateObj=new UttarPradesh();
           break;
         case 27:
           System.out.println("Exploring State Uttarakhand");
           stateObj= new Uttarakhand();
           break;
        case 28:
            System.out.println("Exploring State West Bengal");
            stateObj=new WestBengal();
            break;
        default:
            System.out.println("Invalid state selection.");
            return;
    }
    boolean exploring = true;
    while (exploring) 
    {
        if (!isPremium) 
        {
            exploring=displayLimitedOptions(stateObj);
        } 
        else 
        {
           exploring= displayFullOptions(stateObj);
        }
    }
}

private boolean displayLimitedOptions(States stateObj) 
{
    System.out.println("Limited access. Upgrade to Premium to view complete details.");
    System.out.println("1: State Details");
    System.out.println("2: Famous places (Upgrade required)");
    System.out.println("3: Best Time to Visit");
    System.out.println("4: Exit");

    int choice = scan.nextInt();
    scan.nextLine();

    switch (choice) 
    {
        case 1:
            stateObj.displayStateDetails();
            break;
        case 2:
            upgrade(stateObj);
            break;
        case 3:
            stateObj.bestTimeToVisit();
            break;
        case 4:
            return false;
        default:
            System.out.println("Invalid option. Please try again.");
    }
    return true;
}

private boolean displayFullOptions(States stateObj)
 {
    System.out.println("	1: State Details");
    System.out.println("	2: Famous foods");
    System.out.println("	3: Famous places");
    System.out.println("	4: Cultural Places");
    System.out.println("	5: Natural Attractions");
    System.out.println("	6: Famous Hotels");
    System.out.println("	7: Famous Restaurants");
    System.out.println("	8: Best Time to Visit");
    System.out.println("	9: History");
    System.out.println("	10: Festivals");
    System.out.println("        11: Districts");
    System.out.println("	12: Exit");

    int choice = scan.nextInt();
    scan.nextLine();

    switch (choice) 
    {
        case 1: stateObj.displayStateDetails();
        break;
        case 2: stateObj.famousFood(); 
        break;
        case 3: stateObj.famousPlaces(); 
        break;
        case 4: stateObj.culturalPlaces();
        break;
        case 5: stateObj.naturalAttractions();
        break;
        case 6: stateObj.famousHotels();
        break;
        case 7: stateObj.famousRestaurants(); 
        break;
        case 8: stateObj.bestTimeToVisit();
        break;
        case 9: stateObj.history(); 
        break;
        case 10: stateObj.festivals();
        break;
        case 11: stateObj.displaydistrict();
        break;
        case 12: return false;
        default: System.out.println("Invalid option. Please try again.");
    }
    return true;
}

private void upgrade(States stateObj)
 {
    System.out.println("This feature requires a Premium subscription.");
    System.out.print("Would you like to upgrade? (1 for Yes and 2 for No): ");
    int up = scan.nextInt();
    scan.nextLine();
    if (up== 1)
    {
        upgradeSubscription();
        if (isPremium) 
        {
            System.out.println("Accessing Famous Places...");
            stateObj.famousPlaces();
        }
    } 
    else
    {
        System.out.println("Returning to the explore menu...");
    }
  }      
}
class Bank 
{
 static java.util.Scanner scan=new java.util.Scanner(System.in);
 static double balance=100000000;
 static boolean debit(double amount)
 {
  if(amount<balance)
  {
   if(amount==799)
   {
    balance=balance-amount;
    System.out.println("Payment successfull");
    return true;
   }
   else
   { 
    System.out.println("we are not having the plan of "+amount);
    return false;
   }
  }
  else
  System.out.println("Insufficient balance");
  return false;
 }
}
class Paytm extends Bank
{
 public boolean pay()
 {
  System.out.print("Enter The Amount :");
  int amount=scan.nextInt();
  scan.nextLine();
  return debit(amount);
 }
}
class Gpay extends Bank
{
 public boolean pay()
 {
  System.out.print("Enter The Amount :");
  int amount=scan.nextInt();
  scan.nextLine();
  return debit(amount);
 }
}
class Ppay extends Bank
{
 public boolean pay()
 {
  System.out.print("Enter The Amount :");
  int amount=scan.nextInt();
  scan.nextLine();
  return debit(amount);
 }
}
class Payment
{
 static java.util.Scanner scan=new java.util.Scanner(System.in);
 boolean debiting()
 {
  System.out.println("In Which App You Want To Make The Payment\n1:GooglePay\n2:PhonePay\n3:Paytm");
  int option=scan.nextInt();
  if(option==1)
  {
   Gpay obj=new Gpay();
   return obj.pay();
  }
  else if(option==2)
  {
   Ppay obj=new Ppay();
   return obj.pay();
  }
  else if(option==3)
  {
   Paytm obj=new Paytm();
   return obj.pay();
  }
  else
  {
   System.out.println("Invalid option");
   return false;
  }
 }
}
class Main 
{
    public static void main(String[] args) 
    {
        Login login = new Login();
        System.out.println("*******************************************");
        System.out.println("*     Welcome to State Explorer App       *");
        System.out.println("*******************************************");
        login.displayMenu();
    }
}