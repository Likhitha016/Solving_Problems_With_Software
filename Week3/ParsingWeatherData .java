package Week3;

import edu.duke.*;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.File;
class ParsingWeatherData {
    //finds the hottest temperature
    public double hottestTemperatureInADay(CSVParser csvParser)
    {
        double maxTemp = 0.0;
        for(CSVRecord r: csvParser){
            double currentTemp = Double.parseDouble(r.get("TemperatureF"));
            if(currentTemp>maxTemp)
                maxTemp=currentTemp;
        }
        return maxTemp;
    }
    //finds the record having hottest temperature
    public  CSVRecord hottestTemperatureInFile(CSVParser csvParser){
        double maxTemp=0.0;
        CSVRecord maxTempRecord= null;
        for(CSVRecord r:csvParser){
            double currentTemp = Double.parseDouble(r.get("TemperatureF"));
            if(currentTemp>maxTemp)
                maxTemp=currentTemp;
                maxTempRecord=r;
        }
        return maxTempRecord;
    }

    public void testFindHottestTempInADay()
    {
        FileResource fr = new FileResource();
        CSVParser csvParser = fr.getCSVParser();
        System.out.println(hottestTemperatureInADay(csvParser));
    }
    public void testFindHottestTempFile()
    {
        FileResource fr = new FileResource();
        CSVParser csvParser = fr.getCSVParser();
        System.out.println(hottestTemperatureInFile(csvParser));
    }
    //finds coldest temperature record
    public CSVRecord coldestHourInFile(CSVParser csvParser)
    {
        //gets the maximum temperature in the record and comparing with that finds the minimum temperature
        double minTemp = hottestTemperatureInADay(csvParser);
        CSVRecord minTempRecord = null;
        for(CSVRecord r: csvParser)
        {
            double current_temp = Double.parseDouble(r.get("TemperatureF"));
            if( current_temp < minTemp)
            {
                minTemp = current_temp;
                minTempRecord = r;
            }
        }
        return minTempRecord;
    }
    public void testColdestHourInFile()
    {
        FileResource fr = new FileResource();
        CSVParser csvParser = fr.getCSVParser();
        CSVRecord r= coldestHourInFile(csvParser);
        System.out.println("Coldest temperature: " + r.get("TemperatureF") + " at " + r.get("DateUTC"));
    }
    //finds the file having coldest temperature in the list of selected files
    public void fileWithColdestTemperature()
    {
        DirectoryResource directoryResource = new DirectoryResource();
        File minTempFile = null;
        for(File file: directoryResource.selectedFiles()){
            FileResource fr= new FileResource(file);
            CSVParser csvParser = fr.getCSVParser();
            //finds the minimum temperature
            double minTemp = hottestTemperatureInADay(csvParser);
            CSVRecord r = coldestHourInFile(csvParser);
            double current_temp = Double.parseDouble(r.get("TemperatureF"));
            if(current_temp < minTemp){
                minTemp = current_temp;
                minTempFile = file;
            }
        }
        FileResource fr= new FileResource(minTempFile);
        CSVParser csvParser = fr.getCSVParser();
        for(CSVRecord r: csvParser){
            System.out.println(r.get("DateUTC") + " " + r.get("TemperatureF") + " at " + r.get("DateUTC"));
        }
    }

    public void testFileWithColdestTemperature()
    {
        fileWithColdestTemperature();
    }
    //finds the record with lowest humidity
    public CSVRecord lowestHumidityInFile(CSVParser csvParser){
        //take some maximum value for humidity and find lowest humidity
        double lowestHumidity = 10000;
        CSVRecord lowestRecord = null;
        for(CSVRecord csv: csvParser){
            String temp_humidity = csv.get("Humidity");
            double currentHumidity = Double.parseDouble(temp_humidity);
            if(currentHumidity < lowestHumidity){
                lowestHumidity = currentHumidity;
                lowestRecord = csv;
            }
        }
        return lowestRecord;
    }
    //finds the file with lowest humidity in list of selected files
    public CSVRecord lowestHumidityInManyFiles(){
        double lowestHumidity = 10000;
        CSVRecord lowestRecord = null;
        DirectoryResource directoryResource = new DirectoryResource();
        for(File file: directoryResource.selectedFiles()){
            FileResource fr= new FileResource(file);
            CSVParser csvParser = fr.getCSVParser();
            CSVRecord csv = lowestHumidityInFile(csvParser);
            String temp_csv = csv.get("Humidity");
            if(temp_csv == "N/A") continue;
            double currentHumidity = Double.parseDouble(temp_csv);
            if(currentHumidity< lowestHumidity){
                lowestHumidity = currentHumidity;
                lowestRecord = csv;
            }
        }
        return lowestRecord;
    }
    public void testLowestHumidityInFile()
    {
        FileResource fr = new FileResource();
        CSVParser csvParser =  fr.getCSVParser();
        CSVRecord r= lowestHumidityInFile(csvParser);
        System.out.println("Lowest humidity was " + r.get("Humidity") + " at " + r.get("DateUTC"));
    }
    public void testLowestHumidityInManyFiles()
    {
        CSVRecord r = lowestHumidityInManyFiles();
        System.out.println("Lowest humidity was " + r.get("Humidity") + " at " + r.get("DateUTC"));
    }
    //finds average temperature in the file
    public double averageTemperatureInFile(CSVParser csvParser)
    {
        double totalTemp= 0.0;
        int totalNum= 0;
        for(CSVRecord csv: csvParser){
            double currentTemp = Double.parseDouble(csv.get("TemperatureF"));
            totalNum++;
            totalTemp+= currentTemp;
        }
        return totalTemp/totalNum;
    }

    public void testAverageTemperatureInFile()
    {
        FileResource fr = new FileResource();
        CSVParser csvParser = fr.getCSVParser();
        System.out.println("Average temperature in file is " + averageTemperatureInFile(csvParser));
    }
    // finds average temperature of only those temperatures when the humidity was greater than or equal to value
    public double averageTemperatureWithHighHumidityInFile(CSVParser csvParser, int value){
        double total_temp= 0.0;
        int total_num=0;
        for(CSVRecord csv: csvParser){
            double current_temp = Double.parseDouble(csv.get("TemperatureF"));
            String temp_humidity= csv.get("Humidity");
            if(temp_humidity== "N/A") continue;
            double current_humidity = Double.parseDouble(csv.get("Humidity"));
            if(current_humidity< value) continue;
            total_num++;
            total_temp+=current_temp;
        }
        return total_temp/total_num;
    }

    public void testAverageTemperatureWithHighHumidityInFile()
    {
        FileResource fr =  new FileResource();
        CSVParser csvParser = fr.getCSVParser();
        double average_temp = averageTemperatureWithHighHumidityInFile(csvParser, 80);
        if(average_temp == 0) 
        	System.out.println("No temperatures with that humidity");
        else 
        	System.out.println("Average temperature when high Humidity is " + average_temp);
    }

    public static void main(String[] args) {
        ParsingWeatherData  csvWeather = new ParsingWeatherData ();
        csvWeather.testFindHottestTempInADay();
        csvWeather.testFindHottestTempFile();
        csvWeather.fileWithColdestTemperature();
        csvWeather.testLowestHumidityInManyFiles();
        csvWeather.testAverageTemperatureInFile();
        csvWeather.testAverageTemperatureWithHighHumidityInFile();

    }
}
