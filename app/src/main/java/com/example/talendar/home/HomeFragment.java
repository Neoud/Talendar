package com.example.talendar.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.talendar.R;
import com.example.talendar.data.tale.TaleRemoteDataSource;
import com.example.talendar.data.talesolitaire.TaleSolitaireRemoteDataSource;
import com.example.talendar.data.user.UserRemoteDataSource;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class HomeFragment extends Fragment {
    private TabLayout tabLayoutTabs;
    private ViewPager viewPagerCreation;

    private List<Fragment> fragments = new ArrayList<>();
    private List<String> titles = new ArrayList<>();

    public HomeFragment(){}

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated: 开始加载layout");
        super.onViewCreated(view, savedInstanceState);
        tabLayoutTabs = view.findViewById(R.id.tabLayout_tabs_home);
        viewPagerCreation = view.findViewById(R.id.viewPager_home);
        fragments.add(HomeFollowedFragment.newInstance());
        fragments.add(HomeRecommendFragment.newInstance());
        titles.add("关注");
        titles.add("推荐");
        viewPagerCreation.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                super.destroyItem(container, position, object);
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titles.get(position);
            }
        });
        tabLayoutTabs.setupWithViewPager(viewPagerCreation);
        new HomePresenter((HomeContract.View)fragments.get(0), (HomeContract.View2)fragments.get(1), new TaleRemoteDataSource(), new TaleSolitaireRemoteDataSource(), new UserRemoteDataSource());
    }

}
