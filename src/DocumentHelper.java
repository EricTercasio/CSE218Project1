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
    public int getNumberOfWords(String pattern){

        return pattern.split("\\w+").length;
    }

    public int getNumberofSentences(String pattern){
        return pattern.split("[!?.:]+").length;
    }

    public String getText(){
        return text;
    }
    public double getFleschScore(String pattern){
        int words = getNumberOfWords(pattern);
        int sentences = getNumberofSentences(pattern);
        int syllables = countSyllables(pattern);
        return (206.835 - (1.015*(words/sentences)) - (84.6 * (syllables/words)));
    }
}
