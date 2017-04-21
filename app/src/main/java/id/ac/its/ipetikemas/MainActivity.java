package id.ac.its.ipetikemas;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.mikepenz.actionitembadge.library.ActionItemBadge;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabs)
    TabLayout tabLayout;
    @BindView(R.id.container)
    ViewPager mViewPager;
    private int pesan, alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        String appname = getString(R.string.app_name);
        setTitle(appname);
        pesan = 3;
        alert = 2;
        setupViewPager(mViewPager);
        tabLayout.setupWithViewPager(mViewPager);
        setupTabIcons();
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new TrackFragment(), "track");
        adapter.addFragment(new SuratFragment(), "surat");
        adapter.addFragment(new TpointsFragment(), "tpoints");
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(adapter);
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(new IconicsDrawable(this)
                .icon(FontAwesome.Icon.faw_map)
                .color(Color.WHITE)
                .actionBar());
        tabLayout.getTabAt(1).setIcon(new IconicsDrawable(this)
                .icon(FontAwesome.Icon.faw_file)
                .color(Color.WHITE)
                .actionBar());
        tabLayout.getTabAt(2).setIcon(new IconicsDrawable(this)
                .icon(FontAwesome.Icon.faw_tasks)
                .color(Color.WHITE)
                .actionBar());
    }

    static class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        menu.findItem(R.id.out).setIcon(
                new IconicsDrawable(this)
                        .icon(FontAwesome.Icon.faw_sign_out)
                        .color(Color.WHITE)
                        .actionBar()
        );
        ActionItemBadge.update(this, menu.findItem(R.id.pesan), FontAwesome.Icon.faw_envelope, ActionItemBadge.BadgeStyles.DARK_GREY, pesan);
        ActionItemBadge.update(this, menu.findItem(R.id.alert), FontAwesome.Icon.faw_bell, ActionItemBadge.BadgeStyles.DARK_GREY, alert);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.pesan) {
            Intent i = new Intent(this, Pesan.class);
            startActivity(i);
        } else if (id == R.id.alert) {
            Intent i = new Intent(this, Alert.class);
            startActivity(i);
        } else if (id == R.id.out) {
            out();
        }
        return super.onOptionsItemSelected(item);
    }

    private void out() {
        AlertDialog.Builder pilihan = new AlertDialog.Builder(this);
        pilihan.setMessage("Anda ingin keluar?");
        pilihan.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Prefs.clear();
                startActivity(new Intent(MainActivity.this, Login.class));
                finish();
            }
        });
        pilihan.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        AlertDialog alert = pilihan.create();
        alert.show();
    }
}
