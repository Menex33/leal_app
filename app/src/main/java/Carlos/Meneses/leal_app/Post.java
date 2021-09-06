package Carlos.Meneses.leal_app;

public class Post {

    String title, author, url, domain, id, subreddit, permalink;
    int points, numComments;

    String getDetails () {
        String details = author + " posted this and got "
                                + numComments
                                + " replies ";
        return details;
    }

    String getTitle(){
        return title;
    }

    String getScore(){
        return Integer.toString(points);
    }
}
