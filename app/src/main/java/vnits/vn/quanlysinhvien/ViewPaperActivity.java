package vnits.vn.quanlysinhvien;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import vnits.vn.quanlysinhvien.adapter.PaperAdapter;
import vnits.vn.quanlysinhvien.config.ConfigApplication;
import vnits.vn.quanlysinhvien.fragment.DiligenceFragment;
import vnits.vn.quanlysinhvien.fragment.ScoreFragment;
import vnits.vn.quanlysinhvien.fragment.UserInfoFragment;


public class ViewPaperActivity extends FragmentActivity {

    PaperAdapter paperAdapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int requesetDirect;

    private int[] tabIcons={
            R.drawable.ic_dd,
            R.drawable.ic_score,
            R.drawable.ic_userinfo,
    };
    private int[] tabIconsSelected={
            R.drawable.ic_dd_click,
            R.drawable.ic_score_click,
            R.drawable.ic_userinfo_click,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpaper);
        this.requesetDirect = ConfigApplication.REQUEST_DIRECT_DEFAULT;
        Intent i = getIntent();
        if(i != null){
            Bundle b = i.getBundleExtra(ConfigApplication.BUNDLE_DATA_TRANSFER);
            if(b != null){
                this.requesetDirect = b.getInt(ConfigApplication.DATA_DIRECT_TRANSFER);
            }
        }
        addControls();
        addEvents();
    }

    private void setupViewPager(ViewPager viewPager) {
        paperAdapter = new PaperAdapter(getSupportFragmentManager());
        paperAdapter.addFragment(new DiligenceFragment());
        paperAdapter.addFragment(new ScoreFragment());
        paperAdapter.addFragment(new UserInfoFragment());
        viewPager.setAdapter(paperAdapter);
        if(this.requesetDirect == ConfigApplication.REQUEST_DIRECT_DEFAULT)
            viewPager.setCurrentItem(2);
        else
            if(this.requesetDirect == ConfigApplication.REQUEST_DIRECT_SCORE){
                viewPager.setCurrentItem(1);
            }
    }

    private void addEvents() {
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if(position == 0){
                    tabLayout.getTabAt(0).setIcon(tabIconsSelected[0]);
                }
                if(position == 1){
                    tabLayout.getTabAt(1).setIcon(tabIconsSelected[1]);
                }
                if(position == 2){
                    tabLayout.getTabAt(2).setIcon(tabIconsSelected[2]);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tabLayout.getTabAt(0).setIcon(tabIcons[0]);
                tabLayout.getTabAt(1).setIcon(tabIcons[1]);
                tabLayout.getTabAt(2).setIcon(tabIcons[2]);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void addControls() {

        viewPager = (ViewPager) findViewById(R.id.vpPager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.main_tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }
}
