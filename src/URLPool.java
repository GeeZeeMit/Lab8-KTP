import java.util.LinkedList;
public class URLPool
{
    private LinkedList<URLDepth> m_Processed = new LinkedList<URLDepth>();
    private LinkedList<URLDepth> m_NotProcessed = new LinkedList<URLDepth>();
    private int m_Depth;
    private int m_Waiting;
    private int m_Threads;
    public URLPool(String url, int depth, int threads)
    {
        m_NotProcessed.add(new URLDepth(url, depth));
        m_Depth = depth;
        m_Threads = threads;
    }
    public synchronized URLDepth get() throws InterruptedException {
        if (isEmpty())
        {
            m_Waiting++;
            if (m_Waiting == m_Threads)
            {
                getSites();
                System.exit(0);
            }
            wait();
        }
        return m_NotProcessed.removeFirst();
    }
    public synchronized void addNotProcessed(URLDepth pair)
    {
        m_NotProcessed.add(pair);
        if (m_Waiting > 0)
        {
            m_Waiting--;
            notify();
        }
    }
    private boolean isEmpty()
    {
        if (m_NotProcessed.size() == 0) return true;
        return false;
    }
    public void getSites()
    {
        System.out.println("Depth: " + m_Depth);
        for (int i = 0; i < m_Processed.size(); i++)
        {
            System.out.println( m_Depth - m_Processed.get(i).getDepth() + " " +  m_Processed.get(i).getURL());
        }
        System.out.println("Links visited: " + m_Processed.size());
    }
    public void addProcessed(URLDepth pair) {
        m_Processed.add(pair);
    }
    public LinkedList<URLDepth> getProcessed()
    {
        return m_Processed;
    }
    public LinkedList<URLDepth> getNotProcessed()
    {
        return m_NotProcessed;
    }

}
