import java.util.Objects;
public class URLDepth {
    private String m_Url;
    private int m_Depth;

    public URLDepth(String host, int depth) {
        m_Url = host;
        m_Depth = depth;
    }

    public String getURL() {
        return m_Url;
    }

    public int getDepth() {
        return m_Depth;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof URLDepth) {
            URLDepth o = (URLDepth)obj;
            return this.m_Url.equals(o.getURL());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash();
    }
}


