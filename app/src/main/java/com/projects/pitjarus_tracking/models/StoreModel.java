package com.projects.pitjarus_tracking.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;
import org.parceler.Parcel;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by Sohibun Nawawi on 12/03/2021.
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Parcel(Parcel.Serialization.BEAN)
@Entity(indices = @Index(value = "id", unique = true))

public class StoreModel extends BaseModel{

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    private int id;

    @SerializedName("store_id")
    @ColumnInfo(name = "store_id")
    private String storeId;

    @SerializedName("Store_code")
    @ColumnInfo(name = "store_code")
    private String storeCode;

    @SerializedName("store_name")
    @ColumnInfo(name = "store_name")
    private String storeName;

    @SerializedName("address")
    @ColumnInfo(name = "address")
    private String address;

    @SerializedName("dc_id")
    @ColumnInfo(name = "dc_id")
    private String dcId;

    @SerializedName("dc_name")
    @ColumnInfo(name = "dc_name")
    private String dcName;

    @SerializedName("account_id")
    @ColumnInfo(name = "account_id")
    private String accountId;

    @SerializedName("account_name")
    @ColumnInfo(name = "account_name")
    private String accountName;

    @SerializedName("subchannel_id")
    @ColumnInfo(name = "subchannel_id")
    private String subchannelId;

    @SerializedName("subchannel_name")
    @ColumnInfo(name = "subchannel_name")
    private String subchannelName;

    @SerializedName("channel_id")
    @ColumnInfo(name = "channel_id")
    private String channelId;

    @SerializedName("channel_name")
    @ColumnInfo(name = "channel_name")
    private String channelName;

    @SerializedName("area_id")
    @ColumnInfo(name = "area_id")
    private String areaId;

    @SerializedName("area_name")
    @ColumnInfo(name = "area_name")
    private String areaName;

    @SerializedName("region_id")
    @ColumnInfo(name = "region_id")
    private String regionId;

    @SerializedName("region_name")
    @ColumnInfo(name = "region_name")
    private String regionName;

    @SerializedName("latitude")
    @ColumnInfo(name = "latitude")
    private String latitude;

    @SerializedName("longitude")
    @ColumnInfo(name = "longitude")
    private String longitude;

    @SerializedName("is_perfect")
    @ColumnInfo(name = "is_perfect")
    private Boolean isPerfect;

    @Override
    public JSONObject getAsJSONObject() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", getId());
            jsonObject.put("store_id", getStoreId());
            jsonObject.put("store_code", getStoreCode());
            jsonObject.put("store_name", getStoreName());
            jsonObject.put("address", getAddress());
            jsonObject.put("dc_id", getDcId());
            jsonObject.put("dc_name", getDcName());
            jsonObject.put("account_id", getAccountId());
            jsonObject.put("account_name", getAccountName());
            jsonObject.put("channel_id", getChannelId());
            jsonObject.put("channel_name", getChannelName());
            jsonObject.put("subchannel_id", getSubchannelId());
            jsonObject.put("subchannel_name", getSubchannelName());
            jsonObject.put("area_id", getAreaId());
            jsonObject.put("area_name", getAreaName());
            jsonObject.put("region_id", getRegionId());
            jsonObject.put("region_name", getRegionName());
            jsonObject.put("latitude", getLatitude());
            jsonObject.put("longitude", getLongitude());
            jsonObject.put("is_perfect", getIsPerfect());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
