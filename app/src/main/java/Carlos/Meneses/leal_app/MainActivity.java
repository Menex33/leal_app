package Carlos.Meneses.leal_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

public class MainActivity extends FragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addFragment();
    }

    void addFragment(){
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragments_holder
                        , pFragment.newInstance("top.json"))
                .commit();
    }
}