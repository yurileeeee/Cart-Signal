package com.example.samplesenti;

import com.google.firebase.auth.FirebaseAuth;

public class Review {

    public double total;
    public double time;
    public double trust;
    public String review;
   // public String myUid;

    public Review(){}

    public Review(double total, double time, double trust, String review){
        this.total = total;
        this.time = time;
        this.trust = trust;
        this.review = review;
        //this.myUid = FirebaseAuth.getInstance().getUid();
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getTrust() {
        return trust;
    }

    public void setTrust(double trust) {
        this.trust = trust;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

}
