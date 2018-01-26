package com.github.yglll.funlive.presenter.interfaces;

import com.github.yglll.funlive.model.logic.HomeCarousel;
import com.github.yglll.funlive.model.logic.HomeFaceScoreColumn;
import com.github.yglll.funlive.model.logic.HomeHotColumn;
import com.github.yglll.funlive.mvpbase.BaseModel;
import com.github.yglll.funlive.mvpbase.BasePresenter;
import com.github.yglll.funlive.mvpbase.BaseView;

import java.util.List;
import java.util.Map;

import rx.Observable;

/**
 * 作者：YGL
 * 电话：13036804886
 * 邮箱：2369015621@qq.com
 * 版本号：1.0
 * 类描述：
 * 备注消息：
 * 创建时间：2017/12/27   9:30
 **/
public interface RecommendPresenterInterfaces {
    interface View extends BaseView {
        void showString(String list);
        void showCarousel(List<HomeCarousel> list);
        void showHotColumn(List<HomeHotColumn> list);
        void showFaceScoreColumn(List<HomeFaceScoreColumn> list);
    }

    interface Model extends BaseModel {
        Observable<String> getString(String string, Map<String, Integer> map);
        Observable<List<String>> getGameString(Map<String, Integer> map);
        Observable<List<HomeCarousel>> getCarousel();
        Observable<List<HomeHotColumn>> getHotColumn();
        Observable<List<HomeFaceScoreColumn>> getFaceScoreColumn(int offset, int limit);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void setString();
        public abstract void setCarousel();
        public abstract void setHotColumn();
        public abstract void setFaceScoreColumn(int offset,int limit);
    }
}
