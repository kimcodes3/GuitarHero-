import java.util.*;

public class GuitarString {

   //data fields
   private double decay = 0.996; 
   private Queue<Double> ringBuffer;
    
   public static void main(String[] args) {
   
   }
    
   // constructor creates the guitarstring, which keeps track of a ring buffer
   // Ring buffer length is determined by the input frequency value. 
   // Results in a queue is filled with zeros to represent the string at rest 
   public GuitarString(double frequency) { 
      int N = (int)(Math.round(StdAudio.SAMPLE_RATE / frequency));
      
         // exception cases for the ringBuffer
      if (frequency <= 0 || N < 2 )  {
         throw new IllegalArgumentException();
         
         // creates a ring Buffer with size N and fills it with zeros
      } else {
         ringBuffer = new LinkedList<Double>(); 
         for (int i = 0; i < N; i++) {
            ringBuffer.add(0.0);
         } 
      }
   }
       
   // initializes the guitar string with the same size and contents as the double
   // array of the input. used for testing purposes
   public GuitarString(double[] init) {
      // checks for an array that is less than two values 
      if (init.length <= 2) {
         throw new IllegalArgumentException(); 
      }
      ringBuffer = new LinkedList<Double>();     
      // adds all the values in the double array to the ringBuffer array 
      for (int i = 0; i < init.length; i++) { 
         double value = init[i];
         ringBuffer.add(value);
      }
   }  
  
   // this method fills the ring buffer with a bunch of random values ranging from
   // -0.5 to 0.5. This is done to simulate white noise. 
   public void pluck() { 
      for (int i = 0; i < ringBuffer.size(); i++) { 
         ringBuffer.remove();
         Random r = new Random();
         double value = r.nextDouble() - 0.5; 
         ringBuffer.add(value);
      }
   }
   
   // simulates the dissipation of sound as one unit of time passes. Method changes
   // ring buffer -- removes first value and adds the Karplus method value to the end. 
   public void tic() {
      double temp = ringBuffer.peek(); 
      ringBuffer.remove(); 
      double tic = ringBuffer.peek(); 
      double Karplus = decay * 0.5 * (temp + tic); 
      ringBuffer.add(Karplus);
   } 
     
   // Returns the first value of the queue using one of its'
   // pre determined methods.
   public double sample() {
      return ringBuffer.peek();
   } 
 
}