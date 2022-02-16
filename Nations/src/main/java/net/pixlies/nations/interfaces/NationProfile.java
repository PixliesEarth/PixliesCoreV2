package net.pixlies.nations.interfaces;

import dev.morphia.annotations.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import net.pixlies.core.entity.User;

/**
 * A Morphia-serializable Object to store all important information about the players
 * Nation information.
 *
 * @author MickMMars
 */
@Data
@AllArgsConstructor
@Entity
public class NationProfile {

    // -------------------------------------------------------------------------------------------------
    //                                              DATA
    // -------------------------------------------------------------------------------------------------

    private String nationId;
    private String nationRank;

    // -------------------------------------------------------------------------------------------------
    //                                          STATIC METHODS
    // -------------------------------------------------------------------------------------------------

    /**
     * Check if player is in a nation by checking their extras variable for a "nationsProfile" object.
     *
     * @param user Expects a valid "User" object of the player
     * @see User
     */
    public static boolean isInNation(User user) {
        return user.getExtras().containsKey("nationsProfile");
    }

    /**
     * Get a users NationProfile
     *
     * @param user Expects a valid "User" object of the player
     * @return If user is not in a nation: null; If he is: A valid NationProfile object
     */
    public static NationProfile get(User user) {
        if (!isInNation(user)) return null;
        return (NationProfile) user.getExtras().get("nationsProfile");
    }

}