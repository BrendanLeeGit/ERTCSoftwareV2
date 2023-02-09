import java.util.ArrayList;
import java.math.BigDecimal;
public class Person {
    private String name;
    private ArrayList<BigDecimal> wages;

    public Person(String wage){
        wages = new ArrayList<>();
    }

    public void addWage(String wage){
        wages.add(new BigDecimal(wage));
    }

    public void addToExistingWage(String wage, int index){
        //for when there's a repeat name in a quarter (ew)
        wages.set(index, wages.get(index).add(new BigDecimal(wage)));
    }

    public BigDecimal getWage(int index){
        return wages.get(index);
    }

    public String getName(){
        return name;
    }

}
