
package rss;
import java.net.URL;
import java.util.Iterator;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.HttpURLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ParseRSS {

    public static void main(String[] args) throws Exception {

        URL url = new URL("http://sunsrin.blogspot.com/atom.xml");
        HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(httpcon) {});
        List entries = feed.getEntries();
        Iterator itEntries = entries.iterator();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();

        File file = new File("/tmp/sunsrin.html");
        
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        String content = "<style> a {font-face:Verdana;font-size:22px;} </style>";
        content += "<h1>Updates for " + dateFormat.format(date) + "</h1>";
        while (itEntries.hasNext()) {
            SyndEntry entry = (SyndEntry) itEntries.next();
            content += "<a href='" + entry.getLink() + "' target=_'blank' >" + entry.getTitle() + "</a><br/><br/>";
        }
        bw.write(content);
		bw.close();

    }
}
