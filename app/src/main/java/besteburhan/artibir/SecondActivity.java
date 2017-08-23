package besteburhan.artibir;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.view.WindowInsets;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

public class SecondActivity extends AppCompatActivity {


    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());//kontrol et??
        setContentView(R.layout.activity_second);
        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);




        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        //setupWithViewPager set up TabLayout with a viewpager
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.getTabAt(1).setIcon(R.drawable.questions).setText("");




        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mViewPager.setCurrentItem(1);
        toolbar.setTitle("Sorular");
        setSupportActionBar(toolbar);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // supportactiionbar == toolbar
                getSupportActionBar().setTitle(mSectionsPageAdapter.getPageTitle(tab.getPosition()));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_second_activity,menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.log_out_button:
                FacebookSdk.sdkInitialize(getApplicationContext());//kontrol et??
                LoginManager.getInstance().logOut();
                FirebaseAuth.getInstance().signOut();

                startActivity(new Intent(SecondActivity.this,MainActivity.class));
                finish();

        }
        return super.onOptionsItemSelected(item);
    }



    private void setupViewPager(ViewPager viewPager){

        mSectionsPageAdapter.addFragment(new TabAskQuestionFragment(),"Soru Sor");
        mSectionsPageAdapter.addFragment(new TabQuestionsFragment(),"Sorular");
        mSectionsPageAdapter.addFragment(new TabSavedQuestionsFragment(),"Kaydedilen Sorular");
        viewPager.setAdapter(mSectionsPageAdapter);
    }





}
