import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

public class Request {
    private final String method;
    private final String path;
    private final Map<String, String> headers;
    private final InputStream in;
    private final Map<String,NameValuePair> nameValuePairList;

    private Request(String method, String path, Map<String, String> headers, InputStream in, Map<String,NameValuePair> nameValuePairList) {
        this.method = method;
        this.path = path;
        this.headers = headers;
        this.in = in;
        this.nameValuePairList = nameValuePairList;
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public InputStream getIn() {
        return in;
    }


    protected String getQueryParam(String name) {
        if (nameValuePairList.containsKey(name)) {
            return String.valueOf(nameValuePairList.get(name));
        }
        return null;
    }

    private static List<NameValuePair> getQueryParams(String url) {
        return URLEncodedUtils.parse(url, Charset.defaultCharset(), '?');
    }
}