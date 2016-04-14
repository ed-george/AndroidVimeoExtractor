package uk.breedrapps.vimeoextractor;

import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * An information extracted Vimeo video containing details about
 * stream quality, stream links, duration and video title
 */
public class VimeoVideo {

    //Video title
    private String title;
    //Video length in seconds
    private long duration;
    //Stream information with key being quality name (e.g. 1080p) and value stream url
    private Map<String, String> streams;

    //Initialise VimeoVideo from JSON
    protected VimeoVideo(@NonNull String json){
        streams = new HashMap<>();
        parseJson(json);
    }

    private void parseJson(String json) {
        try {
            //Turn JSON string to object
            JSONObject requestJson = new JSONObject(json);

            //Access video information
            JSONObject videoInfo = requestJson.getJSONObject("video");
            this.duration = videoInfo.getLong("duration");
            this.title = videoInfo.getString("title");

            //Access video stream information
            JSONArray streamArray = requestJson.getJSONObject("request")
                    .getJSONObject("files")
                    .getJSONArray("progressive");

            //Get info for each stream available
            for (int streamIndex = 0; streamIndex < streamArray.length(); streamIndex++) {
                JSONObject stream = streamArray.getJSONObject(streamIndex);
                String url = stream.getString("url");
                String quality = stream.getString("quality");
                //Store stream information
                this.streams.put(quality, url);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Video title
     * @return the video title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Video duration in seconds
     * @return the video duration
     */
    public long getDuration() {
        return duration;
    }

    /**
     * Check if given video has stream information
     * @return true if information is present, false otherwise
     */
    public boolean hasStreams(){
        return streams.size() > 0;
    }

    /**
     * Check if video has HD stream available
     * @return true if 1080 or 4096p streams are available, false otherwise
     */
    public boolean isHD(){
        return streams.containsKey("1080p") || streams.containsKey("4096p");
    }

    /**
     * Get stream information in the form of a key-value map.
     * Keys are the quality information of the stream (e.g. 1080p)
     * Values are the corresponding stream URL
     * @return Map of streams for video
     */
    public Map<String, String> getStreams() {
        return streams;
    }

}
