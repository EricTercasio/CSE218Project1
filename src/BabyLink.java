/**
 * Created by Kitcatski on 4/13/2017.
 */
public class BabyLink {
    private String data;
    private BabyLink next;

    public BabyLink(String data){
        this.data = data;
        next = null;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public BabyLink getNext() {
        return next;
    }

    public void setNext(BabyLink next) {
        this.next = next;
    }

    public void display(){
        System.out.print(data +" ");
    }
}
