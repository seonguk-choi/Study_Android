package com.example.safing.shop.fragment;

import static com.example.safing.async.CommonAsk.FILE_PATH;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.safing.MainActivity;
import com.example.safing.R;
import com.example.safing.async.CommonVal;
import com.example.safing.shop.DAO.ShopDAO;
import com.example.safing.shop.VO.PurchaseHistoryVO;
import com.example.safing.shop.VO.ReviewVO;
import com.example.safing.shop.VO.Review_ImageListVO;
import com.example.safing.shop.activity.Purchase_Result_Activity;
import com.example.safing.shop.activity.Review_Result_Activity;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.io.File;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import javax.xml.transform.Result;


public class Review_Fragment extends Fragment {
    Context context;
    Toolbar toolbar;
    NavigationView review_view;
    ImageView review_img, review_imgbtn1, review_imgbtn2, review_imgbtn3, review_imgbtn4;
    TextView review_tv1, review_tv2, review_tv3, review_tv4;
    RatingBar review_rating;
    EditText review_etv;
    Button review_btn1, review_btn2;

    int rating_check = 0;
    int imgbtn_check = 0;

    AlertDialog dialog;
    final int GALLERY_IMG = 1001;
    final int CAMERA_REQ = 1002;
    String img_filepath;
    String[] spn_item = {"?????????", "?????????"};

    PurchaseHistoryVO vo = new PurchaseHistoryVO();
    ReviewVO reviewVO = new ReviewVO();
    MainActivity mainActivity = new MainActivity();
    ShopDAO dao = new ShopDAO();
    ArrayList<Review_ImageListVO> review_imagelist = new ArrayList<>();

    public Review_Fragment(Context context, PurchaseHistoryVO vo){
        this.context = context;
        this.vo = vo;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_review, container, false);

        review_view = rootView.findViewById(R.id.review_view);
        review_img = rootView.findViewById(R.id.review_img);
        review_tv1 = rootView.findViewById(R.id.review_tv1);
        review_tv2 = rootView.findViewById(R.id.review_tv2);
        review_tv3 = rootView.findViewById(R.id.review_tv3);
        review_tv4 = rootView.findViewById(R.id.review_tv4);
        review_rating = rootView.findViewById(R.id.review_rating);
        review_etv = rootView.findViewById(R.id.review_etv);
        review_imgbtn1 = rootView.findViewById(R.id.review_imgbtn1);
        review_imgbtn2 = rootView.findViewById(R.id.review_imgbtn2);
        review_imgbtn3 = rootView.findViewById(R.id.review_imgbtn3);
        review_imgbtn4 = rootView.findViewById(R.id.review_imgbtn4);
        review_btn1 = rootView.findViewById(R.id.review_btn1);
        review_btn2 = rootView.findViewById(R.id.review_btn2);
        toolbar = rootView.findViewById(R.id.review_toolbar);

        mainActivity = (MainActivity) getActivity();

        //========= ????????? ?????? ==============

        DrawerLayout drawer = rootView.findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                (Activity) context, drawer, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_open
        );
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        View nav_headerview = review_view.getHeaderView(0);
        ImageView header_imge = nav_headerview.findViewById(R.id.header_imge);
        TextView header_text= nav_headerview.findViewById(R.id.header_text);

        if(CommonVal.loginInfo != null){
            Glide.with(context).load(FILE_PATH + CommonVal.loginInfo.getMember_filepath()).into(header_imge);
            header_text.setText(CommonVal.loginInfo.getMember_id());
        }

        //============= navigation view ??????=====
        review_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.menu_cart){
                    mainActivity.changeFragment(new Product_Cart_Fragment(context));
                }else if(item.getItemId() == R.id.menu_purchasehistory){
                    mainActivity.changeFragment(new Product_PurchaseHistory_Fragment(context));
                }else if(item.getItemId() == R.id.menu_customerservice){
                }
                return false;
            }
        });

        //============= ??????????????? ??????=====
        review_imagelist.add(new Review_ImageListVO());
        review_imagelist.add(new Review_ImageListVO());
        review_imagelist.add(new Review_ImageListVO());
        review_imagelist.add(new Review_ImageListVO());

        review_imagelist.get(0).setImageView(review_imgbtn1);
        review_imagelist.get(1).setImageView(review_imgbtn2);
        review_imagelist.get(2).setImageView(review_imgbtn3);
        review_imagelist.get(3).setImageView(review_imgbtn4);

        //???????????? ?????????
        checkDangerousPermissions();

        review_imgbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        review_imgbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        review_imgbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        review_imgbtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        //============= review layout =====
        Glide.with(context).load(FILE_PATH + vo.getFile_path()).into(review_img);
        if(vo.getProduct_num()>0){
            review_tv1.setText(vo.getProduct_name());
        } else {
            review_tv1.setText(vo.getPackage_name());
        }

        review_tv2.setText(NumberFormat.getInstance().format(vo.getProduct_price()) +"???");

        if(vo.getProduct_price() < 100000){
            review_tv3.setText(NumberFormat.getInstance().format(5000) +"???");
        } else {
            review_tv3.setText("??????");
        }

        review_tv4.setText(vo.getOrder_state_name());

        review_rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                rating_check = 1;
            }
        });

        review_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.changeFragment(new Product_PurchaseHistory_Fragment(context));
            }
        });

        review_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rating_check == 0){
                    Toast.makeText(context, "????????? ??????????????????.", Toast.LENGTH_SHORT).show();
                    review_rating.requestFocus();
                } else if((review_etv.getText()+"").trim().equals("") || (review_etv.getText()+"").trim().length() < 10){
                    Toast.makeText(context, "????????? 10??? ?????? ??????????????????.", Toast.LENGTH_SHORT).show();
                    review_etv.requestFocus();
                } else {
                    reviewVO.setMember_id(CommonVal.loginInfo.getMember_id());
                    reviewVO.setOrder_num(vo.getOrder_num());
                    reviewVO.setRating((int) review_rating.getRating());
                    reviewVO.setBoard_content(review_etv.getText()+"");

                    if(vo.getProduct_num() > 0){
                        reviewVO.setProduct_num(vo.getProduct_num());
                    } else {
                        reviewVO.setPackage_num(vo.getPackage_num());
                    }


                    ArrayList<String> imagelist = new ArrayList<>();
                    for (Review_ImageListVO vo:review_imagelist) {
                        if(vo.getImg_filepath() != null){
                            imagelist.add(vo.getImg_filepath());
                        }
                    }


                    reviewVO.setImagelist(imagelist);

                    int result = dao.review_intsert(reviewVO);
                    if(result > 0){
                        Intent intent = new Intent(context, Review_Result_Activity.class);
                        intent.putExtra("vo", vo);
                        startActivity(intent);
                    } else {
                        Toast.makeText(context, "???????????? ??????", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        return rootView;
    }

    //???????????? ?????????????????? ??????(OnClick) ?????? ??? ???????????? ????????? ????????? ??????,
    //?????? ???????????? ???????????? ??????
    public void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("????????? ?????? ???????????????????")
                .setSingleChoiceItems(spn_item, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        if (spn_item[i].equals("?????????")) {

                            //API ?????? ????????? ????????? ?????? ???????????? ?????????
                            //provider?????? ???????????? ????????? ????????? ??? ??? ?????? ????????? ???????????? file????????? ??????
                            go_Camera();

                        } else {
                            Intent intent = new Intent();
                            intent.setType("image/*");
                            intent.setAction(Intent.ACTION_PICK);
                            startActivityForResult(Intent.createChooser(intent, "select Pictuer"),GALLERY_IMG);
                        }

                        dialog.dismiss();
                    }
                });
        dialog = builder.create();
        dialog.show();
        imgbtn_check++;
    }

    public void go_Camera(){
        //????????? ?????? ????????? ?????? ????????? ???????????? ???????????????. (??? ?????? ????????? ????????? ??????)
        Intent cIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //????????? ????????? ???????????? ???????????? ????????? ???????????? ????????? ??? ????????? ??????
        if(cIntent.resolveActivity(mainActivity.getPackageManager()) != null){
            File imgeFile = createFile();

            if(imgeFile != null){

                Uri imgUri = FileProvider.getUriForFile(context.getApplicationContext(),
                        mainActivity.getPackageName()+".fileprovider", imgeFile);
                //????????? API 24 ???????????? ??????
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                    cIntent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);
                } else {
                    cIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imgeFile));
                }

                startActivityForResult(cIntent, CAMERA_REQ);
            }
        }
    }

    File createFile(){
        //?????? ????????? ????????? ?????? ??????.
        String timestamp = new SimpleDateFormat("yyyyMMdd HHmmss").format(new Date());

        String uid = UUID.randomUUID().toString(); //?????? ???????????? ?????? ???????????? ?????? ?????????????????? ??????(???????????? ????????? ????????? ??????)

        String imageFileName = "Review" + timestamp;

        //?????? ????????? ???????????? ?????? ??????
        File storageDir = mainActivity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File curFile = null;
        try{
            //?????? ????????? ?????????(????????????) 2?????? suffix ?????????
            curFile = File.createTempFile(imageFileName, ".jpg", storageDir);
        } catch (Exception e){

        }
        //Multipart??? ????????? ?????? ??????????????? ?????? ?????? ??????????????? ???????????? ????????? ?????????(String)
        img_filepath = curFile.getAbsolutePath(); //?????? ??????
        return curFile;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GALLERY_IMG && resultCode == mainActivity.RESULT_OK){
            Uri galleryUri = data.getData();
            img_filepath = getPathFromURI(galleryUri);
            Glide.with(context).load(galleryUri).into(review_imagelist.get(imgbtn_check-1).getImageView());
        } else if (requestCode == CAMERA_REQ && resultCode == mainActivity.RESULT_OK){
            //???????????? ????????? ???????????? ????????? Uri??? ????????? ?????? Glide??? ?????? ?????????
            //Uri gellaryAddpic????????? ?????????

            //Uri cameraUri = data.getData();
            Glide.with(context).load(img_filepath).into(review_imagelist.get(imgbtn_check-1).getImageView());
        }

        if(imgbtn_check >3){
            imgbtn_check = 3;
        } else {
            review_imagelist.get(imgbtn_check-1).setImg_filepath(img_filepath);
            review_imagelist.get(imgbtn_check).getImageView().setVisibility(View.VISIBLE);
        }

    }

    private void checkDangerousPermissions() {
        String[] permissions = {
                Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_MEDIA_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (int i = 0; i < permissions.length; i++) {
            permissionCheck = ContextCompat.checkSelfPermission(mainActivity, permissions[i]);
            if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                break;
            }
        }

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
        } else {
            Toast.makeText(mainActivity, "?????? ??????", Toast.LENGTH_LONG).show();

            if (ActivityCompat.shouldShowRequestPermissionRationale(mainActivity, permissions[0])) {
                Toast.makeText(mainActivity, "?????? ?????? ?????????.", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(mainActivity, permissions, 1);
            }
        }
    }

    public String getPathFromURI(Uri contentUri){
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cusor = mainActivity.getContentResolver().query(contentUri, proj, null, null, null);
        if(cusor.moveToFirst()){
            int column_indext = cusor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cusor.getString(column_indext);
        }
        return res;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(context, permissions[i] + " ????????? ?????????.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, permissions[i] + " ????????? ???????????? ??????.", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}