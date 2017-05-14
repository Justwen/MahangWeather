package com.mahang.weather.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.AttributeSet;


public class SwipeDismissListView extends RecyclerView {

    private onTouchCallBack mOnTouchCallBack;

    private ItemTouchHelper mTouchHelper;

    public interface onTouchCallBack{

        boolean onMove(int start,int end);

        void onSwipe(int postion);
    }


    public SwipeDismissListView(Context context) {
        super(context,null);
    }

    public SwipeDismissListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        setLayoutManager(new LinearLayoutManager(getContext()));
        mTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.DOWN|ItemTouchHelper.UP, ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

                if (mOnTouchCallBack != null){
                    mOnTouchCallBack.onMove(viewHolder.getAdapterPosition(),target.getAdapterPosition());
                }
                getAdapter().notifyItemMoved(viewHolder.getAdapterPosition(),target.getAdapterPosition());
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                if (mOnTouchCallBack != null){
                    mOnTouchCallBack.onSwipe(viewHolder.getAdapterPosition());
                }
                getAdapter().notifyDataSetChanged();

            }
        });
        mTouchHelper.attachToRecyclerView(this);
    }

    public void setSwipeEnabled(boolean enabled){
        if (enabled){
            mTouchHelper.attachToRecyclerView(this);
        } else{
            mTouchHelper.attachToRecyclerView(null);
        }
    }

    public void setOnTouchCallBack(onTouchCallBack callBack){
        mOnTouchCallBack = callBack;
    }


}
