/*
 * Minecraft Forge
 * Copyright (c) 2016-2021.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation version 2.1
 * of the License.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

package net.minecraftforge.network;

import it.unimi.dsi.fastutil.objects.Reference2ReferenceArrayMap;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.login.ServerboundCustomQueryPacket;
import net.minecraft.network.protocol.login.ClientboundCustomQueryPacket;
import net.minecraft.network.protocol.game.ServerboundCustomPayloadPacket;
import net.minecraft.network.protocol.game.ClientboundCustomPayloadPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.unsafe.UnsafeHacks;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface ICustomPacket<T extends Packet<?>> {
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    enum Fields {
        CPACKETCUSTOMPAYLOAD(ServerboundCustomPayloadPacket.class),
        SPACKETCUSTOMPAYLOAD(ClientboundCustomPayloadPacket.class),
        CPACKETCUSTOMLOGIN(ServerboundCustomQueryPacket.class),
        SPACKETCUSTOMLOGIN(ClientboundCustomQueryPacket.class),
        ;

        static final Reference2ReferenceArrayMap<Class<?>, Fields> lookup;
        static {
            lookup = Stream.of(values()).
                    collect(Collectors.toMap(Fields::getClazz, Function.identity(), (m1, m2)->m1, Reference2ReferenceArrayMap::new));
        }

        private final Class<?> clazz;

        final Optional<Field> data;
        final Optional<Field> channel;
        final Optional<Field> index;

        Fields(Class<?> customPacketClass)
        {
            this.clazz = customPacketClass;
            Field[] fields = customPacketClass.getDeclaredFields();
            data = Arrays.stream(fields).filter(f-> !Modifier.isStatic(f.getModifiers()) && f.getType() == FriendlyByteBuf.class).findFirst();
            channel = Arrays.stream(fields).filter(f->!Modifier.isStatic(f.getModifiers()) && f.getType() == ResourceLocation.class).findFirst();
            index = Arrays.stream(fields).filter(f->!Modifier.isStatic(f.getModifiers()) && f.getType() == int.class).findFirst();
        }

        private Class<?> getClazz()
        {
            return clazz;
        }
    }

    default FriendlyByteBuf getInternalData() {
        return Fields.lookup.get(this.getClass()).data.map(f->UnsafeHacks.<FriendlyByteBuf>getField(f, this)).orElse(null);
    }

    default ResourceLocation getName() {
        return Fields.lookup.get(this.getClass()).channel.map(f->UnsafeHacks.<ResourceLocation>getField(f, this)).orElse(LoginWrapper.WRAPPER);
    }

    default int getIndex() {
        return Fields.lookup.get(this.getClass()).index.map(f->UnsafeHacks.getIntField(f, this)).orElse(Integer.MIN_VALUE);
    }

    default void setData(FriendlyByteBuf buffer) {
        Fields.lookup.get(this.getClass()).data.ifPresent(f->UnsafeHacks.setField(f, this, buffer));
    }

    default void setName(ResourceLocation channelName) {
        Fields.lookup.get(this.getClass()).channel.ifPresent(f->UnsafeHacks.setField(f, this, channelName));
    }

    default void setIndex(int index) {
        Fields.lookup.get(this.getClass()).index.ifPresent(f->UnsafeHacks.setIntField(f, this, index));
    }

    default NetworkDirection getDirection() {
        return NetworkDirection.directionFor(this.getClass());
    }

    @SuppressWarnings("unchecked")
    default T getThis() {
        return (T)this;
    }
}
