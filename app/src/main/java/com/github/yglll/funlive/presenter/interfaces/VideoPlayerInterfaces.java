package com.github.yglll.funlive.presenter.interfaces;

import com.github.yglll.funlive.model.logic.TempLiveVideoInfo;
import com.github.yglll.funlive.mvpbase.BaseModel;
import com.github.yglll.funlive.mvpbase.BasePresenter;
import com.github.yglll.funlive.mvpbase.BaseView;

import rx.Observable;

/**
 * 作者：YGL
 * 电话：13036804886
 * 邮箱：2369015621@qq.com
 * 版本号：1.0
 * 类描述：
 * 备注消息：
 * 创建时间：2018/02/10   22:34
 **/
public interface VideoPlayerInterfaces {
    interface View extends BaseView {
        void acquisitionVideoUrl(String url);
    }

    interface Model extends BaseModel {
        Observable<TempLiveVideoInfo> getVideoUrl(int roomId);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        abstract public void setVideoUrl(int roomId);
    }
}
