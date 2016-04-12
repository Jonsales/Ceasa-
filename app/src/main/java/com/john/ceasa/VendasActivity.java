package com.john.ceasa;

import android.content.Intent;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.john.ceasa.Fragments.ConsolidacaoFragment;
import com.john.ceasa.Fragments.IndividualFragment;
import com.john.ceasa.Utils.AllActivits;

public class VendasActivity extends AppCompatActivity implements ActionBar.TabListener, PagerSlidingTabStrip.IconTabProvider {

    AppSectionsPagerAdapter mAppSectionsPagerAdapter;
    ViewPager mViewPager;
    IndividualFragment individualFragment = new IndividualFragment();
    private int tabIcons[] = {R.drawable.new_admin,
            R.drawable.new_admin,
            R.drawable.new_admin,
            R.drawable.new_admin,
            R.drawable.new_admin };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendas);

//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        AllActivits.vendasActivity = VendasActivity.this;

        final Toolbar actionBar = (Toolbar) findViewById(R.id.toolbar_esse);
        mAppSectionsPagerAdapter = new AppSectionsPagerAdapter(getSupportFragmentManager());
//        mAppSectionsPagerAdapter.;
        mAppSectionsPagerAdapter.setTabTitles(
                new String[]{
                        getString(R.string.individual),
                        getString(R.string.consolidacao)
                }
        );

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            actionBar.setElevation(0);
        }
        // Specify that the Home/Up button should not be enabled, since there is no hierarchical
        // parent.

        // Specify that we will be displaying tabs in the action bar.
        //actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Set up the ViewPager, attaching the adapter and setting up a listener for when the
        // user swipes between sections.
        mViewPager = (ViewPager) findViewById(R.id.viewpager_vendas);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(mAppSectionsPagerAdapter);
        final PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.viewpagertab_vendas);
        //final HorizontalScrollView horizontalScrollView = (HorizontalScrollView) findViewById(R.id.hsv);
        tabs.setViewPager(mViewPager);
        tabs.setSmoothScrollingEnabled(true);
        tabs.setHorizontalFadingEdgeEnabled(true);
        tabs.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // When swiping between different app sections, select the corresponding tab.
                // We can also use ActionBar.Tab#select() to do this if we have a reference to the
                // Tab.
                //actionBar.setSelectedNavigationItem(position);
                //tabs.select
                //int x = tabs.;
                //int y =  horizontalScrollView.getScrollY();
                //horizontalScrollView.smoothScrollTo(x , y);


            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        if(mAppSectionsPagerAdapter != null){
            for (int i = 0; i < mAppSectionsPagerAdapter.getCount(); i++) {
                // Create a tab with text corresponding to the page title defined by the adapter.
                // Also specify this Activity object, which implements the TabListener interface, as the
                // listener for when this tab is selected.
                //actionBar.addTab(
                //actionBar.newTab()
                //.setText(mAppSectionsPagerAdapter.getPageTitle(i))
                //.setTabListener(this));
            }
        }


    }

    /*******************começo metodos click************************************/
    public void menuDrawerOnClick(View view){
        MenuDrawer.whoCalled = AllActivits.vendasActivity;
        Intent i = new Intent(VendasActivity.this, MenuDrawer.class);
        startActivity(i);
    }

    /*******************fim metodos click************************************/




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_chat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }
*/
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

    }

    @Override
    public int getPageIconResId(int i) {

        return tabIcons[i];
    }




    /**** INNER CLASSES ****/

    /**
     * A dummy fragment representing a section of the app, but that simply displays dummy text.
     */
    public static class DummySectionFragment extends Fragment {

        public static final String ARG_SECTION_NUMBER = "section_number";

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_individual, container, false);
            Bundle args = getArguments();
            //((TextView) rootView.findViewById(android.R.id.text1)).setText("EM BREVE!");
            return rootView;
        }
    }


    static class AppSectionsPagerAdapter extends FragmentPagerAdapter {

        public AppSectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        private String tabTitles[] = new String[] { "Individual", "Consolidação"};
        private Fragment fragments[] = new Fragment[] {new IndividualFragment(), new ConsolidacaoFragment()};

        public void setTabTitles(String [] strings){
            tabTitles = strings;
        }

        @Override
        public Fragment getItem(int i) {

            return  fragments [i];
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }


    }
}
