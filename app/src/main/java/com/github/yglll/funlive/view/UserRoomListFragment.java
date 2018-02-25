package com.github.yglll.funlive.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.yglll.funlive.R;
import com.github.yglll.funlive.db.FunLiveDB;
import com.github.yglll.funlive.net.bean.FunLiveRoom;
import com.github.yglll.funlive.view.adapter.user.UserRoomListAdapter;
import com.github.yglll.funlive.view.manager.FullyGridLayoutManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

/**
 * 作者：YGL
 * 版本号：1.0
 * 类描述：
 * 备注消息：
 * 创建时间：2018/02/20   23:24
 **/
public class UserRoomListFragment extends Fragment {

    @BindView(R.id.refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.empty_view)
    TextView emptyView;

    private FunLiveDB funLiveDB;
    private UserRoomListAdapter userRoomListAdapter;
    private String tableName;

    //封装获取实例的过程
    public static UserRoomListFragment getInstance(String tableName){
        UserRoomListFragment userRoomListFragment=new UserRoomListFragment();
        Bundle bundle=new Bundle();
        bundle.putString("tableName",tableName);
        userRoomListFragment.setArguments(bundle);
        return userRoomListFragment;
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle){
        View view=layoutInflater.inflate(R.layout.fragment_user_room_list,viewGroup,false);
        ButterKnife.bind(this,view);
        funLiveDB=FunLiveDB.getInstance(getActivity());
        onInitView();
        return view;
    }

    private void onInitView(){
        tableName=getArguments().getString("tableName");
        userRoomListAdapter=new UserRoomListAdapter(getActivity());
        recyclerView.setAdapter(userRoomListAdapter);
        recyclerView.setLayoutManager(new FullyGridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false));

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                userRoomListAdapter.setData(getData());
                smartRefreshLayout.finishRefresh();
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                Toasty.info(getContext(),getString(R.string.no_more),Toast.LENGTH_SHORT).show();
                smartRefreshLayout.finishLoadMore();
            }
        });
    }

    private void emptyViewControl(boolean bool){
        if(bool){
            emptyView.setVisibility(View.VISIBLE);
        }else {
            emptyView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onStart(){
        super.onStart();
        if(userRoomListAdapter!=null){
            userRoomListAdapter.setData(getData());
        }
    }

    private List<FunLiveRoom> getData(){
        List<FunLiveRoom> list=funLiveDB.getAllFunLiveRoom(tableName);
        //反转排序
        Collections.reverse(list);
        emptyViewControl(list.size()==0);
        return list;
    }
}
