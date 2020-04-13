package Week2.StringsFirstAssignment;

import edu.duke.*;
public class Part4 {
    //finds the web links in which youtube.com is present
    public void findWebLink(String url){
        URLResource ur = new URLResource(url);
        //takes every string in file or page contained in the url
        String checkingString= ur.asString();
        String checkingStringLowercase = checkingString.toLowerCase();
        int index = checkingStringLowercase.indexOf("youtube.com");
        while(index != -1){
            //prints the url containing youtube.com by finding quotes for starting and ending of url
            String res="";
            int startIndex=checkingString.lastIndexOf("\"",index);
            int endIndex=checkingString.indexOf("\"",index);
            res=checkingString.substring(startIndex+1,index)+checkingString.substring(endIndex,index);
            System.out.println(res);
            index = checkingStringLowercase.indexOf("youtube.com",index + 1);
        }
    }

    public static void main(String[] args) {
        Part4 p4 = new Part4();
        p4.findWebLink("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
    }
}