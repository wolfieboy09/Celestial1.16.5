package fishcute.celestial.forge;

import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class PlayerStages {
    public static int hasCurrentStage(@NotNull Player player, String stage) {
        // Will this work? Great Question
        Set<String> tags = player.getTags();
        if (tags.contains(stage)) {
            return 1;
        }
        return 0;
    }

    public static void printTagsForPlayer(Player player) {
        Set<String> tags = player.getTags();
        System.out.println("ALL TAGS");
        System.out.println(tags);
    }
}
