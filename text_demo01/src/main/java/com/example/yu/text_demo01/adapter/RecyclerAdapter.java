package com.example.yu.text_demo01.adapter;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yu.text_demo01.R;
import com.example.yu.text_demo01.ShowActivity;
import com.example.yu.text_demo01.bean.BookBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<BookBean.DataBean> mlist;

    public RecyclerAdapter(Context mContext) {
        this.mContext = mContext;
        mlist=new ArrayList<>();
    }

    public void setMlist(List<BookBean.DataBean> mlist) {
        this.mlist = mlist;
        notifyDataSetChanged();
    }

    public void addMlist(List<BookBean.DataBean> mlist) {
        this.mlist.addAll(mlist);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.recycler_item,viewGroup,false);
        RecyclerView.ViewHolder viewHolder = new ViewHolder(view);
        ButterKnife.bind(viewHolder,view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        ViewHolder holder= (ViewHolder) viewHolder;
        BookBean.DataBean dataBean = mlist.get(i);
        holder.title.setText(dataBean.getTitle());
        holder.price.setText(dataBean.getPrice()+"");
        String[] split = dataBean.getImages().split("\\|");
        Uri uri=Uri.parse(split[0]);
        holder.simpleDraweeView.setImageURI(uri);
        holder.linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ObjectAnimator animator=ObjectAnimator.ofFloat(v,"translationX",0,-1080);
                animator.setDuration(3000);
                animator.setInterpolator(new AccelerateInterpolator());
                animator.start();
                animator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mlist.remove(i);
                        notifyDataSetChanged();
                        notifyItemRangeRemoved(i,mlist.size());
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });

                return true;
            }
        });
    }
    public void linearLayoutOnLongClick(View view,int position){

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.simple)
        SimpleDraweeView simpleDraweeView;
        @BindView(R.id.linear_item)
        LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @OnClick(R.id.linear_item)
        public void linearLayoutOnClick(){
            Intent intent=new Intent(mContext,ShowActivity.class);
            intent.putExtra("pid","45");
            mContext.startActivity(intent);
        }


    }
}
