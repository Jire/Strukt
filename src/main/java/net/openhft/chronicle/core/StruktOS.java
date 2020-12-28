package net.openhft.chronicle.core;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.channels.FileChannel;

import static net.openhft.chronicle.core.OS.*;

public final class StruktOS {
	
	public static long map(@NotNull FileChannel fileChannel, FileChannel.MapMode mode, long start, long size)
			throws IOException, IllegalArgumentException {
		if (isWindows() && !is64Bit() && size > 4L << 30)
			throw new IllegalArgumentException(
					"Mapping more than 4096 MiB is unusable on 32-bit Windows, size = " + (size >> 20) + " MiB");
		return map0(fileChannel, imodeFor(mode), mapAlign(start), pageAlign(size));
	}
	
	private StruktOS() {
		throw new UnsupportedOperationException();
	}
	
}
