package org.sample.capstone.helper;

import java.util.List;

import org.sample.capstone.HibernateProxyTypeAdapter;
import org.sample.capstone.entity.AssetDetail;
import org.sample.capstone.model.AssetDetailModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class AssetDetailUtil {

	public static AssetDetailModel copyAssetDetailToAssetDetailModel(AssetDetail assetDetail) {
	    GsonBuilder b = new GsonBuilder();
	    b.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
	    Gson gson = b.create();
	    String text = gson.toJson(assetDetail);
	    AssetDetailModel newObject = gson.fromJson(text, AssetDetailModel.class);
	    return newObject;
	}
	
	public static AssetDetail copyAssetDetailModelToAssetDetail(AssetDetailModel assetDetailModel) {
	    GsonBuilder b = new GsonBuilder();
	    b.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
	    Gson gson = b.create();
	    String text = gson.toJson(assetDetailModel);
	    AssetDetail newObject = gson.fromJson(text, AssetDetail.class);
	    return newObject;
	}
	
	public static List<AssetDetailModel> copyAssetDetailsToAssetDetailModels(List<AssetDetail> list) {
	    GsonBuilder b = new GsonBuilder();
	    b.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
	    Gson gson = b.create();
	    String text = gson.toJson(list);
	    TypeToken<List<AssetDetailModel>> token = new TypeToken<List<AssetDetailModel>>() {};
	    List<AssetDetailModel> newObject = gson.fromJson(text, token.getType());
	    return newObject;
	}
	
	public static List<AssetDetail> copyAssetDetailModelsToAssetDetails(List<AssetDetailModel> assetDetailModels) {
	    GsonBuilder b = new GsonBuilder();
	    b.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
	    Gson gson = b.create();
	    String text = gson.toJson(assetDetailModels);
	    TypeToken<List<AssetDetail>> token = new TypeToken<List<AssetDetail>>() {};
	    List<AssetDetail> newObject = gson.fromJson(text, token.getType());
	    return newObject;
	}
}
