import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
  Method for filter class is to make it stupidly simple (KISS)
  I dont want to overengineer but then again I dont want it to
  be oversimplified. 
  
  Hmmm, perhaps if a constructor is needed I will implement but so far I
  dont need it. Setters and Getters can be implemented but I dont need it
  since I made the variables into static where they can be seen by all methods.
  
  The cons of this is that I do not know how secure this might be. Since the variables
  for say height or width are global.
  
  The pros of this is the reduction of memory since, if I were to declare an int in each
  method it would take up unnecessary memory. Though admittedly I am not sure what big O notation
  should this be. 
  Anyways, according to my research is that the nested for loop y is responsible for the for first row of x
  Extracting the RGB values
  And then when y 1 terminates and the condition is still true, it will move on to y 2 and then extract the second row of the RGB values. That is the reason why I am going to have 2 for loops with one nested inside it. And then take the RGB values
*/

//Filter Calculator
//What this class does is just make the methods for wash, no red and blackAndWhite
//And then call them in the main class which MadjickSarap.java
public class FilterCalculator extends Filter {

  //Made it static to be seen by all methods within the class
  //I was debating with myself if I should use setters and getters
  //And settled this one so I could clearly see the flow of my code

  //Since the header is 54 bytes, it would not make sense if I were to declare
  //the array over and over again in each method, the same with height and width
  private static byte[] header = new byte[54]; 
  
  //What this function does is brighten the image
  public void washFilter(String filePath, String output, int value)
  {
    try
    {
      //This cannot be made global
      FileInputStream fileInput = new FileInputStream(filePath);
      FileOutputStream fileOutput = new FileOutputStream(output + "Wash.bmp");
      
      //byte[] header = new byte[54];
      
      //Converts byte to int datatypes
      fileInput.read(header);
      fileOutput.write(header);
      
      int width = ((header[21] & 0xFF) << 24) | 
          ((header[20] & 0xFF) << 16) | 
          ((header[19] & 0xFF) << 8 ) | 
          (header[18] & 0xFF);
      
      int height = ((header[25] & 0xFF) << 24) | 
        ((header[24] & 0xFF) << 16) | 
        ((header[23] & 0xFF) << 8) | 
        (header[22] & 0xFF);
                
      int rowSize = (width * 3 + 3) & ~3;

      byte[] row = new byte[rowSize];

      for (int y = 0; y < height; y++) {
        fileInput.read(row); //THis one reads the pixel data
        int red, green, blue, rgb;
        red = green = blue = rgb = 0;
        int amount = (int)(value * 255 /100);
                
        //the nested for loop will extract the RGB values
        for (int x = 0; x < width * 3; x += 3) {
          red = (row[x + 2] & 0xFF) + amount;
          green = (row[x + 1] & 0xFF) + amount;
          blue = (row[x] & 0xFF) + amount;
          
          if (red > 255) red = 255;
          if (green > 255) green = 255;
          if (blue > 255) blue = 255;
          int wash = red + green + blue; 
          row[x + 2] = (byte) wash;
        }
        fileOutput.write(row);
      }
    }
    catch (IOException ioe)
    {
      ioe.printStackTrace();
    }
    
    System.out.println("FILE CREATED WITH WASH COLOR!");
  
  }
  
  //What this function does is to change the bmp file to No red bmp file
  public void noRedFilter(String filePath, String output)
  {
    try
    {
      FileInputStream fileInput = new FileInputStream(filePath);
      FileOutputStream fileOutput = new FileOutputStream(output + "NoRed.bmp");
      
      //byte[] header = new byte[54];
        
      fileInput.read(header);
      fileOutput.write(header);
      
      int width = ((header[21] & 0xFF) << 24) | 
          ((header[20] & 0xFF) << 16) | 
          ((header[19] & 0xFF) << 8 ) | 
          (header[18] & 0xFF);
      
      int height = ((header[25] & 0xFF) << 24) | 
        ((header[24] & 0xFF) << 16) | 
        ((header[23] & 0xFF) << 8) | 
        (header[22] & 0xFF);
                
      int rowSize = (width * 3 + 3) & ~3;
      byte[] row = new byte[rowSize];
              
      //int rowSize = (width * 3 + 3) & ~3;
      //byte[] row = new byte[rowSize];
      
      for (int y = 0; y < height; y++) {
        fileInput.read(row); //THis one reads the pixel data
                
        //the nested for loop will extract the RGB values
        for (int x = 0; x < width * 3; x += 3) {
          int red = row[x + 2] & 0xFF;
          int green = row[x + 1] & 0xFF;
          int blue = row[x] & 0xFF;
          int noRed = (red + green + blue) / 3;

          //row[x] = row[x + 1] = row[x + 2] = (byte) noRed;
          //Let's just say this is my new red hahahahahaha
          //I am color blind I am so sorry
          //Shit, I cant see if it is red or not. 
          row[x + 1] = row[x + 2] = (byte) noRed;
        }
        fileOutput.write(row);
      }
    }
    catch (IOException ioe)
    {
      ioe.printStackTrace();
      
    }
    
    System.out.println("FILE CREATED WITH NO RED COLOR!");
  
  }
  public void blackAndWhiteFilter(String filePath, String output)
  {
    try
    {
    
      FileInputStream fileInput = new FileInputStream(filePath);
      FileOutputStream fileOutput = new FileOutputStream(output + "BlackAndWhite.bmp");
      
      //byte[] header = new byte[54];
        
      fileInput.read(header);
      fileOutput.write(header);
      
      int width = ((header[21] & 0xFF) << 24) | 
          ((header[20] & 0xFF) << 16) | 
          ((header[19] & 0xFF) << 8 ) | 
          (header[18] & 0xFF);
      
      int height = ((header[25] & 0xFF) << 24) | 
        ((header[24] & 0xFF) << 16) | 
        ((header[23] & 0xFF) << 8) | 
        (header[22] & 0xFF);
                
      int rowSize = (width * 3 + 3) & ~3;
      byte[] row = new byte[rowSize];
        
      for (int y = 0; y < height; y++) {
        fileInput.read(row); //THis one reads the pixel data
                
        //the nested for loop will extract the RGB values
        for (int x = 0; x < width * 3; x += 3) {
          int red = row[x + 2] & 0xFF;
          int green = row[x + 1] & 0xFF;
          int blue = row[x] & 0xFF;
          //BOOOM BLACK WHITE STUFF HO HO HO SANTA CLAUS
          int blackAndWhite = (red + green + blue) / 3;

          
          row[x] = row[x + 1] = row[x + 2] = (byte) blackAndWhite;
        }
        fileOutput.write(row);
      }
    }
    catch (IOException ioe)
    {
      ioe.printStackTrace(); 
    }
    System.out.println("FILE CREATED WITH BLACK AND WHITE COLOR!");
  
  }
  
  //What this function does is to make cool colors with minimal bright colors
  //And subjectively it makes think of the beatles
  public void beatlesFilter(String filePath, String output)
  {
    try
    {
      FileInputStream fileInput = new FileInputStream(filePath);
      FileOutputStream fileOutput = new FileOutputStream(output + "beatles.bmp");
    
      fileInput.read(header);
      fileOutput.write(header);
    
      int width = ((header[21] & 0xFF) << 24) | 
          ((header[20] & 0xFF) << 16) | 
          ((header[19] & 0xFF) << 8 ) | 
          (header[18] & 0xFF);
      
      int height = ((header[25] & 0xFF) << 24) | 
        ((header[24] & 0xFF) << 16) | 
        ((header[23] & 0xFF) << 8) | 
        (header[22] & 0xFF);
        
      int rowSize = (width * 3 + 3) & ~3;
      byte[] row = new byte[rowSize];
      for (int y = 0; y < height; y++) {
        fileInput.read(row); //THis one reads the pixel data
                
        //the nested for loop will extract the RGB values
        for (int x = 0; x < width * 3; x += 3) {
          int red = row[x + 2] & 0xFF;
          int green = row[x + 1] & 0xFF;
          int blue = row[x] & 0xFF;
          blue = 0;
          //BOOOM BLACK WHITE STUFF HO HO HO SANTA CLAUS
          int beatles = (red + green + blue) / 3;

          
          row[x + 1] = row[x + 2] = (byte) beatles;
        }
        fileOutput.write(row);
      }
    }
    catch (IOException ioe)
    {
      ioe.printStackTrace(); 
    }
    System.out.println("Hey Jude!");
  
  }
  
  //This function does atari retro 80's aesthetic
  public void atariFilter(String filePath, String output)
  {
    try
    {
      FileInputStream fileInput = new FileInputStream(filePath);
      FileOutputStream fileOutput = new FileOutputStream(output + "atari.bmp");
    
      fileInput.read(header);
      fileOutput.write(header);
    
      int width = ((header[21] & 0xFF) << 24) | 
          ((header[20] & 0xFF) << 16) | 
          ((header[19] & 0xFF) << 8 ) | 
          (header[18] & 0xFF);
      
      int height = ((header[25] & 0xFF) << 24) | 
        ((header[24] & 0xFF) << 16) | 
        ((header[23] & 0xFF) << 8) | 
        (header[22] & 0xFF);
        
      int rowSize = (width * 3 + 3) & ~3;
      byte[] row = new byte[rowSize];
      for (int y = 0; y < height; y++) {
        fileInput.read(row); //THis one reads the pixel data
                
        //the nested for loop will extract the RGB values
        for (int x = 0; x < width * 3; x += 3) {
          int red = row[x + 2] & 0xFF;
          int green = row[x + 1] & 0xFF;
          int blue = row[x] & 0xFF;
          red = 0;
          green = 100;
          blue = 100;
          //BOOOM BLACK WHITE STUFF HO HO HO SANTA CLAUS
          int atari = (red + green + blue) / 3;

          
          row[x + 1] = row[x + 2] = (byte) atari;
        }
        fileOutput.write(row);
      }
    }
    catch (IOException ioe)
    {
      ioe.printStackTrace(); 
    }
    System.out.println("ET HAS GONE HOME!");
  
  }
}
