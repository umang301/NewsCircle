package com.protoplus.newscircle.CustomLists;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeAppInstallAdView;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.NativeContentAdView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.appodeal.ads.Appodeal;
import com.protoplus.newscircle.Bean.SportChildSceneBean;
import com.protoplus.newscircle.R;
import com.protoplus.newscircle.Util.Helper;
import com.protoplus.newscircle.Util.ImageLoader;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Sports extends ArrayAdapter<SportChildSceneBean> {
    Context context;
    SportChildSceneBean bean;
    int resource;
    View advertiseView;
    private static final String ADMOB_AD_UNIT_ID = "ca-app-pub-3940256099942544/2247696110";

    LayoutInflater inflater;

    public ImageLoader imageLoader;
    ArrayList<SportChildSceneBean> object;

    public Sports(Context context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    public Sports(Context context, int resource,
                  ArrayList<SportChildSceneBean> objects) {

        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.object = objects;

        imageLoader = new ImageLoader(context);
    }

    /*@Override
    public int getCount() {
        if (object == null) {
            return 0;
        } else return object.size();
    }*/

  /*  @Override
    public int getViewTypeCount() {
        return 2;
    }
*/
    /*@Override
    public int getItemViewType(int position) {
        if(position%10==0){
            return 1;
        }
        else{
            return 0;
        }
    }
*/
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        System.out.println("_______________________ position : " + position);
        /*if(position==0){
            if(advertiseView==null){
                refreshData();
            }
            return advertiseView;
        } else {*/
            bean = object.get(position);
            SportsHolder holder = new SportsHolder(bean.imageLink, bean.title, bean.postId, bean.description, bean.pubDate);
            if (row == null) {
                row = inflater.inflate(R.layout.sports_child_scene, null);
                holder.imageIcon = (ImageView) row.findViewById(R.id.contentImage);
                holder.txtTitle = (TextView) row.findViewById(R.id.title);
                holder.txtTitle.setTextColor(Color.BLACK);
                holder.txtDateTime = (TextView) row.findViewById(R.id.postDate);
                row.setTag(holder);
            } else {
                holder = (SportsHolder) row.getTag();
            }
            try {
                holder.txtTitle.setText(android.text.Html.fromHtml(bean.title));
                String pubdate = bean.pubDate;
                holder.txtDateTime.setText(pubdate);
                holder.txtDateTime.setTextColor(Color.GRAY);
                ImageView imageView = holder.imageIcon;
                imageLoader.DisplayImage(bean.imageLink, imageView);

            } catch (Exception e1) {
                e1.printStackTrace();
            }
            return row;
        //}
    }


        /*private void refreshData(){
		AdLoader.Builder builder = new AdLoader.Builder(context, ADMOB_AD_UNIT_ID);
        System.out.println("THe Bulder is :::::::::::::::::::: \n" + builder);
		//if (requestAppInstallAds) {
		builder.forAppInstallAd(new NativeAppInstallAd.OnAppInstallAdLoadedListener() {
			@Override
			public void onAppInstallAdLoaded(NativeAppInstallAd ad) {
                System.out.println("________________THE AD IS LOADED__________");
				NativeAppInstallAdView adView = (NativeAppInstallAdView) inflater
						.inflate(R.layout.adinstalllistview_item, null);
				populateInstallAdView(ad, adView);
				advertiseView = adView;
			}
		}).forContentAd(new NativeContentAd.OnContentAdLoadedListener() {
            @Override
            public void onContentAdLoaded(NativeContentAd ad) {
                System.out.println("________________THE AD IS LOADED__________");
                NativeContentAdView adView = (NativeContentAdView) inflater
                        .inflate(R.layout.adcontentlistview_item, null);
                populateContentAdView(ad, adView);
                advertiseView = adView;
            }
        });

		AdLoader adLoader = builder.withAdListener(new AdListener() {
			@Override
			public void onAdFailedToLoad(int errorCode) {
				System.out.println("__________________________THE AD LOADING FAILED");
			}
		}).build();
        System.out.println("_______________AD LOADER IS _____________"+adLoader);
		adLoader.loadAd(new AdRequest.Builder().build());
	}
	private void populateContentAdView(NativeContentAd ad,NativeContentAdView adView){
		adView.setHeadlineView(adView.findViewById(R.id.tvHeader));
		adView.setBodyView(adView.findViewById(R.id.tvDescription));
		adView.setAdvertiserView(adView.findViewById(R.id.tvAdvertiser));
		adView.setLogoView(adView.findViewById(R.id.ivLogo));
        adView.setCallToActionView(adView.findViewById(R.id.btnAction));
        adView.setImageView(adView.findViewById(R.id.ivImage));

		((TextView)adView.getHeadlineView()).setText(ad.getHeadline());
		((TextView)adView.getBodyView()).setText(ad.getBody());
		((TextView)adView.getAdvertiserView()).setText(ad.getAdvertiser());

        if(ad.getLogo()!=null)
        ((ImageView) adView.getLogoView()).setImageDrawable(ad.getLogo().getDrawable());

        ((Button) adView.getCallToActionView()).setText(ad.getCallToAction());

        if(ad.getImages()!=null && ad.getImages().size()>0)
            ((ImageView) adView.getImageView()).setImageDrawable(ad.getImages().get(0).getDrawable());
        NativeAd.Image logoImage = ad.getLogo();
		if(logoImage != null){
			((ImageView) adView.getLogoView()).setImageDrawable(logoImage.getDrawable());
		}
		adView.setNativeAd(ad);
	}
	private void populateInstallAdView(NativeAppInstallAd ad,NativeAppInstallAdView adView){
		adView.setHeadlineView(adView.findViewById(R.id.tvHeader));
		adView.setBodyView(adView.findViewById(R.id.tvDescription));
		//adView.setStarRatingView(adView.findViewById(R.id.install_ad_ratingbar));
		adView.setIconView(adView.findViewById(R.id.ivLogo));
		adView.setCallToActionView(adView.findViewById(R.id.btnAction));
        adView.setStoreView(adView.findViewById(R.id.tvStore));
        adView.setPriceView(adView.findViewById(R.id.tvPrice));
        adView.setImageView(adView.findViewById(R.id.ivImage));

        ((TextView)adView.getHeadlineView()).setText(ad.getHeadline());
		((TextView)adView.getBodyView()).setText(ad.getBody());
		((Button)adView.getCallToActionView()).setText(ad.getCallToAction());
        //((RatingBar)adView.getStarRatingView()).setRating(ad.getStarRating().floatValue());
		if(ad.getIcon()!=null)
            ((ImageView)adView.getIconView()).setImageDrawable(ad.getIcon().getDrawable());
        ((TextView)adView.getStoreView()).setText(ad.getStore());
        ((TextView)adView.getPriceView()).setText(ad.getPrice());
        if(ad.getImages()!=null && ad.getImages().size()>0)
            ((ImageView)adView.getImageView()).setImageDrawable(ad.getImages().get(0).getDrawable());

		adView.setNativeAd(ad);
	}
*/

    public static class SportsHolder {
        ImageView imageIcon;
        TextView txtTitle;
        TextView txtDateTime;
        public String title, description, imageLink, postId, publisherDate;

        public SportsHolder(String ImageLink, String title, String postId, String description, String pubDate) {
            this.title = title;
            this.imageLink = ImageLink;
            this.description = description;
            this.postId = postId;
            this.publisherDate = pubDate;
        }

    }
}
