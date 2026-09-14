import java.util.*;

class BattleShip
{
   static Scanner scan = new Scanner(System.in);
   static Random rand = new Random();
   static int[][] HPBoard;
   static char[][] displayBoardForHP;  
   static int[][] CPBoard;
   static int[] allPos;
   static int nbCPspotsToFind;
   static int nbHPspotsToFind;
   static int count;

   public static void main(String[] args)
   {
      intGame();
      playGame();
   }

   public static void intGame(){
      //**************** game setup
      System.out.print("Size of board? (e.g., 4 for a 4x4 board) ");
      int size = scan.nextInt();

      int row, col, direction;

      int[][] HPBoard = new int[size][size];
      System.out.println("Where do you want to put your 2-long boat? ");
      System.out.print("Coordinates of the upper-left corner: ");
      row = scan.nextInt();
      col = scan.nextInt();
      System.out.print("Direction of the boat? (0=horizontal & 1=vertical) ");
      direction = scan.nextInt();
      HPBoard[row][col] = 1;
      if (direction == 0)
         HPBoard[row][col+1] = 1;
      else
         HPBoard[row+1][col] = 1;
                                    
      char[][] displayBoardForHP = new char[size][size];
      for (int i=0; i<size; i++)
         for (int j=0; j<size; j++)
            displayBoardForHP[i][j] = 'o';
 
      int[][] CPBoard = new int[size][size];
      row = rand.nextInt(size-1);
      col = rand.nextInt(size-1);
      direction = rand.nextInt(2);
      CPBoard[row][col] = 1;
      if (direction == 0)
         CPBoard[row][col+1] = 1;
      else
         CPBoard[row+1][col] = 1;
// if need help when testing: System.out.println("CP boat at: " + row + " " + col + " " + direction);                                    
                                       
      int[] allPos = new int[size*size];
      int p = 0;
      for (int i=0; i<size; i++)
         for (int j=0; j<size; j++)
         {
            allPos[p] = i*10+j;
            p++;
         }

      for (int i=0; i<allPos.length; i++)
      {
         int pos = rand.nextInt(allPos.length);
         int temp = allPos[pos];
         allPos[pos] = allPos[i];
         allPos[i] = temp;
      }
                                    
      int nbCPspotsToFind = 2;
      int nbHPspotsToFind = 2;
      int count = 0;
   }  
   public static void playGame(){
      int row, col;
      //**************** playing game
      
      while(nbCPspotsToFind > 0 && nbHPspotsToFind > 0)
      {
         System.out.println("The board:");
         for (int i=0; i<displayBoardForHP.length; i++)
         {
            for (int j=0; j<displayBoardForHP[i].length; j++)
               System.out.print(displayBoardForHP[i][j] + " ");
            System.out.println();
         }
         
         System.out.print("Shooting coordinates (as <row><space><col>): ");
         row = scan.nextInt();
         col = scan.nextInt();
         
         if (CPBoard[row][col] == 0)
         {
            System.out.println("It's a miss");
            displayBoardForHP[row][col] = 'M';
         }
         else
         {
            System.out.println("It's a hit");
            displayBoardForHP[row][col] = 'H';
            nbCPspotsToFind--;
            if (nbCPspotsToFind == 0)
               break;
         }
         System.out.print("Computer's turn...");
         row = allPos[count]/10;
         col = allPos[count]%10;
         count++;
         System.out.print("shot at " + row + "," + col + "...");
         if (HPBoard[row][col] == 0)
         {
            System.out.println("It's a miss");
         }
         else
         {
            System.out.println("It's a hit");
            nbHPspotsToFind--;
         }
         
      }
      System.out.println("Game over");
   }}

