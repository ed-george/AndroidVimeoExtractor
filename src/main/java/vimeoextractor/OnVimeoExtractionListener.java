package vimeoextractor;

/**
 * Callback from Vimeo Stream extraction, providing video information on success
 * or error information on failure.
 */
public interface OnVimeoExtractionListener {

    /**
     * Returns a {@link VimeoVideo} object relating to the extraction
     * @param video the Vimeo video
     */
    void onSuccess(VimeoVideo video);

    /**
     * Returns when an error occurs during extractions
     * @param throwable the error object
     */
    void onFailure(Throwable throwable);

}
