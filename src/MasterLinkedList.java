/**
 * Created by Kitcatski on 4/13/2017.
 */
public class MasterLinkedList {
    private MasterLink first;

    public MasterLinkedList(){
        first = null;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public void insertFirst(String data){
        MasterLink newLink = new MasterLink(data);
        newLink.setNext(first);
        first = newLink;
    }
    public MasterLink deleteFirst(){
        MasterLink temp = first;
        first = first.getNext();
        return temp;
    }
    public MasterLink find(String data){
        MasterLink current = first;
        while (!current.getData().equals(data)){
            if(current.getNext() == null){
                return null;
            } else {
                current = current.getNext();
            }

        }
        return current;
    }
    public MasterLink delete(String data) {
        MasterLink current = first;
        MasterLink previous = first;
        while (current.getData() != data) {
            if (current.getNext() == null) {
                return null;
            } else {
                previous = current;
                current = current.getNext();
            }
        }
        if (current == first) {
            first = first.getNext();
        } else {
            previous.setNext(current.getNext());
        }
        return current;
    }

    public void displayList(){
        System.out.println("List (First --> Last): ");
        MasterLink current = first;
        while (current != null){
            current.display();
            current = current.getNext();
        }
        System.out.println();
    }
}
