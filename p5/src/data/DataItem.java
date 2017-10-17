
package data;

/**
 *
 * @author jt024
 */
public class DataItem {

    public String dData;
    public int count;

    public DataItem(String term) {
        dData = term;
        count = 1;
    }
    public String getItem() {
        return this.dData;
    }
    public void setItem(String word) {
        dData = word;
    }    
    
    public int getCount() {
        return this.count;
    }
    public String displayItem() {
        if (dData.length()<3) 
            return "\nWord: " + dData + "\t\t\t\tCount: " + count+"\n";
        else if (dData.length() < 10)
            return  "\nWord: " + dData + "\t\t\tCount: " + count+"\n";
        else
            return "\nWord: " + dData + "\t\tCount: " + count+"\n";
        
    }

    ///////////////////////////////////    
    // Make valid String for DataItem//
    ///////////////////////////////////    


}
