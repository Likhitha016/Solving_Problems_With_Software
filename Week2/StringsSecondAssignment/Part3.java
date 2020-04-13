package Week2.StringsSecondAssignment;

public class Part3 {
    //finds the stop codon using the index of start codon index
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        int stopIndex = dna.indexOf(stopCodon,startIndex+3);
        while(stopIndex != -1) {
            int len= stopIndex - startIndex;
            if ((len-3) % 3 == 0)
                return stopIndex;
            stopIndex = dna.indexOf(stopCodon,stopIndex+1);
        }
        return dna.length();
    }
    //Finds the dna string taking start codon and stop coden
    public String findGene(String dna){
        int startIndex = dna.indexOf("ATG");
        if(startIndex == -1)
            return "";
        //finds index of all the stop codons
        int taa_index = findStopCodon(dna,startIndex,"TAA");
        int tag_index = findStopCodon(dna,startIndex,"TAG");
        int tga_index = findStopCodon(dna,startIndex,"TGA");
        if(taa_index == -1)
            taa_index = dna.length();
        if(tag_index == -1)
            tag_index = dna.length();
        if(tga_index == -1)
            tga_index = dna.length();
        //chooses the minimum indexed codon as stopcodon
        int minIndex = Math.min(taa_index,Math.min(tag_index,tga_index));
        //returns the gene string from start index to min index
        if(minIndex < dna.length())
            return dna.substring(startIndex,minIndex + 3);
        else
            return "";
    }
    //prints the gene with start codon as AGT and any one of the 3 stop codons
    public void printAllGenes(String dna){
        int startIndex = dna.indexOf("ATG");
        while(startIndex != -1){
            String gene = findGene(dna.substring(startIndex));
            System.out.println(gene);
            startIndex = dna.indexOf("ATG",startIndex + gene.length());
        }
    }
    public int countGenes(String dna){
        int count=0;
        //finds start codon
        int startIndex = dna.indexOf("ATG");
        while(startIndex != -1){
            count++;
            String gene = findGene(dna.substring(startIndex));
            //change the start codon to the index next to the found gene
            startIndex = dna.indexOf("ATG",startIndex + gene.length());
        }
        return count;
    }
    public void testCountGenes(){
        System.out.println("Number of genes: "+countGenes("ATGTAAGATGCCCTAGT"));
        System.out.println("Number of genes: "+countGenes("ATGTAAGATGTAAAGTTGATAA"));
    }
    public static void main(String args[]){
        Part3 p3=new Part3();
        p3.testCountGenes();
    }

}
