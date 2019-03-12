package apps.abhibhardwaj.com.nearbyplacesusingretrofit;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import apps.abhibhardwaj.com.nearbyplacesusingretrofit.models.NearbyPlacesModel;
import apps.abhibhardwaj.com.nearbyplacesusingretrofit.models.Result;
import apps.abhibhardwaj.com.nearbyplacesusingretrofit.rest.ApiClient;
import apps.abhibhardwaj.com.nearbyplacesusingretrofit.rest.ApiInterface;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements OnClickListener {

  Button btnHospitals, btnCafe, btnATMs;
  RecyclerView recyclerView;
  ProgressDialog progressDialog;

  final static String API_KEY = "AIzaSyB0mzGO2Yn9fl8RCzWFFCdp8_6zfz9Rerc";

  ApiInterface service;

  RecyclerViewAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    progressDialog = new ProgressDialog(this);

    btnHospitals = findViewById(R.id.btn_hospitals);
    btnATMs = findViewById(R.id.btn_atms);
    btnCafe = findViewById(R.id.btn_cafe);


    btnHospitals.setOnClickListener(this);
    btnATMs.setOnClickListener(this);
    btnCafe.setOnClickListener(this);


    RecyclerView.LayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, LinearLayout.VERTICAL);

    recyclerView = findViewById(R.id.recycler_view);
    recyclerView.setLayoutManager(manager);
    recyclerView.addItemDecoration(dividerItemDecoration);

    progressDialog.setMessage("Searching Places...");

  }

  @Override
  public void onClick(View v) {

    switch (v.getId()) {
      case R.id.btn_hospitals: {
        showNearby("hospital");
        break;
      }
      case R.id.btn_atms: {
        showNearby("atm");
        break;
      }
      case R.id.btn_cafe: {
        showNearby("cafe");
        break;
      }
    }
  }

  private void showNearby(String types) {
    progressDialog.show();

    service = ApiClient.getRetrofitInstance().create(ApiInterface.class);

    String location = 30.7168 + ", " + 76.6947;
    Call<NearbyPlacesModel> call = service.getNearbyPlace(location, 3000, types, API_KEY);

    call.enqueue(new Callback<NearbyPlacesModel>() {
      @Override
      public void onResponse(Call<NearbyPlacesModel> call, Response<NearbyPlacesModel> response) {
        progressDialog.dismiss();
        List<Result> placeList = response.body().getResults();
        adapter = new RecyclerViewAdapter(MainActivity.this, placeList);
        recyclerView.setAdapter(adapter);
      }

      @Override
      public void onFailure(Call<NearbyPlacesModel> call, Throwable t) {
        progressDialog.dismiss();
        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
      }
    });





  }
}
