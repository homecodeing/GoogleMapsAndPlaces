package am.ith.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity {
    private static final String TAG="MainActivity";
    private static final int ERROR_DIALOG_REQUEST=9001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (isServicesOK()){
            init();
        }
    }
    private void init(){
        Button btnMap=findViewById(R.id.btnMap);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,MapActivity.class);
                startActivity(intent);
            }
        });
    }
    public boolean isServicesOK(){
        Log.d(TAG,"is ServicesOK: cheching google services version");
        int avalibale= GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);
        if (avalibale== ConnectionResult.SUCCESS){
            //everything is fine and the user can make map request
            Log.d(TAG,"isServices OK: Google services is working");
            return true;
        }else if(GoogleApiAvailability.getInstance().isUserResolvableError(avalibale)) {
             //an error accures but can resolve it
            Log.d(TAG,"isServices OK: Google services is not working");
              Dialog dialog=GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this,avalibale,ERROR_DIALOG_REQUEST);
              dialog.show();
        }else {
            Toast.makeText(this, "We can not make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
