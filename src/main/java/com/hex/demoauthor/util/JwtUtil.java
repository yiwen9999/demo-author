package com.hex.demoauthor.util;

import com.hex.demoauthor.domain.Operator;
import com.hex.demoauthor.enums.ResultEnum;
import com.hex.demoauthor.exception.HexException;
import com.hex.demoauthor.vo.OperatorVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;

public class JwtUtil {

    /**
     * 密钥
     */
    static final String SECRET = "hexsoft.top";

    /**
     * 失效时间（7天）
     */
    static final Date expiration = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7);

    public static String generateToken(Operator operator) {
        HashMap<String, Object> claims = new HashMap<>();
        /**
         * 设置 jwt token 内容
         */
        claims.put("id", operator.getId());
        claims.put("name", operator.getName());
        claims.put("nickname", operator.getNickname());

        String jwt = Jwts.builder()
                .setClaims(claims)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

        String token = "Bearer " + jwt;
        return token; //jwt前面一般都会加Bearer
    }

    public static void validateToken(String token) {
        try {
            /**
             * 检验token是否合格，是否失效
             */
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace("Bearer ", ""))
                    .getBody();
        } catch (Exception e) {
            throw new HexException(ResultEnum.OPERATOR_INFO_ERROR);
        }
    }

    /**
     * 将 JWT 信息转换，从 JWT 信息中获取 OperatorVO 的属性值
     *
     * @param token
     * @return
     */
    public static OperatorVO parseToken(String token) {
        try {
            // parse the token.
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace("Bearer ", ""))
                    .getBody();

            OperatorVO operatorVO = new OperatorVO();
            operatorVO.setId((String) claims.get("id"));
            operatorVO.setName((String) claims.get("name"));
            operatorVO.setNickname((String) claims.get("nickname"));

            return operatorVO;
        } catch (Exception e) {
            e.printStackTrace();
            throw new HexException(ResultEnum.OPERATOR_INFO_ERROR);
        }
    }
}