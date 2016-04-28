package com.opensource.app.idare.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.kinvey.android.callback.KinveyPingCallback;
import com.opensource.app.idare.R;
import com.opensource.app.idare.data.entities.CoreUserEntity;
import com.opensource.app.idare.util.Utility;
import com.opensource.app.idare.util.log.Logger;
import com.opensource.app.idare.view.activities.MainActivity;
import com.opensource.app.idare.view.adapters.CoreListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ajaiswal on 4/4/2016.
 */
public class CoreListFragment extends BaseFragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, RecyclerView.OnItemTouchListener {

    private static final String TAG = "CoreListFragment";
    private MainActivity mMainActivity;
    private Context mContext;

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView rvCoreList;
    private FloatingActionButton fabAddToCoreList;

    private CoreListAdapter coreListAdapter;
    private LinearLayoutManager linearLayoutManager;

    private List<CoreUserEntity> arlCoreUsers;

    private SearchView svSearchCoreUser;
    private String searchQuery;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        mMainActivity = (MainActivity) getActivity();
        mContext = mMainActivity.getApplicationContext();
        setHasOptionsMenu(true);
        mMainActivity.getSupportActionBar().setTitle(mMainActivity.getStringArray(R.array.arr_nav_titles)[1]);

        return view;
    }

    public void fetchCoreList() {
        mMainActivity.getPresenter().getServiceFacade().ping(mMainActivity, new PingCallback());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        linearLayoutManager = new LinearLayoutManager(mMainActivity, LinearLayoutManager.VERTICAL, false);
        addListeners();
    }

    private void addListeners() {
        swipeRefreshLayout.setOnRefreshListener(this);
        fabAddToCoreList.setOnClickListener(this);
        rvCoreList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (rvCoreList.canScrollVertically(-1)) {
                    swipeRefreshLayout.setEnabled(false);
                } else {
                    swipeRefreshLayout.setEnabled(true);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        fetchCoreList();
    }

    private List<CoreUserEntity> filter(List<CoreUserEntity> models, String query) {
        query = query.toLowerCase();

        final List<CoreUserEntity> filteredModelList = new ArrayList<>();
        for (CoreUserEntity model : models) {
            final String text = "";//model.getUsername().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_search, menu);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (!Utility.CORE_LIST.equalsIgnoreCase(mMainActivity.getStringArray(R.array.arr_nav_titles)[1])) {
            menu.clear();
        } else {
            menu.clear();
            MenuInflater inflater = getActivity().getMenuInflater();
            inflater.inflate(R.menu.menu_search, menu);
            MenuItem searchItem = menu.findItem(R.id.action_search);
            svSearchCoreUser = (SearchView) searchItem.getActionView();
            showSearch();
        }
    }

    private void showSearch() {
        svSearchCoreUser.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

            }
        });

        svSearchCoreUser.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchQuery = newText;
                if (arlCoreUsers != null  && arlCoreUsers.size() > 0) {
                    final List<CoreUserEntity> filteredModelList = filter(arlCoreUsers, newText);
                    coreListAdapter.animateTo(filteredModelList);
                    rvCoreList.smoothScrollToPosition(0);
                }
                return true;
            }
        });
    }

    @Override
    int getFragmentLayoutResourceId() {
        return R.layout.fragment_core_list;
    }

    @Override
    void findViews() {
        swipeRefreshLayout = (SwipeRefreshLayout) mRootView.findViewById(R.id.swipe_refresh_layout);
        rvCoreList = (RecyclerView) mRootView.findViewById(R.id.rv_core_list);
        fabAddToCoreList = (FloatingActionButton) mRootView.findViewById(R.id.fab_add_to_core_list);
    }

    @Override
    public void onRefresh() {
        fetchCoreList();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_add_to_core_list:
                addToCoreList();
                break;
        }
    }

    private void addToCoreList() {

    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    private class PingCallback implements KinveyPingCallback {

        @Override
        public void onSuccess(Boolean aBoolean) {
            Log.d(TAG, "SUCCESS");
        }

        @Override
        public void onFailure(Throwable throwable) {
            Log.e(TAG, "ERROR");
        }
    }
}
