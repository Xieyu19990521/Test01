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
import com.example.yu.text_demo01.activity.ShowActivity;
import com.example.yu.text_demo01.bean.BookBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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
        final BookBean.DataBean dataBean = mlist.get(i);
        holder.title.setText(dataBean.getTitle());
        holder.price.setText(dataBean.getPrice()+"");
        String[] split = dataBean.getImages().split("\\|");
        Uri uri=Uri.parse(split[0]);
        holder.simpleDraweeView.setImageURI(uri);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext,ShowActivity.class);
                intent.putExtra("pid",dataBean.getPid()+"");
                mContext.startActivity(intent);
            }
        });
        holder.linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View v) {
                //得到当前的视图的位置x
                final float x = v.getX();
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
                        //复位
                        v.setX(x);
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

    }
}
