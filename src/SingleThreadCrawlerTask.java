import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

public class SingleThreadCrawlerTask implements Runnable{

    private String url;
    private HtmlParser htmlParser;
    private String domain;

    private Set<String> visitedSet;
    private Executor executor;
    private AtomicInteger atomicInteger;

    public SingleThreadCrawlerTask(String url, HtmlParser htmlParser, String domain, Set<String> visitedSet, Executor executor, AtomicInteger atomicInteger){

        this.url = url;
        this.htmlParser = htmlParser;
        this.domain = domain;
        this.visitedSet  = visitedSet;
        this.executor = executor;
        this.atomicInteger = atomicInteger;



    }
    @Override
    public void run() {
        for(String childUrl : htmlParser.getUrls(url)){
            if(childUrl.split("/")[2].equals(domain) && visitedSet.add(childUrl)){
                atomicInteger.incrementAndGet();
                executor.execute(new SingleThreadCrawlerTask(childUrl,htmlParser,domain,visitedSet,executor,atomicInteger));
            }
        }

        atomicInteger.decrementAndGet();
    }
}
