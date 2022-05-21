package com.chenwei.csust.spark.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@ApiModel
@Data
public class RiskControlModel implements Serializable {
    private Double loan_amnt;
    private Double term;
    private Double int_rate;
    private Double installment;
    private Double annual_inc;
    private Double dti;
    private Double earliest_cr_line;
    private Double open_acc;
    private Double pub_rec;
    private Double revol_bal;
    private Double revol_util;
    private Double total_acc;
    private Double mort_acc;
    private Double pub_rec_bankruptcies;
    private Double sub_grade_A2;
    private Double sub_grade_A3;
    private Double sub_grade_A4;
    private Double sub_grade_A5;
    private Double sub_grade_B1;
    private Double sub_grade_B2;
    private Double sub_grade_B3;
    private Double sub_grade_B4;
    private Double sub_grade_B5;
    private Double sub_grade_C1;
    private Double sub_grade_C2;
    private Double sub_grade_C3;
    private Double sub_grade_C4;
    private Double sub_grade_C5;
    private Double sub_grade_D1;
    private Double sub_grade_D2;
    private Double sub_grade_D3;
    private Double sub_grade_D4;
    private Double sub_grade_D5;
    private Double sub_grade_E1;
    private Double sub_grade_E2;
    private Double sub_grade_E3;
    private Double sub_grade_E4;
    private Double sub_grade_E5;
    private Double sub_grade_F1;
    private Double sub_grade_F2;
    private Double sub_grade_F3;
    private Double sub_grade_F4;
    private Double sub_grade_F5;
    private Double sub_grade_G1;
    private Double sub_grade_G2;
    private Double sub_grade_G3;
    private Double sub_grade_G4;
    private Double sub_grade_G5;
    private Double verification_status_Source_Verified;
    private Double verification_status_Verified;
    private Double purpose_credit_card;
    private Double purpose_debt_consolidation;
    private Double purpose_educational;
    private Double purpose_home_improvement;
    private Double purpose_house;
    private Double purpose_major_purchase;
    private Double purpose_medical;
    private Double purpose_moving;
    private Double purpose_other;
    private Double purpose_renewable_energy;
    private Double purpose_small_business;
    private Double purpose_vacation;
    private Double purpose_wedding;
    private Double initial_list_status_w;
    private Double application_type_INDIVIDUAL;
    private Double application_type_JOINT;
    private Double home_ownership_MORTGAGE;
    private Double home_ownership_NONE;
    private Double home_ownership_OTHER;
    private Double home_ownership_OWN;
    private Double home_ownership_RENT;
    private Double zip_code_05113;
    private Double zip_code_11650;
    private Double zip_code_22690;
    private Double zip_code_29597;
    private Double zip_code_30723;
    private Double zip_code_48052;
    private Double zip_code_70466;
    private Double zip_code_86630;
    private Double zip_code_93700;
    private Double loan_status;

    public RiskControlModel() {
    }

    public RiskControlModel(Double loan_amnt, Double term, Double int_rate, Double installment, Double annual_inc, Double loan_status,
                            Double dti, Double earliest_cr_line, Double open_acc, Double pub_rec, Double revol_bal,
                            Double revol_util, Double total_acc, Double mort_acc, Double pub_rec_bankruptcies,
                            Double sub_grade_A2, Double sub_grade_A3, Double sub_grade_A4, Double sub_grade_A5,
                            Double sub_grade_B1, Double sub_grade_B2, Double sub_grade_B3, Double sub_grade_B4,
                            Double sub_grade_B5, Double sub_grade_C1, Double sub_grade_C2, Double sub_grade_C3,
                            Double sub_grade_C4, Double sub_grade_C5, Double sub_grade_D1, Double sub_grade_D2,
                            Double sub_grade_D3, Double sub_grade_D4, Double sub_grade_D5, Double sub_grade_E1,
                            Double sub_grade_E2, Double sub_grade_E3, Double sub_grade_E4, Double sub_grade_E5,
                            Double sub_grade_F1, Double sub_grade_F2, Double sub_grade_F3, Double sub_grade_F4,
                            Double sub_grade_F5, Double sub_grade_G1, Double sub_grade_G2, Double sub_grade_G3,
                            Double sub_grade_G4, Double sub_grade_G5, Double verification_status_Source_Verified,
                            Double verification_status_Verified, Double purpose_credit_card, Double purpose_debt_consolidation,
                            Double purpose_educational, Double purpose_home_improvement, Double purpose_house,
                            Double purpose_major_purchase, Double purpose_medical, Double purpose_moving, Double purpose_other,
                            Double purpose_renewable_energy, Double purpose_small_business, Double purpose_vacation,
                            Double purpose_wedding, Double initial_list_status_w, Double application_type_INDIVIDUAL,
                            Double application_type_JOINT, Double home_ownership_MORTGAGE, Double home_ownership_NONE,
                            Double home_ownership_OTHER, Double home_ownership_OWN, Double home_ownership_RENT,
                            Double zip_code_05113, Double zip_code_11650, Double zip_code_22690, Double zip_code_29597,
                            Double zip_code_30723, Double zip_code_48052, Double zip_code_70466, Double zip_code_86630,
                            Double zip_code_93700) {
        this.loan_amnt = loan_amnt;
        this.term = term;
        this.int_rate = int_rate;
        this.installment = installment;
        this.annual_inc = annual_inc;
        this.dti = dti;
        this.earliest_cr_line = earliest_cr_line;
        this.open_acc = open_acc;
        this.pub_rec = pub_rec;
        this.revol_bal = revol_bal;
        this.revol_util = revol_util;
        this.total_acc = total_acc;
        this.mort_acc = mort_acc;
        this.pub_rec_bankruptcies = pub_rec_bankruptcies;
        this.sub_grade_A2 = sub_grade_A2;
        this.sub_grade_A3 = sub_grade_A3;
        this.sub_grade_A4 = sub_grade_A4;
        this.sub_grade_A5 = sub_grade_A5;
        this.sub_grade_B1 = sub_grade_B1;
        this.sub_grade_B2 = sub_grade_B2;
        this.sub_grade_B3 = sub_grade_B3;
        this.sub_grade_B4 = sub_grade_B4;
        this.sub_grade_B5 = sub_grade_B5;
        this.sub_grade_C1 = sub_grade_C1;
        this.sub_grade_C2 = sub_grade_C2;
        this.sub_grade_C3 = sub_grade_C3;
        this.sub_grade_C4 = sub_grade_C4;
        this.sub_grade_C5 = sub_grade_C5;
        this.sub_grade_D1 = sub_grade_D1;
        this.sub_grade_D2 = sub_grade_D2;
        this.sub_grade_D3 = sub_grade_D3;
        this.sub_grade_D4 = sub_grade_D4;
        this.sub_grade_D5 = sub_grade_D5;
        this.sub_grade_E1 = sub_grade_E1;
        this.sub_grade_E2 = sub_grade_E2;
        this.sub_grade_E3 = sub_grade_E3;
        this.sub_grade_E4 = sub_grade_E4;
        this.sub_grade_E5 = sub_grade_E5;
        this.sub_grade_F1 = sub_grade_F1;
        this.sub_grade_F2 = sub_grade_F2;
        this.sub_grade_F3 = sub_grade_F3;
        this.sub_grade_F4 = sub_grade_F4;
        this.sub_grade_F5 = sub_grade_F5;
        this.sub_grade_G1 = sub_grade_G1;
        this.sub_grade_G2 = sub_grade_G2;
        this.sub_grade_G3 = sub_grade_G3;
        this.sub_grade_G4 = sub_grade_G4;
        this.sub_grade_G5 = sub_grade_G5;
        this.verification_status_Source_Verified = verification_status_Source_Verified;
        this.verification_status_Verified = verification_status_Verified;
        this.purpose_credit_card = purpose_credit_card;
        this.purpose_debt_consolidation = purpose_debt_consolidation;
        this.purpose_educational = purpose_educational;
        this.purpose_home_improvement = purpose_home_improvement;
        this.purpose_house = purpose_house;
        this.purpose_major_purchase = purpose_major_purchase;
        this.purpose_medical = purpose_medical;
        this.purpose_moving = purpose_moving;
        this.purpose_other = purpose_other;
        this.purpose_renewable_energy = purpose_renewable_energy;
        this.purpose_small_business = purpose_small_business;
        this.purpose_vacation = purpose_vacation;
        this.purpose_wedding = purpose_wedding;
        this.initial_list_status_w = initial_list_status_w;
        this.application_type_INDIVIDUAL = application_type_INDIVIDUAL;
        this.application_type_JOINT = application_type_JOINT;
        this.home_ownership_MORTGAGE = home_ownership_MORTGAGE;
        this.home_ownership_NONE = home_ownership_NONE;
        this.home_ownership_OTHER = home_ownership_OTHER;
        this.home_ownership_OWN = home_ownership_OWN;
        this.home_ownership_RENT = home_ownership_RENT;
        this.zip_code_05113 = zip_code_05113;
        this.zip_code_11650 = zip_code_11650;
        this.zip_code_22690 = zip_code_22690;
        this.zip_code_29597 = zip_code_29597;
        this.zip_code_30723 = zip_code_30723;
        this.zip_code_48052 = zip_code_48052;
        this.zip_code_70466 = zip_code_70466;
        this.zip_code_86630 = zip_code_86630;
        this.zip_code_93700 = zip_code_93700;
        this.loan_status = loan_status;
    }

    @Override
    public String toString() {
        return "RiskControlModel{" +
                "loan_amnt=" + loan_amnt +
                ", term=" + term +
                ", int_rate=" + int_rate +
                ", installment=" + installment +
                ", annual_inc=" + annual_inc +
                ", dti=" + dti +
                ", earliest_cr_line=" + earliest_cr_line +
                ", open_acc=" + open_acc +
                ", pub_rec=" + pub_rec +
                ", revol_bal=" + revol_bal +
                ", revol_util=" + revol_util +
                ", total_acc=" + total_acc +
                ", mort_acc=" + mort_acc +
                ", pub_rec_bankruptcies=" + pub_rec_bankruptcies +
                ", sub_grade_A2=" + sub_grade_A2 +
                ", sub_grade_A3=" + sub_grade_A3 +
                ", sub_grade_A4=" + sub_grade_A4 +
                ", sub_grade_A5=" + sub_grade_A5 +
                ", sub_grade_B1=" + sub_grade_B1 +
                ", sub_grade_B2=" + sub_grade_B2 +
                ", sub_grade_B3=" + sub_grade_B3 +
                ", sub_grade_B4=" + sub_grade_B4 +
                ", sub_grade_B5=" + sub_grade_B5 +
                ", sub_grade_C1=" + sub_grade_C1 +
                ", sub_grade_C2=" + sub_grade_C2 +
                ", sub_grade_C3=" + sub_grade_C3 +
                ", sub_grade_C4=" + sub_grade_C4 +
                ", sub_grade_C5=" + sub_grade_C5 +
                ", sub_grade_D1=" + sub_grade_D1 +
                ", sub_grade_D2=" + sub_grade_D2 +
                ", sub_grade_D3=" + sub_grade_D3 +
                ", sub_grade_D4=" + sub_grade_D4 +
                ", sub_grade_D5=" + sub_grade_D5 +
                ", sub_grade_E1=" + sub_grade_E1 +
                ", sub_grade_E2=" + sub_grade_E2 +
                ", sub_grade_E3=" + sub_grade_E3 +
                ", sub_grade_E4=" + sub_grade_E4 +
                ", sub_grade_E5=" + sub_grade_E5 +
                ", sub_grade_F1=" + sub_grade_F1 +
                ", sub_grade_F2=" + sub_grade_F2 +
                ", sub_grade_F3=" + sub_grade_F3 +
                ", sub_grade_F4=" + sub_grade_F4 +
                ", sub_grade_F5=" + sub_grade_F5 +
                ", sub_grade_G1=" + sub_grade_G1 +
                ", sub_grade_G2=" + sub_grade_G2 +
                ", sub_grade_G3=" + sub_grade_G3 +
                ", sub_grade_G4=" + sub_grade_G4 +
                ", sub_grade_G5=" + sub_grade_G5 +
                ", verification_status_Source_Verified=" + verification_status_Source_Verified +
                ", verification_status_Verified=" + verification_status_Verified +
                ", purpose_credit_card=" + purpose_credit_card +
                ", purpose_debt_consolidation=" + purpose_debt_consolidation +
                ", purpose_educational=" + purpose_educational +
                ", purpose_home_improvement=" + purpose_home_improvement +
                ", purpose_house=" + purpose_house +
                ", purpose_major_purchase=" + purpose_major_purchase +
                ", purpose_medical=" + purpose_medical +
                ", purpose_moving=" + purpose_moving +
                ", purpose_other=" + purpose_other +
                ", purpose_renewable_energy=" + purpose_renewable_energy +
                ", purpose_small_business=" + purpose_small_business +
                ", purpose_vacation=" + purpose_vacation +
                ", purpose_wedding=" + purpose_wedding +
                ", initial_list_status_w=" + initial_list_status_w +
                ", application_type_INDIVIDUAL=" + application_type_INDIVIDUAL +
                ", application_type_JOINT=" + application_type_JOINT +
                ", home_ownership_MORTGAGE=" + home_ownership_MORTGAGE +
                ", home_ownership_NONE=" + home_ownership_NONE +
                ", home_ownership_OTHER=" + home_ownership_OTHER +
                ", home_ownership_OWN=" + home_ownership_OWN +
                ", home_ownership_RENT=" + home_ownership_RENT +
                ", zip_code_05113=" + zip_code_05113 +
                ", zip_code_11650=" + zip_code_11650 +
                ", zip_code_22690=" + zip_code_22690 +
                ", zip_code_29597=" + zip_code_29597 +
                ", zip_code_30723=" + zip_code_30723 +
                ", zip_code_48052=" + zip_code_48052 +
                ", zip_code_70466=" + zip_code_70466 +
                ", zip_code_86630=" + zip_code_86630 +
                ", zip_code_93700=" + zip_code_93700 +
                ", loan_status=" + loan_status +
                '}';
    }
}
