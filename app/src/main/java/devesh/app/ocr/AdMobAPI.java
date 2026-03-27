package devesh.app.ocr;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.MuteThisAdListener;
import com.google.android.gms.ads.MuteThisAdReason;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.nativead.MediaView;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdOptions;
import com.google.android.gms.ads.nativead.NativeAdView;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.android.ump.ConsentForm;
import com.google.android.ump.ConsentInformation;
import com.google.android.ump.ConsentRequestParameters;
import com.google.android.ump.UserMessagingPlatform;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import devesh.app.ocr.firebase.RemoteConfig;
import devesh.app.ocr.utils.CachePref;


public class AdMobAPI {
    Context mContext;
    String TAG = "AdMobAPI";
    boolean isAdsEnabled;
    Activity mActivity;
    RemoteConfig remoteConfig;
    private InterstitialAd mInterstitialAd;
    private RewardedAd mRewardedAd;

    CachePref cachePref;
    ConsentInformation consentInformation;
    AtomicBoolean isMobileAdsInitializeCalled;

    public AdMobAPI(Activity activity) {
        mContext = activity.getApplicationContext();
        remoteConfig = new RemoteConfig(activity);
        cachePref = new CachePref(activity);

        // Ads disabled globally
        isAdsEnabled = false;

        isMobileAdsInitializeCalled = new AtomicBoolean(false);
    }

    void initializeMobileAdsSdk(){
        // Do nothing as ads are disabled
    }

    public void setAdBannerTo(AdView adView) {
        adView.setVisibility(View.GONE);
    }

    public void setAdaptiveBanner(AdView adView, Activity activity) {
        adView.setVisibility(View.GONE);
    }

    public void setAdaptiveBanner(FrameLayout adContainerView, Activity activity) {
        adContainerView.setVisibility(View.GONE);
    }

    public void LoadInterstitialAd(Activity activity) {
        // Do nothing as ads are disabled
    }

    public void ShowInterstitialAd() {
        // Do nothing as ads are disabled
    }

    private AdSize getAdSize(Activity activity) {
        return AdSize.BANNER;
    }

    public void InitRewardedAd(Activity activity) {
        // Do nothing as ads are disabled
    }

    public void ShowRewardedAd() {
        // Do nothing as ads are disabled
    }

    // Native Ads
    NativeAd nativeAd;
    AdLoader adLoader;

    public void loadNativeAd(FrameLayout frameLayout, Activity activity) {
        frameLayout.setVisibility(View.GONE);
    }

    private void populateUnifiedNativeAdView(NativeAd nativeAd, NativeAdView adView) {
        // Do nothing
    }

    private void enableCustomMuteWithReasons(List<MuteThisAdReason> reasons) {
        // Do nothing
    }

    private void hideCustomMute() {
        // Do nothing
    }

    private void muteAdDialogDidSelectReason(MuteThisAdReason reason) {
        // Do nothing
    }

    private void muteAd() {
        // Do nothing
    }

    public void DestroyAds() {
        if (nativeAd != null) {
            nativeAd.destroy();
        }
    }
}
