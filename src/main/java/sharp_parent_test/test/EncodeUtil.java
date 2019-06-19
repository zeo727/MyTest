package sharp_parent_test.test;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.util.List;

/**
 * 加密、解密及转换
 * 
 * @author Pactera
 *
 */
public class EncodeUtil {
	private static final char last2bit = (char) Integer.parseInt("00000011", 2);
	private static final char last4bit = (char) Integer.parseInt("00001111", 2);
	private static final char last6bit = (char) Integer.parseInt("00111111", 2);
	private static final char lead6bit = (char) Integer.parseInt("11111100", 2);
	private static final char lead4bit = (char) Integer.parseInt("11110000", 2);
	private static final char lead2bit = (char) Integer.parseInt("11000000", 2);

	private static final char[] encodeTable = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
			'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g',
			'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1',
			'2', '3', '4', '5', '6', '7', '8', '9', '+', '/' };

	private static final String MAC_NAME = "HmacSHA1";
	private static final String ENCODEING = "utf8";

	/**
	 * 用HmacSHA1算法加密data生成哈希序列
	 * 
	 * @param data
	 *            数据
	 * @param key
	 *            密钥
	 * @return 加密后的序列
	 */
	public static String generateOpenWeatherKey(String data, String key) {
		byte[] byteHMAC = null;
		String urlEncoder = "";
		try {
			Mac mac = Mac.getInstance(MAC_NAME);
			SecretKeySpec spec = new SecretKeySpec(key.getBytes(), MAC_NAME);
			mac.init(spec);
			byteHMAC = mac.doFinal(data.getBytes());
			if (byteHMAC != null) {
				String oauth = encode64(byteHMAC);
				// Encoder encoder = Base64.getEncoder();
				// String oauth = encoder.encodeToString(byteHMAC);
				if (oauth != null) {
					urlEncoder = URLEncoder.encode(oauth, ENCODEING);
				}
			}
		} catch (InvalidKeyException e1) {

		} catch (Exception e2) {
		}
		return urlEncoder;
	}

	/**
	 * 基于base64算法处理获得key值
	 * 
	 * @param from
	 *            byte数组
	 * @return base64算法转化后的字符串
	 */
	public static String encode64(byte[] from) {

		StringBuilder str = new StringBuilder((int) (from.length * 1.34 + 3));

		byte currentByte;
		for (int i = 0; i < from.length; i += 3) {
			// 6-
			currentByte = (byte) ((from[i] & lead6bit) >>> 2);
			str.append(encodeTable[currentByte]);

			// 2,4
			currentByte = (byte) ((from[i] & last2bit) << 4);
			if (i + 1 <= from.length - 1) {
				currentByte += (byte) ((from[i + 1] & lead4bit) >>> 4);
				str.append(encodeTable[currentByte]);
			} else {
				str.append(encodeTable[currentByte]);
				str.append("=");
				str.append("=");
				break;
			}

			// 4,2
			currentByte = (byte) ((from[i + 1] & last4bit) << 2);
			if (i + 2 <= from.length - 1) {
				currentByte += (byte) ((from[i + 2] & lead2bit) >>> 6);
				str.append(encodeTable[currentByte]);
			} else {
				str.append(encodeTable[currentByte]);
				str.append("=");
				break;
			}

			// -6
			currentByte = (byte) (from[i + 2] & last6bit);
			str.append(encodeTable[currentByte]);
		}

		return str.toString();
	}

	/**
	 * 将16进制的字符串转换成相应byte数组
	 *
	 * @param code
	 *            字符串
	 * @return byte数组
	 */
	public static byte[] hexToByteArray(String code) {

		byte[] ret = null;

		if (code.length() >= 2) {
			ret = new byte[code.length() / 2];
			char[] array = code.toLowerCase().toCharArray();
			for (int i = 0; i < ret.length; i++) {
				char high = array[2 * i];
				char low = array[2 * i + 1];
				ret[i] = (byte) ((hexCharToValue(high) << 4) + hexCharToValue(low));
			}
		}
		return ret;
	}



	/**
	 * 16进制字符带
	 *
	 * @param c
	 *            16进制字符
	 * @return 对应的byte
	 */
	private static byte hexCharToValue(char c) {
		byte ret = 0;

		switch (c) {
			case '0':
				ret = 0;
				break;
			case '1':
				ret = 1;
				break;
			case '2':
				ret = 2;
				break;
			case '3':
				ret = 3;
				break;
			case '4':
				ret = 4;
				break;
			case '5':
				ret = 5;
				break;
			case '6':
				ret = 6;
				break;
			case '7':
				ret = 7;
				break;
			case '8':
				ret = 8;
				break;
			case '9':
				ret = 9;
				break;
			case 'a':
				ret = 10;
				break;
			case 'b':
				ret = 11;
				break;
			case 'c':
				ret = 12;
				break;
			case 'd':
				ret = 13;
				break;
			case 'e':
				ret = 14;
				break;
			case 'f':
				ret = 15;
				break;
			default:
				break;
		}
		return ret;
	}


	private static String gethexString(byte str) {
		int temp = str&0xff;
		String result = "";
		result = Integer.toHexString(temp);
		if (result.length() == 1) {
			result = "0" + result;
		}
		return result;
	}


	/**
	 * 返回byte的正整数值
	 *
	 * @param b
	 *            byte数据
	 * @return 正整数值
	 */
	public static int unsignedInt(byte b) {
		return (int) b & 0xFF;
	}

	/**
	 * 将十进制整形数组转为byte数组
	 * 
	 * @param
	 * @return byte数组
	 */
	public static byte[] getByteFromIntList(List<Integer> valueList) {

		byte[] ret = null;

		if (valueList != null) {
			ret = new byte[valueList.size()];
			for (int i = 0; i < valueList.size(); i++) {
				ret[i] = (byte) (valueList.get(i).intValue() & 0xFF);
			}
		}
		return ret;
	}

}
