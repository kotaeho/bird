package com.grandra.birds;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Description extends AppCompatActivity {

    private static final String SERVICE_KEY = "SEyTD6N9tYDcENXw1Yd08q3Snfv%2BdPJOGaXAv74WZInaJlTQ3ZAGiEbcb4PbpVOwH7y5WEuEoTHmP1GmlT6%2B6w%3D%3D";

    private ImageView imageView;
    private String bird_num;
    private String des_imageUrl;
    private Context context;

    private FungiApiClient fungiApiClient;
    private AdView mAdview; //애드뷰 변수 선언

    private TextView title;

    private TextView anmlClsEngNm;
    private TextView anmlClsKorNm;
    private TextView anmlFmlyEngNm;
    private TextView anmlFmlyKorNm;
    private TextView anmlGenusEngNm;
    private TextView anmlGenusKorNm;
    private TextView anmlGnrlNm;
    private TextView anmlOrdEngNm;
    private TextView anmlOrdKorNm;
    private TextView anmlPhlmEngNm;
    private TextView anmlPhlmKorNm;
    private TextView anmlScnm;
    private TextView anmlSmplNo;
    private TextView anmlSpecsId;
    private TextView cprtCtnt;
    private TextView eclgDpftrCont;
    private TextView gnrlSpftrCont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.description);

        this.Init();
        this.image();

        MobileAds.initialize(this, new OnInitializationCompleteListener() { //광고 초기화
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdview = findViewById(R.id.des_adView); //배너광고 레이아웃 가져오기
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdview.loadAd(adRequest);
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER); //광고 사이즈는 배너 사이즈로 설정
        adView.setAdUnitId("\n" + "ca-app-pub-4268507364131475/9380769918");

        // Retrofit 및 API 클라이언트 초기화
        fungiApiClient = new FungiApiClient();

        // API 호출
        Call<FungiResponse> call = fungiApiClient.createService().getFungiInfo(
                "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36",
                "_ga=GA1.1.1580580713.1692755799; _ga_7TYF9K5TBJ=GS1.1.1692930264.16.0.1692930264.0.0.0",
                "SEyTD6N9tYDcENXw1Yd08q3Snfv+dPJOGaXAv74WZInaJlTQ3ZAGiEbcb4PbpVOwH7y5WEuEoTHmP1GmlT6+6w==",
                bird_num
        );
        call.enqueue(new Callback<FungiResponse>() {
            @Override
            public void onResponse(@NonNull Call<FungiResponse> call, @NonNull Response<FungiResponse> response) {

                if (response.isSuccessful()) {
                    FungiResponse fungiResponse = response.body();
                    if (fungiResponse != null) {
                        FungiItem fungiItem = fungiResponse.getBody().getItem();
                        title.setText(fungiItem.getanmlGnrlNm());
                        anmlClsEngNm.setText(fungiItem.getanmlClsEngNm());
                        anmlClsKorNm.setText(fungiItem.getanmlClsKorNm());
                        anmlFmlyEngNm.setText(fungiItem.getanmlFmlyEngNm());
                        anmlFmlyKorNm.setText(fungiItem.getanmlFmlyKorNm());
                        anmlGenusEngNm.setText(fungiItem.getanmlGenusEngNm());
                        anmlGenusKorNm.setText(fungiItem.getanmlGenusKorNm());
                        anmlGnrlNm.setText(fungiItem.getanmlGnrlNm());
                        anmlOrdEngNm.setText(fungiItem.getanmlOrdEngNm());
                        anmlOrdKorNm.setText(fungiItem.getanmlOrdKorNm());
                        anmlPhlmEngNm.setText(fungiItem.getanmlPhlmEngNm());
                        anmlPhlmKorNm.setText(fungiItem.getanmlPhlmKorNm());
                        anmlScnm.setText(fungiItem.getanmlScnm());
                        anmlSmplNo.setText(fungiItem.getanmlSmplNo());
                        anmlSpecsId.setText(fungiItem.getanmlSpecsId());
                        cprtCtnt.setText(fungiItem.getcprtCtnt());
                        eclgDpftrCont.setText(fungiItem.geteclgDpftrCont());
                        gnrlSpftrCont.setText(fungiItem.getgnrlSpftrCont());

                    }
                } else {
                    // API 호출 실패 처리
                    ResponseBody errorBody = response.errorBody();
                    if (errorBody != null) {
                        try {
                            String errorResponse = errorBody.string();
                            Log.e("API Error", "Error Response: " + errorResponse);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Log.e("API Error", "Unknown error occurred.");
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<FungiResponse> call, @NonNull Throwable t) {
                // 네트워크 오류 처리
                Log.e("MainActivity", "Network error: " + t.getMessage());
            }
        });
    }

    private void Init() {
        title = findViewById(R.id.bird_title);
        imageView = findViewById(R.id.des_imageView);
        bird_num = getIntent().getStringExtra("bird_num");
        des_imageUrl = getIntent().getStringExtra("bird_image");

        anmlClsEngNm = findViewById(R.id.anmlClsEngNm);
        anmlClsKorNm = findViewById(R.id.anmlClsKorNm);
        anmlFmlyEngNm = findViewById(R.id.anmlFmlyEngNm);
        anmlFmlyKorNm = findViewById(R.id.anmlFmlyKorNm);
        anmlGenusEngNm = findViewById(R.id.anmlGenusEngNm);
        anmlGenusKorNm = findViewById(R.id.anmlGenusKorNm);
        anmlGnrlNm = findViewById(R.id.anmlGnrlNm);
        anmlOrdEngNm = findViewById(R.id.anmlOrdEngNm);
        anmlOrdKorNm = findViewById(R.id.anmlOrdKorNm);
        anmlPhlmEngNm = findViewById(R.id.anmlPhlmEngNm);
        anmlPhlmKorNm = findViewById(R.id.anmlPhlmKorNm);
        anmlScnm = findViewById(R.id.anmlScnm);
        anmlSmplNo = findViewById(R.id.anmlSmplNo);
        anmlSpecsId = findViewById(R.id.anmlSpecsId);
        cprtCtnt = findViewById(R.id.cprtCtnt);
        eclgDpftrCont = findViewById(R.id.eclgDpftrCont);
        gnrlSpftrCont = findViewById(R.id.gnrlSpftrCont);
    }

    private void image(){
        RequestOptions requestOptions = new RequestOptions()
                .transform(new RoundedCorners(20)) // 둥글게 처리를 위한 RoundedCorners 변환 적용
                .diskCacheStrategy(DiskCacheStrategy.ALL); // 디스크 캐싱 전략 설정

        Glide.with(this)
                .load(des_imageUrl)
                .apply(requestOptions)
                .transition(DrawableTransitionOptions.withCrossFade()) // 이미지 로딩 시 CrossFade 효과 적용
                .diskCacheStrategy(DiskCacheStrategy.ALL) // 디스크 캐싱 전략 설정
                .into(imageView);
    }
}