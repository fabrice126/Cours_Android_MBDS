package miage.mbds.cours_mbds;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.androidquery.AQuery;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity implements ResultCallBack {

    private  AQuery aq;
    private EchangeServeur e;
    private List<EchangeServeur.Product> products_entree = new ArrayList<EchangeServeur.Product>();
    private List<EchangeServeur.Product> products_plat = new ArrayList<EchangeServeur.Product>();
    private List<EchangeServeur.Product> products_dessert = new ArrayList<EchangeServeur.Product>();
    private List<EchangeServeur.Product> products_apperitif = new ArrayList<EchangeServeur.Product>();
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.

        aq = new AQuery(this);
        e = new EchangeServeur();
        e.async_list_product(aq, this);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                updateList(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CommandeActivity.class));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_product, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void ResultCallBack() {
        for(int i = 0; i< e.getProducts().size(); i++) {
            if(e.getProducts().get(i).type.equals("Entrée")) {
                products_entree.add(e.getProducts().get(i));
            }
            else if(e.getProducts().get(i).type.equals("Plat ")) {
                products_plat.add(e.getProducts().get(i));
            }
            else if(e.getProducts().get(i).type.equals("Dessert")) {
                products_dessert.add(e.getProducts().get(i));
            }
            else if(e.getProducts().get(i).type.equals("Appéritif")) {
                products_apperitif.add(e.getProducts().get(i));
            }
        }
        EntreeFragment fragment = (EntreeFragment)getSupportFragmentManager().getFragments().get(0);
        fragment.create_list(this, products_entree);
    }

    @Override
    public void ResultCallBackDelete() {

    }

    public void updateList(int position) {

        if(position == 0) {
            EntreeFragment fragment = (EntreeFragment)getSupportFragmentManager().getFragments().get(0);
            fragment.create_list(this, products_entree);
        }
        else if(position == 1) {
            PlatFragment fragment = (PlatFragment)getSupportFragmentManager().getFragments().get(1);
            fragment.create_list(this, products_plat);
        }
        else if(position == 2) {
            DessertFragment fragment = (DessertFragment)getSupportFragmentManager().getFragments().get(2);
            fragment.create_list(this, products_dessert);
        }
        else if(position == 3) {
            ApperitifFragment fragment = (ApperitifFragment)getSupportFragmentManager().getFragments().get(3);
            fragment.create_list(this, products_apperitif);
        }

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class EntreeFragment extends Fragment{
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "1";
        private ProductItemAdapter adapter;
        ListView lst;

        public EntreeFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static EntreeFragment newInstance(int sectionNumber) {
            EntreeFragment fragment = new EntreeFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_product_entree, container, false);
            lst = (ListView)rootView.findViewById(R.id.listView_entree);
            return rootView;
        }

        public void create_list(Context context, List<EchangeServeur.Product> products) {
            adapter = new ProductItemAdapter(context, products);
            lst.setAdapter(adapter);
        }

    }
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlatFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "2";
        private ProductItemAdapter adapter;
        ListView lst;

        public PlatFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlatFragment newInstance(int sectionNumber) {
            PlatFragment fragment = new PlatFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_product_plat, container, false);
            lst = (ListView)rootView.findViewById(R.id.listView_plat);
            return rootView;
        }
        public void create_list(Context context, List<EchangeServeur.Product> products) {
            adapter = new ProductItemAdapter(context, products);
            lst.setAdapter(adapter);
        }

    }
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class DessertFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "3";
        private ProductItemAdapter adapter;
        ListView lst;

        public DessertFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static DessertFragment newInstance(int sectionNumber) {
            DessertFragment fragment = new DessertFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_product_dessert, container, false);
            lst = (ListView)rootView.findViewById(R.id.listView_dessert);
            return rootView;
        }
        public void create_list(Context context, List<EchangeServeur.Product> products) {
            adapter = new ProductItemAdapter(context, products);
            lst.setAdapter(adapter);
        }

    }
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class ApperitifFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "4";
        private ProductItemAdapter adapter;
        ListView lst;

        public ApperitifFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static ApperitifFragment newInstance(int sectionNumber) {
            ApperitifFragment fragment = new ApperitifFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_product_apperitif, container, false);
            lst = (ListView)rootView.findViewById(R.id.listView_apperitif);
            return rootView;
        }
        public void create_list(Context context, List<EchangeServeur.Product> products) {
            adapter = new ProductItemAdapter(context, products);
            lst.setAdapter(adapter);
        }

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:
                    return EntreeFragment.newInstance(position + 1);
                case 1:
                    return PlatFragment.newInstance(position + 1);
                case 2:
                    return DessertFragment.newInstance(position + 1);
                case 3:
                    return ApperitifFragment.newInstance(position + 1);
                default:
                    return EntreeFragment.newInstance(position + 1);
            }
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Entrée";
                case 1:
                    return "Plat";
                case 2:
                    return "Dessert";
                case 3:
                    return "Appéritif";
            }
            return null;
        }
    }

}