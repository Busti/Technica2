package de.honeypot.technica.util;

public interface IEnumVariants<E extends Enum<E>> {
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