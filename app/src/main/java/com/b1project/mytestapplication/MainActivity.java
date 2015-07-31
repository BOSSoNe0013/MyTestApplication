package com.b1project.mytestapplication;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        FirstBlankFragment.OnFragmentInteractionListener,
        SecondBlankFragment.OnFragmentInteractionListener,
        View.OnClickListener{

    private final static String TAG = MainActivity.class.getName();
    private FragmentManager mFragmentManager;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.navigation);
        mNavigationView.setNavigationItemSelectedListener(this);
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction()
                .add(R.id.fragment_container, FirstBlankFragment.newInstance("",""))
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();
        Log.d(TAG, "item id #" + id);
        menuItem.setChecked(true);
        switch (id){
            case R.id.nav_item_1:
                mFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, FirstBlankFragment.newInstance("",""))
                        .commit();
                break;
            case R.id.nav_item_2:
                mFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, SecondBlankFragment.newInstance("",""))
                        .commit();
                break;
        }
        mDrawerLayout.closeDrawer(mNavigationView);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri){
        Log.d(TAG, uri.toString());
        String username = uri.getQueryParameter("username");
        Spanned hello = Html.fromHtml(
                getResources().getString(R.string.hello_user, username)
        );
        Snackbar.make(findViewById(R.id.fragment_container), hello, Snackbar.LENGTH_LONG)
                .setAction("annuler", this)
                    .show();
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        Log.d(TAG, "Snackbar click");
    }
}
