package vimeoextractor;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
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
    //Stream thumbnails with key being quality and value url of image
    private Map<String, String> thumbs;
    //User that created video
    private VimeoUser videoUser;

    //Initialise VimeoVideo from JSON
    protected VimeoVideo(@NotNull String json){
        streams = new HashMap<>();
        thumbs = new HashMap<>();
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

            //Get user information
            JSONObject userInfo = videoInfo.getJSONObject("owner");
            this.videoUser = new VimeoUser(userInfo);

            //Get thumbnail information
            JSONObject thumbsInfo = videoInfo.getJSONObject("thumbs");
            Iterator<String> iterator;
            for(iterator = thumbsInfo.keys(); iterator.hasNext();) {
                String key = iterator.next();
                this.thumbs.put(key, thumbsInfo.getString(key));
            }

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

    /**
     * Check if video has associated thumbnails
     * @return true if thumbnails are present; false otherwise
     */
    public boolean hasThumbs() {
        return this.thumbs.size() > 0;
    }

    /**
     * Get thumbnail information in the form of a key-value map.
     * Keys are the quality information of the thumbnail (e.g. base, 640, 1280)
     * The default key returned from Vimeo's API is "base"
     * Values are the corresponding thumbnail image URL
     * @return Map of available thumbnails for video
     */
    public Map<String, String> getThumbs() {
        return thumbs;
    }

    /**
     * Get information on the user that created / uploaded the video
     * @return VimeoUser object containing information on the user
     */
    public VimeoUser getVideoUser() {
        return videoUser;
    }
}
