package Carlos.Meneses.leal_app;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class pFragment extends Fragment {
    ListView postsList;
    ArrayAdapter<Post> adapter;
    Handler handler;

    String subreddit;
    List<Post> posts;
    Holder postsHolder;

    pFragment(){
        handler=new Handler();
        posts=new ArrayList<Post>();
    }

    public static pFragment newInstance(String subreddit){
        pFragment pf=new pFragment();
        pf.subreddit=subreddit;
        pf.postsHolder=new Holder(pf.subreddit);
        return pf;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.posts
                , container
                , false);
        postsList=(ListView)v.findViewById(R.id.posts_list);
        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initialize();
    }

    private void initialize(){

        if(posts.size()==0){

            new Thread(){
                public void run(){
                    posts.addAll(postsHolder.fetchPosts());

                    handler.post(new Runnable(){
                        public void run(){
                            createAdapter();
                        }
                    });
                }
            }.start();
        }else{
            createAdapter();
        }
    }

    private void createAdapter(){

        if(getActivity()==null) return;

        adapter=new ArrayAdapter<Post>(getActivity()
                ,R.layout.posts
                , posts){
            @Override
            public View getView(int position,
                                View convertView,
                                ViewGroup parent) {

                if(convertView==null){
                    convertView=getActivity()
                            .getLayoutInflater()
                            .inflate(R.layout.item, null);
                }

                TextView postTitle;
                postTitle= convertView
                        .findViewById(R.id.post_title);

                TextView postDetails;
                postDetails= convertView
                        .findViewById(R.id.post_details);

                TextView postScore;
                postScore= convertView
                        .findViewById(R.id.post_score);

                postTitle.setText(posts.get(position).title);
                postDetails.setText(posts.get(position).getDetails());
                postScore.setText(posts.get(position).getScore());
                return convertView;
            }
        };
        postsList.setAdapter(adapter);
    }
}
