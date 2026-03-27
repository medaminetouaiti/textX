package devesh.app.ocr;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;


import devesh.app.ocr.databinding.SettingsActivityBinding;
import devesh.app.ocr.utils.CachePref;
import devesh.app.ocr.utils.InstallSource;

public class SettingsActivity extends AppCompatActivity {
    SettingsActivityBinding binding;
    AdMobAPI adMobAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = SettingsActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        adMobAPI = new AdMobAPI(this);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(binding.settings.getId(), new SettingsFragment())
                    .commit();


        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        adMobAPI.setAdaptiveBanner(binding.AdFrameLayout, this);
    }


    public static class SettingsFragment extends PreferenceFragmentCompat {
        String TAG = "settings";
        CachePref cachePref;
        final String[] LanguageOptionsFull = {"Default (English)", "Devanagari देवनागरी", "Japanese 日本", "Korean 한국인", "Chinese 中國人"};

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
            cachePref = new CachePref(getActivity());

            Preference PrefRateApp = findPreference("ratekey");
            PrefRateApp.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                public boolean onPreferenceClick(Preference preference) {
                    Log.d(TAG, "onPreferenceClick: ");
                    String url = "";
                    if (InstallSource.getInstallSource(getActivity()).equals(InstallSource.GOOGLE_PLAY_STORE)) {
                        url = getString(R.string.PLAY_STORE_URL);
                    } else if (InstallSource.getInstallSource(getActivity()).equals(InstallSource.SAMSUNG_APP_STORE)) {
                        url = getString(R.string.GALAXY_STORE_URL);
                    } else {
                        url = getString(R.string.PLAY_STORE_URL);
                    }
                    Uri uri = Uri.parse(url);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);

                    return true;
                }
            });


            Preference SecPri = findPreference("secpri");
            SecPri.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                public boolean onPreferenceClick(Preference preference) {
                    Log.d(TAG, "onPreferenceClick: ");
                    String url = "https://www.ephrine.in/privacy-policy";

                    Uri uri = Uri.parse(url);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);

                    return true;
                }
            });

            String d = cachePref.getString("ocrlang");
            Preference OCRLanguage = findPreference("ocrlang");
            if (d != null) {
                int i = Integer.parseInt(d);
                OCRLanguage.setSummary(LanguageOptionsFull[i]);
            } else {
                OCRLanguage.setSummary(LanguageOptionsFull[0]);
            }

            OCRLanguage.setOnPreferenceChangeListener((preference, newValue) -> {
                int i = Integer.parseInt(newValue.toString());
                preference.setSummary(LanguageOptionsFull[i]);
                return true;
            });


            Preference PrefAppUpdate = findPreference("appupdate");
            PrefAppUpdate.setOnPreferenceClickListener(preference -> {
                String url = getString(R.string.PLAY_STORE_URL);

                if (InstallSource.isGalaxyStore(getActivity())) {
                    url = getString(R.string.GALAXY_STORE_URL);
                }

                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

                return true;

            });

        }

    }
}
