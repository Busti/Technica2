package de.honeypot.technica.block;

public interface IEnumVariants<E extends Enum<E>> {
    E byMetadata(int meta);
}
