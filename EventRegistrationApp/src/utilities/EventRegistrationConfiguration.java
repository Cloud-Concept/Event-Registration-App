package utilities;

import com.salesforce.androidsdk.rest.RestClient;

/**
 * Created by Abanoub Wagdy on 1/17/2016.
 */
public class EventRegistrationConfiguration {

    public static final String MOBILE_SERVICE_UTILITY_URL = "";

    private static RestClient client;

    public static RestClient getClient() {
        return client;
    }

    public static void setClient(RestClient client) {
        EventRegistrationConfiguration.client = client;
    }
}
