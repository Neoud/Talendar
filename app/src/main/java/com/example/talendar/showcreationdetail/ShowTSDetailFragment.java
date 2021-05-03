package com.example.talendar.showcreationdetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.example.talendar.R;
import com.example.talendar.data.bean.Comments;
import com.example.talendar.data.bean.CreationHome;
import com.example.talendar.showcreation.ShowCreationPresenter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class ShowTSDetailFragment extends Fragment implements ShowCreationDetailContract.TSView{
    private ShowCreationDetailContract.TSPresenter mPresenter;

    private TextView nickName, date, title, content, commentNum, fansNum;
    private TabLayout tabLayoutTabs;
    private ViewPager viewPagerCreation;

    private List<Fragment> fragments = new ArrayList<>();
    private List<String> titles = new ArrayList<>();

    public ShowTSDetailFragment() {}

    public static ShowTSDetailFragment newInstance() {
        return new ShowTSDetailFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_show_ts_detail, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TSCommentsFragment tsCommentsFragment = TSCommentsFragment.newInstance(mPresenter, mPresenter.getCreationId());
        TSChapterFragment tsChapterFragment = TSChapterFragment.newInstance(mPresenter);
        mPresenter.setCommentAndChapterView(tsCommentsFragment, tsChapterFragment);
        super.onViewCreated(view, savedInstanceState);
        nickName = view.findViewById(R.id.text_ts_detail_nickname);
        date = view.findViewById(R.id.text_ts_detail_date);
        title = view.findViewById(R.id.text_ts_detail_title);
        content = view.findViewById(R.id.text_ts_detail_content);
        commentNum = view.findViewById(R.id.text_ts_detail_comment_num);
        fansNum = view.findViewById(R.id.text_ts_detail_likes_num);
        tabLayoutTabs = view.findViewById(R.id.tabLayout_tabs_ts_comment_and_chapter);
        viewPagerCreation = view.findViewById(R.id.viewPager_creation_ts_comment_and_chapter);
        fragments.add(tsCommentsFragment);
        fragments.add(tsChapterFragment);
        titles.add("评论");
        titles.add("章节");
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
        mPresenter.start();
    }

    @Override
    public void setPresenter(ShowCreationDetailContract.TSPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showToast(String message) {

    }

    @Override
    public void showSnackBar(int code, String message) {

    }

    @Override
    public void setText(String viewName, String text) {
        switch (viewName) {
            case "nickName":
                nickName.setText(text);
                break;
            case "date":
                date.setText(text);
                break;
            case "title":
                title.setText(text);
                break;
            case "content":
                content.setText(text);
                break;
            case "commentNum":
                commentNum.setText(text);
                break;
            case "fansNum":
                fansNum.setText(text);
                break;
            default:
                break;
        }
    }

    @Override
    public void setCommentsList(List<Comments> comments) {

    }

    @Override
    public void setLastChapter(List<CreationHome> lastChapter) {

    }

    @Override
    public void setNextChapter(List<CreationHome> nextChapter) {

    }
}
