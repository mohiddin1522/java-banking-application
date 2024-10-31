package banking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;


public class BankingOperations {
	static Connection con = null;
	static PreparedStatement psmt = null;
	static ResultSet rs = null;
	static int n = 1;
	static Scanner sc = new Scanner(System.in);
		
	public static void deposit(float amt, long accnos) throws InterruptedException {
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
		try {
			 while(n>0) {
			con = DatabaseConnection.getConnection();
			
			String Query = "select balance from customer where ac_no = ?";
			
			psmt = con.prepareStatement(Query);
         psmt.setLong(1, accnos);
         
         rs = psmt.executeQuery();
        
         if (rs.next()) {
             double balance = rs.getLong("balance");
             
          	   String queryforupdate = "UPDATE customer SET balance = balance + ? where ac_no = ?";
          	   
          	   psmt = con.prepareStatement(queryforupdate);
          	   psmt.setFloat(1,amt);
          	   psmt.setLong(2, accnos);
          	   
          	   int rowsupdated = psmt.executeUpdate();
          	   Query = "select balance from customer where ac_no = ?";
 				
 				  psmt = con.prepareStatement(Query);
                psmt.setLong(1, accnos);
                
                rs = psmt.executeQuery();
                if (rs.next()) {
                    double afterbalance = rs.getLong("balance");
                    System.out.println("you successfully withdraw money");
      
                 System.out.println("amount before transaction : " + balance);
          	   System.out.println("amount after transaction : " + afterbalance);
                	System.out.println("============================================");
         			System.out.println("-----Succesfully your transaction done------");
         			System.out.println("============================================");
                }
          	   
             n = 0;
       
         } else {
             System.out.println("Invalid User ID or Account Number. Please try again.");
            
         }
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
		public static void checkBalance(long accno) throws InterruptedException {
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
			try {
				 while(n>0) {
				con = DatabaseConnection.getConnection();
				
				String Query = "select balance, c_name from customer where ac_no = ?";
				
				psmt = con.prepareStatement(Query);
                psmt.setLong(1, accno);
                
                rs = psmt.executeQuery();
               
                if (rs.next()) {
                    double balance = rs.getLong("balance");
                    String name = rs.getString("c_name");
                   
                    
                    System.out.println("Welcome  " + name);
                    System.out.println("Your current balance is: " + balance);
                    System.out.println("============================================");
           			System.out.println("-----Thank you for using my application-----");
           			System.out.println("============================================");
                    n = 0;
              
                } else {
                    System.out.println("Invalid User ID or Account Number. Please try again.");
                    System.out.print("Re-enter account no:");
                    accno = sc.nextLong();
                }
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			 
		}
		
		public static void moneyTransfer(float amt, long accnos, long accnor) throws InterruptedException {
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
			try {
				 while(n>0) {
				con = DatabaseConnection.getConnection();
				
				String Query = "select balance from customer where ac_no = ?";
				
				psmt = con.prepareStatement(Query);
               psmt.setLong(1, accnos);
               
               rs = psmt.executeQuery();
              
               if (rs.next()) {
                   double balance = rs.getLong("balance");
                   if(balance >= amt) {
                	   String queryforupdate = "UPDATE customer SET balance = balance - ? where ac_no = ?";
                	   
                	   psmt = con.prepareStatement(queryforupdate);
                	   psmt.setFloat(1,amt);
                	   psmt.setLong(2, accnos);
                	   
                	   int rowsupdated = psmt.executeUpdate();
                	   
                	   Query = "select balance from customer where ac_no = ?";
       				
       				  psmt = con.prepareStatement(Query);
                      psmt.setLong(1, accnos);
                      
                      rs = psmt.executeQuery();
                      if (rs.next()) {
                          double afterbalance = rs.getLong("balance");
                         System.out.println("$"+ amt +" is succesfully transfered to acc no: " + accnor);
                       System.out.println("amount before transaction : " + balance);
                	   System.out.println("amount after transaction : " + afterbalance);
                      	System.out.println("============================================");
               			System.out.println("-----Succesfully your transaction done------");
               			System.out.println("============================================");
                      }
                	   

                   }
                   else {
                	   System.out.println("your current balance lower than amount you want to transfer");
                	   System.out.println("---------Transaction Denied-------------");
                   }
                   n = 0;
             
               } else {
                   System.out.println("Invalid User ID or Account Number. Please try again.");
                  
               }
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public static void withdraw(float amt, long accnos) throws InterruptedException {
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
			try {
				 while(n>0) {
				con = DatabaseConnection.getConnection();
				
				String Query = "select balance from customer where ac_no = ?";
				
				psmt = con.prepareStatement(Query);
              psmt.setLong(1, accnos);
              
              rs = psmt.executeQuery();
             
              if (rs.next()) {
                  double balance = rs.getLong("balance");
                  if(balance >= amt) {
               	   String queryforupdate = "UPDATE customer SET balance = balance - ? where ac_no = ?";
               	   
               	   psmt = con.prepareStatement(queryforupdate);
               	   psmt.setFloat(1,amt);
               	   psmt.setLong(2, accnos);
               	   
               	   int rowsupdated = psmt.executeUpdate();
               	   Query = "select balance from customer where ac_no = ?";
      				
      				  psmt = con.prepareStatement(Query);
                     psmt.setLong(1, accnos);
                     
                     rs = psmt.executeQuery();
                     if (rs.next()) {
                         double afterbalance = rs.getLong("balance");
                         System.out.println("you successfully withdraw money");
           
                      System.out.println("amount before transaction : " + balance);
               	   System.out.println("amount after transaction : " + afterbalance);
                     	System.out.println("============================================");
              			System.out.println("-----Succesfully your transaction done------");
              			System.out.println("============================================");
                     }
               	   

                  }
                  else {
               	   System.out.println("your current balance lower than amount you want to transfer");
               	   System.out.println("---------Transaction Denied-------------");
                  }
                  n = 0;
            
              } else {
                  System.out.println("Invalid User ID or Account Number. Please try again.");
                 
              }
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
}
