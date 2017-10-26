package eu.jnksoftware.discountfinderandroid.ui.customer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.api.APIConfig;
import eu.jnksoftware.discountfinderandroid.api.IAPI;
import eu.jnksoftware.discountfinderandroid.api.ShopsAPI;
import eu.jnksoftware.discountfinderandroid.services.GeoLocation;
import eu.jnksoftware.discountfinderandroid.services.Network;
import eu.jnksoftware.discountfinderandroid.ui.general.AboutUs;
import eu.jnksoftware.discountfinderandroid.ui.general.Shops;

public class MenuCustomer extends AppCompatActivity {

    private GeoLocation geoLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        geoLocation = new GeoLocation(this);

        if (geoLocation.canGetLocation()) {

            double latitude = geoLocation.getLatitude();
            double longitude = geoLocation.getLongitude();

            // \n is for new line
            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        }

        Button shops = (Button) findViewById(R.id.showShopsBtn);
        shops.setOnClickListener(shopsClick);
        Button about = (Button) findViewById(R.id.aboutBtn);
        about.setOnClickListener(aboutClick);

    }

    private final View.OnClickListener shopsClick = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
            if (geoLocation.getLocation() != null) {
                try {
                    // Ask API for Shops
                    Network network = new Network(APIConfig.APILinkReal + "shop/");
                    network.call();
                    eu.jnksoftware.discountfinderandroid.models.Shops shops = new eu.jnksoftware.discountfinderandroid.models.Shops();
                    // Parse the result

                    IAPI shopsAPI = new ShopsAPI();
                    if (shopsAPI.load(network.getResult(), geoLocation.getLocation())) {
                        //noinspection unchecked
                        shops = new eu.jnksoftware.discountfinderandroid.models.Shops(shopsAPI.getList());
                    }

                    Intent intent = new Intent(MenuCustomer.this, Shops.class);
                    intent.putExtra("shopsList", shops);
                    startActivity(intent);

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


}