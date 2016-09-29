package com.example.lmj.a2hm2.Msg;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lmj.a2hm2.R;

/**
 * Created by lmj on 2016/9/11.
 */
public class bb_Msg extends Fragment {
    private View v_my_msg;
    private TextView ic_my_msg_leave_msg;
    private TextView ic_my_msg_system_msg;
    private TextView ic_my_msg_leave_msg_into;
    private TextView ic_my_msg_system_msg_into;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v_my_msg= inflater.inflate(R.layout.msg_fragment,container,false);
        initView();
        return v_my_msg;
    }
    private void initView() {
        ic_my_msg_leave_msg= (TextView) v_my_msg.findViewById(R.id.ic_my_msg_leave_msg);
        ic_my_msg_system_msg= (TextView) v_my_msg.findViewById(R.id.ic_my_msg_system_msg);
        ic_my_msg_leave_msg_into= (TextView) v_my_msg.findViewById(R.id.ic_my_msg_leave_msg_into);
        ic_my_msg_system_msg_into= (TextView) v_my_msg.findViewById(R.id.ic_my_msg_system_msg_into);

        Typeface iconfont1 = Typeface.createFromAsset(getActivity().getAssets(), "ic_msg/iconfont.ttf");
        ic_my_msg_leave_msg.setTypeface(iconfont1);ic_my_msg_system_msg.setTypeface(iconfont1);
        Typeface iconfont2 = Typeface.createFromAsset(getActivity().getAssets(), "ic_my/iconfont.ttf");
        ic_my_msg_leave_msg_into.setTypeface(iconfont2);ic_my_msg_system_msg_into.setTypeface(iconfont2);


    }

}
