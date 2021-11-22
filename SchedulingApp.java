import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class SchedulingApp
{

   public static Scanner input = new Scanner(System.in);
   
   
   public static void main(String[] args)
   {
      if(args.length < 1){
         System.out.println("Error. Usage: java ClassName inputfile");
         System.exit(1);
      } 
      try(Scanner fileInput = new Scanner(new File(args[0])))
      {
               
      
         String scheduler = fileInput.nextLine();
         String[] processString = fileInput.nextLine().split(",");
         int[] processes = convertToIntArray(processString);
         //can also do as 1 line
         int[] arrivals = convertToIntArray(fileInput.nextLine().split(","));
         int[] executions = convertToIntArray(fileInput.nextLine().split(","));
         //int[] rt = convertToIntArray(fileInput.nextLine().split(","));
                  
      
         if(scheduler.equalsIgnoreCase("FCFS"))
            fcfsScheduler(processes, arrivals, executions);
            //SRTN scheduler class
         if(scheduler.equalsIgnoreCase("SRTN"))
            srtnScheduler(processes, arrivals, executions);
      
      }
      catch(FileNotFoundException e){
         System.out.println("File cannot be found.");
      }
   
   }
      
   public static int[] convertToIntArray(String[] source)
   {
      
         //create the int array to store the items
         //should be the same size as the array of Strings
      int[] numArray = new int[source.length];
         
         //loop through the array of strings
         //convert each one to an int and store it in the parallel
         //index in the int array
         //so if the String array is ["1", "2", "3", "4", "5"]
         //the int array would be [1,2,3,4,5] where 1 in the String
         //array is in the same spot in the int array 
      for(int i = 0; i < source.length; i++)
      {
         
         numArray[i] = Integer.parseInt(source[i]);
      }
         
         //return it back to where you needto convert it 
      return numArray;
   }
      
         
   public static void fcfsScheduler(int[] pids, int[] arrivals, int[] executions)
   {
         
      int[] exits = new int[pids.length];
      int[] turnarounds = new int[pids.length];
      int[] waits = new int[pids.length];
      int currentTime = 0;
         
      for(int i = 0; i < pids.length; i++){
         
         currentTime += executions[i];
         exits[i] = currentTime;
         turnarounds[i] = exits[i] - arrivals[i];
         waits[i] = turnarounds[i] - executions[i];
      }
      System.out.printf("%-10s%-10s%-12s%-7s%-13s%-7s%n", 
         "Process", "Arrival", "Execution", "Exit", "Turnaround", "Wait");
      for(int i = 0; i < pids.length; i++)
      {
         System.out.printf("%-10s%-10s%-12s%-7s%-13s%-7s%n",
               pids[i], arrivals[i], executions[i], exits[i], turnarounds[i], waits[i]);
      }
                  
   }
   public static void srtnScheduler(int[] pids, int[] arrivals, int[] executions)
   { 
                 
      int[] exits = new int[pids.length];
      int[] turnarounds = new int[pids.length];
      int[] waits = new int[pids.length];
      final int runtime = 0;
      int currentTime = 0;
      
         
      for(int i = 0; i < pids.length; i++){  
                                     
         currentTime += executions[i];
         exits[i] = currentTime;
         turnarounds[i] = exits[i] - arrivals[i];
         //waits[i] = turnarounds[i] - executions[i];
         waits[i] = exits[i] - arrivals[i];
         //rt[i] = executions[i] + turnarounds[i];
         //runtime[i] = arrival[i] 
         
         
        
            
      }
      System.out.printf("%-10s%-10s%-12s%-7s%-13s%-7s%n", 
         "Process", "Arrival", "Execution", "Exit", "Turnaround", "Wait");
      for(int i = 0; i < pids.length; i++)
      {
         System.out.printf("%-10s%-10s%-12s%-7s%-13s%-7s%n",
               pids[i], arrivals[i], executions[i], exits[i], turnarounds[i], waits[i]);
      }
                  
   }

      
}
