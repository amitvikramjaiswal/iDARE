package com.opensource.app.idare.view.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.opensource.app.idare.R;
import com.opensource.app.idare.data.entities.CoreUserEntity;
import com.opensource.app.idare.view.activities.MainActivity;
import com.opensource.app.idare.view.fragments.CoreListFragment;

import java.util.List;

/**
 * Created by ajaiswal on 4/26/2016.
 */
public class CoreListAdapter extends RecyclerView.Adapter<CoreListAdapter.CardViewHolder> {

    private final List<CoreUserEntity> mArlCoreUsers;
    private Context mContext;
    private MainActivity mMenuActivity;
    private CoreListFragment mFragment;

    public CoreListAdapter(List<CoreUserEntity> mArlCoreUsers, Context mContext, MainActivity mMenuActivity, CoreListFragment mFragment) {
        this.mArlCoreUsers = mArlCoreUsers;
        this.mContext = mContext;
        this.mMenuActivity = mMenuActivity;
        this.mFragment = mFragment;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_simple_list_item, parent, false);

        return new CardViewHolder(mContext, itemView);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        final CoreUserEntity coreUserEntity = mArlCoreUsers.get(position);

        holder.tvCoreUserName.setText("");
        holder.cvUserRow.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                return true;
            }
        });

        holder.cvUserRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mArlCoreUsers.size();
    }

    public void animateTo(List<CoreUserEntity> arlUsers) {
        applyAndAnimateRemovals(arlUsers);
        applyAndAnimateAdditions(arlUsers);
        applyAndAnimateMovedItems(arlUsers);
    }

    private void applyAndAnimateRemovals(List<CoreUserEntity> newModels) {
        for (int i = mArlCoreUsers.size() - 1; i >= 0; i--) {
            final CoreUserEntity model = mArlCoreUsers.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<CoreUserEntity> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final CoreUserEntity model = newModels.get(i);
            if (!mArlCoreUsers.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<CoreUserEntity> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final CoreUserEntity model = newModels.get(toPosition);
            final int fromPosition = mArlCoreUsers.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    public CoreUserEntity removeItem(int position) {
        final CoreUserEntity model = mArlCoreUsers.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    public void addItem(int position, CoreUserEntity model) {
        mArlCoreUsers.add(position, model);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final CoreUserEntity model = mArlCoreUsers.remove(fromPosition);
        mArlCoreUsers.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {

        protected ImageView ivCoreUser;
        protected TextView tvCoreUserName;
        protected CardView cvUserRow;
        protected RelativeLayout rlUserRow;
        protected Context mContext;

        public CardViewHolder(Context context, View view) {
            super(view);
            mContext = context;
            ivCoreUser = (ImageView) view.findViewById(R.id.iv_core_user);
            tvCoreUserName = (TextView) view.findViewById(R.id.tv_core_user_name);
            cvUserRow = (CardView) view;
            rlUserRow = (RelativeLayout) view.findViewById(R.id.rl_core_list_row);
        }
    }
}
