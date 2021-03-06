package com.github.yglll.funlive.mvpbase;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者：YGL
 * 版本号：1.0
 * 类描述：定义每一个Activity都应该有的行为：在初始化时绑定MVP，在销毁时解除绑定MVP
 * 备注消息：
 * 创建时间：2018/01/10   22:33
 **/
public abstract class BaseActivity<M extends BaseModel,P extends BasePresenter> extends AppCompatActivity {
    //    定义Presenter
    protected P mPresenter;
    protected Unbinder unbinder;

    //在初始化时绑定MVP
    @Override
    public void onCreate(Bundle s){
        super.onCreate(s);
        //设置布局资源文件
        setContentView(getLayoutId());
        //注解绑定
        unbinder = ButterKnife.bind(this);
        bindMVP();
        initView(s);
        //设置返回键和标题
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(activityTitle());
        }
    }

    public abstract void initView(Bundle bundle);
    public abstract String activityTitle();

    //在销毁时解除绑定
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
        if (mPresenter != null) {
            ContractProxy.getInstance().unbindView(getViewImp(), mPresenter);
            ContractProxy.getInstance().unbindModel(getModelClazz(), mPresenter);
            mPresenter = null;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        if(menuItem.getItemId()==android.R.id.home){
            finish();
        }
        return true;
    }

    //获取布局资源文件
    protected abstract int getLayoutId();

    private void bindMVP() {
        if (getPresenterClazz() != null) {
            mPresenter = getPresenterImpl();
            mPresenter.mContext = this;
            bindVM();//Presenter绑定VM
        }
    }
    private  void bindVM() {
        if(mPresenter!=null&&!mPresenter.isViewBind()&&getModelClazz()!=null&&getViewImp()!=null) {
            ContractProxy.getInstance().bindModel(getModelClazz(),mPresenter);
            ContractProxy.getInstance().bindView(getViewImp(),mPresenter);
            mPresenter.mContext=this;
        }
    }

    //   获取抽取View对象
    protected   abstract BaseView getViewImp();
    //    获得抽取接口Presenter对象
    protected Class getPresenterClazz() {
        return (Class<P>) ContractProxy.getPresnterClazz(getClass(), 1);
    }
    //    获得抽取接口Model对象
    protected   Class getModelClazz()  {
        return (Class<M>)ContractProxy.getModelClazz(getClass(), 0);
    }
    //获得Presenter实例
    private <T> T getPresenterImpl() {
        return ContractProxy.getInstance().presenter(getPresenterClazz());
    }
}
