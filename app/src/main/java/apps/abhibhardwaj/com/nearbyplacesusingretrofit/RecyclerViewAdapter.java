package apps.abhibhardwaj.com.nearbyplacesusingretrofit;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import apps.abhibhardwaj.com.nearbyplacesusingretrofit.models.Result;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

  List<Result> placeList;
  Context context;

  public RecyclerViewAdapter(Context context, List<Result> placeList) {
    this.placeList = placeList;
    this.context = context;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_single_item, viewGroup, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

    final Result place = placeList.get(i);

    viewHolder.tvPlaceName.setText(place.getName());
    viewHolder.tvPlaceAddress.setText(place.getVicinity());
    viewHolder.tvPlaceRating.setText(String.valueOf(place.getRating()));

    Picasso.get().load(place.getIcon()).into(viewHolder.imgPlaceImage);



    viewHolder.tvPlaceAddress.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent mapsActivity = new Intent(context, MapsActivity.class);

        mapsActivity.putExtra("placeName", place.getName());
        mapsActivity.putExtra("latitude", String.valueOf(place.getGeometry().getLocation().getLat()));
        mapsActivity.putExtra("longitude", String.valueOf(place.getGeometry().getLocation().getLng()));

        context.startActivity(mapsActivity);

      }
    });




  }

  @Override
  public int getItemCount() {
    return placeList.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder{

    ImageView imgPlaceImage;
    TextView tvPlaceName, tvPlaceAddress, tvPlaceRating;


    public ViewHolder(@NonNull View itemView) {
      super(itemView);

      imgPlaceImage = itemView.findViewById(R.id.iv_place_image);
      tvPlaceName = itemView.findViewById(R.id.tv_place_name);
      tvPlaceAddress = itemView.findViewById(R.id.tv_place_address);
      tvPlaceRating = itemView.findViewById(R.id.tv_place_rating);


    }
  }
}