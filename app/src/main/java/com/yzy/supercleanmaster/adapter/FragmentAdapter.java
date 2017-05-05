package com.yzy.supercleanmaster.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
//import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v13.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by zhicheng.huang on 5/4/17.
 */

public class FragmentAdapter extends FragmentStatePagerAdapter {
    public List<Fragment> list;
    private List<String> titles;

    /**
     * 构造方法
     */
    public FragmentAdapter(FragmentManager fm, List<Fragment> list, List<String> titles) {
        super(fm);
        this.list = list;
        this.titles = titles;
    }

    /**
     * 返回显示的Fragment总数
     */
    @Override
    public int getCount() {
        return list.size();
    }

    /**
     * 返回要显示的Fragment的某个实例
     */
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    /**
     * 返回每个Tab的标题
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
