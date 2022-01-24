// skeleton version of the class

public class Guitar37 implements Guitar {

   //data fields
   public static final String KEYBOARD = 
      "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";  // keyboard layout
   private GuitarString[] instrument; 
   private int concertA;
   private int numStrings;
   private double frequency;  
   
     
   // creates a guitar of 37 different strings
   public Guitar37() {
      numStrings = 37;
      //puts in a new string on the guitar
      instrument = new GuitarString[numStrings];
      for (int i = 0; i < instrument.length; i++) {
         frequency = 440.0 * (double)Math.pow(2, ((i - 24.0) /12.0)); 
         instrument[i] = new GuitarString(frequency);
      }
      
   }
   
   /* Pre: Each string on the guitar is assigned to a particular key 
   stroke. 
   post: This method is to assign a pitch to each key stroke that 
   references a particular string. */
    
   public void playNote(int pitch) {
   
      int base = 36;
      int newPitch = pitch + 24;
      if (0 <= newPitch || newPitch <= base) {
         pluck(KEYBOARD.charAt(newPitch));
      }
      return;
   } 
   
   
   /* checks to see if that Key is apart of the keyboard
   if that key is pressed, then the corresponding string should be plucked */
   public void pluck(char key) {
      int index = 0;
      //exception case for if Key is not in the KEYBOARD string
      if (KEYBOARD.indexOf(key) == -1) {
         throw new IllegalArgumentException();
      } else {
         index = KEYBOARD.indexOf(key); 
         instrument[index].pluck(); 
      }
   } 
   
   /* checks the key to see if its one of the characters on the 
   keyboard, otherwise if not it will return false */
   public boolean hasString(char key) {
   
      if (KEYBOARD.indexOf(key) == -1) {
         return false;
      } else {
         return true;
      }    
   }
   
   // takes the first value of the ringBuffer of each string
   // and adds them all together. The result should be the 
   // sum of all 37 strings
   public double sample() {
      double sum = 0.0; 
      for (int i = 0; i < instrument.length; i++) {
         sum += instrument[i].sample();  
      }
      return sum; 
   }
   
   /*Pre: iterates through one string at a time and updates
   the ring buffer to simulate the dissipation of sound using
   the Kurplus algorithm. 
   Post: A string that has vibrated after one unit time */
   public void tic() {
      for (int i = 0; i < instrument.length; i++) {
         instrument[i].tic(); 
      }
   }
   
   
   
   public int time() {
   //int tics = instrument; 
     return -1;
   }
}