package Week2.StringsFirstAssignment;

public class Part2 {
    //change the dna ,startcodon and stopcodon to any one of the case to get output in required case
	public String findSimpleGene(String dna,String startCodon, String stopCodon){
	    //change the  strings to uppercase
        int startIndex = dna.toUpperCase().indexOf(startCodon.toUpperCase());
        if(startIndex == -1)
        	return "";
        int stopIndex = dna.toUpperCase().indexOf(stopCodon.toUpperCase(),startIndex + 3);
        if(stopIndex == -1) 
        	return "";
        int len= stopIndex - startIndex;
        if(((len-3) % 3) != 0) 
        	return "";
        return dna.substring(startIndex,stopIndex+3);
    }

    public void testSimpleGene(){
        System.out.println(findSimpleGene("aattgatgatgatgaattaataa","atg","taa"));
        System.out.println(findSimpleGene("aaggatatagaa","atg","tag"));
        System.out.println(findSimpleGene("","atg","taa"));
        System.out.println(findSimpleGene("ttggtaaag","atg","TAA"));
        System.out.println(findSimpleGene("ATGGTATAGTAA","ATG","TAA"));
    }
    public static void main(String[] args) {
        Part2 p2 = new Part2();
        p2.testSimpleGene();
    }
}
