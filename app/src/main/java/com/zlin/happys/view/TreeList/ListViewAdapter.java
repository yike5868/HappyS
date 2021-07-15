package com.zlin.happys.view.TreeList;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zlin.happys.R;

import java.util.List;

/**
 * Created by xiaoyehai on 2018/7/12 0012.
 */

public class ListViewAdapter extends TreeListViewAdapter {

    private OnTreeNodeCheckedChangeListener checkedChangeListener;

    public void setCheckedChangeListener(OnTreeNodeCheckedChangeListener checkedChangeListener) {
        this.checkedChangeListener = checkedChangeListener;
    }

    public ListViewAdapter(ListView listView, Context context, List<Node> datas, int defaultExpandLevel, int iconExpand, int iconNoExpand) {
        super(listView, context, datas, defaultExpandLevel, iconExpand, iconNoExpand);
    }

    @Override
    public View getConvertView(final Node node, final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.tree_list_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvName.setText(node.getName());

        if (node.getIcon() == -1) {
            holder.ivExpand.setVisibility(View.INVISIBLE);
        } else {
            holder.ivExpand.setVisibility(View.VISIBLE);
            holder.ivExpand.setImageResource(node.getIcon());
        }

        return convertView;
    }

    static class ViewHolder {
        private TextView tvName;
        private ImageView ivExpand;

        public ViewHolder(View convertView) {
            tvName = convertView.findViewById(R.id.tv_body);
            ivExpand = convertView.findViewById(R.id.iv_title);
        }
    }
}