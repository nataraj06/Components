package com.android.components.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

import com.android.components.R;
import com.android.components.activity.fragments.FragmentA;
import com.android.components.activity.fragments.FragmentB;
import com.android.components.activity.fragments.OnFragmentInteractionListener;
import com.android.components.utils.UIUtil;

public class DemoFragmentActivity extends BaseActivity implements OnFragmentInteractionListener {

    private static final int FRAGMENT_A = 0;
    private static final int FRAGMENT_B = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_demo);
        UIUtil.showToast(this, "Activity:: onCreate()");
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        UIUtil.showToast(this, "Activity:: onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        UIUtil.showToast(this, "Activity:: onResume()");
    }


    @Override
    protected void onPause() {
        super.onPause();
        UIUtil.showToast(this, "Activity:: onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        UIUtil.showToast(this, "Activity:: onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UIUtil.showToast(this, "Activity:: onDestroy()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        UIUtil.showToast(this, "Activity:: onRestart()");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        UIUtil.showToast(this, "Activity:: onCreateOptionsMenu(Menu menu)");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        UIUtil.showToast(this, "Activity:: onOptionsItemSelected(MenuItem item)");
        if (id == R.id.action_home) {
            displayView(FRAGMENT_A);
            return true;
        } else if (id == R.id.action_contact_us) {
            displayView(FRAGMENT_B);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(String fragmentName) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(fragmentName);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    /**
     * Displaying fragment view for selected navigation drawer list item
     */
    private void displayView(int position) {
        // update the main content by replacing fragments
        Fragment fragment = null;
        String fragmentName = null;
        switch (position) {
            case FRAGMENT_A:
                fragment = new FragmentA();
                fragmentName = FragmentA.class.getSimpleName();
                break;
            case FRAGMENT_B:
                fragment = new FragmentB();
                break;
            default:
                break;
        }
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_container, fragment);
            fragmentTransaction.addToBackStack(fragmentName);
            fragmentTransaction.commit();
        }
    }


    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
        UIUtil.showToast(this, "Activity:: onBackPressed()");
    }
}