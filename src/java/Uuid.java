import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.UUID;

public class Uuid {
    private static String randomId() {

        // Create random UUID
        UUID uuid = UUID.randomUUID();

        // Internally UUID is represented as two long integers (2 long = 128 bits) which can be accessed by
        // uuid.getMostSignificantBits() (MSB) and uuid.getLeastSignificantBits() (LSB)

        // Create byte[] for base64 from uuid
        byte[] src = ByteBuffer.wrap(new byte[16])
                .putLong(uuid.getMostSignificantBits())
                .putLong(uuid.getLeastSignificantBits())
                .array();

        // Encode to Base64 and remove trailing ==
        return Base64.getUrlEncoder().encodeToString(src).substring(0, 22);
    }

    public static void main(String[] args) {
        System.out.println(randomId());
        // Ah2_xI48RWSGwGDcNfbcGQ
        // eLQuFAB1QRyWY_DHYxUX4Q
        // oUSCSgEEQCqw1wlGT_kiSw
        // R_0tJRgqQDGGVT4kXFli_A
        // SqrVhuCsQlmoiiIn5Pgpiw
        // FMiXuPLFQwu7yINCqBt-yQ
        // UQk9E_8ZSaufMhe33Yh6CA
        // Uj8RweXQRh-Lj5J0CrI5Vw
        // d8bkw3SeStW-nS7SFMUV4A
        // -1b351PDTyqaVQ8OhsrAyQ
        // Hexadecimal uuid f487fbcf-3606-4f35-a23d-5ac3af6de754 generated using UUID.randomUUID().toString() has 36 bytes including the -.
        // Base64 uuid 9If7zzYGTzWiPVrDr23nVA generated using uuid.randomId() has only 22 bytes.
    }
}
