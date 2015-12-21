package com.orange.gws;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by a on 14/12/15.
 */
@ParseClassName("Message")
public class Message extends ParseObject {
    public String getUserId() {
        return getString("userId");
    }

    public String getBody() {
        return getString("body");
    }

    public void setUserName(String appUserName) { put("userName", appUserName); }

    public void setPhoneNo(String appPhoneNo) { put("phoneNo", appPhoneNo);}

    public void setUserId(String userId) {
        put("userId", userId);
    }

    public void setBody(String body) {
        put("body", body);
    }
}


