package com.yzy.supercleanmaster.base;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yzy.supercleanmaster.utils.T;


@SuppressLint("NewApi")
public abstract class BaseFragment extends Fragment {
	protected View mRootView;
	protected boolean isVisible;
	protected boolean isPrepared;
	protected boolean isFirstLoad;

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if (isVisibleToUser) {
			isVisible = true;
			onVisible();
		} else {
			isVisibleToUser = false;
			onInvisible();
		}
	}

	@Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);
		if (!hidden) {
			isVisible = true;
			onVisible();
		} else {
			isVisible = false;
			onInvisible();
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		//return super.onCreateView(inflater, container, savedInstanceState);
		if (null == mRootView) {mRootView = initViews(inflater, container, savedInstanceState);}
		isFirstLoad = true;
		Log.i("CleanMaster", "BaseFragment.onCreateView isFirstLoad=" + isFirstLoad);
		return mRootView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		isPrepared = true;
		Log.i("CleanMaster", "BaseFragment.onViewCreated isPrepared=" + isPrepared);
		lazyLoad();
	}

	protected void lazyLoad() {
		if (isPrepared && isVisible && isFirstLoad) {
			initData();
			isFirstLoad = false;
		}
	}

	/** 通过Class跳转界面 **/
	protected void startActivity(Class<?> cls) {
		startActivity(cls, null);
	}

	/** 含有Bundle通过Class跳转界面 **/
	protected void startActivity(Class<?> cls, Bundle bundle) {
		Intent intent = new Intent();
		intent.setClass(getActivity(), cls);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivity(intent);
	}

	/** 通过Action跳转界面 **/
	protected void startActivity(String action) {
		startActivity(action, null);
	}

	/** 含有Bundle通过Action跳转界面 **/
	protected void startActivity(String action, Bundle bundle) {
		Intent intent = new Intent();
		intent.setAction(action);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivity(intent);
	}

	/**
	 * 吐司
	 * 
	 * @param message
	 */
	protected void showShort(String message) {
		T.showShort(getActivity(), message);
	}

	protected void showLong(String message) {
		T.showLong(getActivity(), message);
	}

	protected abstract View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);
	protected abstract void initData();
	protected void onVisible() { lazyLoad(); }
	protected void onInvisible() {}
}
