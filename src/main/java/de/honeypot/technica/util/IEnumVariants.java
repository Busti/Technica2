package de.honeypot.technica.util;

import net.minecraft.util.IStringSerializable;

public interface IEnumVariants<E extends Enum<E> & IEnumVariants<E>> extends IStringSerializable {
    /**
     * Get the Metadata of the Variant
     * @return The Metadata
     */
    int getMetadata();

    /**
     * Get a Variant by its metadata.
     * @return The Variant Enum entry.
     */
    E byMetadata(int meta);
}