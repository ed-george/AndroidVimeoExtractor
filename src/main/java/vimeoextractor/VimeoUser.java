package vimeoextractor;

import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

/**
 * Created by edgeorge on 18/01/2017.
 */

public class VimeoUser {

    // Account type for user
    @Nullable
    private String accountType;
    // Name for user
    @Nullable
    private String name;
    // Image url for user
    @Nullable
    private String imageUrl;
    // HQ Image url
    @Nullable
    private String image2xUrl;
    // Profile url of user
    @Nullable
    private String url;
    // User Id
    private long id;

    private VimeoUser(){}

    VimeoUser(JSONObject userObject){
        this.accountType = userObject.optString("account_type");
        this.name = userObject.optString("name");
        this.imageUrl = userObject.optString("img");
        this.image2xUrl = userObject.optString("img_2x");
        this.url = userObject.optString("url");
        this.id = userObject.optLong("id");
    }

    /**
     * Get account type of user - e.g. plus, basic
     * @return Account type of user
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * Get full name of user
     * @return Name of user
     */
    public String getName() {
        return name;
    }

    /**
     * Profile image of user
     * @return Image url
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Larger profile image of user
     * @return HQ image url
     */
    public String getImage2xUrl() {
        return image2xUrl;
    }

    /**
     * Profile URL of the user
     * @return url of profile
     */
    public String getUrl() {
        return url;
    }

    /**
     * Get the Vimeo assigned ID for the user
     * @return id for user
     */
    public long getId() {
        return id;
    }
}
