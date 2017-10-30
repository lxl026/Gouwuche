package company.leon.gouwuche;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Leon on 2017/10/28.
 */

public abstract  class CommonAdapter<T> extends RecyclerView.Adapter<myViewHolder>{
    protected Context mcontext;
    protected  int mLayoutId;
    protected List<T> mDatas;
    private OnItemClickListener mOnItemClickListener=null;
    public CommonAdapter(Context context,int layoutId, List datas){
        mcontext=context;
        mLayoutId=layoutId;
        mDatas=datas;
    }
    @Override
    public myViewHolder onCreateViewHolder(final ViewGroup parent, int viewType)
    {
        myViewHolder viewHolder=myViewHolder.get(mcontext,parent,mLayoutId);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder (final myViewHolder holder,int position)
    {
        convert(holder,mDatas.get(position));
        if(mOnItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    mOnItemClickListener.onClick(holder.getAdapterPosition());
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public  boolean onLongClick(View v){
                    mOnItemClickListener.onLongClick(holder.getAdapterPosition());
                    return true;
                }
            });
        }
    }
    protected abstract void convert(myViewHolder holder, T t);
    @Override
    public int getItemCount()
    {
        return mDatas.size();
    }
    public void Remove(int position){
        mDatas.remove(position);
        notifyItemRemoved(position);
    }
    public interface OnItemClickListener
    {
        void onClick(int position);
        void onLongClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener)
    {
        this.mOnItemClickListener=onItemClickListener;
    }
}
