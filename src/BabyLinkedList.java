import java.util.Random;

/**
 * Created by Kitcatski on 4/13/2017.
 */
public class BabyLinkedList {
    private BabyLink first;

    public BabyLinkedList(){
        first = null;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public void insertFirst(String data){
        BabyLink newLink = new BabyLink(data);
        newLink.setNext(first);
        first = newLink;
    }
    public BabyLink deleteFirst(){
        BabyLink temp = first;
        first = first.getNext();
        return temp;
    }
    public BabyLink find(String data){
        BabyLink current = first;
        while (current.getData() != data){
            if(current.getNext() == null){
                return null;
            } else {
                current = current.getNext();
            }

        }
        return current;
    }
    public BabyLink delete(String data) {
        BabyLink current = first;
        BabyLink previous = first;
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
        System.out.print("BabyLink (First --> Last): ");
        BabyLink current = first;
        while (current != null){
            current.display();
            current = current.getNext();
        }
        System.out.println();
    }

    public String getRandomWord() {
        Random random = new Random();
        int i;
        BabyLink current = first;
        String word = current.getData();
        while(current.getNext() != null){
            i = random.nextInt(11);
            if(i > 5){
                word = current.getData();
            }
            current = current.getNext();
        }
        return word;
    }
}
