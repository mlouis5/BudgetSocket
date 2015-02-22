/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.budgetsocket.pojos.ui;

import java.util.Objects;

/**
 *
 * @author Mac
 */
public class AddressUIModel {

    private String addLine1;
    private String addLine2;
    private String city;
    private String state;
    private String zip;

    public String getAddLine1() {
        return addLine1;
    }

    public void setAddLine1(String addLine1) {
        this.addLine1 = addLine1;
    }

    public String getAddLine2() {
        return addLine2;
    }

    public void setAddLine2(String addLine2) {
        this.addLine2 = addLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        setState(state);
        return state;
    }

    public void setState(String state) {
        if (Objects.nonNull(state)) {
            State st = State.getState(state);
            if (Objects.nonNull(st)) {
                this.state = st.abbr();
            }
        }
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    private static enum State {

        ALABAMA("AL"), ALASKA("AK"), ARIZONA("AZ"), ARKANSAS("AR"), CALIFORNIA("CA"),
        COLORADO("CO"), CONNECTICUT("CT"), DELAWARE("DE"), FLORIDA("FL"),
        GEORGIA("GA"), HAWAII("HI"), IDAHO("ID"), ILLINOIS("IL"),
        INDIANA("IN"), IOWA("IA"), KANSAS("KS"), KENTUCKY("KY"), LOUISIANA("LA"),
        MAINE("ME"), MARYLAND("MD"), MASSACHUSETTS("MA"), MICHIGAN("MI"), MINNESOTA("MN"),
        MISSISSIPPI("MS"), MISSOURI("MO"), MONTANA("MT"), NEBRASKA("NE"), NEVADA("NV"),
        NEW_HAMPSHIRE("NH"),
        NEW_JERSEY("NJ"),
        NEW_MEXICO("NM"),
        NEW_YORK("NY"),
        NORTH_CAROLINA("NC"),
        NORTH_DAKOTA("ND"),
        OHIO("OH"),
        OKLAHOMA("OK"),
        OREGON("OR"),
        PENNSYLVANIA("PA"),
        RHODE_ISLAND("RI"),
        SOUTH_CAROLINA("SC"),
        SOUTH_DAKOTA("SD"),
        TENNESSEE("TN"),
        TEXAS("TX"),
        UTAH("UT"),
        VERMONT("VT"),
        VIRGINIA("VA"),
        WASHINGTON("WA"),
        WEST_VIRGINIA("WV"),
        WISCONSIN("WI"),
        WYOMING("WY");

        private final String abbr;

        State(String abbr) {
            this.abbr = abbr;
        }

        public String abbr() {
            return this.abbr;
        }

        public String state() {
            return this.name().replace("_", " ");
        }

        public static State getState(String value) {
            State[] states = State.class.getEnumConstants();
            for (State st : states) {
                String state = st.name();
                if (state.contains("_")) {
                    state = state.replace("_", " ");
                }
                if (value.equalsIgnoreCase(state) || value.equalsIgnoreCase(st.abbr())) {
                    return st;
                }
            }
            return null;
        }
    }
}
