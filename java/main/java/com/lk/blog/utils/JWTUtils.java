package com.lk.blog.utils;

import com.lk.blog.entity.User;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author likeLove
 * @time 2020-09-14  12:19
 */
@Slf4j
public class JWTUtils {

    //过期时间
    public static final Integer expire = 1000 * 60 * 30;
    public static final String App_secret = "likelove";

    public static String genJwtToken(User user) {
        //创建builder对象
        JwtBuilder builder = Jwts.builder();
        //第一步部分 jwt 头部 header
        builder.setHeaderParam("alg", "HS256");//签名算法
        builder.setHeaderParam("typ", "JWT");//令牌类型

        //第二部分 有效载荷 playload
        builder.setId("1");//唯一标识
        builder.setSubject("blog-of-likelive");//令牌主题
        builder.setIssuedAt(new Date(System.currentTimeMillis()));
        builder.setExpiration(new Date(System.currentTimeMillis() + expire));//过期时间
        //私有字段
        String uid = user.getUid();
        String password = user.getPassword();
        builder.claim("uid", uid);
        builder.claim("password", password);

        //第三部分，签名哈希
        builder.signWith(SignatureAlgorithm.HS256, App_secret);

        //连接起来
        return builder.compact();
    }

    public static User getTokenInfo(HttpServletRequest request) {
        User user = null;
        String token = null;
        try {
            token = request.getHeader("token");
            if (token == null) {
                return null;
            }
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(App_secret).parseClaimsJws(token);
            Claims body = claimsJws.getBody();
            user = new User();
            user.setUid((String) body.get("uid"));
            user.setPassword((String) body.get("password"));
        } catch (ExpiredJwtException e) {
            log.info("JWTUtils====getTokenInfo:" + token);
        }
        return user;
    }

    public static boolean checkJwt(String token) {
        if (token == null) {
            return false;
        }
        try {
            Jwts.parser().setSigningKey(App_secret).parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean checkJwtHttpHeader(HttpServletRequest request) {
        try {
            String token = request.getHeader("token");
            if (token != null) {
                return false;
            }
            Jwts.parser().setSigningKey(App_secret).parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int i = 1000 * 60 * 30;
        System.out.println(i);
    }

}
