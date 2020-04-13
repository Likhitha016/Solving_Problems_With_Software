package Week2.StringsThirdAssignment;

import edu.duke.FileResource;
import edu.duke.StorageResource;
public class Part1{
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        int stopIndex = dna.indexOf(stopCodon,startIndex+3);
        while(stopIndex != -1) {
            int len= stopIndex - startIndex;
            if ((len-3) % 3 == 0) return stopIndex;
            stopIndex = dna.indexOf(stopCodon,stopIndex+1);
        }
        return dna.length();
    }
    public String findGene(String dna){
        int startIndex = dna.indexOf("ATG");
        if(startIndex == -1)
        	return "";
        int taa_index = findStopCodon(dna,startIndex,"TAA");
        int tag_index = findStopCodon(dna,startIndex,"TAG");
        int tga_index = findStopCodon(dna,startIndex,"TGA");

        if(taa_index == -1) 
        	taa_index = dna.length();
        if(tag_index == -1)
        	tag_index = dna.length();
        if(tga_index == -1)
        	tga_index = dna.length();

        int minIndex = Math.min(taa_index,Math.min(tag_index,tga_index));
        if(minIndex < dna.length()) return dna.substring(startIndex,minIndex + 3);
        else return "";
    }
    public void printAllGenes(String dna)
    {
        int startIndex = dna.indexOf("ATG");
        while(startIndex != -1){
            String gene = findGene(dna.substring(startIndex));
            System.out.println(gene);
            startIndex = dna.indexOf("ATG",startIndex + gene.length());
        }
    }

    //stores all the genes in the given dna and stores them in a storage resource
    public StorageResource getAllGenes(String dna)
    {
        //creates a new storage resource
        StorageResource storageResource = new StorageResource();
        int startIndex = dna.indexOf("ATG");
        while(startIndex != -1){
            String gene = findGene(dna.substring(startIndex));
            //stores the gene found in the created storage resource
            storageResource.add(gene);
            startIndex = dna.indexOf("ATG",startIndex + gene.length());
        }
        return storageResource;
    }
    //finds cg ratio by counting number of c's and g's
    public double cgRatio(String dna)
    {
        int count = 0;
        for(int i = 0; i < dna.length(); i++){
            if(dna.charAt(i) == 'C' || dna.charAt(i) == 'G'){
                count++;
            }
        }
        return (double)count/dna.length();
    }
    //counts number of times CTG occurs
    public int countCTG(String dna)
    {
        int count = 0;
        int index=dna.indexOf("CTG");
        while (index!=-1){
            //increment the count an check in the remaining string
            count++;
            index=dna.indexOf("CTG",index+dna.length());
        }
        return count;
    }

    public void processGenes(StorageResource storageResource){
        int longStringsCount = 0;
        int cgRatioCount = 0;
        String longestString = "";
        for(String str: storageResource.data()){
            //finds longest string
            if(str.length() > longestString.length()){
                longestString = str;
            }
            //prints all strings having cg ratio greater than 0.35
            if(cgRatio(str) > 0.35) {
                cgRatioCount++;
                System.out.println("String with CG Ratio greater than 0.35: " + str);
            }
            //prints strings having length greater than 9 characters
            if(str.length() > 9) {
                longStringsCount++;
                System.out.println("Strings with length greater than 9 characters: " + str);
            }

        }
        System.out.println("Number of Strings with length greater than 9 characters: " + longStringsCount);
        System.out.println("Number of Strings having CG ratio greater than 0.35" + cgRatioCount);
        System.out.println("Longest string in the file: " + longestString);
    }
    public void testGetAllGenes()
    {
        StorageResource storageResource = new StorageResource();
        storageResource = getAllGenes("AATATGTAGTAGATGCCTAGT”");
        for(String gene: storageResource.data()){
            System.out.println(gene);
        }
        storageResource = getAllGenes("ATGGAATTATAAAAATGATATGAATGGAATTATAG");
        for(String gene: storageResource.data()){
            System.out.println(gene);
        }
    }
    public void testCGRatio()
    {
        System.out.println(cgRatio("GGATGAACCCCATAG"));
    }

    public void testCountCTG(){
        System.out.println(countCTG("ATGCTGAGGCTAACGCCCCTGTAG"));
    }

    public void testProcessGenes(){
        FileResource fileResource = new FileResource("brca1line.fa");
        String dna = fileResource.asString();
        StorageResource storageResource = getAllGenes(dna);
        processGenes(storageResource);
    }

    public static void main(String[] args) {
        Part1 p1 = new Part1();
        p1.testGetAllGenes();
        p1.testCountCTG();
        p1.testCGRatio();
        p1.testProcessGenes();
    }
}
