package xyz.kohara.stellarity.client.registry.particle;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;

import net.minecraft.client.particle.Particle;
import net.minecraft.core.particles.SimpleParticleType;

//? < 1.21.9 {

import net.minecraft.client.particle.*;
    //? } else {
/*import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;

import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.util.RandomSource;

import net.minecraft.client.particle.SpriteSet;
*///? }

@Environment(EnvType.CLIENT)
public class CriticalStrikeParticle extends /*? < 1.21.9 {*/TextureSheetParticle/*? } else {*//*SingleQuadParticle*//*? }*/ {
    private float scale = 1f;

    public CriticalStrikeParticle(ClientLevel clientLevel, double d, double e, double f/*? > 1.21.9 >> ') {'*//*, TextureAtlasSprite textureAtlasSprite*/) {
        super(clientLevel, d, e, f/*? > 1.21.9 >> ');'*//*, textureAtlasSprite*/);

        this.x = d;
        this.y = e;
        this.z = f;

        setLifetime(20);

        scale(1f);
    }

    @Override
    public void tick() {
        scale -= 0.05f;
        scale(scale);

        super.tick();

    }

    @Override
    protected int getLightColor(float f) {
        return 240;
    }

    //? < 1.21.9 {
    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }


    //? } else {

    /*@Override
    public Layer getLayer() {
        return Layer.OPAQUE;
    }

    *///? }
    
    @Environment(EnvType.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {


        private final SpriteSet sprite;

        public Provider(SpriteSet spriteSet) {
            this.sprite = spriteSet;
        }


        @Override
        public Particle createParticle(SimpleParticleType simpleParticleType, ClientLevel clientLevel, double d, double e, double f, double g, double h, double i/*? > 1.21.9 >> ') {'*//*, RandomSource randomSource*/) {
            var particle = new CriticalStrikeParticle(clientLevel, d, e, f/*? > 1.21.9 >> ');'*//*, this.sprite.get(randomSource)*/);
            //? < 1.21.9 {
            particle.pickSprite(this.sprite);
            //? }
            return particle;
        }


    }
}