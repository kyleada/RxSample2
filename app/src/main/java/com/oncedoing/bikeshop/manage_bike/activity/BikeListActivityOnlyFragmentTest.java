package com.oncedoing.bikeshop.manage_bike.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.oncedoing.bikeshop.R;
import com.oncedoing.bikeshop.manage_bike.fragment.BikeListFragment;
import com.oncedoing.bikeshop.manage_bike.fragment.BikeListFragment2;

import me.kkwang.commonlib.base.BaseActivity;

public class BikeListActivityOnlyFragmentTest extends BaseActivity {

    private FragmentManager fragmentManager;
    private BikeListFragment2 bikeListFragment;


    @Override protected int getLayoutId() {
        return R.layout.activity_bike_list2;
    }



    @Override protected void afterCreate(Bundle savedInstanceState) {

        bikeListFragment = BikeListFragment2.newInstance();
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fl_container,bikeListFragment,
                BikeListFragment.TAG).commit();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.bike_list_menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.filter_whole_bike) {
//            boolean isCheck = item.isChecked();
//            if(!isCheck){
//                item.setChecked(true);
//                bikeListFragment.addTopCategory("101");
//            }else {
//                item.setChecked(false);
//                bikeListFragment.removeTopCategory("101");
//            }
//
//            bikeListFragment.search();
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
