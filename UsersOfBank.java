package banking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class UsersOfBank {
    public static void main(String[] args) {
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        long id;
        System.out.println("==============================================");
        System.out.println("--------------Welcome to MS Bank--------------");
        System.out.println("==============================================");
        System.out.println();
        System.out.println();
        System.out.println("--choose the choice for further process--");
        System.out.println("1. New User");
        System.out.println("2. Existing User");
        System.out.println("3. Exit application");
        System.out.print("Enter your choice: ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        try {
            con = DatabaseConnection.getConnection();
            while(n>0) {
            	if (con != null) {
            		if (n == 1) {
                    String query = "INSERT INTO user(user_name, password) VALUES(?, ?)";
                    // Specify RETURN_GENERATED_KEYS when preparing the statement
                    psmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                    while (n == 1) {
                        System.out.println("----Registering New User----");
                        System.out.print("Enter User Name: ");
                        String user_name = sc.next();

                        System.out.print("Enter Password: ");
                        String password = sc.next();

                        System.out.print("Re-enter Password: ");
                        String password2 = sc.next();

                        if (password.equals(password2)) {
                            System.out.println("Password matched.");
                            System.out.print("please wait it is processing");
                            Thread.sleep(3000);
                            System.out.print("..");
                            Thread.sleep(3000);
                            System.out.print("..");
                            Thread.sleep(3000);
                            System.out.print("..");
                            Thread.sleep(3000);
                            System.out.print("..");
                            System.out.println();

                            psmt.setString(1, user_name);
                            psmt.setString(2, password);

                            int rowsAffectedouter = psmt.executeUpdate();

                            if (rowsAffectedouter > 0) {
                                System.out.println("  user successfully registered.");

                                // Fetch the generated user_id
                                ResultSet generatedKeys = psmt.getGeneratedKeys();
                                if (generatedKeys.next()) {
                                    id = generatedKeys.getLong(1); // Get the generated user_id
                                    
                                    
                                    
                                    String query3 = "Insert into customer values(?,?,?,?)";
                                    psmt = con.prepareStatement(query3);
                                    
                                    sc.nextLine();
                                    long uid = id;
                                    
                                    System.out.print("enter your name: ");
                                    String name = sc.nextLine();
                                    
                                    System.out.print("enter account number: ");
                                    long ac_no = sc.nextInt();
                                    
                                    System.out.print("enter your balance: ");
                                    double balance = sc.nextDouble();
                                    
                                    psmt.setLong(1, uid);
                                    psmt.setString(2, name);
                                    psmt.setLong(3, ac_no);
                                    psmt.setDouble(4, balance);
                                    
                                    int rowsAffectedinner = psmt.executeUpdate();
                                    System.out.print("please wait it is processing");
                                    Thread.sleep(3000);
                                    System.out.print("..");
                                    Thread.sleep(3000);
                                    System.out.print("..");
                                    Thread.sleep(3000);
                                    System.out.print("..");
                                    Thread.sleep(3000);
                                    System.out.print("..");
                                    System.out.println();
                                    System.out.println("process is completed");
                                    System.out.println("Now you can login");
                                    n = 2;
                                    
                                    
                                } else {
                                    System.out.println("Failed to retrieve the generated User ID.");
                                }
                              // Exit after successful registration
                            } else {
                                System.out.println("Registration failed. Try again.");
                            }
                        } else {
                            System.out.println("Passwords do not match. Please retry.");
                        }
                    }
                }

                if (n == 2) {
                    String query2 = "SELECT user_name, password FROM user WHERE user_name = ? AND password = ?";
                    psmt = con.prepareStatement(query2);

                    while (n == 2) {
                        System.out.print("Enter User Name: ");
                        String user_name = sc.next();

                        System.out.print("Enter Password: ");
                        String password = sc.next();
                        
                        System.out.print("please wait checking your password and user_name.");
                        Thread.sleep(3000);
                        System.out.print("..");
                        Thread.sleep(3000);
                        System.out.print("..");
                        Thread.sleep(3000);
                        System.out.print("..");
                        Thread.sleep(3000);
                        System.out.print("..");
                        System.out.println();

                        psmt.setString(1, user_name);
                        psmt.setString(2, password);

                        rs = psmt.executeQuery();

                        if (rs.next()) {
                            System.out.println("Successfully logged in.");
                            n = 0;  // Exit after successful login
                           System.out.println("please select the operation you want to perform");
                           System.out.println("1-balance enquiry");
                           System.out.println("2-deposit money");
                           System.out.println("3-transfer money");
                           System.out.println("4-withdraw money");
                           System.out.println("5-exit the application");
                           System.out.print("enter your choice: ");
                           int chc = sc.nextInt();
                           System.out.print("please wait it is processing");
                           Thread.sleep(3000);
                           System.out.print("..");
                           Thread.sleep(3000);
                           System.out.print("..");
                           Thread.sleep(3000);
                           System.out.print("..");
                           Thread.sleep(3000);
                           System.out.print("..");
                           System.out.println();
                           while(chc>0) {
                           switch(chc) {
                           		
	                           case 1: {
	                      			
	                      			System.out.print("enter your account no: ");
	                      			long accountno = sc.nextLong();
	                 
	                      			BankingOperations.checkBalance(accountno);
	                      			chc=0;
	                      			break;
	                      		}
	                           case 2: {
	                      			System.out.print("enter your account number: ");
	                      			long accnos = sc.nextLong();
	                      			
	                      			System.out.print("enter amount: ");
	                      			float amn = sc.nextFloat();
	                      			
	                      			BankingOperations.deposit(amn, accnos);
	                      			chc=0;
	                      			break;
	                      		}
                           		
                           		case 3: {
                           			System.out.print("enter your account number: ");
                           			long accnos = sc.nextLong();
                           			
                           			System.out.print("enter receivers account number: ");
                           			long accnor = sc.nextLong();
                           			
                           			System.out.print("enter amount: ");
                           			float amn = sc.nextFloat();
                           			BankingOperations.moneyTransfer(amn, accnos, accnor);
                           			chc=0;
                           			break;
                           		}
                           		case 4: {
                           			System.out.print("enter your account number: ");
                           			long accnos = sc.nextLong();
                           			
                           			System.out.print("enter amount: ");
                           			float amn = sc.nextFloat();
                           			
                           			BankingOperations.withdraw(amn, accnos);
                           			chc=0;
                           			break;
                           		}
                           		case 5: {
                           			System.out.println("============================================");
                           			System.out.println("-----Thank you for using my application-----");
                           			System.out.println("============================================");
                           			chc=0;
                           			break;
                           		}
                           		default : {
                           			System.out.print("you entered a wrong choice by mistaken re-enter:");
                                	chc = sc.nextInt();
                           		}
                           }
                           }
                            
                        } else {
                            System.out.println("Username or password incorrect. Try again.");
                        }
                    }
                }
            }
            if(n == 3) {
            	System.out.println("============================================");
       			System.out.println("-----Thank you for using my application-----");
       			System.out.println("============================================");
       			n=0;
            }
            
            if(n!=1&&n!=2&&n!=0&&n!=3) {
            	System.out.print("you entered a wrong choice by mistaken re-enter:");
            	n = sc.nextInt();
            }
            }
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } 
    }
}
