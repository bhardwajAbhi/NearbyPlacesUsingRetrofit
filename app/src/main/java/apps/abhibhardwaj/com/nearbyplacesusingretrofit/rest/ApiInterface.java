package apps.abhibhardwaj.com.nearbyplacesusingretrofit.rest;

import apps.abhibhardwaj.com.nearbyplacesusingretrofit.models.NearbyPlacesModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

  @GET("json")
  Call<NearbyPlacesModel> getNearbyPlace (@Query("location") String location,
                                          @Query("radius") int radius,
                                          @Query("types") String types,
                                          @Query("key") String APIkey);

}


/*
*
*     https://maps.googleapis.com/maps/api/place/nearbysearch/json
  ?location=-33.8670522,151.1957362
  &radius=500
  &types=food
  &key=YOUR_API_KEY




*
*
* */