import java.util.List;
import java.util.Random;

public class HtmlParserImpl implements HtmlParser {

    private static final  int MAX = 10;
    private static  final int MIN  =  1;
    private static final String base = "http://vamsi.com/home/";

    /**
     * get urls from the the html content of a url.
     * @param url
     * @return list of urls.
     */
    @Override
    public List<String> getUrls(String url) {


        Random random =  new Random();
        int num1 = random.nextInt(MAX-MIN+1) + MIN;
        int num2 = random.nextInt(MAX-MIN+1) + MIN;


        return List.of(base + String.valueOf(num1) , base + String.valueOf(num2));
    }
}
