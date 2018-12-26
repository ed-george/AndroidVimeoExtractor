package vimeoextractor;


import org.jetbrains.annotations.NotNull;

/**
 * Parser for a given Vimeo Link
 */
public class VimeoParser {

    //Full URL of Vimeo video
    private String url;

    /**
     * Initialise VideoParser with url
     * @param url Vimeo Video url
     */
    public VimeoParser(@NotNull String url){
        this.url = url;
    }

    /**
     * Check if a Vimeo URL has a valid identifier
     * @return true if identifier is valid, false otherwise
     */
    public boolean isVimeoURLValid(){
        String videoID = getExtractedIdentifier();
        return videoID.length() > 0 && VimeoUtils.isDigitsOnly(videoID);
    }

    /**
     * Get a Vimeo identifier from the url
     * @return Vimeo identifier if found and an empty string otherwise
     */
    public String getExtractedIdentifier(){
        String[] urlParts = url.split("/");
        if(urlParts.length == 0){
            return "";
        }
        return urlParts[urlParts.length - 1];
    }

    /**
     * Get the URL stored by parser
     * @return the url
     */
    public String getUrl() {
        return url;
    }

}
