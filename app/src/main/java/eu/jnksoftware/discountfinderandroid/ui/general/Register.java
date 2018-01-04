package eu.jnksoftware.discountfinderandroid.ui.general;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import eu.jnksoftware.discountfinderandroid.Apis.ApiUtils;
import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.models.token.User;
import eu.jnksoftware.discountfinderandroid.services.IuserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends Activity {

    private EditText eMail;
    private EditText password;
    private EditText firstName;
    private EditText lastName;
    private String mail;
    private String fName;
    private String lName;
    private String pass;
    IuserService iuserService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button register = findViewById(R.id.registerBtn);
        iuserService= ApiUtils.getUserService();
        register.setOnClickListener(registerBtnClick);
        eMail= findViewById(R.id.eMailField);
        firstName= findViewById(R.id.firstNameField);
        lastName= findViewById(R.id.lastNameField);
        password= findViewById(R.id.passwordField);
    }

  
    private final View.OnClickListener registerBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(final View reg) {



                fName=firstName.getText().toString().trim();
                lName=lastName.getText().toString().trim();
                mail=eMail.getText().toString().trim();
                pass=password.getText().toString().trim();
                doRegister(fName,lName,mail,pass);





        }
    };
    public void doRegister(String fName,String lName,String mail,String pass){
        Call<User> call=iuserService.register(fName,lName,mail,pass);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                int statusCode=response.code();


                if(response.isSuccessful())
                {
                    Log.d("Register","onResponse:"+statusCode);
                    Toast.makeText(Register.this,""+response.message(),Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Register.this,Login.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(Register.this,""+response.message(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                call.cancel();
                Log.d("MaincActivity","onFailure"+t.getMessage());
                Toast.makeText(Register.this,"Wrong!"+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
   
}