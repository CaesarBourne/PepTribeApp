package com.bourne.caesar.peptribeconcept.UI.Fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.bourne.caesar.peptribeconcept.Constants.Constants;
import com.bourne.caesar.peptribeconcept.MainActivity;
import com.bourne.caesar.peptribeconcept.Network.Connection;
import com.bourne.caesar.peptribeconcept.Network.ErrorNetworkActivity;
import com.bourne.caesar.peptribeconcept.R;
import com.bourne.caesar.peptribeconcept.Retrofit.CustomResponses.CookieTestResponse;
import com.bourne.caesar.peptribeconcept.Retrofit.RetrofitClient;
import com.bourne.caesar.peptribeconcept.Storage.SharedPreferencesManager;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFeedFragment extends Fragment {

    ProgressDialog progressDialog;
    public NewsFeedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_news_feed, container, false);
        final WebView webViewPep = view.findViewById(R.id.webpep);
        webViewPep.getSettings().setJavaScriptEnabled(true);
        final TextView shwhtml = view.findViewById(R.id.showhtml);

        String tmpHtml = "<html> </html>";
        String htmlTextStr = Html.fromHtml(tmpHtml).toString();

        webViewPep.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

                //if the network isnt avaialablehen redirect user
                if (!Connection.connectionAvaialable(getActivity()) ){
                    Intent intent = new Intent(getActivity(), ErrorNetworkActivity.class);
                    intent.putExtra(ErrorNetworkActivity.ERROR_NO_INTERNET, "Please check Internet Connection!!!");
                    startActivity(intent);

                }
                else {
                    progressDialog = new ProgressDialog(getActivity());
                    progressDialog.setMessage("PepLoading.....");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (progressDialog!= null){
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                Intent intent = new Intent(getActivity(), ErrorNetworkActivity.class);
                intent.putExtra(ErrorNetworkActivity.ERROR_NO_INTERNET, "Something went wrong");
                startActivity(intent);
            }

//            @Override
//            public boolean shouldOverrideUrlLoading(WebView vieww, WebResourceRequest request) {
//                vieww.loadUrl(request.getUrl().toString());
//                return super.shouldOverrideUrlLoading(vieww, request);
//            }
        });


//        browserCookie.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//
//                if (response.isSuccessful()){
//                    String cookie  = response.headers().get("Set-Cookie");
//                        String html = response.body().toString();
//
//                    shwhtml.setText(cookie);
//                    webViewPep.loadData(html, "text/html", "UTF-8");
//                    Toast.makeText(getActivity(), "Cokies response is succesful", Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//                shwhtml.setText(call.toString()+""+ t.toString());
//                Toast.makeText(getActivity(), "Oaga nothing they show", Toast.LENGTH_LONG).show();
//            }
//        });


//        String url = "https://www.peptribe.co/get_news_feed";
//
//        Call<ResponseBody> getnewsfeed  = RetrofitClient.getInstance().getApi().getNewsfeed(
//                Constants.VALUE_SERVER_KEY);
//
//        getnewsfeed.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//
//                if (response.isSuccessful()){
//
//                    String html = null;
//                    try {
//                        html = response.body().string();
////                        Html html1 = response.body().;
//                        shwhtml.setText(html);
////                        webViewPep.loadData(html, );
//                    } catch (IOException e) {
//
//                        e.printStackTrace();
//                    }
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//            }
//        });


        return view;

    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//
//        if ()
//  }
//    try{
//        String html = response.body().string();
//        Document document = Jsoup.parse(html);
//        Elements elements = document.select("name_of_tag_you want_to_get");
//        for (Element element:elements) {
//            if (element.attr("name_of_attribute_you want to check").equals("value_of_the_attribute")){
//                //Save As you want to
//                Log.d(TAG, " myHTMLResponseCallback : " +element.attr("value"));
//            }
//        }
//    }catch (Exception e){
//        e.printStackTrace();
//    }

}
