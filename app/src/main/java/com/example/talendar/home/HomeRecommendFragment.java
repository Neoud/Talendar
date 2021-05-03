package com.example.talendar.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.talendar.R;
import com.example.talendar.data.bean.CreationHome;
import com.example.talendar.data.tale.TaleRemoteDataSource;
import com.example.talendar.data.user.UserRemoteDataSource;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class HomeRecommendFragment extends Fragment implements HomeContract.View2 {
    public static final int TAG_CODE_tag1 = 1;
    public static final int TAG_CODE_tag2 = 2;
    public static final int TAG_CODE_tag3 = 3;
    public static final int TAG_CODE_tag4 = 4;
    public static final int TAG_CODE_tag5 = 5;

    private TabLayout tabLayoutTabs;
    private ViewPager viewPagerCreation;

    private List<Fragment> fragments = new ArrayList<>();
    private List<String> titles = new ArrayList<>();

    private HomeContract.Presenter mPresenter;

    public HomeRecommendFragment() {}

    public static HomeRecommendFragment newInstance() {
        return new HomeRecommendFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_recommend_creation, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabLayoutTabs = view.findViewById(R.id.tabLayout_tabs_home_recommend);
        viewPagerCreation = view.findViewById(R.id.viewPager_creation_home_recommend);
        fragments.add(HomeRecommendCreationFragment.newInstance(TAG_CODE_tag1));
        fragments.add(HomeRecommendCreationFragment.newInstance(TAG_CODE_tag2));
        fragments.add(HomeRecommendCreationFragment.newInstance(TAG_CODE_tag3));
        fragments.add(HomeRecommendCreationFragment.newInstance(TAG_CODE_tag4));
        fragments.add(HomeRecommendCreationFragment.newInstance(TAG_CODE_tag5));
        titles.add("日常");
        titles.add("校园");
        titles.add("寓言");
        titles.add("奇幻");
        titles.add("科幻");
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
        viewPagerCreation.setOffscreenPageLimit(4);
        tabLayoutTabs.setupWithViewPager(viewPagerCreation);
        new HomeRecommendPresenter((HomeRecommendCreationFragment)fragments.get(0), new TaleRemoteDataSource(), new UserRemoteDataSource());
        new HomeRecommendPresenter((HomeRecommendCreationFragment)fragments.get(1), new TaleRemoteDataSource(), new UserRemoteDataSource());
        new HomeRecommendPresenter((HomeRecommendCreationFragment)fragments.get(2), new TaleRemoteDataSource(), new UserRemoteDataSource());
        new HomeRecommendPresenter((HomeRecommendCreationFragment)fragments.get(3), new TaleRemoteDataSource(), new UserRemoteDataSource());
        new HomeRecommendPresenter((HomeRecommendCreationFragment)fragments.get(4), new TaleRemoteDataSource(), new UserRemoteDataSource());
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showToast(String message) {

    }

    @Override
    public void showSnackBar(int code, String message) {

    }

    @Override
    public void setCreationList(List<CreationHome> creationHomeList) {

    }
}
