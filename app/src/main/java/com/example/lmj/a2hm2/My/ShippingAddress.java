package com.example.lmj.a2hm2.My;

/**
 * Created by lmj on 2016/9/24.
 */
public class ShippingAddress {
    private String receiver_name;
    private String receiver_phone;
    private String zip_code;
    private String province;
    private String city;
    private String area;
    private String detailed_address;
    private String objectId;
    private boolean isDefault;


    ShippingAddress(String receiver_name,String receiver_phone,String zip_code,
                           String province,String city,String area,String detailed_address,String objectId,boolean isDefault){
        this.receiver_name=receiver_name;
        this.receiver_phone=receiver_phone;
        this.zip_code=zip_code;
        this.province=province;
        this.city=city;
        this.area=area;
        this.detailed_address=detailed_address;
        this.objectId=objectId;
        this.isDefault=isDefault;
    }
    public boolean getIsDefault(){
        return isDefault;
    }
    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getReceiver_name() {
        return receiver_name;
    }

    public void setReceiver_name(String receiver_name) {
        this.receiver_name = receiver_name;
    }

    public String getReceiver_phone() {
        return receiver_phone;
    }

    public void setReceiver_phone(String receiver_phone) {
        this.receiver_phone = receiver_phone;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDetailed_address() {
        return detailed_address;
    }

    public void setDetailed_address(String detailed_address) {
        this.detailed_address = detailed_address;
    }

}
