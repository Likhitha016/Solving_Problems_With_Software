package Week2.StringsSecondAssignment;

public class Part2 {
    //returns count of how many times stringa occurs in stringb
    public int howMany(String stringa, String stringb) 
    {
        int index = stringb.indexOf(stringa);
        int count = 0;
        while(index != -1){
            count++;
            index = stringb.indexOf(stringa,index + stringa.length());
        }
        return count;
    }
    public void testHowMany()
    {
        System.out.println(howMany("GAA","ATGAACGAATTGAATC"));
        System.out.println(howMany("AA","ATAAAA"));
        System.out.println(howMany("ap","application"));
    }
    public static void main(String[] args) {
        Part2 p2 = new Part2();
        p2.testHowMany();
    }
}