package com.acompletenoobsmoke.refresher;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.stripe")
public class StipeConfig {
    private String stripeAPIKEY;
    private String stripeURL;

    public StipeConfig() {
    }

    public StipeConfig(String stripeAPIKEY, String stripeURL) {
        this.stripeAPIKEY = stripeAPIKEY;
        this.stripeURL = stripeURL;
    }

    public String getStripeAPIKEY() {
        return stripeAPIKEY;
    }

    public String getStripeURL() {
        return stripeURL;
    }

    public void setStripeAPIKEY(String stripeAPIKEY) {
        this.stripeAPIKEY = stripeAPIKEY;
    }

    public void setStripeURL(String stripeURL) {
        this.stripeURL = stripeURL;
    }

    @Override
    public String toString() {
        return "StipeConfig{" +
                "stripeAPIKEY='" + stripeAPIKEY + '\'' +
                ", stripeURL='" + stripeURL + '\'' +
                '}';
    }
}
