package com.yw.android.store.vm.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.ww.mvp.view.IView;
import com.yw.android.store.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Optional;
import ww.com.core.ScreenUtil;
import ww.com.core.widget.CustomRecyclerView;
import ww.com.core.widget.CustomSwipeRefreshLayout;

/**
 * Created by feng on 2017/9/20.
 */

public class RefreshView implements IView{
    @Nullable
    @BindView(R.id.crv)
    CustomRecyclerView crv;
    @Nullable
    @BindView(R.id.csr)
    CustomSwipeRefreshLayout csr;

    protected Context context;

    @Override
    public void onAttach(@NonNull Activity activity, @NonNull View view) {
        ButterKnife.bind(this, view);
        context = view.getContext();
        attach();
    }

    @Optional
    public void attach() {
        final GridLayoutManager manager = new GridLayoutManager(context,2);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position==0){
                    return 2;
                }else {
                    return 1;
                }

            }
        });
        crv.setLayoutManager(manager);
        crv.setItemAnimator(new DefaultItemAnimator());

        View emptyView = LayoutInflater.from(context).inflate(R.layout.layout_empty, null);
        ScreenUtil.scale(emptyView);
        crv.addEmpty(emptyView);

        csr.setRefreshView(crv);
        csr.setColorSchemeColors(new int[]{Color.parseColor("#14191d"), -65536});
        csr.setEnableRefresh(false);

    }

    public CustomRecyclerView getCrv() {
        return crv;
    }

    public CustomSwipeRefreshLayout getCsr() {
        return csr;
    }


    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }
}
