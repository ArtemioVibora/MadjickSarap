//abstract class Filter
//Responsible laying out the blueprint for the actual Filter class
//Child would be Filter Calculator

public abstract class Filter
{
  //Commented this out
  //public abstract void filter(String filePath, String output, int value);
  
//Primary features -- abstract methods (NoRed, Wash, and BlackAndWhite)  
  //Abstract method for Wash
  public abstract void washFilter(String filepath, String output, int value);
  //Abstract method for NoRed
  public abstract void noRedFilter(String filepath, String output);
  //Abstract method for BlackAndWhite
  public abstract void blackAndWhiteFilter(String filepath, String output);
  
  //Secondary features
  //If -- no time
  //Submit without secondary features
  //HAD FUN WITH THIS ONE
  
  //Abstract method for beatlesFilter essentially turning it blue (I think) but it looks cool
  //despite being color blind
  public abstract void beatlesFilter(String filepath, String output);
  //Green and Blue (I think) which kind of reminds me of those retro games stuff
  public abstract void atariFilter(String filepath, String output);
}
