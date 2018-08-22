package com.dekuofa.utils;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.Sha512Hash;

/**
 * @author ：gx <br>
 * 时间：2018年07月14日 21:12<br>
 * 标题：SHA64Encoder<br>
 * 功能：<br>
 */
public class ShaUtil {

    /**
     * SHA64加密字符串
     *
     * @param str 被加密的字符串
     * @return SHA64加密的字符串
     */
    public static String sha256Encode(String str) {
        return new Sha256Hash(str).toHex();
    }

    /**
     * SHA64加密字符串
     *
     * @param str 被加密的字符串
     * @return SHA64加密的字符串
     */
    public static String sha512Encode(String str) {
        return new Sha512Hash(str).toHex();
    }

}
