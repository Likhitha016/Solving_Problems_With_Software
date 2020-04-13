package Week2.StringsFirstAssignment;

public class Part1 {
    //this method returns dna string starts with ATG and ends with TAA
    public String findSimpleGene(String dna){
        //find index of first occurrence of ATG
        int startIndex = dna.indexOf("ATG");
        //if AGT is not present in string it returns empty string
        if(startIndex == -1) 
        	return "";
        //find stop index i.e index of TAA from the index of start index
        int stopIndex = dna.indexOf("TAA",startIndex + 3);
        if(stopIndex == -1) 
        	return "";
        int len= stopIndex - startIndex;
        //if length of the string found is not multiple of 3 return  empty string else return required string
        if(((len-3) % 3) != 0) 
        	return "";
        return dna.substring(startIndex,stopIndex+3);
    }

    public void testSimpleGene(){
        System.out.println(findSimpleGene("AATTGATGATGAATTAATAA"));
        System.out.println(findSimpleGene("AAGGATATGAA"));
        System.out.println(findSimpleGene(""));
        System.out.println(findSimpleGene("TTGGTAAAAG"));
        System.out.println(findSimpleGene("ATGGTATATAA"));
    }

    public static void main(String[] args) {
        Part1 p1 = new Part1();
        p1.testSimpleGene();
    }
}