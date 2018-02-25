package com.github.yglll.funlive.view;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.flyco.tablayout.SlidingTabLayout;
import com.github.yglll.funlive.R;
import com.github.yglll.funlive.model.ClassifyModel;
import com.github.yglll.funlive.net.bean.CapiCategory;
import com.github.yglll.funlive.net.bean.CateList;
import com.github.yglll.funlive.mvpbase.BaseFragment;
import com.github.yglll.funlive.mvpbase.BaseView;
import com.github.yglll.funlive.presenter.impl.ClassifyPresenter;
import com.github.yglll.funlive.presenter.interfaces.ClassifyPresenterInterfaces;
import com.github.yglll.funlive.view.adapter.classify.ClassifyAdapter;

import java.util.List;

import butterknife.BindView;
import es.dmoral.toasty.Toasty;

/**
 * 作者：YGL
 * 版本号：1.0
 * 类描述：
 * 备注消息：
 * 创建时间：2018/01/14   17:42
 **/
public class ClassifyFragment extends BaseFragment<ClassifyModel,ClassifyPresenter> implements ClassifyPresenterInterfaces.View{

    @BindView(R.id.sliding_tab_layout)
    SlidingTabLayout slidingTabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private ClassifyAdapter classifyAdapter;

    @Override
    public void showErrorWithStatus(String msg) {
        Toasty.info(getActivity(),msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showCateList(List<CateList> list) {
        String[] titles = new String[list.size()];
        for (int i=0;i<list.size();i++) {
            titles[i]=list.get(i).getCate_name();
        }
        //不摧毁Fragment
        viewPager.setOffscreenPageLimit(titles.length);
        classifyAdapter.setData(list);
        slidingTabLayout.setViewPager(viewPager,titles);
    }

    @Override
    public void showCate(List<CapiCategory> list) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_classify;
    }

    @Override
    protected void onInitView(Bundle bundle) {
        classifyAdapter = new ClassifyAdapter(getChildFragmentManager());
        viewPager.setAdapter(classifyAdapter);
    }

    @Override
    protected BaseView getViewImp() {
        return this;
    }

    private void refresh(){
        mPresenter.setCateList();
    }

    @Override
    public void onStart() {
        super.onStart();
        refresh();
    }
}
