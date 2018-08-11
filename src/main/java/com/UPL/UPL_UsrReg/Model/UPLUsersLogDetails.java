package com.UPL.UPL_UsrReg.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="USER_LOG_DETAILS")
public class UPLUsersLogDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="log_id",nullable = false)
    private int log_id;

    private String captured_img_base64;

    @Lob
    @Column(name="captured_img",columnDefinition="BLOB")
    private String captured_img;

    @Column(name="botanical_img_name")
    private String botanical_img_name;

    @Column(name="regional_name")
    private String regional_name;

    @Column(name="product")
    private String product;

    @Column(name="dosage")
    private String dosage;

    @Column(name="distribution")
    private String distribution;

    @Column(name="coupen")
    private String coupen;

    @Column(name="has_coupen")
    private boolean has_coupen;

    @DateTimeFormat
    @Column(name="date_time")
    private Timestamp date_time;

    @Column(name="latitude")
    private long latitude;

    @Column(name="longitude")
    private long longitude;

    @Column(name="user_id",nullable = false)
    private int user_id;

    public int getLog_id() {
        return log_id;
    }

    public void setLog_id(int log_id) {
        this.log_id = log_id;
    }

    public String getBotanical_img_name() {
        return botanical_img_name;
    }

    public void setBotanical_img_name(String botanical_img_name) {
        this.botanical_img_name = botanical_img_name;
    }

    public String getRegional_name() {
        return regional_name;
    }

    public void setRegional_name(String regional_name) {
        this.regional_name = regional_name;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getDistribution() {
        return distribution;
    }

    public void setDistribution(String distribution) {
        this.distribution = distribution;
    }

    public String getCoupen() {
        return coupen;
    }

    public void setCoupen(String coupen) {
        this.coupen = coupen;
    }

    public boolean isHas_coupen() {
        return has_coupen;
    }

    public void setHas_coupen(boolean has_coupen) {
        this.has_coupen = has_coupen;
    }

    public Timestamp getDate_time() {
        return date_time;
    }

    public void setDate_time(Timestamp date_time) {
        this.date_time = date_time;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public String getCaptured_img_base64() { return captured_img_base64; }

    public void setCaptured_img_base64(String captured_img_base64) { this.captured_img_base64 = captured_img_base64; }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getCaptured_img() {
        return captured_img;
    }

    public void setCaptured_img(String captured_img) {
        this.captured_img = captured_img;
    }


}
