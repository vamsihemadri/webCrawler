import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        String firstUrl = "http://vamsi.com/home/0";

        HtmlParser htmlParser = new HtmlParserImpl();
        System.out.println(" START CRAWLING ");

        List<String> crawledUrls = crawl(firstUrl,htmlParser);
        System.out.println(crawledUrls);


        System.out.println(" DONE CRAWLING ");

    }


    private static List<String> crawl(String url,HtmlParser htmlParser){


        ExecutorService executor = Executors.newFixedThreadPool(3);
        String domain = url.split("/")[2];

        Set<String> visitedSet = Collections.synchronizedSet(new HashSet<>());
        AtomicInteger atomicInteger = new AtomicInteger(1);

        visitedSet.add(url);
        executor.execute(new SingleThreadCrawlerTask(url,htmlParser,domain,visitedSet,executor,atomicInteger));

        while(atomicInteger.get()>0){

        }
        executor.shutdownNow();

        return new ArrayList<>(visitedSet);
    }
}