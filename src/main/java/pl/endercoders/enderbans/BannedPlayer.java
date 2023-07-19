package pl.endercoders.enderbans;

import java.util.List;


public record BannedPlayer(String name,
                           String reason,
                           Long banDate,
                           String banAdmin) {
}

record BannedPlayers(List<BannedPlayer> players) {
}