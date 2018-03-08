package sample.kingja.contactssir;

/**
 * Description:TODO
 * Create Time:2018/3/8 16:58
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class Contacts implements FirstLetter{
    @Override
    public String getFirstLetter() {
        return firstLetter.substring(0,1);
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    private String firstLetter;
    private String number;
    private String name;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Contacts(String firstLetter, String number, String name) {
        this.firstLetter = firstLetter;
        this.number = number;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Contacts{" +
                "firstLetter='" + firstLetter + '\'' +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
