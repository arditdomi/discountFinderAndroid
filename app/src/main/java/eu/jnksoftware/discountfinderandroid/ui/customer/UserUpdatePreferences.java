package eu.jnksoftware.discountfinderandroid.ui.customer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import eu.jnksoftware.discountfinderandroid.Apis.ApiUtils;
import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.models.Category;
import eu.jnksoftware.discountfinderandroid.models.DiscountPreferencesPostResponse;
import eu.jnksoftware.discountfinderandroid.models.DiscountPreferencesRequest;
import eu.jnksoftware.discountfinderandroid.models.UserTokenResponse;
import eu.jnksoftware.discountfinderandroid.services.IuserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserUpdatePreferences extends AppCompatActivity {

    private UserTokenResponse userTokenResponse;
    private int seekBarProgress = 0;
    private TextView showSeekProgress;
    private List<Category> categories = new ArrayList<>();
    private List<String> catTemp = new ArrayList<>();
    IuserService iuserService;
    String accessToken;
    private ArrayAdapter<String> spinContentAdapter;
    private Spinner spinnerCat;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_preferences);
        String accessToken;
        iuserService= ApiUtils.getUserService();

        Gson user = new Gson();
        userTokenResponse = user.fromJson(getIntent().getStringExtra("User"),UserTokenResponse.class);
        Toast.makeText(getApplicationContext(), "token"+userTokenResponse.getTokenType(), Toast.LENGTH_LONG).show();
        
        spinnerCat = (Spinner) findViewById(R.id.spinnerCategory);
        spinContentAdapter = new ArrayAdapter<String>(UserUpdatePreferences.this,android.R.layout.simple_list_item_1, catTemp);
        spinnerCat.getBackground().setAlpha(130);
        spinContentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCat.setAdapter(spinContentAdapter);

        fetchCategories();

        SeekBar seekBarPrice = (SeekBar) findViewById(R.id.seekBarPrice);
        showSeekProgress = (TextView) findViewById(R.id.tvSeekBarValue);
        Button savePrefButton = (Button) findViewById(R.id.btSavePreferences);
        savePrefButton.setOnClickListener(savePrefClick);

        showSeekProgress.setText("Μέχρι "+seekBarProgress + " ευρώ");
        seekBarPrice.setMax(500);
        seekBarPrice.setProgress(seekBarProgress);
        seekBarPrice.getBackground().setAlpha(200);

        seekBarPrice.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                seekBarProgress = i;
                showSeekProgress.setText("Μέχρι " + seekBarProgress + " ευρώ");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private final View.OnClickListener savePrefClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ;
            DiscountPreferencesRequest discountPreferencesRequest=new DiscountPreferencesRequest();
            discountPreferencesRequest.setCategory(String.valueOf(categories.get((int) spinnerCat.getSelectedItemId()).getId()));
            discountPreferencesRequest.setPrice(String.valueOf(seekBarProgress));
            discountPreferencesRequest.setTags("Sample");
            String auth;
            auth="Bearer "+userTokenResponse.getAccessToken();

            Toast.makeText(UserUpdatePreferences.this, discountPreferencesRequest.getCategory(), Toast.LENGTH_SHORT).show();
        }
    };

    public void doUpdateUserPreference(int id,final DiscountPreferencesRequest discountPreferencesRequest,String auth) {

        Call<DiscountPreferencesPostResponse> call =iuserService.putDiscountPreferences(id,discountPreferencesRequest,auth);
    }

    private void fetchCategories() {
        Call<List<Category>> call = iuserService.fetchCategories();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                UserUpdatePreferences.this.categories = response.body();
                List<String> temp = new ArrayList<>();
                for(int i=0;i<categories.size();i++){
                    temp.add(categories.get(i).getTitle());
                }
                UserUpdatePreferences.this.catTemp.addAll(temp);
                spinContentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(UserUpdatePreferences.this, "Failed to fetch categories", Toast.LENGTH_SHORT).show();
            }

        });
    }

}