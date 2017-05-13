package forismatic;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import sun.util.BuddhistCalendar;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Forismatic {
    private static final String BASE_URL = "http://api.forismatic.com/api/1.0/";

    public class Quote {
        public String rawJsonString;
        public String quoteText;
        public String quoteAuthor;
        public String senderName;
        public String senderLink;
        public String quoteLink;

        public String getQuoteText() {
            return quoteText;
        }

        public void setQuoteText(String quoteText) {
            this.quoteText = quoteText;
        }

        public String getQuoteAuthor() {
            return quoteAuthor;
        }

        public void setQuoteAuthor(String quoteAuthor) {
            this.quoteAuthor = quoteAuthor;
        }

        public String getSenderName() {
            return senderName;
        }

        public void setSenderName(String senderName) {
            this.senderName = senderName;
        }

        public String getSenderLink() {
            return senderLink;
        }

        public void setSenderLink(String senderLink) {
            this.senderLink = senderLink;
        }

        public String getQuoteLink() {
            return quoteLink;
        }

        public void setQuoteLink(String quoteLink) {
            this.quoteLink = quoteLink;
        }

        @Override
        public String toString() {
            return "Quote{" +
                    "rawJsonString='" + rawJsonString + '\'' +
                    '}';
        }
    }

    public Quote getQuote(){
        try {
            HttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(BASE_URL);

            List<NameValuePair> nameValuePairs = new ArrayList<>();

            nameValuePairs.add(new BasicNameValuePair("method", "getQuote"));
            nameValuePairs.add(new BasicNameValuePair("format", "json"));
            nameValuePairs.add(new BasicNameValuePair("lang", "ru"));

            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));

            HttpResponse response = httpClient.execute(httpPost);

            HttpEntity entity = response.getEntity();
            if(entity != null){
                InputStream inputStream = entity.getContent();

                String raw = getQuoteFromStream(inputStream);
                
                Gson gson = new Gson();

                Quote quote = gson.fromJson(raw, Quote.class);

                quote.rawJsonString = raw;

                return quote;
            }
        }catch (UnsupportedEncodingException | ClientProtocolException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    private String getQuoteFromStream(InputStream inputStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = in.readLine()) != null){
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }


}
