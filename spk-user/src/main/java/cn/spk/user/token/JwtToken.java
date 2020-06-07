package cn.spk.user.token;

import cn.spk.base.dict.Constant;
import cn.spk.base.dict.Dict;
import cn.spk.user.service.IRedisService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Java web token 工具类
 *
 * @author qiaokun
 * @date 2018/08/10
 */
@Component
public class JwtToken {

    public static final long EXPIRE_TIME = 24 * 60 * 60 * 1000;

    public static final String TOKEN_SECRET = "f26e587c28064d0e855e72c0a6a0e618";

    @Resource
    private IRedisService redisService;

    /**
     * 校验token是否正确
     *
     * @param token 密钥
     * @return 是否正确
     */
    public static boolean verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }


    /**
     * 验证当前token是否有效
     *
     * @param token
     * @return
     */
    public boolean checkToken(String token) {
        try {
            if (token == null || "".equals(token.trim()))
                return false;
            String username = this.getUserName(token);
            if (username == null)
                return false;
            String oldToken = redisService.get(Dict.USER_TOKEN + username);
            if (oldToken != null && !oldToken.equals(token)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }

    }


    /**
     * 获取登陆用户名
     *
     * @param token
     * @return
     */
    public String getUserName(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            String username = jwt.getClaim(Dict.USERNAME).asString();
            if (null == username || "".equals(username.trim()))
                return null;
            return username;
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 生成签名,15min后过期
     *
     * @param userName 用户名
     * @return 加密的token
     */
    public String sign(String userName) {
        try {
            //过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            //私钥及加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            //设置头部信息
            Map<String, Object> header = new HashMap<>(2);
            header.put("typ", "JWT");
            header.put("alg", "HS256");
            // 附带username，userId信息，生成签名
            return JWT.create()
                    .withHeader(header)
                    .withClaim(Dict.USERNAME, userName)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

}
