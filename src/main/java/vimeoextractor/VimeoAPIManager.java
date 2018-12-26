package vimeoextractor;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

/**
 * An API and request manager for Vimeo's web service.
 */
class VimeoAPIManager {

    //Base URL for Vimeo videos
    protected static final String VIMEO_URL = "https://vimeo.com/%s";
    //Config URL containing video information
    protected static final String VIMEO_CONFIG_URL = "https://player.vimeo.com/video/%s/config";

    /**
     * Builds an HTTP call to Vimeo, from an identifier, to extract video information
     * @param identifier Vimeo video identifier
     * @param referrer Video referrer, null if none present
     * @return an OKHttp3 Call to use asynchronously or otherwise.
     * @throws IOException If a connection or other error occurs
     */
    protected Call extractWithIdentifier(@NotNull String identifier, @Nullable String referrer) throws IOException {

        String url = String.format(VIMEO_CONFIG_URL, identifier);

        if(VimeoUtils.isEmpty(referrer)){
            //If no referrer exists, generate from base URL
            referrer = String.format(VIMEO_URL, identifier);
        }

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .header("Content-Type", "application/json")
                    .header("Referer", referrer)
                    .build();

            return client.newCall(request);
    }

    /**
     * Generates an appropriate error for a given response
     * @param response The response that was not successful
     * @return An Exception based on the HTTP Status code of the response
     */
    protected Throwable getError(Response response) {

        switch (response.code()){
            case 404:
                return new IOException("Video could not be found");
            case 403:
                return new IOException("Video has restricted playback");
            default:
                return new IOException("An unknown error occurred");
        }
    }
}
