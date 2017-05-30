
import java.util.Scanner;

public class ConnectFour 
{
        public static void main (String[]args)
        {
            char[][] connectGrid = new char [6][7];
            showGrid(connectGrid);
            playGame(connectGrid);
        }
        //plays the game connect four
        //Ask the user to enter a red or yellow chip
        //Game ends when there are four in a row of the same colored disk
           public static void playGame(char[][] connectGrid)
        {
            //while loop going to repeatedly ask user to enter red or yellow chip, until it knows its gameover
            //Assume that players turn = true is red players turn
            //Assume that playerturn = false is red players turn
            Scanner input = new Scanner(System.in); //to scan user's input
            boolean gameEnds = false, playersTurn = true; // as long as its not false its true so it will keep running this loop
            int columnPosition = 0;
            char chipColor;
            while(!gameEnds)
            {
                if(playersTurn)
                    { 
                        System.out.print("Please insert a red disk in one of the columns from 0 to 6:");
                        chipColor ='R';
                        playersTurn = !playersTurn; //reversed
                    }
                    else
                    {
                        System.out.print("Please insert a yellow disk in one of the columns from 0 to 6:");
                        chipColor = 'Y';
                        playersTurn = !playersTurn; //reversed
                    }
                    columnPosition = input.nextInt();
                if (dropChip(connectGrid, columnPosition, chipColor))//which chip column to drop to
                     {
                     playersTurn = !playersTurn;
                    }
                     else
                     {
                     showGrid(connectGrid);
                     if(gameStatus(connectGrid, columnPosition, chipColor))
                     {
                         gameEnds= true;
                         System.out.print(chipColor + " won! Game Over!");
                        }
                     }
                    }
                    
               input.close(); //closes the stream
            }
        //Returns true if four same color disks in a row or diagonal is found
           
         //Checks for winners and the current game status 
        public static boolean gameStatus (char[][] connectGrid, int columnPosition, char chipColor)
            {
             int rowPosition = 0; 
             for (int i=0; i<connectGrid.length; i++)
                    if (connectGrid[i][columnPosition]!=0)//if it's not empty then we found the most recent chip found
                    {
                        rowPosition = i;
                        break;
                    }
                    if (checkColumn(connectGrid, columnPosition,chipColor, rowPosition))
                    return true;
                    if (checkRow(connectGrid, columnPosition,chipColor, rowPosition))
                    return true;
                  
                    return false;
            }
            
            //return true if four same colored disks in a column is found checks up and down
        public static boolean checkColumn(char[][] connectGrid, int columnPosition, char chipColor, int rowPosition)
            {
             int chipCounter = 1;
                if ((rowPosition +4) <=6) // there's 6 rows in the grid, no need  to check this statement, if this is not true than no need to go down
                    for (int i = rowPosition + 1; i <= (rowPosition + 3); i++) // check for three times cuz you already have 1
                        if (chipColor == connectGrid[i][columnPosition])
                            chipCounter++;
                            else
                            break;
                if (chipCounter ==4) //if they all happen to be the same return true, if not return false
                    return true;
                return false;
            }
            
            //Returns true if same four color in a row is found checks left and right
            //two for loops
       public static boolean checkRow(char[][] connectGrid, int columnPosition, char chipColor, int rowPosition)
            {
              int chipCounter = 1;
                for (int i = columnPosition-1;i>=0;i--)
                    if (chipColor == connectGrid[rowPosition][i]) //check from left side if there's matching, if its not break out  
                        chipCounter++; //if it's matching increase it by 1
                    else
                        break;
                if (chipCounter >=4) //if there are three more in a row then do this check and if its true than return true and don't need to do 2nd for loop
                    return true;
                    
                for(int i=columnPosition +1; i<connectGrid[0].length; i++) //checks the right side, same thing
                    if (chipColor == connectGrid[rowPosition][i])
                        chipCounter++;
                    else
                        break;
                        
                if(chipCounter>=4)
                   return true;
                        
                    return false;
            }
       
        public static boolean dropChip(char[][] connectGrid, int columnPosition, char chipColor)
        {
            for (int i= connectGrid.length-1; i>=0; i--)
                if(connectGrid[i][columnPosition]==0)
                    {
                        connectGrid[i][columnPosition] = chipColor;
                        return false;
                }
            return true;
            }
        //Display the connect four grid  with the yellow or red chip if there are any

        public static void showGrid(char[][] connectGrid)
        {
            for (int i=0; i< connectGrid.length; i++)
            {
                for (int j = 0; j< connectGrid[i].length; j++)
                    System.out.print("|" + connectGrid[i][j]);
                    System.out.println("|");
                    
                }
            }
    }