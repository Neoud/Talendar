package com.example.talendar.talesolitare;

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
import com.example.talendar.data.tale.TaleRemoteDataSource;
import com.example.talendar.data.talesolitaire.TaleSolitaire;
import com.example.talendar.data.talesolitaire.TaleSolitaireRemoteDataSource;
import com.example.talendar.data.user.UserRemoteDataSource;
import com.example.talendar.home.HomeRecommendCreationFragment;
import com.example.talendar.home.HomeRecommendPresenter;
import com.example.talendar.showcreation.MyCreationFragment;
import com.example.talendar.showcreation.MyFollowedCreationFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class TaleSolitaireFragment extends Fragment {
    public static final int TAG_CODE_tag1 = 1;
    public static final int TAG_CODE_tag2 = 2;
    public static final int TAG_CODE_tag3 = 3;
    public static final int TAG_CODE_tag4 = 4;
    public static final int TAG_CODE_tag5 = 5;

    private TabLayout tabLayoutTabs;
    private ViewPager viewPagerCreation;

    private List<Fragment> fragments = new ArrayList<>();
    private List<String> titles = new ArrayList<>();

    public TaleSolitaireFragment() {}

    public static TaleSolitaireFragment newInstance() {
        return new TaleSolitaireFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tale_solitare, null);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabLayoutTabs = view.findViewById(R.id.tabLayout_tabs_tale_solitaire);
        viewPagerCreation = view.findViewById(R.id.viewPager_tale_solitaire);
        fragments.add(TaleSolitaireFragmentShow.newInstance(TAG_CODE_tag1));
        fragments.add(TaleSolitaireFragmentShow.newInstance(TAG_CODE_tag1));
        fragments.add(TaleSolitaireFragmentShow.newInstance(TAG_CODE_tag1));
        fragments.add(TaleSolitaireFragmentShow.newInstance(TAG_CODE_tag1));
        fragments.add(TaleSolitaireFragmentShow.newInstance(TAG_CODE_tag1));
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
        new TaleSolitairePresenter((TaleSolitaireFragmentShow)fragments.get(0), new TaleSolitaireRemoteDataSource(), new UserRemoteDataSource());
        new TaleSolitairePresenter((TaleSolitaireFragmentShow)fragments.get(1), new TaleSolitaireRemoteDataSource(), new UserRemoteDataSource());
        new TaleSolitairePresenter((TaleSolitaireFragmentShow)fragments.get(2), new TaleSolitaireRemoteDataSource(), new UserRemoteDataSource());
        new TaleSolitairePresenter((TaleSolitaireFragmentShow)fragments.get(3), new TaleSolitaireRemoteDataSource(), new UserRemoteDataSource());
        new TaleSolitairePresenter((TaleSolitaireFragmentShow)fragments.get(4), new TaleSolitaireRemoteDataSource(), new UserRemoteDataSource());
    }
}
