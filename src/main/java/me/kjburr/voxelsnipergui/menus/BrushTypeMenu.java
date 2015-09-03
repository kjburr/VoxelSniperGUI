/*
 * Copyright (c) kjburr 2015
 */

package me.kjburr.voxelsnipergui.menus;

import com.google.common.collect.Lists;
import me.kjburr.voxelsnipergui.VoxelSniperGUI;
import me.kjburr.voxelsnipergui.config.ConfigValues;
import me.kjburr.voxelsnipergui.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Ace on 4/20/2015.
 */
public class BrushTypeMenu implements IMenu {

    public BrushTypeMenu() {

        int max = 44;
        int currentSlot = 0;
        int page = 1;
        List<ItemStack> itemStacks = Lists.newArrayList();
        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bBall",
                "The Ball Brush behaves much like the disc but produces a sphere of blocks centered on the snipe-point. " +
                        "This is great for creating clumps of material for terraforming."));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bBiome", ""));
        String blend = "This suite of brushes allows the sniper to clean up ragged borders between different types of materials. " +
                "The brush looks at the neighboring blocks of each block in the brush's area of effect to determine which material is the most common neighbor. " +
                "If the brush can find a most common neighboring material for a given voxel (this excludes ties), " +
                "that voxel is changed to better match the blocks around it." +
                "The user may choose to either include or exclude air in this search:" +
                "By using the arrow tool, air is included, so repeated uses of these brushes are likely to act similarly " +
                "to the melt and smooth presetsof the erosion brush." +
                "On the other hand, excluding air (by using the gunpowder tool will make repeated uses of the " +
                "blend brushes act more like the fill preset of the erosion brush." +
                "Additionally, water may also be optionally included or excluded (default)." +
                "Blend brushes use standard brush size and several available sized shapes:" +
                " /b bd: Blend Disc" +
                " /b bb: Blend Ball" +
                " /b bvd: Blend Voxel Disc" +
                " /b bv: Blend Voxel" +
                " /b bb water: Blend Ball, toggles water exclusion ";

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bBlendBall", blend));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bBlendDisc", blend));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bBlendVoxel", blend));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bBlendVoxelDisc", blend));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bBlob", "/b blob: The Blob brush is a close relative of the splatter brush. " +
                "The primary difference between the Blobs and Splatters brushes is that the Blob brush always has exactly one seed. " +
                "This means that you will typically produce only a single large cluster. " +
                "Consequently, the only parameter that you can change is growth percent, as the block clicked is always " +
                "the only seed and the number of recursions is the brush size. However, the brush does have more variety than you might expect. " +
                "The arrow and gunpowder versions of this brush function very differently." +
                " Note: When using the Blob brush, the arrow tool will grow new blocks outwards from the center (block clicked)." +
                " Note: In opposition, the gunpowder tools starts from a edges of the sphere area and works from the outside towards the center. " +
                "As a result of these differences, growth percent affects these two versions of the blob brush in opposite ways. A low growth percent means that the arrow blob will only produce a few voxels while the gunpowder blob makes a near-perfect sphere. " +
                "A high growth percent means that the arrow will nearly fill the sphere with voxels while the gunpowder has eroded the sphere so completely that only a few random voxels remain. " +
                "This allows the sniper to have both a coarse brush to generate a lot of voxels with a single click as well as a finer brush for detail work with only a change in tool required. " +
                "You can work without the need to change the growth parameter while working with this brush!" +
                " Blob brush for incredibly natural looking mountains, valleys, and hills. " +
                "Note: When used for terraforming, all of the Splatter brushes benefit greatly from be used in conjunction with the Erosion brush, particularly the smooth preset. "));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bBlockReset", ""));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bBlockResetSurface", ""));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bCanyon",
                "/b ca y(1-63): The CANYONATOR moves landscape down to a specified y-coordinate level. This is great for making low sections of a map for building extra tall buildings. " +
                        "Stagger different y-levels for a steppe-effect, then clean the chunk edges up with erosion and overlay brushes. " +
                "Sclupting a landscape that takes advantage of the entire map height starts with this brush! Like the Oceanator, this brush will lower nine chunks at once when used with the gunpowder." +
                " Example: \"/b ca y30\" Moves landscape based at sea level (y:64) to y:30." +
                " Note: The Canyonator will copy a few squares of natural landscape below sea level, place a few layers of dirt for comfort and then solid stone all the way down to bedrock at y:1. " +
                        "All natural formations and mineral deposits below Canyonated areas will be completely demolished. (This is to keep the brush as fact-acting as possible). "));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bCanyonSelection",
                "/b cas y(1-63): The Canyon-selection brush uses a gunpowder and arrow snipe (similar to the line brush) to select a region for " +
                        "canyonation. " +
                        "It uses the same height variable as the chunk-based canyonator, but can be used to generate precision zoned areas for droppage." +
                " Note: This brush has no undo. "));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bCheckerVoxelDisc",
                "/b cvd: The Voxel Checker Disc Brush behaves like the Voxel Disc, but makes an checker shape. "));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bCleanSnow", "Indev" +
                " /b cls: The Clean Snow brush removes floating snow tiles. The brush is set to non-true circles by default." +
                " /b cls true|false: Sets the brush use either true or false circles. "));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1,
                "&bCloneStamp", "/b cs: The Clone / Stamp Cylinder Brush copies a zone of " +
                "blocks in a cylindrical shape, allowing those copied blocks to be pasted in elswhere (hereafter referred to as the \"clone zone\". " +
                        "The Clone zone is defined relative to a \"key block\" you will make your copy selection from." +
                "/b 4: The brush size variable sets the diameter of the clone zone." +
                " Example: In the image at left, we've set our clone zone's radius to 4 to include the 3 blocks of the street lamp's arm " +
                "and the torch on the side. " +
                "/vc sets the centroid of the cylinder relative to the key block. The value can be set to a negative integer," +
                " enabling the clone zone to extend below the key block." +
                " Example: Because the center block is difficult to click as a key block on this structure, we use \"/vc -1\" " +
                "to include the base of the pole that resides below our key block, which will be the first wood block in the lamp's \"pole\". " +
                "/vh sets the height, in blocks, of the clone zone." +
                " Note: The height is counted from the centroid point, so make sure to set your height setting takes this into account " +
                "if you are using a negative centroid! " +
                "Make your clone zone selection final by right-clicking the key block with the Gunpowder tool. " +
                "VoxelSniper will report the number of copied blocks." +
                "Re-adjust your Centroid to paste in at floor-level with the \"/vc\" command." +
                " Tip: Often, this is as simple inverting the centroid value from the Clone Zone's. " +
                "In this example, we will adjust from \"/vc -1\" to \"/vc 1\" before Stamping our clone. " +
                "Stamp a new copy of your clone zone with the Arrow tool. The Stamp Zone comes in three flavors:" +
                " /b cs 0: The Default Clone Stamp pastes in the clone zone exactly as it was at the original key block's location, " +
                        "including the air / empty blocks." +
                " /b cs a: No-Air Clone Stamp the Clone Zone in, but does not stamp in the air blocks from the clone zone. " +
                        "This will build your stamp into the surrounding landscape." +
                " /b cs f: Fill Stamp replaces ONLY the empty blocks in the stamp zone. All blocks in the stamp zone will remain untouched. " +
                        "In the example, note the missing half-block at the rear base of the stand." +
                " Tip: This function is ideal for using the clone stamp brush with clumps for terraforming. "));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bComet", ""));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bCopyPasta", "/b cp" +
                "This is a 2-point cuboid copy and paste brush for snipers, with rotation! For safety reasons, " +
                "Snipers are limited to copying a region no bigger than 10k blocks, which will accommodate a 21x21x21 region. " +
                "Snipers may also toggle the pasting of air with /b cp air if they so desire. You can set the rotation with /b cp # " +
                "where # is 0, 90, 180 or 270 degrees clockwise. The axis of rotation should be the column of your first point selection, " +
                "but that is still being tested. Functionality of this brush is as follows:" +
                " Select two corners with the arrow (remember which you clicked first)." +
                " Copy the region with the powder." +
                " Paste as many times as you would like with the powder, the blocks will be pasted as if this new point was the first block you " +
                "had clicked with the arrow." +
                " You may switch rotation at any point without losing your block array, but you cannot change the axis of rotation." +
                " You may reset the brush at any time in the process by clicking the arrow a third time. "));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bCylinder", ""));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bDisc", ""));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bDiscFace", ""));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bDome",
                "/b dome: This brush creates parabolic domes centered on the block clicked. " +
                        "It uses the brush size to determine the dome's radius. /vh [#]: This defines your dome height." +
                "" +
                " Example: /b dome + /vh 5 + /b 20 + /v 1 will create a stone dome 41 blocks across and 5 blocks high." +
                " Note: The dome brush does not use performers at this time. "));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bDrain", ""));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bEllipse", "/b el x[n] y[n] t[n]" +
                "The Ellipse brush is an extension of the Disc brush which has already been implemented into VoxelSniper. " +
                "The brush uses the a and b properties of an elllipse. A great definition of an ellipse may be found here. For the benefits of the sniper, " +
                "the brush can be scaled in two dimensions. The brush offers the parameters x[n] and y[n] (where n is any positive whole number) " +
                "that scales the ellipse's width and height factors (corresponding to the X and Y \"axes\" in the block placement), respectively. " +
                "Furthermore, since the brush utilizes the parametric form of an ellipse, the brush includes the parameter t[n], " +
                "which is the time step number of the brush. The time step can range from 1 to 1000 (though roughly 50 - 250 suits most applications). " +
                "The time step enhances the accuracy of the block placement of the brush." +
                "Moreover, the brush uses a \"face\" mode, like Disk Face, and a \"fill\" mode. " +
                "Therefore, the face of the block clicked determines the orientation of the ellipse. " +
                "Also, the fill mode can be toggled on and off using the parameter fill. "));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1,
                "&bEllipsoid", "/b elo x[n] y[n] z[n] [true] The Ellipsoid Brush is a three dimension version of the Ellipse Brush. " +
                        "It allows you to define a radii for each of the dimensions, x[n] y[n] and z[n] respectively where n is any positive integer. " +
                        "In addition you can enable true-circle mode just like the ball brush or other similar brushes. "));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bEntity",
                "/b en [EntityType]: The Entity Brush allowes a user to snipe-spawn single or groups (defined by the brush size variable) of " +
                        "entities from a safe distance. (Item, XPOrb, Painting, Arrow, Snowball, Fireball, SmallFireball, ThrownEnderpearl, " +
                        "EyeOfEnderSignal, ThrownExpBottle, PrimedTnt, FallingSand, Minecart, Boat, Creeper, Skeleton, Spider, Giant, Zombie, " +
                        "Slime, Ghast, PigZombie, Enderman, CaveSpider, Silverfish, Blaze, LavaSlime, EnderDragon, WitherBoss, Bat, Witch, Pig, Sheep, " +
                        "Cow, Chicken, Squid, Wolf, MushroomCow, SnowMan, Ozelot, VillagerGolem, Villager, EnderCrystal). Thanks to xmlns for use of his " +
                        "Mob Class from SpawnMob." +
                " Example: After entering \"/b en Sheep\" and \"/b 5\", clicking the sniper will spawn 5 sheep at the snipe-point." +
                " Note: You must have the animal or monster you wish to spawn enabled in Bukkit to be able to create it with VoxelSniper." +
                " /b en info: Shows a full list of available entities. "));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bEntityRemoval",
                "/b er: Removes all entities except itemframes and paintings within your brush size in chunks. "));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bEraser",
                "/b erase " +
                "Ever want to kill a build but didn't want to have to fix the land afterwards? " +
                        "The eraser brush deletes all blocks except sand, stone, dirt, grass, sandstone and gravel. " +
                        "The landscape remains more or less intact! If you want to ignore water as well, simply use the powder brush " +
                        "instead of the arrow brush. "));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bErode", ""));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bExtrude", ""));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bFillDown", "/b fd" +
                "" +
                " Useful for replacing drained water" +
                " Accepts the brush size command \"/b [radius]\" and voxel type \"/v [number]\"" +
                " Toggles \"Existing only mode\" with /b fd -e. "));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bFlatOcean", ""));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bGenerateTree", ""));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bHeatRay",
                "/b hr: The Heat Ray Brush burns nearby blocks and cinders others to a \"burned\" state by altering them to materials like cobblestone, " +
                        "or obsidian. Try torching a village with it! The Heat Ray Brush uses the brush size variable to widen or narrow it's beam." +
                " /b hr oct[int]: Octaves parameter for the noise generator." +
                " /b hr amp[float]: Amplitude parameter for the noise generator." +
                " /b hr freq[float]: Frequency parameter for the noise generator. "));
        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bJaggedLine",
                "/b j r[#] - Works much like the line brush, but with a small amount of noise thrown in." +
                " r[#] - Recursions (1-10, default 3) - How many times the brush goes back to try to fill in the line. " +
                        "More creates a smoother, more continous jagged line, while less leads to more gaps and a thinner jagged line." +
                " s[#] - Spread (default 3) "));
        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bJockey",
                "/b jockey: This brush allows you to sit on any other player or creature. Ride a slime, or just annoy the holy hell out of someone you " +
                        "wish would log off. Crotch + Face = FUN!" +
                " -i:[y|n] - Enables (y) or disables (n) the inverse mode. The inverse modes lets your target sit on you." +
                " -po:[y|n] - Enables (y) or disables (n) the player-only mode. If the player-only mode is enabled, only other players can used as a target." +
                " -s:[y|n] - Enables (y) or disables (n) the stack mode. If enabled, stacks all entities above the player within the players brush size. "));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bLightning", "/b light: Lightning will strike the block sniped. "));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bLine",
                "The line brush created geometrically calculated single-block-thin lines of the /v Voxel Select material. " +
                "To use the line brush, simply snipe with the arrow tool to set the first point, followed by a snipe with the gunpowder tool." +
                        "The line brush can be used to calculate easy roads, bridges and more."));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bMove", "The Move Brush will move a selection by a specified amount of blocks. (Max 5000000 blocks)      /b mv x[#] y[#] z[#] - Specifies the amount and direction the selection will be moved. (X: positive number => east, Y: positive number => up, Z: positive number => south).  /b mv reset - Resets the amount and direction to 0 "));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bOcean", "This oceanator turns mountains upside-down with a single click, and fills everything below sea-level with water, creating larger, deeper waterways and oceans on your map with a convincing ocean floor, like those on The Voxel Box world of Pangea. When the Land-Inversion Brush is active, the the arrow and gunpowder behave very differently from one another compared to the other brushes. The arrow will invert the landscape ONE chunk (a 16x16 grid of blocks from the vertical minimum to the vertical limit) at a time, whereas the gunpowder will target NINE chunks at once, centered on the targeted chunk."));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bOverlay", "The Overlay Brush will \"spray-paint\" the top most blocks in its area to the blocktype set by your \"/v\" command. This can be used to easily clean up any exposed materials once you are done with the erosion brush, or to create new fill material two link two pieces of landscape together. "));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bPainting", "The Painting Picker Brush allows a sniper to use the Arrow and Gunpowder to cycle through available painting styles until the desired painting is reached.     Note: You must be within arm's length of the painting to use your tools in this fashion.  Note: Any player (not just snipers!) may access this function by targeting a painting with arm's reach and using the /paint (0-24) command. "));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bPull", "The punish brush will apply a specific punishment to everybody in the brush size radius. /b p [punishment]  You can list all available punishments by typing: /b p info.  Some punishments accept an additional potion level parameter. This will basically define how \"intensive\" the effect is and the default is 10. This is specified with /vc [level]. You can also define the length in seconds with /vh [duration]."));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bPunish", "The punish brush will apply a specific punishment to everybody in the brush size radius. /b p [punishment]  You can list all available punishments by typing: /b p info.  Some punishments accept an additional potion level parameter. This will basically define how \"intensive\" the effect is and the default is 10. This is specified with /vc [level]. You can also define the length in seconds with /vh [duration]."));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bRandomErode", ""));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bRegenerateChunk", ""));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bRing",
                "/b ri Creates a ring based off where you clicked." +
                " /b ri true|false Use true or false circle algorithm. False is default." +
                " /b ri ir[#] Sets the inner radius of the ring. "));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bRot2d", ""));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bRot2dvert", ""));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bRot3d", ""));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bRuler",
                "/b r: The Ruler Brush allows a sniper to use a snipe with the Arrow and a snipe with Gunpowder to define two points. " +
                        "VS will then print measurements in the change in X, Y and Z, along with a Euclidean distance."));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bScanner", ""));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bSet",
                "/b set " +
                "This brush sets all the blocks within a region to the voxel material (ex: /v stone). " +
                        "The region is defined by two snipes. For this brush, the arrow tool will set the point as the block sniped, " +
                        "and the gunpowder tool will set the point on the sniped block face. After the first point is selected, you will recieve a " +
                        "message \"Point One\". When the seconds point is selected, the region will be automatically filled. " +
                        "The first point must be reset each time. "));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bSetRedstoneFlip", ""));

        String shell = "/b shb (Shell Ball)" +
                " /b shv (Shell Voxel) " +
                "This is a hollow-out exclude-based replace brush: Any block within the brush area that does NOT have at least one face exposed to the /vr Voxel Replace material is replaced with the /v Voxel material." +
                " Note: These brushes do not use Performers " +
                "Example Uses:" +
                " /vr 0 and /v 0 to empty out the inside of a giant tree for a elven home, or a cave from blocks of rock." +
                " /vr 9 and /v 0 to make a floating lake that doesn't spill." +
                " /vr 0 and /v 10 Use this to fill a solid block of glass tank with lava. The shell brush will always leave your structures liquid-tight! ";
        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bShellBall", shell));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bShellSet",
                "/b set " +
                "This brush sets all the blocks within a region to the voxel material (ex: /v stone). The region is defined by two snipes. For this brush, the arrow tool will set the point as the block sniped, and the gunpowder tool will set the point on the sniped block face. After the first point is selected, you will recieve a message \"Point One\". " +
                "When the seconds point is selected, the region will be automatically filled. The first point must be reset each time. "));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bShellVoxel", shell));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bSignOverwrite",
                "The sign overwrite brush overwrites the text of signs. /b sio" +
                "The arrow sets one/multiple sign/signs. The powder tool will read the text of the targeted sign into the internal buffer." +
                "Available parameters:" +
                " -1 [...] - Sets the first text line of the internal buffer. (e.g. /b sio -1 This is text.)" +
                " -2 [...] - Sets the second text line of the internal buffer. (e.g. /b sio -2 This is text.)" +
                " -3 [...] - Sets the third text line of the internal buffer. (e.g. /b sio -3 This is text.)" +
                " -4 [...] - Sets the fourth text line of the internal buffer. (e.g. /b sio -4 This is text.)" +
                " -clear - Clears the internal buffer by setting all lines to an empty string. (Alias: -c)" +
                " -multiple [on|off] - Enables or disables the ranged mode. With ranged mode enabled all sings in a box (with x=z=2*brushSize+1 and y=2*voxelHeight+1) will be set. " +
                "Keep in mind that the maximum length of a sign line is 15. "));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bSnipe", ""));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bSnowcase", ""));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bSpiralStaircase", "/b sstair: This brush has four modes of making a spiral staircase, each of which automates the build process of a common type of spiral staircase. In all three, the brush size (ex: /b 3) determines the radius of the stairwell, and the brush height determines the number of stairs (ex: /vh 10). " +
                "You may also control the turning direction of the stairwell (ex: /b sstair cc for counterclockwise) and the direction that the first stair faces (ex: /b sstair e for east). Using the gunpowder brush will build the staircase above the target block, and the arrow brush will dig the staircase below the target block. This Brush does not" +
                "Block Mode (default) - /b sstair block" +
                " This mode uses your voxel material (ex: /v stone) to place single blocks around the edge of the stairwell. " +
                "Step Mode - /b sstair step" +
                " This mode alternates between step and double step, rather than using a single voxel material. You will need to set your ink (ex: /vi 1) to get the different types of slabs. " +
                "Wood or Cobblestone Stairs Mode - /b sstair woodstair or /b sstair cobblestair" +
                " These modes use your voxel material (ex: /v stone) to place single blocks at the corners of the staircase and fills in with either wooden stairs or cobblestone stairs, respectively. " +
                " Note: This brush does not use Performers "));
        String splatter = "Each of these brushes have three important parameters besides the usual brush size and voxel material. These are seed percent, growth percent and recursions. Seed percent determines how likely it is for the brush to generate a new cluster (or \"seed\") within the brush area. " +
                "Growth percent determines how likely it is for new voxels to be spawned adjacent to each seed. For example, a high seed percent and low growth percent would probably fill the brush area with almost totally random 1x1x1 static. " +
                "In contrast, a low seed percent and a high growth percent would fill the brush area with just one or two big clumps. Both percent parameters vary from 1 to 9999 with the default being 1000 - representing 10.00 percent. The number of recursions determines how far from the original seeds that new voxels can grow. " +
                "A recursion of 3 (the default) means that a seed can only grow out 3 steps in one of the cardinal directions, at most. Recursion can be adjusted from 1 to 10.";
        String examples = "  Example: /b sd g2500 + /v 38 + /b5 will place flowers in a natural looking patch." +
                " Example: /b sb g3300 + /v 18 + /b 4 can be used to create neatly trimmed trees that aren't perfect spheres. ";
        String notes = "The splatter overlay also supports a Y-offset parameter (yoff[n]) and a height randomization argument (randh to toogle random height). ";
        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bSplatterBall", "/b sb: Splatter Ball (sphere) This collection of brushes provides a variety of ways to create blocks in predictable shapes without be outright geometric solids. If you've ever used something like photoshop, the idea is rather like turning down the density on a brush. Most of the splatter brushes are variations on the basic sized brushes: "));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bSplatterDisc", splatter));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bSplatterOverlay",
                "/b sover s[#] g[#] r[#]: This brush combines the functions of the Splatter and Overlay Brushes. " +
                        "The /v Voxel Select value is used to paint in, as an overlay brush would, using the seed (s), growth (g) and recursion (r) of the splatter. "));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bSplatterVoxel", splatter));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bSplatterVoxelDisc", splatter));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bSpline",
                "  /b sp: This brush is used to create a spline. You can use either end points or control points to create your spline. " +
                        "To select these points, use the Gunpowder to select blocks, and use the arrow to remove them." +
                "  /b sp ss: Enable endpoint selection mode for desired curve." +
                "  /b sp sc: Enable control point selection mode for desired curve." +
                "  /b sp clear: Clear out the curve selection." +
                "  /b sp ren: Render curve from control points. "));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1,
                "&bStencil", "Stencil works similarly to schematics, you select an area, save it and later load it and paste it. The max cuboid size for Stencil is 100k blocks." +
                "  /b st: Shows in-game help." +
                "  /b st [fill|full|replace] [name]: Selects the paste method for stencil and the stencil to be pasted. If no paste method is given, it defaults to fill. " +
                "To make a new stencil, type /b st [name] and select a cuboid with first two arrow right clicks, third arrow right click will select paste point. " +
                "Right click with gunpowder to paste." +
                "  fill: Paste only into air blocks." +
                "  full: Paste all blocks." +
                "  replace: Paste only into existing blocks."));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bStencilList",
                "StencilList brush takes existing stencils and pastes them in a random order. This can be useful for example pasting a set of trees. " +
                "To create a StencilList, navigate to plugins\\VoxelSniper\\stencilLists\\ and create a .txt file, the name of this file is the same as your " +
                        "stencilList name. The contents of the file is each stencil name on their own line." +
                "Eg:" +
                "tree1" +
                "tree2" +
                "tree3" +
                "To load a stencilList, you type /b sl [name]. Now when you right-click with your arrow, " +
                        "it pastes randomly one of the stencils defined in the file. "));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1,
                "&bThreePointCircle", "/b tpc accurate|default|smooth - Use the arrow brush to select three points, which form the corners of a triangle. " +
                        "Use the powder brush to have VoxelSniper place the circle that circumscribes that triangle. " +
                "Works at ANY angle, although some angles work better than others." +
                " accurate|default|smooth - Toggles how picky this brush is about placing blocks." +
                "  accurate - Only those blocks which are as close to mathematically perfect as possible." +
                "  default - A balanced mode which is generally pretty accurate, but can still leave gaps at some odd angles." +
                "  smooth - The brush will place every block that could reasonably be expected to be part of the circle. " +
                "Tends to make the discs thicker. May still miss a few blocks in some worst-case scenarios, but will get most of just about any circle. " +
                "" +
                "Note: Only available in versions 5.155 and higher. "));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bTreeSnipe", ""));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bTriangle", "  /b tri " +
                "Right-click three times with the arrow to select the corners of a triangle. Then activate the brush with a right-click of powder to create a smooth polygon surface of your voxel material between them. " +
                "This brush suffers from the granularity of minecraft's voxels. Because the blocks must always be placed at an integer coordinate, sometimes a block or two gets left out - especially around the edges or in a particularly steep triangle. " +
                "Since the earliest version of the brush, some redundancy has been built in to improve the performance, but it will never be perfect unless minecraft's coordinate system changes. "));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bUnderlay",
                "/b under d[#]: The Underlay is essentially the opposite of the Overlay Brush and will paint the ceilings of caves and buildings by your /v command. The (d) variable determines the depth of the brush. " +
                "This will be how high you want the brush to penetrate "));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bVoltmeter", "/b volt: The Volt-Meter Brush allows a sniper to use the Arrow and Gunpowder to get redstone power distance/information." +
                " Right-click with your arrow to get current flow (six directional) information." +
                " Right-click with your gunpowder to get a voltage reading. " +
                "The readout will tell you the number of remaining blocks before the line will require a repeater. "));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bVoxel",
                "The Voxel Brush is similar to the Ball Brush, but it generates a full cube of material. Great for clearing out underground tunnels with air-cubes."));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bVoxelDisc",
                "The Voxel Disc Brush is similar to the Disc Brush, but it generates a flat square of material instead of a circle. Instant flooring!"));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1,
                "&bVoxelDiscFace", "The Voxel Disc Face Brush behaves like the Disc Face Brush, but in a square shape. Use this brush to throw up some quick walls around a project."));

        itemStacks.add(ItemUtil.createItem(Material.SULPHUR, 1, "&bWarp",
                "/b w: This brush will warp you to the block sniped using the arrow. " +
                "The gunpowder will create a lightning bolt at the sniped block, as well: Amaze and astound your friends! "));

        List<IconMenu> iconMenus = VoxelSniperGUI.getInstance().getBrushTypeMenus();
        IconMenu iconMenu = GUIUtil.newPage(page, "", VoxelSniperGUI.getInstance().getBrushTypeMenus(), this);
        Iterator iterator = itemStacks.iterator();
        while (iterator.hasNext()) {
            ItemStack itemStack = (ItemStack) iterator.next();
            if (itemStack != null && itemStack.hasItemMeta() && itemStack.getItemMeta().hasDisplayName()) {
                if (iconMenu == null) {
                    continue;
                }
                if (currentSlot > max) {
                    GUIUtil.finishMenu(iconMenu, iconMenus);
                    currentSlot = 0;
                    page++;
                    iconMenu = GUIUtil.newPage(page, "", iconMenus, this);
                }
                iconMenu.setOption(currentSlot, itemStack);
                currentSlot++;
            }
        }
        GUIUtil.finishMenu(iconMenu, iconMenus);
        /*Multimap<Class<? extends IBrush>, String> multiMap = VoxelSniperGUI.getInstance().getVoxelSniper().getBrushManager().getRegisteredBrushesMultimap();
        Iterator iterator = multiMap.keySet().iterator();
        List<String> brushes = Lists.newArrayList();
        while (iterator.hasNext()) {
            Class key = (Class) iterator.next();
            String brushType = key.getSimpleName().replace("Brush", "");
            if (brushType != null) {
                brushes.add(brushType);
            }
        }
        Collections.sort(brushes);
        String between = firstLetter + "-" + secondLetter;
        List<IconMenu> iconMenus = VoxelSniperGUI.getInstance().getBrushTypeMenus();
        IconMenu iconMenu = GUIUtil.newPage(page, between, VoxelSniperGUI.getInstance().getBrushTypeMenus(), this);
        for (int i = 0; i < brushes.size(); i++) {
            String brush = brushes.get(i);
            if (iconMenu == null) {
                continue;
            }
            if (currentSlot > max) {
                GUIUtil.finishMenu(iconMenu, iconMenus);
                currentSlot = 0;
                page++;
                firstLetter = brushes.get(i).substring(0, 1);
                if (brushes.size() < i + 44) {
                    secondLetter = brushes.get(brushes.size() - 1).substring(0, 1);
                } else {
                    secondLetter = brushes.get(i + 44).substring(0, 1);
                }
                between = firstLetter + "-" + secondLetter;
                iconMenu = GUIUtil.newPage(page, between, iconMenus, this);
            }
            iconMenu.setOption(currentSlot, ItemUtil.createItem(Material.SULPHUR, "&b" + brush, new String[]{}));
            currentSlot++;
        }*/
    }

    public static IconMenu openMenu(Player player, int page) {
        List<IconMenu> iconMenus = VoxelSniperGUI.getInstance().getBrushTypeMenus();
        if (iconMenus.size() >= page) {
            iconMenus.get(page).open(player);
            return iconMenus.get(page);
        } else {
            iconMenus.get(0).open(player);
            return iconMenus.size() > 0 ? iconMenus.get(0) : null;
        }
    }

    public IconMenu getNewMenu(final int page, String between) {
        return new IconMenu(VoxelSniperGUI.getInstance().getConfig().getString(ConfigValues.BRUSHTYPE_MENU_NAME.getPath()) + " (Page " + page + ") " + between, 54, new IconMenu.OptionClickEventHandler() {
            public void onOptionClick(IconMenu.OptionClickEvent event) {
                int position = event.getPosition();
                Player player = event.getPlayer();
                if (position < 45) {
                    String displayName = ChatColor.stripColor(event.getName());
                    Bukkit.dispatchCommand(player, "b " + displayName);
                    SoundUtil.playSoundEffect(player);
                    VoxelSniperGUI.openMenu(player, VoxelSniperGUI.getInstance().getMainMenu());
                } else if (position == 48) {
                    if (page - 2 < 0) {
                        player.sendMessage(ChatUtils.fixColor("&cNo pages that way."));
                    } else {
                        BrushTypeMenu.openMenu(player, page - 2);
                    }
                } else if (position == 49) {
                    VoxelSniperGUI.openMenu(player, VoxelSniperGUI.getInstance().getMainMenu());
                } else if (position == 50) {
                    List<IconMenu> iconMenus = VoxelSniperGUI.getInstance().getBrushTypeMenus();
                    if (page >= iconMenus.size()) {
                        player.sendMessage(ChatUtils.fixColor("&cNo pages that way."));
                    } else {
                        BrushTypeMenu.openMenu(player, page);
                    }
                }
                event.setWillClose(false);
                event.setWillDestroy(false);
            }
        }, VoxelSniperGUI.getInstance())
                .finishCreating();
    }
}
