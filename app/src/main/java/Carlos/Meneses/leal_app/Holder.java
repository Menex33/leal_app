package Carlos.Meneses.leal_app;

import android.app.RemoteAction;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Holder {

    private final String URL_Tem = "https://www.reddit.com/top"
                                + ".json"
                                + "?after=AFTER";
    String subreddit, url, after;

    Holder(String sr) {
        subreddit = sr;
        after = "";
        generateURL ();
    }

    private void generateURL() {
        url = URL_Tem.replace("SUBREDDIT_NAME", subreddit);
        url = url.replace("AFTER", after);
    }

    List<Post> fetchPosts () {
        String raw = Data.readContents(url);
        List<Post> list = new ArrayList<Post>();
        try {
            JSONObject data = new JSONObject(raw).getJSONObject("data");
            JSONArray children = data.getJSONArray("children");
            after = data.getString("after");
            for (int i = 0; i < children.length(); i++) {
                JSONObject cur = children.getJSONObject(i).getJSONObject("data");
                Post p = new Post();
                p.title = cur.optString("title");
                p.author = cur.optString("author");
                p.url = cur.optString("url");
                p.domain = cur.optString("domain");
                p.id = cur.optString("id");
                p.subreddit = cur.optString("subreddit");
                p.permalink = cur.optString("permalink");
                p.points = cur.optInt("score");
                p.numComments = cur.optInt("num_comments");
                if (p.title!=null)
                    list.add(p);
            }
        } catch (Exception e) {
            Log.e("fetchPosts()", e.toString());
        }
        return list;
    }

    List<Post> fetchMorePosts(){
        generateURL();
        return fetchPosts();
    }

}
