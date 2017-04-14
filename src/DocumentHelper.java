import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Kitcatski on 4/12/2017.
 */
public class DocumentHelper {
    private String text;

    public DocumentHelper(String text){
        this.text = text;
    }
    public List<String> getTokens(String pattern){
        ArrayList<String> tokens = new ArrayList<>();
        Pattern tokenSplitter = Pattern.compile(pattern);
        Matcher m = tokenSplitter.matcher(text);

        while(m.find()){
            tokens.add(m.group());
        }
        return tokens;

    }
    public int countSyllables(String word){
        int counter = 0;
        String pattern = "[aeiouyAEIOUY]+";
        Pattern tokenSplitter = Pattern.compile(pattern);
        Matcher matcher = tokenSplitter.matcher(word);
        String lastToken = "";
        while(matcher.find()){
            counter++;
            lastToken = matcher.group();
        }
        if(counter > 1 && word.charAt(word.length() - 1) == 'e' && lastToken.equals("e")){
            counter--;

        }
        return counter;
    }
    //public abstract int getNumberOfWords();

    //public abstract int getNumberofSentences();

    //public Stri
}
