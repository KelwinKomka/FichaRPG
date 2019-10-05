package com.ES3.ficharpg.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.ES3.ficharpg.main.creationTabs.CreationTabAbilities;
import com.ES3.ficharpg.main.creationTabs.CreationTabClass;
import com.ES3.ficharpg.main.creationTabs.CreationTabDescription;
import com.ES3.ficharpg.main.creationTabs.CreationTabEquipment;
import com.ES3.ficharpg.main.creationTabs.CreationTabPref;
import com.ES3.ficharpg.main.creationTabs.CreationTabRace;
import com.ES3.ficharpg.main.creationTabs.CreationTabSummary;
import com.ES3.ficharpg.model.Sheet;
import com.ES3.ficharpg.R;

public class SheetCreationActivity extends AppCompatActivity {

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

    public static Sheet sheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheet_creation);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        sheet = (Sheet) getIntent().getSerializableExtra("Sheet");
        if (sheet == null)
            sheet = new Sheet();
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
            switch (position){
                case 0:
                    return new CreationTabPref();
                case 1:
                    return new CreationTabRace();
                case 2:
                    return new CreationTabClass();
                case 3:
                    return new CreationTabAbilities();
                case 4:
                    return new CreationTabDescription();
                case 5:
                    return new CreationTabEquipment();
                case 6:
                    return new CreationTabSummary();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 7;
        }
    }

    public SectionsPagerAdapter getmSectionsPagerAdapter(){
        return this.mSectionsPagerAdapter;
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(SheetCreationActivity.this);
        dialog.setTitle("Confirmação")
                .setMessage("Deseja sair da criação de ficha?")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        Intent intent = new Intent(SheetCreationActivity.this, SheetPickActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialoginterface, int i) {
                dialoginterface.cancel();
            }
        }).show();

        //super.onBackPressed();
    }
}
