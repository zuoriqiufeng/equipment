package com.bistu.equip.common.helper;

import io.jsonwebtoken.*;
import org.springframework.util.StringUtils;
import java.util.Date;

/**
 * Jwt工具类，用于生成对应的token
 * @author Dx666
 * @Description
 * @Date 2021/6/13 - 11:08
 */
public class JwtHelper {
	
	// token过期时间设置
	private static long tokenExpiration = 24 * 60 * 60 * 1000;
	// 签名密钥
	private static String tokenSignKey = "123456";
	
	// 根据参数生成token
	public static String createToken(Long userId, String userName) {
		String token = Jwts.builder()
				.setSubject("Equip-USER")
				.setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
				.claim("userId", userId)
				.claim("userName", userName)
				.signWith(SignatureAlgorithm.HS512, tokenSignKey)
				.compressWith(CompressionCodecs.GZIP)
				.compact();
		return token;
	}
	
	// 根据token字符串得到id
	public static Long getUserId(String token) {
		if (StringUtils.isEmpty(token))
			return null;
		Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
		Claims claims = claimsJws.getBody();
		Integer userId = (Integer) claims.get("userId");
		return userId.longValue();
	}
	
	/**
	 * 根据tolen字符串得到用户名
	 * @param token
	 * @return
	 */
	public static String getUserName(String token) {
		if (StringUtils.isEmpty(token))
			return "";
		Jws<Claims> claimsJws
				= Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
		Claims claims = claimsJws.getBody();
		return (String) claims.get("userName");
	}
	
	public static String getAuthStatus(String token) {
		if (StringUtils.isEmpty(token))
			return "";
		Jws<Claims> claimsJws
				= Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
		Claims claims = claimsJws.getBody();
		return (String) claims.get("authStatus");
	}
	
	public static void main(String[] args) {
		String token = createToken(1L, "丁哥");
		System.out.println(token);
		System.out.println(getUserId(token));
		System.out.println(getUserName(token));
		System.out.println(getAuthStatus(token));
	}
	
}
