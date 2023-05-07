package net.itemstackarray.hololines.composer;

import net.itemstackarray.hololines.HoloLinesPlugin;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

/**
 * @Class MinecraftHologramComposer
 * @Date 07/05/2023
 * @Time 19:21
 * @Author ItemStackArray
 */

public final class MinecraftHologramComposer {

    // Create a new Arraylist for the ArmorStands which get added and removed by creating/deleting
    private final List<ArmorStand> armorStands = new ArrayList<>();

    public void createHologram(final Location location, final String... lines) {
        final Vector vector = new Vector(0, 0.25, 0);

        for (int i = 0; i < lines.length; i++) {

            // Set the line location
            final Location line = location.clone().add(vector.clone().multiply(i));
            // Create the Armorstand
            final ArmorStand armorStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);

            // Make the armorstand to a hologram

            // Set the visibility to false
            armorStand.setVisible(false);

            // Set the armorstand small
            armorStand.setSmall(true);

            // Set the name for the hologram
            armorStand.setCustomName(lines[i]);

            // Set the name visible
            armorStand.setCustomNameVisible(true);

            // Set the gravity to false
            armorStand.setGravity(false);

            // Set the marker
            armorStand.setMarker(true);

            // Set the pick-ability from items to false
            armorStand.setCanPickupItems(false);

            // Set the invulnerability to true
            armorStand.setInvulnerable(true);

            // Set collidable to false
            armorStand.setCollidable(false);

            // Create the armorstand
            armorStands.add(armorStand);

        }
    }

    /**
     * @param name
     * @return armorstand which has the name {String name}
     */


    // Find the armorstand by name
    private ArmorStand findArmorStand(final String name) {
        for (final ArmorStand armorStand : this.armorStands) {
            if (armorStand.getName().equalsIgnoreCase(name)) {
                return armorStand;
            }
        }
        return null;
    }


    // Start moving the armorstand randomly
    public void moveRandomly(final ArmorStand armorStand, final double distance) {

        // Set the location
        final Location location = armorStand.getLocation();

        // Set the double x
        final double x = location.getX() + (Math.random() * distance * 2 - distance);

        // Set the double y
        final double y = location.getY() + (Math.random() * distance * 2 - distance);

        // Set the double z
        final double z = location.getZ() + (Math.random() * distance * 2 - distance);

        armorStand.teleport(new Location(location.getWorld(), x, y, z));
    }


    // Start exploding the armorstand
    public void explode(final ArmorStand armorStand) {
        final Location location = armorStand.getLocation();
        location.getWorld().createExplosion(location, 2, false, false);
    }


    // Start spinning the armorstand
    public void spin(final ArmorStand armorStand) {
        new BukkitRunnable() {
            // Initialize the count integer
            int count = 0;

            @Override
            public void run() {
                final Location location = armorStand.getLocation();
                // Set the yaw
                location.setYaw(location.getYaw() + 10);
                // Teleport the armorstand
                armorStand.teleport(location);
                // If the count is >= 36 cancel the spinning
                if (++count >= 36) {
                    this.cancel();
                }
            }
        }.runTaskTimer(HoloLinesPlugin.getINSTANCE(), 0, 2);
    }

    // Deletes the hologram
    public void deleteHologram(final ArmorStand armorStand) {
        if (armorStands.contains(armorStand)) {
            armorStand.remove();
            armorStands.remove(armorStand);
        }
    }

    // Set the rainbow text to the hologram
    public void rainbowText(final String... lines) {
        final List<Color> colors = new ArrayList<>();

        // Add {Color.RED}
        colors.add(Color.RED);

        // Add {Color.ORANGE}
        colors.add(Color.ORANGE);

        // Add {Color.YELLOW}
        colors.add(Color.YELLOW);

        // Add {Color.GREEN}
        colors.add(Color.GREEN);

        // Add {Color.BLUE}
        colors.add(Color.BLUE);

        // Add {Color.PURPLE}
        colors.add(Color.PURPLE);

        int i = 0;
        for (String line : lines) {
            final Color color = colors.get(i % colors.size());
            final ArmorStand armorStand = armorStands.get(i);

            // Set the custom name visible to true
            armorStand.setCustomNameVisible(true);

            // Set the custom name to {line}
            armorStand.setCustomName(line);

            // Set the glowing boolean to true
            armorStand.setGlowing(true);

            // Count the int + 1
            i++;
        }
    }

    /**
     *
     * @param armorStand
     * @return if the armorstand has a rainbow text
     */
    public boolean hasRainbowText(final ArmorStand armorStand) {
        return armorStand.isCustomNameVisible() && armorStand.isGlowing();
    }

    // Deletes all the rainbow texts if the armorstand has one
    public void deleteAllRainbowTexts() {
        for (final ArmorStand armorStand : this.armorStands) {
            if (this.hasRainbowText(armorStand)) this.rainbowText(null);
        }
    }

    /**
     * @param line
     * @return armorStands#get(line)
     */
    public ArmorStand getLine(final int line) {
        return armorStands.get(line);
    }
}
