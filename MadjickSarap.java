//Main class called MadjickSarap
//A play on the Magick and a Filipino thing called Magic Sarap
//It is the main class for an image manipulator
//For the core java 2 Midterms project

public class MadjickSarap {

//Alright, right of the bat, I will admit I am color blind so I could not indicate red
//However that isnt stopping me from trying to figure it out
//Hey at least I am taking it like a champ
  
  public static void main(String args[])
  {
    MenuDisplay menu = new MenuDisplay();

    //Commented this out because I am going to store them in a single filter class
    //Wash, NoRed, and BlackAndWhite are done
    //Now for the secondary features hehe
    //I am going to derefractor the Wash class, NoRed class, and the BlackAndWhiteClass
    
    //Objects commented out. 
    //BlackAndWhiteFilter bw = new BlackAndWhiteFilter();
    //NoRed nr = new NoRed();
    //Wash w = new Wash();
    
    //Filter class instantiated
    FilterCalculator fc = new FilterCalculator();

    //User Interaction with the console
    if (args[0].equals("--help"))
    {
      //User will be shown how to use the program
      menu.help();
    }
    else if(args[0].equals("--about"))
    {
      //User will be shown what this program is all about
      //Perhaps use the file input output stream thing to read out a text instead
      //Thinking if this should be a documentation.txt instead of another java program
      menu.about();

    }
    //BlackAndWhite
    else if (args[0].equals("BlackAndWhite") && args[1].equals(args[1]) && args[2].equals(args[2]))
    {
      //Commented this out
      //Default argument for parameter 3 = 0
      //bw.filter(args[1], args[2], 0);
      
      //New code here below
      fc.blackAndWhiteFilter(args[1], args[2]);

    }
    //NoRed
    else if (args[0].equals("NoRed") && args[1].equals(args[1]) && args[2].equals(args[2]))
    {
      //Commented this out
      //Default argument for parameter 3 = 0
      //nr.filter(args[1], args[2], 0);
      
      //New code here below
      fc.noRedFilter(args[1], args[2]);

    }
    //Wash
    else if (args[0].equals("Wash") && args[1].equals(args[1]) && args[2].equals(args[2]) && args[3].equals(args[3]))
    {
      //Converting args[3] into an integer
      int num = Integer.parseInt(args[3]);
      //Commented this out for derefractoring
      //w.filter(args[1], args[2], num);
      
      //New code here
      fc.washFilter(args[1], args[2], num);
    }
    //Beatles
    else if (args[0].equals("Beatles") && args[1].equals(args[1]) && args[2].equals(args[2]))
    {
      fc.beatlesFilter(args[1], args[2]);

    }
    //Atari
    else if (args[0].equals("Atari") && args[1].equals(args[1]) && args[2].equals(args[2]))
    {
      
      fc.atariFilter(args[1], args[2]);

    }
    else
    {
      System.out.println("Sorry I did not understand that!");
      System.out.println("Please run the program again");

    }
    
  }
}
