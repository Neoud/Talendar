package com.example.talendar.home;
import com.example.talendar.data.bean.CreationHome;
import com.example.talendar.data.tale.Tale;
import com.example.talendar.data.tale.TaleDataSource;
import com.example.talendar.data.talesolitaire.TaleSolitaireDataSource;
import com.example.talendar.data.user.UserDataSource;

import java.util.ArrayList;
import java.util.List;

import static com.example.talendar.home.HomeRecommendFragment.TAG_CODE_tag1;
import static com.example.talendar.home.HomeRecommendFragment.TAG_CODE_tag2;
import static com.example.talendar.home.HomeRecommendFragment.TAG_CODE_tag3;
import static com.example.talendar.home.HomeRecommendFragment.TAG_CODE_tag4;
import static com.example.talendar.home.HomeRecommendFragment.TAG_CODE_tag5;

public class HomePresenter implements HomeContract.Presenter, TaleDataSource.GetTaleCallBack {
    HomeContract.View mView;
    HomeContract.View2 mView2;
    TaleDataSource mTaleDataSource;
    TaleSolitaireDataSource mTSDataSource;
    UserDataSource mUserDataSource;

    public HomePresenter(HomeContract.View view, HomeContract.View2 view2, TaleDataSource taleDataSource, TaleSolitaireDataSource taleSolitaireDataSource, UserDataSource userDataSource) {
        mView = view;
        mView2 = view2;
        mTaleDataSource = taleDataSource;
        mTSDataSource = taleSolitaireDataSource;
        mUserDataSource = userDataSource;

        mView.setPresenter(this);
        mView2.setPresenter(this);
    }

    @Override
    public void start() {
        List<CreationHome> creationHomeList = new ArrayList<>();
        creationHomeList.add(new CreationHome("测试1号", "2021-4-24 15:56:22", "Title", "从前，在一座茂密的森林里，住着一只漂亮的小狐狸，它有个坏毛病，就是要骗别人。\n" +
                "　　\n" +
                "　　有一次，小狐狸和好朋友一起去玩，这时，小狐狸发现了一大片的蘑菇，便大叫道：“看！蘑菇！”\n" +
                "　　\n" +
                "　　“嘿！真的！”大家高兴地跑到蘑菇丛里。\n" +
                "　　\n" +
                "　　“咦？这蘑菇怎么好像见过？”小山羊拿着一个蘑菇仔细地看着，“啊！我知道了！这就是奇妙蘑菇！吃了这个蘑菇，好孩子就会变漂亮，骗人的坏孩子就会变丑。”\n" +
                "　　\n" +
                "　　大家听了，连忙都把蘑菇往嘴里塞。啊呜啊呜，真好吃啊！\n" +
                "　　\n" +
                "　　小狐狸看它们吃得这么香，直流口水，小动物们见了，说：“小狐狸，你怎么不来吃啊？”\n" +
                "　　\n" +
                "　　小狐狸抵不住大家的诱惑，还是吃了一个。\n" +
                "　　\n" +
                "　　小狐狸全身立刻变黑了，它觉得没有面子见别人，就躲在一颗大树后，伤心地哭了起来。\n" +
                "　　\n" +
                "　　“呜呜，为什么会这样呢？”\n" +
                "　　\n" +
                "　　小动物跑过来，关心地问：“小狐狸，你怎么了？”\n" +
                "　　\n" +
                "　　“我爱撒谎，爱骗人，是个坏孩子，呜呜……”\n" +
                "　　\n" +
                "　　小山羊说：“你干了哪些不好的，骗人的事啊？”\n" +
                "　　\n" +
                "　　“我……我把黄牛爷爷的麦子偷走了，说是小黄牛弟弟拿的；我把小兔家的胡萝卜吃了，说是小狗偷的……”\n" +
                "　　\n" +
                "　　奇怪，小狐狸一把骗人的事说出来，全身又变回金色，像一片美丽的金子一样闪闪发光，漂亮极了。\n" +
                "　　\n" +
                "　　动物们高兴了，说：“小狐狸，你也是诚实守信的好孩子！”\n" +
                "　　\n" +
                "　　小狐狸终于明白了以后要做一个诚实的乖孩子。", 12, 12, 0));
        creationHomeList.add(new CreationHome("测试1号", "2021-4-24 15:56:22", "Title", "这是一个接龙故事测试", 1, 1, 1));
        mView.setCreationList(creationHomeList);
    }

    @Override
    public void initRecommendFragment(int tagCode) {
    }

    @Override
    public void onTaleGot(List<Tale> taleList) {

    }

    @Override
    public void onDataNotAvailable(String message) {

    }
}
