package me.spacemex.spacelib.enums;

public enum LootType {
    MINESHAFT("minecraft:chests/abandoned_mineshaft","abandoned_mineshaft"),
    ANCIENT_CITY("minecraft:chests/ancient_city","ancient_city"),
    ANCIENT_CITY_ICE_BOX("minecraft:chests/ancient_city_ice_box","ancient_city_ice_box"),
    BASTION_BRIDGE("minecraft:chests/bastion_bridge","bastion_bridge"),
    BASTION_HOGLIN_STABLE("minecraft:chests/bastion_hoglin_stable","bastion_hoglin_stable"),
    BASTION_OTHER("minecraft:chests/bastion_other","bastion_other"),
    BASTION_TREASURE("minecraft:chests/bastion_treasure","bastion_treasure"),
    BURIED_TREASURE("minecraft:chests/buried_treasure","buried_treasure"),
    DESERT_PYRAMID("minecraft:chests/desert_pyramid","desert_pyramid"),
    END_CITY_TREASURE("minecraft:chests/end_city_treasure","end_city_treasure"),
    IGLOO_CHEST("minecraft:chests/igloo_chest","igloo_chest"),
    JUNGLE_TEMPLE("minecraft:chests/jungle_temple","jungle_temple"),
    JUNGLE_TEMPLE_DISPENSER("minecraft:chests/jungle_temple_dispenser","jungle_temple_dispenser"),
    NETHER_BRIDGE("minecraft:chests/nether_bridge","nether_bridge"),
    PILLAGER_OUTPOST("minecraft:chests/pillager_outpost","pillager_outpost"),
    RUINED_PORTAL("minecraft:chests/ruined_portal","ruined_portal"),
    SHIPWRECK_MAP("minecraft:chests/shipwreck_map","shipwreck_map"),
    SHIPWRECK_SUPPLY("minecraft:chests/shipwreck_supply","shipwreck_supply"),
    SHIPWRECK_TREASURE("minecraft:chests/shipwreck_treasure","shipwreck_treasure"),
    SIMPLE_DUNGEON("minecraft:chests/simple_dungeon","simple_dungeon"),
    SPAWN_BONUS_CHEST("minecraft:chests/spawn_bonus_chest","spawn_bonus_chest"),
    STRONGHOLD_CORRIDOR("minecraft:chests/stronghold_corridor","stronghold_corridor"),
    STRONGHOLD_CROSSING("minecraft:chests/stronghold_crossing","stronghold_crossing"),
    STRONGHOLD_LIBRARY("minecraft:chests/stronghold_library","stronghold_library"),
    UNDERWATER_RUIN_BIG("minecraft:chests/underwater_ruin_big","underwater_ruin_big"),
    UNDERWATER_RUIN_SMALL("minecraft:chests/underwater_ruin_small","underwater_ruin_small"),
    VILLAGE_ARMORER("minecraft:chests/village/village_armorer","village_armorer"),
    VILLAGE_BUTCHER("minecraft:chests/village/village_butcher","village_butcher"),
    VILLAGE_CARTOGRAPHER("minecraft:chests/village/village_cartographer","village_cartographer"),
    VILLAGE_DESERT_HOUSE("minecraft:chests/village/village_desert_house","village_desert_house"),
    VILLAGE_FISHER("minecraft:chests/village/village_fisher","village_fisher"),
    VILLAGE_FLETCHER("minecraft:chests/village/village_fletcher","village_fletcher"),
    VILLAGE_MASON("minecraft:chests/village/village_mason","village_mason"),
    VILLAGE_PLAINS_HOUSE("minecraft:chests/village/village_plains_house","village_plains_house"),
    VILLAGE_SAVANNA_HOUSE("minecraft:chests/village/village_savanna_house","village_savanna_house"),
    VILLAGE_SHEPHERD("minecraft:chests/village/village_shepherd","village_shepherd"),
    VILLAGE_SNOWY_HOUSE("minecraft:chests/village/village_snowy_house","village_snowy_house"),
    VILLAGE_TAIGA_HOUSE("minecraft:chests/village/village_taiga_house","village_taiga_house"),
    VILLAGE_TANNERY("minecraft:chests/village/village_tannery","village_tannery"),
    VILLAGE_TEMPLATE("minecraft:chests/village/village_temple","village_temple"),
    VILLAGE_TOOLSMITH("minecraft:chests/village/village_toolsmith","village_toolsmith"),
    VILLAGE_WEAPONSMITH("minecraft:chests/village/village_weaponsmith","village_weaponsmith");
    private final String resourcelocation;
    private final String modifier;
    LootType(String resourcelocation, String modifier){
        this.resourcelocation=resourcelocation;
        this.modifier=modifier;
    }
    public String toString(){
        return resourcelocation;
    }
    public String getModifier(){
        return modifier;
    }
}
