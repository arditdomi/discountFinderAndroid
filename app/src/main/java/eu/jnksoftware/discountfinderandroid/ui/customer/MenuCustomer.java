package eu.jnksoftware.discountfinderandroid.ui.customer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

import eu.jnksoftware.discountfinderandroid.Apis.ApiUtils;
import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.models.User;
import eu.jnksoftware.discountfinderandroid.models.UserTokenResponse;
import eu.jnksoftware.discountfinderandroid.services.GeoLocation;
import eu.jnksoftware.discountfinderandroid.services.IuserService;
import eu.jnksoftware.discountfinderandroid.ui.general.AboutUs;
import eu.jnksoftware.discountfinderandroid.ui.general.Login;
import eu.jnksoftware.discountfinderandroid.ui.general.Settings;
import retrofit2.Call;

public class MenuCustomer extends AppCompatActivity {

    private GeoLocation geoLocation;
    private UserTokenResponse userTokenResponse;
    String accessToken;
    IuserService iuserService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_customer);


        Gson user = new Gson();
        userTokenResponse = user.fromJson(getIntent().getStringExtra("User"),UserTokenResponse.class);
        Toast.makeText(getApplicationContext(), "token"+userTokenResponse.getTokenType(), Toast.LENGTH_LONG).show();

        /*Bundle bundle=getIntent().getExtras();

        String accessToken;
        accessToken=bundle.getString("tokenAccess");
        Toast.makeText(MenuCustomer.this, "" +accessToken, Toast.LENGTH_LONG).show();*/




        geoLocation = new GeoLocation(this);

        if (geoLocation.canGetLocation()) {

            double latitude = geoLocation.getLatitude();
            double longitude = geoLocation.getLongitude();
            //Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        }

        Button about = (Button) findViewById(R.id.aboutBtn);
        about.setOnClickListener(aboutClick);
        Button settings = (Button) findViewById(R.id.settingsBtn);
        settings.setOnClickListener(settingsClick);
        Button myShops = (Button) findViewById(R.id.showShopsButton);
        myShops.setOnClickListener(showShopsButtonClick);
        Button filtersBtn = (Button) findViewById(R.id.filtersBtn);
        filtersBtn.setOnClickListener(filtersButtonClick);


    }

    private final View.OnClickListener showShopsButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MenuCustomer.this, SellerShops.class);
            startActivity(intent);
        }
    };

    private final View.OnClickListener shopsClick = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
            if (geoLocation.getLocation() != null) {
                try {
                    // TODO : call discountAPI to take the nearest discounts
                    MenuCustomer.this.startActivity(new Intent(MenuCustomer.this, DiscountCustomerList.class));

                } catch (Exception ex) {
                    Toast.makeText(MenuCustomer.this, ex.getMessage(), Toast.LENGTH_SHORT).show();

                }
            } else {
                Toast.makeText(MenuCustomer.this, "We don't have your location yet !", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private final View.OnClickListener aboutClick = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
            startActivity(new Intent(MenuCustomer.this, AboutUs.class));
        }
    };

    private final View.OnClickListener settingsClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button button = (Button) findViewById(R.id.showShopsButton);
            Intent intent = new Intent(MenuCustomer.this, Settings.class);
            intent.putExtra("isSellerEnabled", button.isShown());
            startActivity(intent);
        }
    };

    private final View.OnClickListener filtersButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Gson user=new Gson();
            Intent userPreferences=new Intent(MenuCustomer.this,UserPreferences.class);
            userPreferences.putExtra("User", user.toJson(userTokenResponse));
            startActivity(userPreferences);
        }
    };
}