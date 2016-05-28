package com.oncedoing.bikeshop.manage_bike.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.google.zxing.MipcaActivityCapture;
import com.jude.swipbackhelper.SwipeBackHelper;
import com.oncedoing.bikeshop.R;
import com.oncedoing.bikeshop.config.AppConstants;
import com.oncedoing.bikeshop.model.BikeInfoEntity;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.kkwang.commonlib.base.BaseToolBarActivity;
import me.nereo.multi_image_selector.KMultiImageSelectorActivity;
import timber.log.Timber;

public class ProductEditActivity extends BaseToolBarActivity {

    private static final String TAG = "ProductEditActivity";

    public  interface NAV_STATE{
        String NAVIGATION_KEY = "navigation_key";
        String EDIT_PRODUCT = "edit_product";
        String ADD_PRODUCT = "add_product";
    }

    String[] categoryArray;

    @Bind(R.id.iv_qrcode)
    ImageView ivQrScan;
    @Bind(R.id.tv_code)
    TextView tvCode;
    @Bind(R.id.rl_qrcode)
    RelativeLayout rlQrcode;

    @Bind(R.id.tv_brand)
    TextView tvBrand;
    @Bind(R.id.rl_brand)
    RelativeLayout rlBrand;

    @Bind(R.id.tv_category)
    TextView tvCategory;
    @Bind(R.id.rl_category)
    RelativeLayout rlCategory;

    @Bind(R.id.tv_specification)
    TextView tvSpecification;
    @Bind(R.id.rl_specification)
    RelativeLayout rlSpecification;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.rl_name)
    RelativeLayout rlName;
    @Bind(R.id.rl_color)
    RelativeLayout rlColor;
    @Bind(R.id.rl_size)
    RelativeLayout rlSize;
    @Bind(R.id.rl_price)
    RelativeLayout rlPrice;
    @Bind(R.id.rl_markettime)
    RelativeLayout rlMarkettime;
    @Bind(R.id.tv_price_in)
    TextView tvPriceIn;
    @Bind(R.id.rl_price_in)
    RelativeLayout rlPriceIn;
    @Bind(R.id.tv_count_in)
    TextView tvCountIn;
    @Bind(R.id.rl_count_in)
    RelativeLayout rlCountIn;


    @Bind(R.id.ll_tips)
    LinearLayout llTips;

    @Bind(R.id.iv_takepic_pic)
    ImageView ivTakepic1;
    @Bind(R.id.iv_takepic_delete)
    ImageView ivTakepicDelete1;

    @Bind(R.id.iv_takepic_pic2)
    ImageView ivTakepic2;
    @Bind(R.id.iv_takepic_delete2)
    ImageView ivTakepicDelete2;

    @Bind(R.id.iv_takepic_pic3)
    ImageView ivTakepic3;
    @Bind(R.id.iv_takepic_delete3)
    ImageView ivTakepicDelete3;

    @Bind(R.id.tv_save_addbike)
    TextView tvSaveAddbike;

    int REQUEST_RQCODE_SCAN = 100;
    int REQUEST_IMAGE_CODE_1 = 101;
    int REQUEST_IMAGE_CODE_2 = 102;
    int REQUEST_IMAGE_CODE_3 = 103;
    int REQUEST_CROP_IMAGE_CODE_1 = 104;


    BikeInfoEntity bikeInfoEntity;
    final int MAX_PIC_COUNT = 3;
    int picCount = MAX_PIC_COUNT;

    @OnClick(R.id.rl_qrcode)
    public void onClickQRCode() {
        Intent intent = new Intent(this, MipcaActivityCapture.class);
        startActivityForResult(intent, REQUEST_RQCODE_SCAN);
    }

    @OnClick(R.id.rl_brand)
    public void onClickBrand() {

        new MaterialDialog.Builder(this)
                .title("品牌选择")
                .items(R.array.items_bike_brands)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        tvBrand.setText(getResources().getStringArray(R.array.items_bike_brands)[which]);
                    }
                })
                .show();
    }

    @OnClick(R.id.rl_category)
    public void onClickCategory() {

        new MaterialDialog.Builder(this)
                .title("类别选择")
                .items(R.array.product_top_category)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        tvCategory.setText(getResources().getStringArray(R.array.product_top_category)[which]);
                    }
                })
                .show();
    }


    private ArrayList<String> defaultDataArray;
    private ImageView picView;

    @OnClick({R.id.iv_takepic_pic, R.id.iv_takepic_pic2, R.id.iv_takepic_pic3})
    public void onClickTakePic1(View view) {

        picView = (ImageView) view;

        Intent intent = new Intent(this, KMultiImageSelectorActivity.class);
        // whether show camera
        intent.putExtra(KMultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
        // default select images (support array list)
//        intent.putStringArrayListExtra(KMultiImageSelectorActivity.EXTRA_DEFAULT_SELECTED_LIST, defaultDataArray);

//        if (view.getId() == R.id.iv_takepic_pic) {
//            // max select image amount
//            intent.putExtra(KMultiImageSelectorActivity.EXTRA_SELECT_COUNT, MAX_PIC_COUNT);
//            // select mode (KMultiImageSelectorActivity.MODE_SINGLE OR KMultiImageSelectorActivity.MODE_MULTI)
//            intent.putExtra(KMultiImageSelectorActivity.EXTRA_SELECT_MODE, KMultiImageSelectorActivity.MODE_MULTI);
//
//            startActivityForResult(intent, REQUEST_IMAGE_CODE_1);
//        } else if (view.getId() == R.id.iv_takepic_pic2) {
//            intent.putExtra(KMultiImageSelectorActivity.EXTRA_SELECT_COUNT, MAX_PIC_COUNT - 1);
//            // select mode (KMultiImageSelectorActivity.MODE_SINGLE OR KMultiImageSelectorActivity.MODE_MULTI)
//            intent.putExtra(KMultiImageSelectorActivity.EXTRA_SELECT_MODE, KMultiImageSelectorActivity.MODE_MULTI);
//
//            startActivityForResult(intent, REQUEST_IMAGE_CODE_2);
//        } else {
            intent.putExtra(KMultiImageSelectorActivity.EXTRA_SELECT_COUNT, 1);
            // select mode (KMultiImageSelectorActivity.MODE_SINGLE OR KMultiImageSelectorActivity.MODE_MULTI)
            intent.putExtra(KMultiImageSelectorActivity.EXTRA_SELECT_MODE, KMultiImageSelectorActivity.MODE_SINGLE);
            startActivityForResult(intent, REQUEST_IMAGE_CODE_1);
//        }


//        Context appContext = getApplicationContext();
//        ImageConfig imageConfig
//                = new ImageConfig.Builder(new PicSelecterImageLoader())
//                .steepToolBarColor(appContext.getResources().getColor(R.color.colorPrimary))
//                .titleBgColor(appContext.getResources().getColor(R.color.blue))
//                .titleSubmitTextColor(appContext.getResources().getColor(R.color.white))
//                .titleTextColor(appContext.getResources().getColor(R.color.white))
//                .crop(1,1, 500, 500)
//                // 开启多选   （默认为多选）
//                .singleSelect()
//                // 多选时的最大数量   （默认 9 张）
////                .mutiSelectMaxSize(3)
//                // 开启拍照功能 （默认关闭）
//                .showCamera()
//                // 已选择的图片路径
//                .pathList(path)
//                // 拍照后存放的图片路径（默认 /temp/picture） （会自动创建）
//                .filePath("/ImageSelector/Pictures")
//                .build();
//
//        ImageSelector.open(ProductEditActivity.this, imageConfig);   // 开启图片选择器

    }

    @OnClick(R.id.tv_save_addbike)
    void onClickSave() {
        onBackPressed();
    }

    //场景是模仿淘宝，最多上传三张。第一次拍一张，则第二个显示加号

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CODE_1 && resultCode == RESULT_OK && data != null) {

            defaultDataArray = data.getStringArrayListExtra(KMultiImageSelectorActivity.EXTRA_RESULT);
            Timber.e(defaultDataArray.get(0));
            Uri sourceUri = Uri.fromFile(new File(defaultDataArray.get(0)));
            Uri destFileUri = Uri.fromFile(new File(getCacheDir(), "crop.jpg"));
            UCrop.of(sourceUri, destFileUri)
                    .withAspectRatio(1, 1)
                    .withMaxResultSize(500, 500)
                    .start(this);

//            ivTakepic2.setVisibility(View.VISIBLE);
//            int size = defaultDataArray.size();
//            picCount = size;
//            if (size > 1) {
//                Glide.with(ProductEditActivity.this).fromFile().load(new File(defaultDataArray.get(1))).into(ivTakepic2);
//                ivTakepic3.setVisibility(View.VISIBLE);
//                if (size > 2) {
//                    Glide.with(ProductEditActivity.this).fromFile().load(new File(defaultDataArray.get(2))).into(ivTakepic3);
//                }
//            }
//            defaultDataArray.clear();

        } else if (requestCode == REQUEST_IMAGE_CODE_2 && resultCode == RESULT_OK && data != null) {
            defaultDataArray = data.getStringArrayListExtra(KMultiImageSelectorActivity.EXTRA_RESULT);
            Glide.with(ProductEditActivity.this).fromFile().load(new File(defaultDataArray.get(0))).into(ivTakepic2);
            ivTakepic3.setVisibility(View.VISIBLE);
            int size = defaultDataArray.size();
            if (size > 1) {
                Glide.with(ProductEditActivity.this).fromFile().load(new File(defaultDataArray.get(1))).into(ivTakepic3);
            }
            defaultDataArray.clear();

        } else if (requestCode == REQUEST_IMAGE_CODE_3 && resultCode == RESULT_OK && data != null) {
            defaultDataArray = data.getStringArrayListExtra(KMultiImageSelectorActivity.EXTRA_RESULT);
            Glide.with(ProductEditActivity.this).fromFile().load(new File(defaultDataArray.get(0))).into(ivTakepic3);

        } else if (requestCode == REQUEST_RQCODE_SCAN && resultCode == RESULT_OK && data != null) {
            String code = data.getStringExtra("result");
            tvCode.setText(code);
        } else if(requestCode == UCrop.REQUEST_CROP && resultCode == RESULT_OK && data != null){
            final Uri resultUri = UCrop.getOutput(data);
            Glide.with(ProductEditActivity.this).load(resultUri.getPath()).into(ivTakepic1);

        }
    }

    @OnClick({R.id.iv_takepic_delete, R.id.iv_takepic_delete2, R.id.iv_takepic_delete3})
    public void onClickDeletePic1(View view) {
        switch (view.getId()) {
            case R.id.iv_takepic_delete:
                ivTakepic1.setImageResource(R.drawable.ic_takepic);
                break;
            case R.id.iv_takepic_delete2:
                ivTakepic2.setImageResource(R.drawable.ic_takepic);
                break;
            case R.id.iv_takepic_delete3:
                ivTakepic3.setImageResource(R.drawable.ic_takepic);
                break;
        }
    }

    @Override
    protected void beforeSetContentView() {
        super.beforeSetContentView();
        SwipeBackHelper.onCreate(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bike_add;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        categoryArray = getResources().getStringArray(R.array.product_top_category);
        Intent intent = getIntent();
        if(TextUtils.equals(NAV_STATE.EDIT_PRODUCT,intent.getStringExtra(NAV_STATE.NAVIGATION_KEY))){
            bikeInfoEntity = intent.getParcelableExtra(AppConstants.COMMODITY_INFO_KEY);
            if(bikeInfoEntity != null){
                tvName.setText(bikeInfoEntity.getName());
                tvBrand.setText(bikeInfoEntity.getBrandname());
                tvCategory.setText(bikeInfoEntity.getCategory() + "缺转换表");
            }
        }

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        SwipeBackHelper.onPostCreate(this);
    }

    @Override
    protected boolean isActionBarNeedBackEnable() {
        return true;
    }

    @Override
    public String getToolbarTitle() {
        return "新增商品";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SwipeBackHelper.onDestroy(this);

    }

    public static void start(Activity context){
        Intent intent = new Intent(context, ProductEditActivity.class);
        context.startActivity(intent);
    }

    public static void startForEdit(Activity context, BikeInfoEntity bikeInfoEntity, int request_code){
        Intent intent = new Intent(context, ProductEditActivity.class);
        intent.putExtra(NAV_STATE.NAVIGATION_KEY,NAV_STATE.EDIT_PRODUCT);
        intent.putExtra(AppConstants.COMMODITY_INFO_KEY,bikeInfoEntity);
        context.startActivityForResult(intent,request_code);
    }

    public static void startForResult(Activity context, String state, int request_code){
        Intent intent = new Intent(context, ProductListActivity.class);
        intent.putExtra(NAV_STATE.NAVIGATION_KEY,state);
        context.startActivityForResult(intent,request_code);
    }
}
