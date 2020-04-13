package Week3;

import edu.duke.*;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
public class ParsingExportData {
    //gets the information of the country given in the argument
    public String countryInfo(CSVParser parser, String country){
        for(CSVRecord r: parser){
            if(r.get("Country").equals(country)){
                return country + ": " + r.get("Exports") + ": " + r.get("Value (dollars)");
            }
        }
        return "NOT FOUND";
    }
    //gets the list of countries having both exportItem1 and exportItem2
    public void listExportersTwoProducts(CSVParser csvParser, String exportItem1, String exportItem2){
        for(CSVRecord r: csvParser){
            if(r.get("Exports").contains(exportItem1) && r.get("Exports").contains(exportItem2)){
                System.out.println(r.get("Country"));
            }
        }
    }
    //finds number of countries export the given Item
    public int numberOfExporters(CSVParser csvParser, String exportItem){
        int no_of_countries= 0;
        for(CSVRecord r: csvParser){
            if(r.get("Exports").contains(exportItem)){
            	no_of_countries ++;
            }
        }
        return no_of_countries;
    }
    //finds the countries having the value of amount greater than the given amount
    public void bigExporters(CSVParser csvParser, String amount){
        int amt=Integer.parseInt(amount);
        for(CSVRecord r: csvParser){
            if(Integer.parseInt(r.get("Value (dollars)")) > amt){
                System.out.println(r.get("Country") + " " + r.get("Value (dollars)"));
            }
        }
    }

    public void tester(){
        FileResource fileResource = new FileResource();
        CSVParser csvParser = fileResource.getCSVParser();
        System.out.println(countryInfo(csvParser,"South Africa"));
        csvParser = fileResource.getCSVParser();
        listExportersTwoProducts(csvParser, "gold", "platinum");
        csvParser = fileResource.getCSVParser();
        System.out.println(numberOfExporters(csvParser,"diamonds"));
        csvParser = fileResource.getCSVParser();
        bigExporters(csvParser,"$400,000,000");
    }

    public static void main(String[] args) {
        ParsingExportData csvParserRunner = new ParsingExportData();
        csvParserRunner.tester();
    }
}