package net.minecraft.world.biome;

import fr.maxlego08.kronos.init.Blocks;
import net.minecraft.entity.passive.EntityMooshroom;

public class BiomeGenMushroomIsland extends BiomeGenBase
{
    private static final String __OBFID = "CL_00000177";

    public BiomeGenMushroomIsland(int p_i1984_1_)
    {
        super(p_i1984_1_);
        this.theBiomeDecorator.treesPerChunk = -100;
        this.theBiomeDecorator.flowersPerChunk = -100;
        this.theBiomeDecorator.grassPerChunk = -100;
        this.theBiomeDecorator.mushroomsPerChunk = 1;
        this.theBiomeDecorator.bigMushroomsPerChunk = 1;
        this.topBlock = Blocks.mycelium;
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityMooshroom.class, 8, 4, 8));
    }
}
