package com.example.newlastproject.member;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.newlastproject.R;
import com.example.newlastproject.async.AskParam;
import com.example.newlastproject.async.CommonAsk;
import com.example.newlastproject.async.CommonMethod;
import com.google.gson.Gson;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class JoinActivity extends AppCompatActivity {
    Button btn_ok, btn_cancle;
    EditText edt_id, edt_pw, edt_name;
    ImageView imgv_profile;
    AlertDialog dialog;
    final int GALLERY_IMG = 1001;
    final int CAMERA_REQ = 1002;
    String img_filepath; //이미지 경로 저장하기 위한 String 변수.



    //Spinner spn_time
    String[] spn_item = {"카메라", "겔러리"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        //카메라를 사용하거나 갤러리를 사용하기 위해서는 권한이 피룡함.
        //권한을 요청하는 메소드와 Manifest에 기재된 permission을 이용해서 사용
        // ex) 인터넷 권한 -> 매니페스트에 써놓으면 가능
        // ex) 카메라 권한 -> 매니패스트에 쓰곡 사용자 동의 필요
        //1.메니페스트에 Perimssion 어떤 권한을 사용할지 기재
        //2.별도의 권한 동의가 필요하다면 checkDangerous메소드 이용해서 사용자에게 동의 구함
        imgv_profile = findViewById(R.id.join_image);
        edt_id = findViewById(R.id.join_edtid);
        edt_pw = findViewById(R.id.join_edtpw);
        edt_name = findViewById(R.id.join_edtname);

        //권한요청 메소드
        checkDangerousPermissions();

        imgv_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        findViewById(R.id.join_btnedit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MemberVO를 만들고 Spring도 MemberVO -> Mapper 추가해서 DB에 입력
                //VO를 보내서 인서트하게 만듬

                MemberVO vo = new MemberVO();
                vo.setId(edt_id.getText()+"");
                vo.setPw(edt_pw.getText()+"");

                Gson gson = new Gson();

                CommonAsk commonAsk = new CommonAsk("join");
                commonAsk.params.add(new AskParam("vo", gson.toJson(vo)));
                commonAsk.fileprams.add(new AskParam("file", img_filepath));
                CommonMethod.excuteAsk(commonAsk);
                finish();
            }
        });


    }

    //사용자가 프로필사진을 클릭(OnClick) 했을 때 카메라로 사진을 추가할 건지,
    //또는 갤러리로 추가할지 선택
    public void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
        builder.setTitle("사진을 어떤 것으로 추가 하시겠습니까?")
                .setSingleChoiceItems(spn_item, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        if (spn_item[i].equals("카메라")) {
                            Toast.makeText(JoinActivity.this, "카메라 선택됨", Toast.LENGTH_SHORT).show();

                            //API 요즘 버전은 카메라 관련 서비스가 복잡함
                            //provider라는 카메라의 사진을 가지고 올 수 있는 객체도 필요하고 file객체도 필요
                            go_Camera();

                        } else {
                            Toast.makeText(JoinActivity.this, "갤러리 선택됨", Toast.LENGTH_SHORT).show();
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
    }

    public void go_Camera(){
        //카메라 앱을 실행할 때는 파일을 만들어서 보내줘야함. (그 파일 형태에 이미지 채움)
        Intent cIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //권한을 가지고 있으면서 해당하는 인텐트 서비스를 사용할 수 있는지 체크
        if(cIntent.resolveActivity(getPackageManager()) != null){
            File imgeFile = createFile();

            if(imgeFile != null){

                Uri imgUri = FileProvider.getUriForFile(getApplicationContext(),
                        JoinActivity.this.getPackageName()+".fileprovider", imgeFile);

                //만약에 API 24 이상이면 체크
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
        //파일 이름을 만들기 위한 처리.
        String timestamp = new SimpleDateFormat("yyyyMMdd HHmmss").format(new Date());

        String uid = UUID.randomUUID().toString(); //어떤 파일이나 값을 식별하기 위한 고유식별자로 사용(중복값이 나오면 안되는 경우)

        String imageFileName = "My" + timestamp;

        //사진 파일을 저장하기 위한 경로
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File curFile = null;
        try{
            //임시 파일을 생성함(전체경로) 2번째 suffix 확장자
            curFile = File.createTempFile(imageFileName, ".jpg", storageDir);
        } catch (Exception e){

        }

        //Multipart에 보내기 위한 임시파일이 있는 곳의 절대경로를 저장하는 로직이 필요함(String)
        img_filepath = curFile.getAbsolutePath(); //절대 경로

        return curFile;


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GALLERY_IMG && resultCode == RESULT_OK){
            Toast.makeText(JoinActivity.this, "이미지가져오기 성공", Toast.LENGTH_SHORT).show();
            Uri galleryUri = data.getData();
            img_filepath = getPathFromURI(galleryUri);
            Glide.with(JoinActivity.this).load(galleryUri).into(imgv_profile);
        } else if (requestCode == CAMERA_REQ && resultCode == RESULT_OK){
            Toast.makeText(JoinActivity.this, "사진을 찍어서 가지고 옴", Toast.LENGTH_SHORT).show();

            //갤러리에 사진을 저장하고 저장한 Uri를 통해서 다시 Glide를 통해 붙이기
            //Uri gellaryAddpic메소드 하기전

            //Uri cameraUri = data.getData();
            Glide.with(JoinActivity.this).load(img_filepath).into(imgv_profile);

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
            permissionCheck = ContextCompat.checkSelfPermission(this, permissions[i]);
            if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                break;
            }
        }

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "권한 있음", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "권한 없음", Toast.LENGTH_LONG).show();

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0])) {
                Toast.makeText(this, "권한 설명 필요함.", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(this, permissions, 1);
            }
        }
    }

    public String getPathFromURI(Uri contentUri){
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cusor = getContentResolver().query(contentUri, proj, null, null, null);
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
                    Toast.makeText(this, permissions[i] + " 권한이 승인됨.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, permissions[i] + " 권한이 승인되지 않음.", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}