/**
 * Created by Kitcatski on 4/13/2017.
 */
public class MasterLink {
    private String data;
    private MasterLink next;
    private BabyLinkedList babyLinkedList;

    public MasterLink(String data) {
        this.data = data;
        next = null;
        babyLinkedList = null;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public MasterLink getNext() {
        return next;
    }

    public void setNext(MasterLink next) {
        this.next = next;
    }

    public BabyLinkedList getBabyLinkedList() {
        return babyLinkedList;
    }

    public void setBabyLinkedList(BabyLinkedList babyLinkedList) {
        this.babyLinkedList = babyLinkedList;
    }

    public void display(){
        System.out.print("MasterLink { " +data + " } ");
        if(babyLinkedList != null) {
            babyLinkedList.displayList();
        }
        System.out.println();

    }
}
