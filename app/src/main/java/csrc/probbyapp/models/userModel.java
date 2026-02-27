package csrc.probbyapp.models;
import com.google.gson.annotations.SerializedName;

public class userModel {
    @SerializedName("_id")
    private String id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;

}
