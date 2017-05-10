package com.yzy.supercleanmaster.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yzy.supercleanmaster.adapter.FragmentAdapter;
import com.yzy.supercleanmaster.R;
import com.yzy.supercleanmaster.base.ActivityTack;
import com.yzy.supercleanmaster.fragment.MainFragment;
import com.yzy.supercleanmaster.fragment.RelaxFragment;
import com.yzy.supercleanmaster.fragment.SettingsFragment;
import com.yzy.supercleanmaster.fragment.TabFragment;
import com.yzy.supercleanmaster.utils.T;

public class TabMainActivity extends AppCompatActivity {
    private String[] titles = new String[]{"首页", "工具箱", "发现", "我"};
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private FragmentAdapter adapter;
    //ViewPage选项卡页面集合
    private List<Fragment> mFragments;
    //Tab标题集合
    private List<String> mTitles;
    /**
     * 图片数组
     */
    private int[] mImgs=new int[]{R.drawable.selector_tab_weixin, R.drawable.selector_tab_friends, R.drawable.selector_tab_find,
            R.drawable.selector_tab_me};

    private final long TWO_SECOND = 2 * 1000;
    private long preTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("CleanMaster", "TabMainActivity.onCreate111");

        setContentView(R.layout.activity_main_tab);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mTabLayout = (TabLayout) findViewById(R.id.tablayout);

        mTitles = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            mTitles.add(titles[i]);
        }

        mFragments = new ArrayList<>();
        mFragments.add(new MainFragment());
        mFragments.add(TabFragment.newInstance(1));
        mFragments.add(new RelaxFragment());
        mFragments.add(new SettingsFragment());
        /*for (int i = 0; i < mTitles.size(); i++) {
            mFragments.add(TabFragment.newInstance(i));
        }*/
        adapter = new FragmentAdapter(getFragmentManager(), mFragments, mTitles);
        mViewPager.setAdapter(adapter);//给ViewPager设置适配器
        mTabLayout.setupWithViewPager(mViewPager);//将TabLayout和ViewPager关联起来

        mTabLayout.setSelectedTabIndicatorHeight(0);

        for (int i = 0; i < mTitles.size(); i++) {
            //获得到对应位置的Tab
            TabLayout.Tab itemTab = mTabLayout.getTabAt(i);
            if (itemTab != null) {
                //设置自定义的标题
                itemTab.setCustomView(R.layout.item_tab);
                TextView textView = (TextView) itemTab.getCustomView().findViewById(R.id.tv_name);
                textView.setText(mTitles.get(i));
                ImageView imageView= (ImageView) itemTab.getCustomView().findViewById(R.id.iv_img);
                imageView.setImageResource(mImgs[i]);
            }
        }
        mTabLayout.getTabAt(0).getCustomView().setSelected(true);

        mTabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager) {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //super.onTabSelected(tab);
                mViewPager.setCurrentItem(tab.getPosition(), false);

                getSupportActionBar().setTitle(titles[tab.getPosition()]);
            }
        });

        Log.i("CleanMaster", "TabMainActivity.onCreate222");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 截获后退键
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long currentTime = new Date().getTime();

            // 如果时间间隔大于2秒, 不处理
            if ((currentTime - preTime) > TWO_SECOND) {
                // 显示消息
                T.showShort(this, "再按一次退出应用程序");

                // 更新时间
                preTime = currentTime;

                // 截获事件,不再处理
                return true;
            } else {
                ActivityTack.getInstanse().exit(this);
            }
        }

        return super.onKeyDown(keyCode, event);
    }
}
