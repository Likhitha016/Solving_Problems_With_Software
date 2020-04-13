package Week2.StringsFirstAssignment;

public class Part3 {
	//checks whether stringa appears at least twice in stringb
	 public boolean twoOccurrences(String stringa, String stringb)
	 {
	 		//find the index where stringa first occurs in stringb
	        int index=stringb.indexOf(stringa);
	        int count=0;
	        //checks till end of stringb how many times stringb occurs
	        while(index!=-1){
	            count++;
	            index=stringb.indexOf(stringa,index + stringa.length());
	        }
	        if(count<2) 
	        	return false;
	        else 
	        	return true;
	  }
	  // finds the first occurrence of stringa in stringb, and returns the part of stringb that follows stringa
	  public String lastPart(String stringa, String stringb)
	  {
	        int index = stringb.indexOf(stringa);
	        if(index == -1)
	        	return stringb;
	        else 
	        	return stringb.substring(index + stringa.length());
	  }
	  public void testExamples() 
	  {
	        System.out.println(twoOccurrences("by", "A story by Abby Long"));
	        System.out.println(twoOccurrences("atg", "ctgtatgta"));
	        System.out.println(twoOccurrences("sea", "she sells sea shells at sea shore"));
	        System.out.println(lastPart("an", "banana"));
	        System.out.println(lastPart("zoo", "forest"));
	        System.out.println(lastPart("ce", "accessories"));
	  }
	  public static void main(String[] args)
	  {
	        Part3 p3 = new Part3();
	        p3.testExamples();
	  }
}
