package vimeoextractor;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

/**
 * A class used to extract Vimeo video information
 * through an all-digit video identifier or a full video URL.
 *
 * Information includes stream urls, title and duration.
 *
 * See {@link VimeoVideo} for full information available
 */
public class VimeoExtractor {

    //Singleton
    private static VimeoExtractor instance;

    private VimeoExtractor(){}

    /**
     * Get singleton instance of the extractor
     * @return singleton instance
     */
    public static VimeoExtractor getInstance() {
        if(instance == null){
            instance = new VimeoExtractor();
        }
        return instance;
    }

    /**
     * Get Video stream information using its identifier
     * @param identifier Non-null numeric video identifier (e.g. 123456)
     * @param referrer Video referrer URL. Leaving as null provides referrer as video url by default
     * @param listener Callback from extraction
     */
    public void fetchVideoWithIdentifier(@NotNull String identifier, @Nullable String referrer, @NotNull final OnVimeoExtractionListener listener){
        //If an invalid identifier is entered, throw an error
        if(identifier.length() == 0){
            listener.onFailure(new IllegalArgumentException("Video identifier cannot be empty"));
            return;
        }

        final VimeoAPIManager manager = new VimeoAPIManager();
        try {
            manager.extractWithIdentifier(identifier, referrer).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    listener.onFailure(e);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    //Check if response is successful
                    if(response.isSuccessful()) {
                        //Generate video object from JSON response
                        VimeoVideo vimeoVideo = new VimeoVideo(response.body().string());
                        listener.onSuccess(vimeoVideo);
                    }else{
                        //Generate an appropriate error
                        listener.onFailure(manager.getError(response));
                    }
                }
            });
        } catch (IOException e) {
            listener.onFailure(e);
            e.printStackTrace();
        }
    }

    /**
     * Get Video stream information from its full URL
     * @param videoURL Video URL
     * @param referrer Video referrer URL
     * @param listener Callback from extraction
     */
    public void fetchVideoWithURL(@NotNull String videoURL, @Nullable String referrer, @NotNull final OnVimeoExtractionListener listener){
        //Check for valid URL length
        if(videoURL.length() == 0){
            listener.onFailure(new IllegalArgumentException("Video URL cannot be empty"));
            return;
        }

        VimeoParser parser = new VimeoParser(videoURL);
        //Determine if Vimeo URL is valid
        if(!parser.isVimeoURLValid()) {
            listener.onFailure(new IllegalArgumentException("Vimeo URL is not valid"));
            return;
        }

        //Extract identifier from URL
        String identifier = parser.getExtractedIdentifier();
        //Get Video stream information using its identifier
        fetchVideoWithIdentifier(identifier, referrer, listener);
    }

}
