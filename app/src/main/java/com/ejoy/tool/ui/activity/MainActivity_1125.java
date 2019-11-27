package com.ejoy.tool.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ejoy.tool.R;
import com.ejoy.tool.scaffold.view.ExpandImageView;
import com.ejoy.tool.ui.base.base_activity.BaseActivity;
import com.ejoy.tool.ui.mvp.base.BasePresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * CN:      MainActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/10/10
 * Des:    主界面
 */
public class MainActivity_1125 extends BaseActivity {

    @BindView(R.id.ibnBack)
    ImageButton ibnBack;
    @BindView(R.id.tvProReport)
    TextView tvProReport;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.SimpleDraweeView)
    ExpandImageView ThumbImgA;
    @BindView(R.id.SimpleDraweeView1)
    ExpandImageView ThumbImgB;
    @BindView(R.id.eiv_a)
    ExpandImageView eiv_a;
    @BindView(R.id.eiv_b)
    ExpandImageView eiv_b;
    @BindView(R.id.eiv_c)
    ExpandImageView eiv_c;
    @BindView(R.id.eiv_d)
    ExpandImageView eiv_d;
    @BindView(R.id.eiv_e)
    ExpandImageView eiv_e;
    @BindView(R.id.eiv_f)
    ExpandImageView eiv_f;
    @BindView(R.id.eiv_g)
    ExpandImageView eiv_g;
    @BindView(R.id.eiv_h)
    ExpandImageView eiv_h;
    @BindView(R.id.eiv_i)
    ExpandImageView eiv_i;
    @BindView(R.id.eiv_j)
    ExpandImageView eiv_j;
    @BindView(R.id.eiv_k)
    ExpandImageView eiv_k;
    @BindView(R.id.eiv_l)
    ExpandImageView eiv_l;
    @BindView(R.id.SimpleDraweeView3)
    ExpandImageView ThumbImgC;

    private String localImgUrl = "";

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(View mRootView) {
        localImgUrl = "res://" + this.getPackageName() + "/";
        ThumbImgA.setScaleType(ImageView.ScaleType.FIT_XY);
        ThumbImgA.setImageURI(Uri.parse(localImgUrl + "" + R.drawable.six));
        ThumbImgB.setScaleType(ImageView.ScaleType.FIT_XY);
        ThumbImgB.setImageURI(Uri.parse(localImgUrl + "" + R.drawable.img_banner_c));
        ThumbImgC.setImageURI(Uri.parse(localImgUrl + "" + R.drawable.img_banner_d));
        eiv_a.showUrlBlur(eiv_a, localImgUrl + "" + R.mipmap.img_f, 0, 5);
        eiv_b.showUrlBlur(eiv_b, localImgUrl + "" + R.mipmap.img_b, 0, 3);
        eiv_c.showUrlBlur(eiv_c, localImgUrl + "" + R.mipmap.img_d, 0, 3);
        eiv_d.showUrlBlur(eiv_d, localImgUrl + "" + R.mipmap.img_e, 0, 3);
        eiv_e.showUrlBlur(eiv_e, localImgUrl + "" + R.mipmap.img_a, 0, 3);
        eiv_f.showUrlBlur(eiv_f, localImgUrl + "" + R.mipmap.img_g, 0, 3);
        eiv_g.showUrlBlur(eiv_g, localImgUrl + "" + R.drawable.first, 0, 3);
        eiv_h.showUrlBlur(eiv_h, localImgUrl + "" + R.drawable.fourth, 0, 3);
        eiv_i.showUrlBlur(eiv_i, localImgUrl + "" + R.mipmap.img_i, 0, 3);
        eiv_j.showUrlBlur(eiv_j, localImgUrl + "" + R.mipmap.img_j, 0, 3);
        eiv_k.showUrlBlur(eiv_k, localImgUrl + "" + R.mipmap.img_l, 0, 3);
        eiv_l.showUrlBlur(eiv_l, localImgUrl + "" + R.mipmap.img_k, 0, 3);
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }*/


    @OnClick({
            R.id.cvToast
            , R.id.cvScrollView
            , R.id.cvFloatTab
    })
    public void bindViewClick(View view) {
        switch (view.getId()) {
            case R.id.cvToast://Toast
                startActivity(new Intent(this, ToastActivity.class));
                break;
            case R.id.cvScrollView://Toast
                startActivity(new Intent(this, IScrollViewActivity.class));
                break;
            case R.id.cvFloatTab://Toast
                break;
        }
    }


}
