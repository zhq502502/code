package com.packages.util;

public final class SK_Function {
	
	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5',
		'6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
	
	public static boolean areEqual(byte[] a, byte[] b) {
		
		int aLength = a.length;
		if (aLength != b.length)
			return false;
		for (int i = 0; i < aLength; i++)
			if (a[i] != b[i])
				return false;
		return true;
	}
	
	public static String byteToString(byte n) {
		
		char[] buf = { HEX_DIGITS[(n >>> 4) & 0x0F], HEX_DIGITS[n & 0x0F] };
		return new String(buf);
	}
	
	public static String intToString(int n) {
		
		char[] buf = new char[8];
		for (int i = 7; i >= 0; i--) {
			buf[i] = HEX_DIGITS[n & 0x0F];
			n >>>= 4;
		}
		return new String(buf);
	}
	
	public static String toString(byte[] ba) {
		
		int length = ba.length;
		char[] buf = new char[length * 2];
		for (int i = 0, j = 0, k; i < length;) {
			k = ba[i++];
			buf[j++] = HEX_DIGITS[(k >>> 4) & 0x0F];
			buf[j++] = HEX_DIGITS[k & 0x0F];
		}
		return new String(buf);
	}
	
	public static String toString(int[] ia) {
		
		int length = ia.length;
		char[] buf = new char[length * 8];
		for (int i = 0, j = 0, k; i < length; i++) {
			k = ia[i];
			buf[j++] = HEX_DIGITS[(k >>> 28) & 0x0F];
			buf[j++] = HEX_DIGITS[(k >>> 24) & 0x0F];
			buf[j++] = HEX_DIGITS[(k >>> 20) & 0x0F];
			buf[j++] = HEX_DIGITS[(k >>> 16) & 0x0F];
			buf[j++] = HEX_DIGITS[(k >>> 12) & 0x0F];
			buf[j++] = HEX_DIGITS[(k >>> 8) & 0x0F];
			buf[j++] = HEX_DIGITS[(k >>> 4) & 0x0F];
			buf[j++] = HEX_DIGITS[k & 0x0F];
		}
		return new String(buf);
	}	
	
	public static long b2iu(byte b) {
		
		return b < 0 ? b & 0x7F + 128 : b;
	}	
	 /*
     * Methods for unpacking primitive values from byte arrays starting at
     * given offsets.
     */

	public static boolean getBoolean(byte[] b, int off) {
		return b[off] != 0;
    }
    
	public static char getChar(byte[] b, int off) {
		return (char) (((b[off + 1] & 0xFF) << 0) + 
		     ((b[off + 0] & 0xFF) << 8));
    }
    
	public static short getShort(byte[] b, int off) {
		return (short) (((b[off + 1] & 0xFF) << 0) + 
			((b[off + 0] & 0xFF) << 8));
    }
    
	public static int getInt(byte[] b, int off) {
		return ((b[off + 3] & 0xFF) << 0) +
	       	((b[off + 2] & 0xFF) << 8) +
	       	((b[off + 1] & 0xFF) << 16) +
	       	((b[off + 0] & 0xFF) << 24);
    }
    
	public static float getFloat(byte[] b, int off) {
		int i = ((b[off + 3] & 0xFF) << 0) +
			((b[off + 2] & 0xFF) << 8) +
			((b[off + 1] & 0xFF) << 16) +
			((b[off + 0] & 0xFF) << 24);
		return Float.intBitsToFloat(i);
    }
    
	public static long getLong(byte[] b, int off) {
		return ((b[off + 7] & 0xFFL) << 0) +
	       ((b[off + 6] & 0xFFL) << 8) +
	       ((b[off + 5] & 0xFFL) << 16) +
	       ((b[off + 4] & 0xFFL) << 24) +
	       ((b[off + 3] & 0xFFL) << 32) +
	       ((b[off + 2] & 0xFFL) << 40) +
	       ((b[off + 1] & 0xFFL) << 48) +
	       ((b[off + 0] & 0xFFL) << 56);
    }

	public static double getDouble(byte[] b, int off) {
		long j = ((b[off + 7] & 0xFFL) << 0) +
		 	((b[off + 6] & 0xFFL) << 8) +
		 	((b[off + 5] & 0xFFL) << 16) +
		 	((b[off + 4] & 0xFFL) << 24) +
		 	((b[off + 3] & 0xFFL) << 32) +
		 	((b[off + 2] & 0xFFL) << 40) +
		 	((b[off + 1] & 0xFFL) << 48) +
		 	((b[off + 0] & 0xFFL) << 56);
		return Double.longBitsToDouble(j);
    }
    
    /*
     * Methods for packing primitive values into byte arrays starting at given
     * offsets.
     */

	public static void putBoolean(byte[] b, int off, boolean val) {
		b[off] = (byte) (val ? 1 : 0);
    }

	public static void putChar(byte[] b, int off, char val) {
		b[off + 1] = (byte) (val >>> 0);
		b[off + 0] = (byte) (val >>> 8);
    }

	public static void putShort(byte[] b, int off, short val) {
		b[off + 1] = (byte) (val >>> 0);
		b[off + 0] = (byte) (val >>> 8);
    }

	public static void putInt(byte[] b, int off, int val) {
		b[off + 3] = (byte) (val >>> 0);
		b[off + 2] = (byte) (val >>> 8);
		b[off + 1] = (byte) (val >>> 16);
		b[off + 0] = (byte) (val >>> 24);
    }

	public static void putFloat(byte[] b, int off, float val) {
		int i = Float.floatToIntBits(val);
		b[off + 3] = (byte) (i >>> 0);
		b[off + 2] = (byte) (i >>> 8);
		b[off + 1] = (byte) (i >>> 16);
		b[off + 0] = (byte) (i >>> 24);
    }

	public static void putLong(byte[] b, int off, long val) {
		b[off + 7] = (byte) (val >>> 0);
		b[off + 6] = (byte) (val >>> 8);
		b[off + 5] = (byte) (val >>> 16);
		b[off + 4] = (byte) (val >>> 24);
		b[off + 3] = (byte) (val >>> 32);
		b[off + 2] = (byte) (val >>> 40);
		b[off + 1] = (byte) (val >>> 48);
		b[off + 0] = (byte) (val >>> 56);
    }

	public static void putDouble(byte[] b, int off, double val) {
		long j = Double.doubleToLongBits(val);
		b[off + 7] = (byte) (j >>> 0);
		b[off + 6] = (byte) (j >>> 8);
		b[off + 5] = (byte) (j >>> 16);
		b[off + 4] = (byte) (j >>> 24);
		b[off + 3] = (byte) (j >>> 32);
		b[off + 2] = (byte) (j >>> 40);
		b[off + 1] = (byte) (j >>> 48);
		b[off + 0] = (byte) (j >>> 56);
    }
   

}
