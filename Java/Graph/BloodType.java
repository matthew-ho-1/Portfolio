import java.io.Serializable;

//ID: 112509194 
//Email: matthew.w.ho@stonybrook.edu
//Homework 7
//CSE214
//Recitation 8-	TA Robert Ignatowicz 

/**
 * A class that contains the bloodType and if the two blood types are compatible with each other
 */
public class BloodType implements Serializable
{
   private String bloodType;
   
   /**
    * A constructor for BloodType
    */
   public BloodType(String bType)
   {
	   bloodType = bType;
   }
   
   /**
    * A method that gets the blood type in a String format
    * @return
    * 		 the blood type in a String format
    */
   public String getBloodType()
   {
	   return bloodType;
   }
   
   /**
    * A method that determines whether two blood types are compatible
    * @param recipient
    * 		 the specified recipient 
    * @param donor
    * 		 the specified donor
    * @return
    * 		 whether or not the two blood types are compatible
    */
   public static boolean isCompatible(BloodType recipient, BloodType donor)
   {
	   boolean isCompatible = false;
	   if(donor.getBloodType().equals("O"))
		   isCompatible = true;
	   else if(donor.getBloodType().equals("A"))
	   {
		   if(recipient.getBloodType().equals("A") || recipient.getBloodType().equals("AB"))
			   isCompatible = true;
	   }
	   else if(donor.getBloodType().equals("B"))
	   {
		   if(recipient.getBloodType().equals("B") || recipient.getBloodType().equals("AB"))
			   isCompatible = true;
	   }
	   else if(donor.getBloodType().equals("AB"))
	   {
		   if(recipient.getBloodType().equals("AB"))
			   isCompatible = true;
	   }
	   else if(recipient.getBloodType().equals("O"))
	   {
		   if(donor.getBloodType().equals("O"))
			   isCompatible = true;
	   }
	   else if(recipient.getBloodType().equals("A"))
	   {
		   if(donor.getBloodType().equals("O") || donor.getBloodType().equals("A"))
			   isCompatible = true;
	   }
	   else if(recipient.getBloodType().equals("B"))
	   {
		   if(donor.getBloodType().equals("O") || donor.getBloodType().equals("B"))
			   isCompatible = true;
	   }
	   else if(recipient.getBloodType().equals("AB"))
		   isCompatible = true;
	   
	   return isCompatible;
   }
}
