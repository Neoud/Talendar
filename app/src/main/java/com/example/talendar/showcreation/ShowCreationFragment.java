package com.example.talendar.showcreation;

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
import com.example.talendar.data.talesolitaire.TaleSolitaireRemoteDataSource;
import com.example.talendar.data.user.User;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobUser;

public class ShowCreationFragment extends Fragment {

    private TabLayout tabLayoutTabs;
    private ViewPager viewPagerCreation;

    private List<Fragment> fragments = new ArrayList<>();
    private List<String> titles = new ArrayList<>();

    public ShowCreationFragment() {}

    public static ShowCreationFragment newInstance() {
        return new ShowCreationFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_show_creation, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabLayoutTabs = view.findViewById(R.id.tabLayout_tabs);
        viewPagerCreation = view.findViewById(R.id.viewPager_creation);
        fragments.add(MyCreationFragment.newInstance());
        fragments.add(MyFollowedCreationFragment.newInstance());
        titles.add("我的");
        titles.add("关注");
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
        if (BmobUser.isLogin()) {
            new ShowCreationPresenter(new TaleRemoteDataSource(), new TaleSolitaireRemoteDataSource(), (MyCreationFragment)fragments.get(0), (MyFollowedCreationFragment)fragments.get(1), BmobUser.getCurrentUser(User.class).getObjectId());
        } else {
            new ShowCreationPresenter(new TaleRemoteDataSource(), new TaleSolitaireRemoteDataSource(), (MyCreationFragment)fragments.get(0), (MyFollowedCreationFragment)fragments.get(1), null);
        }
    }

}
